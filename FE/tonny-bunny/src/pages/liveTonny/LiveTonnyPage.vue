<template>
    <div class="w-100">
        <div id="main-container" class="container w-100">
            <div id="join" v-if="!session">
                <div id="join-dialog">
                    <h1>Join a video session</h1>
                    <p>
                        <label>Session</label>
                        <input
                            class="form-control"
                            type="text"
                            id="sessionName"
                            :value="sessionName"
                            required
                        />
                    </p>
                    <button class="btn btn-lg btn-success" id="join-btn" @click="joinSession()">
                        Join!
                    </button>
                </div>
            </div>

            <div id="session" v-if="session" class="w-100">
                <div id="session-header">
                    <h1 id="session-title"></h1>
                    <medium-btn text="통역 시작하기" color="carrot" @click.prevent="startService" />
                    <medium-btn
                        text="통역 종료하기"
                        color="carrot"
                        @click.prevent="completeService"
                    />
                    <medium-btn text="방 나가기" color="carrot" @click.prevent="exitRoom" />
                    <medium-btn
                        text="음소거 토글 아이콘"
                        color="carrot"
                        @click.prevent="muteToggle"
                    />
                    <medium-btn
                        text="카메라 토글 아이콘"
                        color="carrot"
                        @click.prevent="cameraToggle"
                    />

                    <input
                        class="btn btn-sm btn-danger"
                        type="button"
                        id="buttonCloseSession"
                        @click="closeSession()"
                        value="Close session"
                    />
                    <input
                        class="btn btn-sm btn-danger"
                        type="button"
                        id="buttonLeaveSession"
                        @click="
                            removeUser();
                            leaveSession();
                        "
                        value="Leave session"
                    />
                </div>
                <div class="container position-relative">
                    <div
                        id="main-video"
                        class="w-100 position-absolute"
                        style="display: inline-block"
                    >
                        <user-video :stream-manager="mainStreamManager" />
                    </div>
                    <div
                        id="video-container"
                        class="w-25 position-absolute"
                        style="display: inline-block"
                    >
                        <div>
                            <user-video
                                v-if="mainStreamManager != publisher"
                                :stream-manager="publisher"
                                style="width: 320px"
                                @click="updateMainVideoStreamManager(publisher)"
                            />
                        </div>
                        <div v-for="sub in subscribers" :key="sub.stream.connection.connectionId">
                            <user-video
                                v-if="mainStreamManager != sub"
                                :stream-manager="sub"
                                style="width: 320px"
                                @click="updateMainVideoStreamManager(sub)"
                            />
                        </div>
                    </div>
                </div>
                <!-- <div id="video-container" class="col-md-12"></div> -->
                <div id="recording-btns">
                    <div class="btns">
                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonStartRecording"
                            @click="startRecording()"
                            value="Start recording"
                        />
                    </div>
                    <div class="btns">
                        <div class="vertical-separator-bottom"></div>

                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonStopRecording"
                            @click="stopRecording()"
                            value="Stop recording"
                        />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/UserVideo";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

// axios.defaults.headers.post["Content-Type"] = "application/json";

// const APPLICATION_SERVER_URL =
//     process.env.NODE_ENV === "production" ? "" : "http://localhost:5000/";

export default {
    name: "LiveTonnyPage",

    components: {
        UserVideo,
        MediumBtn,
    },

    data() {
        return {
            // custom
            isMute: false,
            isCamOFF: false,

            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],

            // Join form
            mySessionId: "SessionA",

            recordingId: "",

            // OV: null,
            isExisted: false,
            session: null,
            sessionName: "SessionA",
            token: null,
            numVideos: 0,
            events: "",
        };
    },

    methods: {
        // made by 승
        muteToggle() {
            const muteBtn = document.querySelector("#muteBtn");
            console.log(muteBtn);
            if (!this.isMute) {
                this.publisher.publishAudio(false);
                this.isMute = true;
                muteBtn.innerText = "UnMute";
            } else {
                this.publisher.publishAudio(true);
                this.isMute = false;
                muteBtn.innerText = "Mute";
            }
        },

        // made by 승
        cameraToggle() {
            console.log(this.OV);
            const cameraBtn = document.querySelector("#cameraBtn");
            if (!this.isCamOFF) {
                this.publisher.publishVideo(false);
                this.isCamOFF = true;
                cameraBtn.innerText = "camera ON";
            } else {
                this.publisher.publishVideo(true);
                this.isCamOFF = false;
                cameraBtn.innerText = "camera OFF";
            }
        },

        getToken(callback) {
            console.log("getToken Method");
            // this.sessionName = $("#sessionName").val(); // Video-call chosen by the user
            // console.log("몇번 호출?");
            this.httpRequest(
                "POST",
                "recording-node/api/get-token",
                {
                    sessionName: this.mySessionId,
                },
                "Request of TOKEN gone WRONG:",
                (res) => {
                    console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    console.log("res", res);
                    console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    this.token = res[0]; // Get token from response
                    this.isExisted = res["isExisted"];
                    // console.log(this.token);
                    console.warn("Request of TOKEN gone WELL (TOKEN:" + this.token + ")");
                    callback(this.isExisted); // Continue the join operation
                }
            );
        },

        joinSession() {
            console.log("joinSession Method");

            // --- 0) Change the button ---

            this.getToken(() => {
                this.OV = new OpenVidu();

                this.session = this.OV.initSession();

                this.session.on("streamCreated", (event) => {
                    const subscriber = this.session.subscribe(event.stream, undefined);
                    this.subscribers.push(subscriber);
                });

                this.session.on("streamDestroyed", (event) => {
                    const index = this.subscribers.indexOf(event.stream.streamManager, 0);
                    if (index >= 0) {
                        this.subscribers.splice(index, 1);
                    }
                });

                this.session.on("sessionDisconnected", (event) => {
                    if (event.reason !== "disconnect") {
                        this.removeUser();
                    }
                    if (event.reason !== "sessionClosedByServer") {
                        this.session = null;
                        this.numVideos = 0;
                    }
                });

                this.session
                    .connect(this.token)
                    .then(() => {
                        const publisher = this.OV.initPublisher(undefined, {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "320x240", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });
                        // publisher.properties.resolution = "320x240";
                        this.publisher = publisher;

                        this.mainStreamManager = publisher;

                        this.publisher.on("accessAllowed", () => {});

                        this.publisher.on("accessDenied", () => {});

                        this.publisher.on("accessDialogOpened", () => {});

                        this.publisher.on("accessDialogClosed", () => {});

                        this.publisher.on("streamCreated", () => {});

                        // When our HTML video has been added to DOM...
                        this.publisher.on("videoElementCreated", () => {
                            this.updateNumVideos(1);
                        });

                        // When the HTML video has been appended to DOM...
                        this.publisher.on("videoElementDestroyed", () => {
                            this.updateNumVideos(-1);
                        });

                        this.session.publish(this.publisher);

                        if (!this.isExisted) {
                            console.log(
                                "방생성@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
                            );
                        }
                    })
                    .catch((error) => {
                        console.warn(
                            "There was an error connecting to the session:",
                            error.code,
                            error.message
                        );
                        this.enableBtn();
                    });

                return false;
            });
        },
        leaveSession() {
            this.session.disconnect();
        },

        removeUser() {
            this.httpRequest(
                "POST",
                "recording-node/api/remove-user",
                {
                    sessionName: this.sessionName,
                    token: this.token,
                },
                "User couldn't be removed from session"
            );
        },
        closeSession() {
            this.httpRequest(
                "DELETE",
                "recording-node/api/close-session",
                {
                    sessionName: this.sessionName,
                },
                "Session couldn't be closed"
            );
        },

        httpRequest(method, url, body, errorMsg, callback) {
            var http = new XMLHttpRequest();

            http.open(method, `http://localhost:5000/` + url, true);
            http.setRequestHeader("Content-type", "application/json");
            http.addEventListener("readystatechange", processRequest, false);
            http.send(JSON.stringify(body));

            function processRequest() {
                if (http.readyState == 4) {
                    if (http.status == 200) {
                        try {
                            callback(JSON.parse(http.responseText));
                        } catch (e) {
                            callback(e);
                        }
                    } else {
                        console.warn(errorMsg + " (" + http.status + ")");
                        console.warn(http.responseText);
                    }
                }
            }
            console.log(url);
        },
        startRecording() {
            this.httpRequest(
                "POST",
                "recording-node/api/recording/start",
                {
                    session: this.session.sessionId,
                    outputMode: "COMPOSED",
                    hasAudio: true,
                    hasVideo: true,
                },
                "Start recording WRONG",
                (res) => {
                    console.log(res);
                    this.recordingId = res.id;
                }
            );
        },
        stopRecording() {
            if (this.recordingId == "") {
                // 동작 안함 로직
                return;
            }
            this.httpRequest(
                "POST",
                "recording-node/api/recording/stop",
                {
                    recording: this.recordingId,
                },
                "Stop recording WRONG",
                (res) => {
                    console.log(res);
                    this.recordingId = "";
                }
            );
        },

        updateMainVideoStreamManager(stream) {
            // console.log(this.mainStreamManager === this.publisher);
            if (this.mainStreamManager === stream) return;
            // this.mainStreamManager = stream;
            this.mainStreamManager = stream;
        },
    },
};
</script>
