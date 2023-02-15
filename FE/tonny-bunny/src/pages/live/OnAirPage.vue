<template>
    <div class="liveContainer">
        <div class="liveWrap">
            <div class="liveNoSession customForm" v-if="!session">
                <img src="@/assets/logo2.png" />

                <div class="w-100">
                    <div class="jTonnyInfo">
                        <div class="questProfile">
                            <div class="profile ms-3">
                                <user-profile-img
                                    class="profileImg"
                                    :profileImagePath="startResData?.client?.profileImagePath"
                                    width="70" />
                                <!-- <img
                                    class="profileImg"
                                    src="@/assets/noProfile_white.png"
                                    width="70"
                                    height="70" /> -->
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
                                <user-profile-img
                                    class="profileImg"
                                    :profileImagePath="startResData?.helper?.profileImagePath"
                                    width="70" />
                                <!-- <img
                                    class="profileImg"
                                    src="@/assets/noProfile_white.png"
                                    width="70"
                                    height="70" /> -->
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
                                            :text="getStartLangCode"
                                            sub
                                            class="me-2"></square-tag>
                                        <div class="me-2">
                                            <span class="material-symbols-outlined">
                                                compare_arrows
                                            </span>
                                        </div>
                                        <square-tag :text="getEndLangCode" sub></square-tag>
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
                                        <square-tag :text="getTonnySituCode" sub></square-tag>
                                    </td>
                                </tr>

                                <tr>
                                    <td>상황 설명</td>
                                    <td class="">{{ startResData.content }}</td>
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
                            <h2>{{ totalPrice }} CRT</h2>
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
                            :stream-manager="sub" />
                    </div>
                </div>

                <div class="chats">
                    <transition name="bounce" v-show="isChatOpen">
                        <div class="chatContent">
                            <div class="content">
                                <div v-for="(msg, index) in chatList" class="mb-1" :key="index">
                                    <h2 class="me-2">{{ msg.from }}:</h2>
                                    <span>{{ msg.data }}</span>
                                </div>
                            </div>

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
        <AlarmModal
            title="경고"
            type="danger"
            btnText2="예"
            btnColor1="carrot"
            btnColor2="main"
            btnFontColor1="white"
            btnFontColor2="white"
            @clickBtn2="closeModal">
            <template #content>
                고객님의 보유 금액이 부족합니다! <br /><br />
                라이브를 종료합니다.
            </template>
        </AlarmModal>
    </div>
</template>

<script>
/* eslint-disable */
import axios from "axios";
import UserVideo from "@/components/openvidu/UserVideo";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import UserProfileImg from "@/components/common/UserProfileImg.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import { OpenVidu } from "openvidu-browser";
import { mapGetters } from "vuex";

const APPLICATION_SERVER_URL = process.env.VUE_APP_OPENVIDU_URL;

export default {
    name: "OnAirPage",

    components: {
        UserVideo,
        MediumBtn,
        SquareTag,
        UserProfileImg,
        AlarmModal,
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
            isChatOpen: false,

            // chat
            message: "",
            chatList: [],

            // record
            isRecordOn: false,
            recordId: "",

            // 타이머
            time: "00:00:00.000",
            timer: 0,
            timer_func: null,

            // totalPrice
            totalPrice: 0,

            // history
            historySeq: "",

            // 하나하나,,,
            isExisted: false,
            isClient: false,
            isHelper: false,
        };
    },

    computed: {
        ...mapGetters({
            startResData: "getStartResData",
            userInfo: "getUserInfo",
            getHistorySeq: "getHistorySeq",
            langCode: "getLangCode",
            tonnySituCode: "getTonnySituCode",
        }),

        mySessionName() {
            return this.startResData.sessionName;
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

        getStartLangCode() {
            let code;
            this.langCode.forEach((e) => {
                if (e.value == this.startResData.startLangCode) code = e.name;
            });
            return code;
        },
        getEndLangCode() {
            let code;
            this.langCode.forEach((e) => {
                if (e.value == this.startResData.endLangCode) code = e.name;
            });
            return code;
        },
        getTonnySituCode() {
            let code;
            this.tonnySituCode.forEach((e) => {
                if (e.value == this.startResData.tonnySituCode) code = e.name;
            });
            return code;
        },
    },

    methods: {
        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
            this.leaveSession();
        },
        // 계산하는 동작
        calculateTotalPrice() {
            const updatedPrice = (Math.floor(this.timer / 300) + 1) * this.startResData.unitPrice; // 실제
            // const updatedPrice = (Math.floor(this.timer / 5) + 1) * 600;                         // 테스트용
            if (this.$store.state.account.userInfo.point < updatedPrice) {
                // TODO : 모달 열기, 타이머 종료
                this.session
                    .signal({ data: "pointIssue", type: "live" })
                    .then(() => {
                        console.log("Message successfully sent");
                    })
                    .catch((error) => {
                        console.error(error);
                    });
            } else {
                this.session
                    .signal({ data: updatedPrice, type: "updatePrice" })
                    .then(() => {
                        console.log("Message successfully sent");
                    })
                    .catch((error) => {
                        console.error(error);
                    });
            }
        },

        joinSession() {
            this.OV = new OpenVidu();

            this.session = this.OV.initSession();

            this.session.on("streamCreated", ({ stream }) => {
                const subscriber = this.session.subscribe(stream);
                this.subscribers.push(subscriber);
            });

            this.session.on("streamDestroyed", ({ stream }) => {
                const index = this.subscribers.indexOf(stream.streamManager, 0);
                if (index >= 0) this.subscribers.splice(index, 1);
            });

            this.session.on("signal:live", (event) => {
                // 서비스 시작 - 타이머 동작
                if (event.data == "Start") {
                    this.timer_func = setInterval(() => {
                        this.timer = this.timer + 1;
                        if (this.$store.state.account.userInfo.seq == this.startResData.client.seq)
                            this.calculateTotalPrice();
                    }, 1000);
                }

                // 서비스 종료 - 타이머 종료
                if (event.data == "End") {
                    clearInterval(this.timer_func);
                }

                // 잔액 부족
                if (event.data == "pointIssue") {
                    this.endLive(); // 양쪽 타이머 종료용
                    this.$store.commit("TOGGLE_ALARM_MODAL"); // 양쪽 모달 열기
                }
            });

            // 금액 업데이트
            this.session.on("signal:updatePrice", (event) => {
                this.totalPrice = JSON.parse(event.data);
                console.log(event.data);
                console.log("받은금액 ", this.totalPrice);
            });

            // 채팅 로직
            this.session.on("signal:chat", (event) => {
                let data = JSON.parse(event.data);

                let chat = {
                    data: data.msg,
                    from: data.from,
                };

                this.chatList.push(chat);
            });

            this.session.on("sessionDisconnected", (event) => {
                if (this.isRecordOn) {
                    this.stopRecording();
                }

                // ------------------------------------------------------------------------------------------
                // 내 코드

                /**
                 * 로그인한 사용자의 시퀀스가 고객인지 헬퍼인지 비교
                 * 고객이라면 세션 나갈 시 거래리뷰페이지 + 고객 으로 이동,
                 * 헬퍼라면 세션 나갈 시 거래완료페이지 + 헬퍼 로 이동
                 */

                const data = {
                    totalTime: this.timer,
                    totalPrice: this.totalPrice,
                };

                this.$store.commit("SET_COMPLETE_DATA", data);

                if (this.isClient) {
                    this.$router.push({ name: "LiveClosePage", params: { user: "client" } });
                }
                if (this.isHelper) {
                    this.$router.push({ name: "LiveClosePage", params: { user: "helper" } });
                }

                // ----------------------------------------------------------------------------------

                if (event.reason !== "disconnect") {
                    this.removeUser();
                }
                if (event.reason !== "sessionClosedByServer") {
                    this.session = null;
                    this.numVideos = 0;
                }
            });

            this.session.on("exception", ({ exception }) => {
                console.warn(exception);
            });

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

                        if (!this.isExisted) {
                            console.log("방생성, 내가 레코딩 시작");
                            this.startRecording();
                        }
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

        startLive() {
            console.log("Start");

            this.session
                .signal({ data: "Start", type: "live" })
                .then(() => {
                    console.log("Message successfully sent");
                })
                .catch((error) => {
                    console.error(error);
                });
        },

        endLive() {
            console.log("End");
            this.session
                .signal({ data: "End", type: "live" })
                .then(() => {
                    console.log("Message successfully sent");
                })
                .catch((error) => {
                    console.error(error);
                });
        },

        async startRecording() {
            this.isRecordOn = true;

            let res = await axios.post(
                APPLICATION_SERVER_URL + "recording-node/api/recording/start",
                {
                    session: this.session.sessionId,
                    outputMode: "COMPOSED",
                    hasAudio: true,
                    hasVideo: true,
                }
            );

            console.log(res);
            this.recordId = res.data.id;
        },

        async stopRecording() {
            console.log("내가 레코딩 종료 @@");
            if (this.recordId == "") return;

            let res = await axios.post(
                APPLICATION_SERVER_URL + "recording-node/api/recording/stop",
                { recording: this.recordId }
            );

            console.log("레코딩 종료 끝");

            console.log(res);

            // 히스토리 저장 요청
            const payload = {
                historySeq: this.getHistorySeq,
                recordVideoPath: this.recordId,
                totalTime: this.timeToHHMMSS,
            };

            console.log("히스토리 저장 시작");
            console.log(payload);

            await this.$store.dispatch("completeLive", payload);
            console.log("히스토리 저장 끝");
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
                    data: JSON.stringify({ msg: this.message, from: this.myUserName }), // Any string (optional)
                    to: [], // Array of Connection objects (optional. Broadcast to everyone if empty)
                    type: "chat", // The type of message (optional)
                })
                .then(() => {
                    this.message = "";
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

            console.log("res!!!!!!!!!!!!!!!!!:", res);
            this.isExisted = res.data["isExisted"];
            console.log(this.isExisted);
            return res.data[0];
        },
    },

    created() {
        window.scrollTo(0, 0);
        this.leaveSession();
        console.log("resDate", this.startResData);
        console.log(this.$store.state.account.userInfo);
        this.$store.state.account.userInfo.seq == this.startResData.client.seq
            ? (this.isClient = true)
            : (this.isClient = false);
        this.$store.state.account.userInfo.seq == this.startResData.helper.seq
            ? (this.isHelper = true)
            : (this.isHelper = false);
    },

    mounted() {
        window.scrollTo(0, 0);
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/live.scss";
</style>
