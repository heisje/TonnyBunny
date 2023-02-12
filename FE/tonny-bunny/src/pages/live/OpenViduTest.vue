<template>
    <div class="liveContainer">
        <div class="liveWrap">
            <div class="liveNoSession customForm" v-if="!session">
                <img src="@/assets/logo2.png" />

                <div class="w-100">
                    <div class="jTonnyInfo">
                        <div class="questProfile">
                            <div class="profile ms-3">
                                <img
                                    class="profileImg"
                                    src="@/assets/noProfile_white.png"
                                    width="70"
                                    height="70" />
                                <div class="userType ms-2">
                                    <span class="label">고객</span>
                                    <h4>{{ startResData.client.nickName }}</h4>
                                </div>
                            </div>

                            <div>
                                <span class="material-symbols-outlined arrow"> sync_alt </span>
                            </div>

                            <div class="profile me-3">
                                <div class="userType me-2">
                                    <span class="label text-end">헬퍼</span>
                                    <h4>{{ startResData.helper.nickName }}</h4>
                                </div>
                                <img
                                    class="profileImg"
                                    src="@/assets/noProfile_white.png"
                                    width="70"
                                    height="70" />
                            </div>
                        </div>

                        <v-table class="mt-4">
                            <tbody>
                                <tr>
                                    <td>Session Code</td>
                                    <td>{{ mySessionName }}</td>
                                </tr>

                                <tr>
                                    <td>언어</td>
                                    <td class="d-flex flex-row align-items-center">
                                        <square-tag
                                            :text="startResData.startLangCode"
                                            sub
                                            class="me-2"></square-tag>
                                        <div class="me-2">
                                            <span class="material-symbols-outlined">
                                                compare_arrows
                                            </span>
                                        </div>
                                        <square-tag
                                            :text="startResData.endLangCode"
                                            sub></square-tag>
                                    </td>
                                </tr>

                                <tr>
                                    <td>지불 캐럿</td>
                                    <td>
                                        {{ startResData.unitPrice }}
                                        <span class="label">CRT</span>
                                    </td>
                                </tr>

                                <tr>
                                    <td>상황 카테고리</td>
                                    <td>
                                        <square-tag
                                            :text="startResData.tonnySituCode"
                                            sub></square-tag>
                                    </td>
                                </tr>

                                <tr>
                                    <td>상황 설명</td>
                                    <td class="pt-3">{{ startResData.content }}</td>
                                </tr>
                            </tbody>
                        </v-table>
                    </div>

                    <div @click="joinSession()">
                        <medium-btn text="시작하기" class="w-100" color="carrot"></medium-btn>
                    </div>
                </div>
            </div>

            <div class="liveYesSession w-100" v-else>
                <div class="settings">
                    <div class="settingContent container row" v-show="isSettingOpen">
                        <div class="metas col-12 col-md-6">
                            <h2>{{ timeToHHMMSS }}</h2>
                            <h2>{{ (Math.floor(timer / 5) + 1) * startResData.unitPrice }} CRT</h2>
                        </div>

                        <div class="btns col-12 col-md-6">
                            <div
                                class="d-flex flex-column justify-content-center align-items-center">
                                <div class="btn" @click.prevent="startLive">
                                    <span class="material-symbols-outlined"> play_arrow </span>
                                </div>
                                <h2>시작</h2>
                            </div>

                            <div
                                class="d-flex flex-column justify-content-center align-items-center">
                                <div class="btn" @click.prevent="endLive">
                                    <span class="material-symbols-outlined"> stop </span>
                                </div>
                                <h2>정지</h2>
                            </div>

                            <div
                                class="d-flex flex-column justify-content-center align-items-center">
                                <div class="btn" @click.prevent="toggleSpeaker">
                                    <span class="material-symbols-outlined"> mic_off </span>
                                </div>
                                <h2>마이크 끄기</h2>
                            </div>

                            <div
                                class="d-flex flex-column justify-content-center align-items-center">
                                <div class="btn" @click.prevent="toggleCamera">
                                    <span class="material-symbols-outlined"> videocam_off </span>
                                </div>
                                <h2>비디오 끄기</h2>
                            </div>

                            <div
                                class="d-flex flex-column justify-content-center align-items-center">
                                <div class="btn closeBtn" @click.prevent="leaveSession">
                                    <span class="material-symbols-outlined"> close </span>
                                </div>
                                <h2>떠나기</h2>
                            </div>
                        </div>
                    </div>

                    <div class="settingBtn m-0 p-0" @click="toggleIsSettingOpen">
                        <span class="material-symbols-outlined"> expand_more </span>
                    </div>
                </div>

                <div class="displays row w-100">
                    <div id="main-video" class="col-md-6 col-12">
                        <user-video :stream-manager="mainStreamManager" />
                    </div>
                    <div id="video-container" class="col-md-6 col-12">
                        <user-video
                            v-for="sub in subscribers"
                            :key="sub.stream.connection.connectionId"
                            :stream-manager="sub"
                            @click="updateMainVideoStreamManager(sub)" />
                    </div>
                </div>

                <div class="chats">
                    <transition name="bounce" v-show="isChatOpen">
                        <div class="chatContent">
                            <div class="content"></div>

                            <div class="sendBtns">
                                <input type="text" v-model="message" @keydown.enter="sendMessage" />
                                <medium-btn
                                    class="sendBtn w-90"
                                    text="보내기"
                                    color="outline"
                                    font="active"
                                    @click="sendMessage"></medium-btn>
                            </div>
                        </div>
                    </transition>

                    <div class="chatBtn" @click="toggleIsChatOpen">
                        <span class="material-symbols-outlined"> chat </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/UserVideo";
import { mapGetters } from "vuex";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";

const APPLICATION_SERVER_URL =
    process.env.NODE_ENV === "production" ? "" : "http://localhost:5000/";

export default {
    name: "App",

    components: {
        UserVideo,
        MediumBtn,
        SquareTag,
    },

    data() {
        return {
            // OpenVidu objects
            OV: undefined,
            session: undefined,
            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],

            // Settings
            isSpeakOn: false,
            isCamOn: false,
            isSettingOpen: true,
            isChatOpen: true,
            message: "",

            // 타이머
            time: "00:00:00.000",
            timer: 0,
            timer_func: null,
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

        timeToHHMMSS() {
            var myNum = this.timer;
            var hours = Math.floor(myNum / 3600);
            var minutes = Math.floor((myNum - hours * 3600) / 60);
            var seconds = myNum - hours * 3600 - minutes * 60;

            if (hours < 10) {
                hours = "0" + hours;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }
            return hours + ":" + minutes + ":" + seconds;
        },
    },

    methods: {
        joinSession() {
            this.OV = new OpenVidu();

            this.session = this.OV.initSession();

            this.session.on("streamCreated", ({ stream }) => {
                const subscriber = this.session.subscribe(stream);
                this.subscribers.push(subscriber);
                console.log("누군데??", this.subscribers);
            });

            this.session.on("streamDestroyed", ({ stream }) => {
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) {
                    this.subscribers.splice(index, 1);
                }
            });

            this.session.on(`signal:${this.myUserName}`, (event) => {
                console.log("이건 나의 채팅 !!");
                console.log(event.data); // Message
                console.log(event.from); // Connection object of the sender
                console.log(event.type); // The type of message ("my-chat")
            });

            this.session.on("signal", (event) => {
                console.log("이건 다른 사람의 채팅 !!");
                console.log(event.data); // Message
                console.log(event.from); // Connection object of the sender
                console.log(event.type); // The type of message
            });

            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });

            this.getToken(this.mySessionId).then((token) => {
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

        toggleSpeaker() {
            if (!this.isSpeakOn) {
                this.publisher.publishAudio(false);
                this.isSpeakOn = true;
            } else {
                this.publisher.publishAudio(true);
                this.isSpeakOn = false;
            }
        },

        toggleCamera() {
            console.log(this.OV);
            if (!this.isCamOn) {
                this.publisher.publishVideo(false);
                this.isCamOn = true;
            } else {
                this.publisher.publishVideo(true);
                this.isCamOn = false;
            }
        },

        toggleIsChatOpen() {
            this.isChatOpen = !this.isChatOpen;
        },

        toggleIsSettingOpen() {
            this.isSettingOpen = !this.isSettingOpen;
        },

        sendMessage() {
            this.session
                .signal({
                    data: this.message, // Any string (optional)
                    to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
                    type: this.myUserName, // The type of message (optional)
                })
                .then(() => {
                    console.log("Message successfully sent");
                })
                .catch((error) => {
                    console.error(error);
                });
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
        window.scrollTo(0, 0);
        this.leaveSession();
        console.log("resDate", this.startResData);
    },

    mounted() {
        window.scrollTo(0, 0);
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/live.scss";
</style>
