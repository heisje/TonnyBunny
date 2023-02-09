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
                            v-model="sessionName"
                            required
                        />
                    </p>
                    <button class="btn btn-lg btn-success" id="join-btn" @click="joinSession()">
                        Join!
                    </button>
                </div>
            </div>

            <div id="session" v-if="session" class="w-100">
                <!-- 고객용 디브 -->
                <div>
                    고객용 디브
                    <p>
                        ON-AIR : {{ timeToHHMMSS }} 미터기 :
                        {{ (Math.floor(timer / 5) + 1) * unitPrice }} 캐럿
                    </p>
                    <h1 id="session-title"></h1>
                    <medium-btn text="통역 시작하기" color="carrot" @click.prevent="startLive" />
                    <medium-btn text="통역 종료하기" color="carrot" @click.prevent="endLive" />
                    <medium-btn
                        text="방 나가기(leave)"
                        color="carrot"
                        @click.prevent="leaveSession"
                    />
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
                </div>
                <!-- 고객용 디브 -->
                <div>
                    헬퍼용 디브
                    <p>
                        ON-AIR : {{ timeToHHMMSS }} 미터기 :
                        {{ (Math.floor(timer / 5) + 1) * unitPrice }} 캐럿
                    </p>
                    <medium-btn
                        text="방 나가기(leave)"
                        color="carrot"
                        @click.prevent="leaveSession"
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
import { mapGetters } from "vuex";
// import http from "@/common/axios";

// axios.defaults.headers.post["Content-Type"] = "application/json";

// const APPLICATION_SERVER_URL =
//     process.env.NODE_ENV === "production" ? "" : "http://localhost:5000/";

export default {
    name: "LivePage",

    components: {
        UserVideo,
        MediumBtn,
    },

    data() {
        return {
            // custom
            isMute: false,
            isCamOFF: false,

            // 녹화
            isRecording: false,
            recordingId: "",

            // OV: null,
            isExisted: false,
            session: null,
            sessionName: "SessionA",
            token: null,
            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],
            events: "",

            // 타이머
            time: "00:00:00.000",
            timer: 0,
            timer_func: null,

            // 공고 정보
            startData: "",
            unitPrice: 5, // 단가

            // 완료 정보
            historySeq: "",
        };
    },

    computed: {
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
        ...mapGetters({ getStartResData: "getStartResData" }),
    },

    methods: {
        // --------------------------------------- 커스텀 로직 --------------------------------------------

        startLive() {
            // this.startRecording(); // 삭제
            console.log("Start");
            this.session
                .signal({
                    data: "Start",
                })
                .then(() => {
                    console.log("Message successfully sent");
                })
                .catch((error) => {
                    console.error(error);
                });
        },

        endLive() {
            // this.stopRecording();    // 삭제
            console.log("End");
            this.session
                .signal({
                    data: "End",
                })
                .then(() => {
                    console.log("Message successfully sent");
                })
                .catch((error) => {
                    console.error(error);
                });
        },

        exitLive() {},

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

        // --------------------------------------------------- 공식문서 로직 ------------------------------------------------------

        getToken(callback) {
            console.log("getToken Method");
            this.httpRequest(
                "POST",
                "recording-node/api/get-token",
                {
                    sessionName: this.sessionName,
                },
                "Request of TOKEN gone WRONG:",
                (res) => {
                    this.token = res[0]; // Get token from response
                    this.isExisted = res["isExisted"];
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
                    if (this.isRecording) {
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
                        totalPrice: (Math.floor(this.timer / 5) + 1) * this.unitPrice,
                    };

                    this.$store.commit("SET_COMPLETE_DATA", data);

                    if (this.getStartResData.client.seq == this.$store.state.account.userInfo.seq) {
                        this.$router.push({ name: "LiveClosePage", params: { user: "client" } });
                    }
                    if (this.getStartResData.helper.seq == this.$store.state.account.userInfo.seq) {
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

                this.session.on("signal", (event) => {
                    console.log(event.data); // Message

                    if (event.data == "Start") {
                        this.timer_func = setInterval(() => {
                            this.timer = this.timer + 1;
                        }, 1000);
                    }

                    if (event.data == "End") {
                        clearInterval(this.timer_func);
                    }
                    // console.log(event.from); // Connection object of the sender
                    // console.log(event.type); // The type of message
                });

                this.session
                    .connect(this.token)
                    .then(() => {
                        const publisher = this.OV.initPublisher(undefined, {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "640x480", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });
                        // publisher.properties.resolution = "320x240";
                        this.publisher = publisher;

                        this.mainStreamManager = publisher;

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
                            console.log("방생성, 내가 레코딩 시작");
                            this.startRecording();
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
            this.stopRecording();
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

            http.open(method, process.env.VUE_APP_OPENVIDU_URL + url, true);
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
            this.isRecording = true;
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
        async stopRecording() {
            // 시작 한 사람만 정지할 수 있음
            if (this.recordingId == "") {
                return;
            }

            // 녹화 중지 요청
            await this.httpRequest(
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

            // 히스토리 저장 요청
            const payload = {
                historySeq: this.historySeq,
                recordVideoPath: this.recordingId,
                totalTime: this.timeToHHMMSS,
            };
            await this.$store.dispatch("completeLive", payload);
        },

        updateMainVideoStreamManager(stream) {
            // console.log(this.mainStreamManager === this.publisher);
            if (this.mainStreamManager === stream) return;
            // this.mainStreamManager = stream;
            this.mainStreamManager = stream;
        },
    },

    async created() {
        this.historySeq = this.getStartResData.seq;
        this.sessionName = this.getStartResData.uuid;
        this.unitPrice = this.getStartResData.unitPrice;
        this.joinSession();
    },
};
</script>
