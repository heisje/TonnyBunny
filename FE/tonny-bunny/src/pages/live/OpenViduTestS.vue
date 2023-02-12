<template>
    <div id="main-container" class="container">
        <div id="join" v-if="!session">
            <div id="img-div">
                <img src="resources/images/openvidu_grey_bg_transp_cropped.png" />
            </div>
            <div id="join-dialog" class="jumbotron vertical-center">
                <h1>Join a video session</h1>
                <div class="form-group">
                    <p>
                        <label>Participant</label>
                        <input v-model="myUserName" class="form-control" type="text" required />
                    </p>
                    <p>
                        <label>Session</label>
                        <input v-model="mySessionName" class="form-control" type="text" required />
                    </p>
                    <p class="text-center">
                        <button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
                    </p>
                </div>
            </div>
        </div>

        <div id="session" v-if="session">
            <medium-btn text="세션 떠나기" @click="leaveSession"></medium-btn>
            <div id="session-header">
                <h1 id="session-title">{{ mySessionName }}</h1>
                <input
                    class="btn btn-large btn-danger"
                    type="button"
                    id="buttonLeaveSession"
                    @click="leaveSession"
                    value="Leave session" />
            </div>
            <div class="row">
                <div id="main-video" class="col-md-6">
                    <user-video :stream-manager="mainStreamManager" />
                </div>
                <div id="video-container" class="col-md-6">
                    <!-- <user-video
                        :stream-manager="publisher"
                        @click="updateMainVideoStreamManager(publisher)" /> -->
                    <!-- <user-video
                        v-for="sub in subscribers"
                        :key="sub.stream.connection.connectionId"
                        :stream-manager="sub"
                        @click="updateMainVideoStreamManager(sub)" /> -->
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/UserVideo";
import { mapGetters } from "vuex";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const APPLICATION_SERVER_URL =
    process.env.NODE_ENV === "production" ? "" : "http://localhost:5000/";

export default {
    name: "OpenViduTestS",

    components: {
        UserVideo,
        MediumBtn,
    },

    data() {
        return {
            // OpenVidu objects
            OV: undefined,
            session: undefined,
            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],
        };
    },

    computed: {
        ...mapGetters({
            startResData: "getStartResData",
            userInfo: "getUserInfo",
        }),

        mySessionName() {
            return this.startResData.uuid;
        },

        myUserName() {
            return this.userInfo.nickName;
        },
    },

    methods: {
        joinSession() {
            this.OV = new OpenVidu();

            this.session = this.OV.initSession();

            // this.session.on("streamCreated", ({ stream }) => {
            //     const subscriber = this.session.subscribe(stream);
            //     this.subscribers.push(subscriber);
            // });

            // this.session.on("streamDestroyed", ({ stream }) => {
            //     const index = this.subscribers.indexOf(stream.streamManager, 0);
            //     if (index >= 0) {
            //         this.subscribers.splice(index, 1);
            //     }
            // });

            // this.session.on("exception", ({ exception }) => {
            //     console.warn(exception);
            // });

            this.getToken(this.mySessionName).then((token) => {
                this.session
                    .connect(token, { clientData: this.myUserName })
                    .then(() => {
                        let publisher = this.OV.initPublisher(undefined, {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "640x480", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });

                        this.mainStreamManager = publisher;
                        this.publisher = publisher;

                        this.session.publish(this.publisher);
                    })
                    .catch((error) => {
                        console.log(
                            "There was an error connecting to the session:",
                            error.code,
                            error.message
                        );
                    });
            });

            window.addEventListener("beforeunload", this.leaveSession);
        },

        leaveSession() {
            if (this.session) this.session.disconnect();

            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;

            window.removeEventListener("beforeunload", this.leaveSession);
        },

        updateMainVideoStreamManager(stream) {
            if (this.mainStreamManager === stream) return;
            this.mainStreamManager = stream;
        },

        async getToken(sessionName) {
            const res = await axios.post(
                APPLICATION_SERVER_URL + "recording-node/api/get-token",
                { sessionName: sessionName },
                { headers: { "Content-Type": "application/json" } }
            );
            return res.data[0];
        },
    },

    created() {
        console.log("mySessionName:", this.mySessionName);
    },
};
</script>
