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
                        <input v-model="mySessionId" class="form-control" type="text" required />
                    </p>
                    <p class="text-center">
                        <button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
                    </p>
                </div>
            </div>
        </div>

        <div id="session" v-if="session">
            <p class="text-center">
                <button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
            </p>
            <div id="session-header">
                <h1 id="session-title">{{ mySessionId }}</h1>
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
                    <user-video
                        :stream-manager="publisher"
                        @click="updateMainVideoStreamManager(publisher)" />
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
import { mapGetters } from "vuex";

// import MediumBtn from "@/components/common/button/MediumBtn.vue";
import UserVideo from "@/components/openvidu/UserVideo.vue";

export default {
    name: "OpenViduTest",

    components: {
        // MediumBtn,
        UserVideo,
    },

    MediumBtndata() {
        return {
            OV: undefined, // session object
            session: undefined,
            mainStreamManager: undefined, // main cam
            publisher: undefined, // my stream
            subscribers: [], // other stream

            token: null,
            isExisted: false,
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
        // async getToken() {
        //     const res = await axios.post(
        //         process.env.VUE_APP_OPENVIDU_URL + "recording-node/api/get-token",
        //         { sessionName: this.mySessionName },
        //         { headers: { "Content-Type": "application/json" } }
        //     );
        //     return res;
        // },

        async getToken(sessionName) {
            const res = await axios.post(
                process.env.VUE_APP_OPENVIDU_URL + "recording-node/api/get-token",
                { sessionName: sessionName },
                { headers: { "Content-Type": "application/json" } }
            );
            return res.data[0];
        },

        joinSession() {
            // session 에 누군가가 들어올 때
            // this.session.on("streamCreated", ({ stream }) => {
            //     let subscriber = this.session.subscribe(stream);
            //     console.log("누구긔: ", subscriber);
            //     console.log("누구냐:", this.subscribers);
            //     this.subscribers.push(subscriber);
            // });

            // // session 에 누군가가 나갈 때
            // this.session.on("streamDestroyed", (e) => {
            //     console.log("누군데: ", e.streamManager);
            //     const index = this.subscribers.indexOf(e.streamManager, 0);
            //     if (index >= 0) {
            //         this.subscribers.splice(index, 1);
            //     }
            // });

            // exception
            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });

            this.getToken(this.mySessionName).then((token) => {
                // First param is the token. Second param can be retrieved by every user on event
                // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
                this.session
                    .connect(token, { clientData: this.myUserName })
                    .then(() => {
                        // --- 5) Get your own camera stream with the desired properties ---

                        // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
                        // element: we will manage it on our own) and with the desired properties
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

                        // Set the main video in the page to display our webcam and store our Publisher
                        this.mainStreamManager = publisher;
                        this.publisher = publisher;

                        // --- 6) Publish your stream ---

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
        },

        updateMainVideoStreamManager(stream) {
            if (this.mainStreamManager === stream) return;
            this.mainStreamManager = stream;
        },

        leaveSession() {
            console.log("세션 끝내기 !!!!!!!!!!!!");
            if (this.session) this.session.disconnect();

            this.session = undefined;
            this.mainStreamManager = undefined;
            this.publisher = undefined;
            this.subscribers = [];
            this.OV = undefined;

            window.removeEventListener("beforeunload", this.leaveSession);
        },
    },

    beforeUnmount() {
        this.leaveSession();
    },

    async mounted() {
        // token 세팅
        // let res = await this.getToken();
        // this.token = res.data[0];
        // this.isExisted = res.data[1];
        // console.log("token:", this.token);
        // await this.joinSession();
    },

    created() {
        // OV 객체 받기
        this.OV = new OpenVidu();

        // session 세팅
        this.session = this.OV.initSession();
        console.log(this.session);
    },
};
</script>

<style></style>
