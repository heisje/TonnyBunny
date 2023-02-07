<template>
    <div>
        <div id="main-container" class="container">
            <div id="join" class="vertical-center" v-if="!session">
                <div id="img-div">
                    <img src="images/openvidu_grey_bg_transp_cropped.png" />
                </div>
                <div id="join-dialog" class="jumbotron">
                    <h1>Join a video session</h1>
                    <form class="form-group" onsubmit="return false">
                        <p>
                            <label>Session</label>
                            <input
                                class="form-control"
                                type="text"
                                id="sessionName"
                                value="SessionA"
                                required
                            />
                        </p>
                        <p class="text-center">
                            <button
                                class="btn btn-lg btn-success"
                                id="join-btn"
                                @click="joinSession()"
                            >
                                Join!
                            </button>
                        </p>
                    </form>
                    <hr />
                </div>
            </div>

            <!-- <div id="session" style="display: none"> -->
            <div id="session" v-if="session">
                <div id="session-header">
                    <h1 id="session-title"></h1>
                    <input
                        class="btn btn-sm btn-danger"
                        type="button"
                        id="buttonCloseSession"
                        onmouseup="closeSession()"
                        value="Close session"
                    />
                    <input
                        class="btn btn-sm btn-danger"
                        type="button"
                        id="buttonLeaveSession"
                        onmouseup="removeUser(); leaveSession()"
                        value="Leave session"
                    />
                    <div class="vertical-separator-top"></div>
                    <input
                        class="form-control"
                        id="forceValue"
                        type="text"
                        onkeyup="checkBtnsForce()"
                    />
                    <input
                        class="btn btn-sm"
                        type="button"
                        id="buttonForceUnpublish"
                        onmouseup="forceUnpublish()"
                        value="Force unpublish"
                        disabled
                    />
                    <input
                        class="btn btn-sm"
                        type="button"
                        id="buttonForceDisconnect"
                        onmouseup="forceDisconnect()"
                        value="Force disconnect"
                        disabled
                    />
                    <div class="vertical-separator-top"></div>
                    <input
                        class="btn btn-sm"
                        type="button"
                        id="buttonFetchInfo"
                        onmouseup="fetchInfo()"
                        value="Fetch info"
                    />
                    <input
                        class="btn btn-sm"
                        type="button"
                        id="buttonFetchAll"
                        onmouseup="fetchAll()"
                        value="Fetch all"
                    />
                </div>
                <div id="video-container" class="col-md-12"></div>
                <div id="recording-btns">
                    <div class="btns">
                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonStartRecording"
                            @click="startRecording()"
                            value="Start recording"
                        />
                        <form>
                            <label class="radio-inline">
                                <input
                                    type="radio"
                                    name="outputMode"
                                    value="COMPOSED"
                                    id="radio-composed"
                                    checked
                                />COMPOSED
                            </label>
                            <label class="radio-inline">
                                <input
                                    type="radio"
                                    name="outputMode"
                                    value="INDIVIDUAL"
                                    id="radio-individual"
                                />INDIVIDUAL
                            </label>
                        </form>
                        <form>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="has-audio-checkbox" checked />Has audio
                            </label>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="has-video-checkbox" checked />Has video
                            </label>
                        </form>
                    </div>
                    <div class="btns">
                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonListRecording"
                            onmouseup="listRecordings()"
                            value="List recordings"
                        />
                        <div class="vertical-separator-bottom"></div>
                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonGetRecording"
                            onmouseup="getRecording()"
                            value="Get recording"
                            disabled
                        />
                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonStopRecording"
                            @click="stopRecording()"
                            value="Stop recording"
                        />
                        <input
                            class="btn btn-md"
                            type="button"
                            id="buttonDeleteRecording"
                            onmouseup="deleteRecording()"
                            value="Delete recording"
                            disabled
                        />
                        <input
                            class="form-control"
                            id="forceRecordingId"
                            type="text"
                            onkeyup="checkBtnsRecordings()"
                        />
                    </div>
                    <div class="textarea-container" id="textarea-http-container">
                        <button
                            type="button"
                            class="btn btn-outline-secondary"
                            id="clear-http-btn"
                            onclick="clearHttpTextarea()"
                        >
                            Clear
                        </button>
                        <span>HTTP responses</span>
                        <textarea
                            id="textarea-http"
                            readonly="true"
                            class="form-control"
                            name="textarea-http"
                        ></textarea>
                    </div>
                    <div class="textarea-container" id="textarea-events-container">
                        <button
                            type="button"
                            class="btn btn-outline-secondary"
                            id="clear-events-btn"
                            onclick="clearEventsTextarea()"
                        >
                            Clear
                        </button>
                        <span>OpenVidu events</span>
                        <textarea
                            id="textarea-events"
                            readonly="true"
                            class="form-control"
                            name="textarea-events"
                        ></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- <div id="main-container" class="container">
        <div id="join" v-if="!session">
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
            <div id="session-header">
                <h1 id="session-title">{{ mySessionId }}</h1>
                <input
                    class="btn btn-large btn-danger"
                    type="button"
                    id="buttonLeaveSession"
                    @click="leaveSession"
                    value="Leave session"
                />
            </div>

            <div id="video-container" class="col-md-6">
                <user-video
                    :stream-manager="publisher"
                    @click="updateMainVideoStreamManager(publisher)"
                />
                <user-video
                    v-for="sub in subscribers"
                    :key="sub.stream.connection.connectionId"
                    :stream-manager="sub"
                    @click="updateMainVideoStreamManager(sub)"
                />
            </div>
            <div>
                <button id="muteBtn" @click="muteToggle">Mute</button>
                <button id="cameraBtn" @click="cameraToggle">camera OFF</button>
            </div>

            <div class="btns">
                <div class="vertical-separator-bottom"></div>
                <input
                    class="btn btn-md"
                    type="button"
                    id="buttonStartRecording"
                    @click="startRecording()"
                    value="Start recording"
                />
                <input
                    class="btn btn-md"
                    type="button"
                    id="buttonStopRecording"
                    @click="stopRecording()"
                    value="Stop recording"
                />
            </div>
        </div>
    </div> -->
</template>

<script>
// import axios from "axios";
import { OpenVidu } from "openvidu-browser";
// import UserVideo from "@/components/openvidu/UserVideo";

// axios.defaults.headers.post["Content-Type"] = "application/json";

// const APPLICATION_SERVER_URL =
//     process.env.NODE_ENV === "production" ? "" : "http://localhost:5000/";

export default {
    name: "OpenviduTest",

    components: {
        // UserVideo,
    },

    data() {
        return {
            // custom
            isMute: false,
            isCamOFF: false,

            // OpenVidu objects
            // OV: undefined,
            // session: undefined,
            mainStreamManager: undefined,
            publisher: undefined,
            subscribers: [],

            // Join form
            mySessionId: "SessionA",
            myUserName: "Participant" + Math.floor(Math.random() * 100),

            // OV: null,
            session: null,
            sessionName: null,
            token: null,
            numVideos: 0,
            events: "",
        };
    },

    methods: {
        getToken(callback) {
            console.log("getToken Method");
            // this.sessionName = $("#sessionName").val(); // Video-call chosen by the user
            console.log("몇번 호출?");
            this.httpRequest(
                "POST",
                "recording-node/api/get-token",
                {
                    sessionName: this.mySessionId,
                },
                "Request of TOKEN gone WRONG:",
                (res) => {
                    console.log("res", res);
                    this.token = res[0]; // Get token from response
                    // console.log(this.token);
                    console.warn("Request of TOKEN gone WELL (TOKEN:" + this.token + ")");
                    callback(this.token); // Continue the join operation
                }
            );
        },

        joinSession() {
            console.log("joinSession Method");

            // --- 0) Change the button ---

            document.getElementById("join-btn").disabled = true;
            document.getElementById("join-btn").innerHTML = "Joining...";
            console.log(this.token);

            this.getToken((token) => {
                console.log("callback callback callback");
                // --- 1) Get an OpenVidu object ---
                console.log("@@@");
                console.log("12312");
                console.log("123123", token);
                console.log("@@@");
                console.log(this.OV);
                this.OV = new OpenVidu();

                // --- 2) Init a session ---
                console.log("??");
                console.log("??", token);
                this.session = this.OV.initSession();

                // --- 3) Specify the actions when events take place in the session ---

                // this.session.on("connectionCreated", (event) => {
                //     pushEvent(event);
                // });

                // session.on("connectionDestroyed", (event) => {
                //     pushEvent(event);
                // });

                // On every new Stream received...
                this.session.on("streamCreated", (event) => {
                    // pushEvent(event);

                    // Subscribe to the Stream to receive it
                    // HTML video will be appended to element with 'video-container' id
                    var subscriber = this.session.subscribe(event.stream, "video-container");

                    // When the HTML video has been appended to DOM...
                    subscriber.on("videoElementCreated", () => {
                        // pushEvent(event);
                        // Add a new HTML element for the user's name and nickname over its video
                        this.updateNumVideos(1);
                    });

                    // When the HTML video has been appended to DOM...
                    subscriber.on("videoElementDestroyed", () => {
                        // pushEvent(event);
                        // Add a new HTML element for the user's name and nickname over its video
                        this.updateNumVideos(-1);
                    });

                    // When the subscriber stream has started playing media...
                    // subscriber.on("streamPlaying", (event) => {
                    //     this.pushEvent(event);
                    // });
                });

                this.session.on("streamDestroyed", () => {
                    // pushEvent(event);
                });

                this.session.on("sessionDisconnected", (event) => {
                    // pushEvent(event);
                    if (event.reason !== "disconnect") {
                        this.removeUser();
                    }
                    if (event.reason !== "sessionClosedByServer") {
                        this.session = null;
                        this.numVideos = 0;
                        // $("#join").show();
                        // $("#session").hide();
                    }
                });

                // --- 4) Connect to the session passing the retrieved token and some more data from
                //        the client (in this case a JSON with the nickname chosen by the user) ---

                this.session
                    .connect(this.token)
                    .then(() => {
                        var publisher = this.OV.initPublisher("video-container", {
                            audioSource: undefined, // The source of audio. If undefined default microphone
                            videoSource: undefined, // The source of video. If undefined default webcam
                            publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
                            publishVideo: true, // Whether you want to start publishing with your video enabled or not
                            resolution: "320x240", // The resolution of your video
                            frameRate: 30, // The frame rate of your video
                            insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
                            mirror: false, // Whether to mirror your local video or not
                        });

                        // --- 7) Specify the actions when events take place in our publisher ---

                        // When the publisher stream has started playing media...
                        publisher.on("accessAllowed", () => {});

                        publisher.on("accessDenied", () => {});

                        publisher.on("accessDialogOpened", () => {});

                        publisher.on("accessDialogClosed", () => {});

                        // When the publisher stream has started playing media...
                        publisher.on("streamCreated", () => {});

                        // When our HTML video has been added to DOM...
                        publisher.on("videoElementCreated", () => {
                            this.updateNumVideos(1);
                        });

                        // When the HTML video has been appended to DOM...
                        publisher.on("videoElementDestroyed", () => {
                            // Add a new HTML element for the user's name and nickname over its video
                            this.updateNumVideos(-1);
                        });

                        this.session.publish(publisher);
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
            // --- 9) Leave the session by calling 'disconnect' method over the Session object ---
            this.session.disconnect();
            this.enableBtn();
        },

        /* OPENVIDU METHODS */

        enableBtn() {
            document.getElementById("join-btn").disabled = false;
            document.getElementById("join-btn").innerHTML = "Join!";
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
        fetchInfo() {
            this.httpRequest(
                "POST",
                "recording-node/api/fetch-info",
                {
                    sessionName: this.sessionName,
                },
                "Session couldn't be fetched"
            );
        },
        fetchAll() {
            this.httpRequest(
                "GET",
                "recording-node/api/fetch-all",
                {},
                "All session info couldn't be fetched"
            );
        },
        forceDisconnect() {
            this.httpRequest(
                "DELETE",
                "recording-node/api/force-disconnect",
                {
                    sessionName: this.sessionName,
                    connectionId: document.getElementById("forceValue").value,
                },
                "Connection couldn't be closed"
            );
        },
        forceUnpublish() {
            this.httpRequest(
                "DELETE",
                "recording-node/api/force-unpublish",
                {
                    sessionName: this.sessionName,
                    streamId: document.getElementById("forceValue").value,
                },
                "Stream couldn't be closed"
            );
        },

        httpRequest(method, url, body, errorMsg, callback) {
            var http = new XMLHttpRequest();
            console.log(url);
            console.log(http);
            http.open(method, `http://localhost:5000/` + url, true);
            console.log(url);
            http.setRequestHeader("Content-type", "application/json");
            console.log(url);
            http.addEventListener("readystatechange", processRequest, false);
            console.log(url);
            http.send(JSON.stringify(body));
            console.log(url);

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
            console.log("@@@@@@@@@@@@@@@");
            // var outputMode = $("input[name=outputMode]:checked").val();
            // var hasAudio = $("#has-audio-checkbox").prop("checked");
            // var hasVideo = $("#has-video-checkbox").prop("checked");
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
                    document.getElementById("forceRecordingId").value = res.id;
                    // this.checkBtnsRecordings();
                    // $("#textarea-http").text(JSON.stringify(res, null, "\t"));
                }
            );
        },
        stopRecording() {
            var forceRecordingId = document.getElementById("forceRecordingId").value;
            console.log(forceRecordingId);
            this.httpRequest(
                "POST",
                "recording-node/api/recording/stop",
                {
                    recording: forceRecordingId,
                },
                "Stop recording WRONG",
                (res) => {
                    console.log(res);
                }
            );
        },

        updateNumVideos(i) {
            this.numVideos += i;
            switch (this.numVideos) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        },
    },
};

/* APPLICATION BROWSER METHODS */

//     // made by 승
//     async startRecording() {
//         console.log(this.session);
//         console.log(this.mySessionId);
//         await axios.post(
//             APPLICATION_SERVER_URL + "recording-node/api/recording/start",
//             {
//                 session: this.session.sessionId,
//                 outputMode: "COMPOSED",
//                 hasAudio: true,
//                 hasVideo: true,
//             },
//             {
//                 headers: { "Content-Type": "application/json" },
//             }
//         );
//     },

//     // made by 승
//     muteToggle() {
//         const muteBtn = document.querySelector("#muteBtn");
//         console.log(muteBtn);
//         if (!this.isMute) {
//             this.publisher.publishAudio(false);
//             this.isMute = true;
//             muteBtn.innerText = "UnMute";
//         } else {
//             this.publisher.publishAudio(true);
//             this.isMute = false;
//             muteBtn.innerText = "Mute";
//         }
//     },

//     // made by 승
//     cameraToggle() {
//         console.log(this.OV);
//         console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//         console.log(this.publisher);
//         console.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//         const cameraBtn = document.querySelector("#cameraBtn");
//         if (!this.isCamOFF) {
//             this.publisher.publishVideo(false);
//             this.isCamOFF = true;
//             cameraBtn.innerText = "camera ON";
//         } else {
//             this.publisher.publishVideo(true);
//             this.isCamOFF = false;
//             cameraBtn.innerText = "camera OFF";
//         }
//     },

//     joinSession() {
//         // --- 1) Get an OpenVidu object ---
//         this.OV = new OpenVidu();

//         // --- 2) Init a session ---
//         this.session = this.OV.initSession();

//         // --- 3) Specify the actions when events take place in the session ---

//         // On every new Stream received...
//         this.session.on("streamCreated", ({ stream }) => {
//             const subscriber = this.session.subscribe(stream);
//             this.subscribers.push(subscriber);
//         });

//         // On every Stream destroyed...
//         this.session.on("streamDestroyed", ({ stream }) => {
//             const index = this.subscribers.indexOf(stream.streamManager, 0);
//             if (index >= 0) {
//                 this.subscribers.splice(index, 1);
//             }
//         });

//         // On every asynchronous exception...
//         this.session.on("exception", ({ exception }) => {
//             console.warn(exception);
//         });

//         // --- 4) Connect to the session with a valid user token ---

//         // Get a token from the OpenVidu deployment
//         this.getToken(this.mySessionId).then((token) => {
//             // First param is the token. Second param can be retrieved by every user on event
//             // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
//             this.session
//                 .connect(token, { clientData: this.myUserName })
//                 .then(() => {
//                     // --- 5) Get your own camera stream with the desired properties ---

//                     // Init a publisher passing undefined as targetElement (we don't want OpenVidu to insert a video
//                     // element: we will manage it on our own) and with the desired properties
//                     let publisher = this.OV.initPublisher(undefined, {
//                         audioSource: undefined, // The source of audio. If undefined default microphone
//                         videoSource: undefined, // The source of video. If undefined default webcam
//                         publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
//                         publishVideo: true, // Whether you want to start publishing with your video enabled or not
//                         resolution: "640x480", // The resolution of your video
//                         frameRate: 30, // The frame rate of your video
//                         insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
//                         mirror: false, // Whether to mirror your local video or not
//                     });

//                     // Set the main video in the page to display our webcam and store our Publisher
//                     this.mainStreamManager = publisher;
//                     this.publisher = publisher;

//                     // --- 6) Publish your stream ---

//                     this.session.publish(this.publisher);
//                 })
//                 .catch((error) => {
//                     console.log(
//                         "There was an error connecting to the session:",
//                         error.code,
//                         error.message
//                     );
//                 });
//         });

//         window.addEventListener("beforeunload", this.leaveSession);
//     },

//     leaveSession() {
//         // --- 7) Leave the session by calling 'disconnect' method over the Session object ---
//         if (this.session) this.session.disconnect();

//         // Empty all properties...
//         this.session = undefined;
//         this.mainStreamManager = undefined;
//         this.publisher = undefined;
//         this.subscribers = [];
//         this.OV = undefined;

//         // Remove beforeunload listener
//         window.removeEventListener("beforeunload", this.leaveSession);
//     },

//     updateMainVideoStreamManager(stream) {
//         if (this.mainStreamManager === stream) return;
//         this.mainStreamManager = stream;
//     },

//     /**
//      * --------------------------------------------
//      * GETTING A TOKEN FROM YOUR APPLICATION SERVER
//      * --------------------------------------------
//      * The methods below request the creation of a Session and a Token to
//      * your application server. This keeps your OpenVidu deployment secure.
//      *
//      * In this sample code, there is no user control at all. Anybody could
//      * access your application server endpoints! In a real production
//      * environment, your application server must identify the user to allow
//      * access to the endpoints.
//      *
//      * Visit https://docs.openvidu.io/en/stable/application-server to learn
//      * more about the integration of OpenVidu in your application server.
//      */
//     async getToken(mySessionId) {
//         const sessionId = await this.createSession(mySessionId);
//         return await this.createToken(sessionId);
//     },

//     async createSession(sessionId) {
//         const response = await axios.post(
//             APPLICATION_SERVER_URL + "api/sessions",
//             { customSessionId: sessionId },
//             {
//                 headers: { "Content-Type": "application/json" },
//             }
//         );
//         return response.data; // The sessionId
//     },

//     async createToken(sessionId) {
//         const response = await axios.post(
//             APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
//             {},
//             {
//                 headers: { "Content-Type": "application/json" },
//             }
//         );
//         return response.data; // The token
//     },
//     },
// };
//
</script>

<style lang="scss" scoped>
#local-video-undefined {
    csstext: "-moz-transform: scale(-1, 1); \
    -webkit-transform: scale(-1, 1);\
    -o-transform: scale(-1, 1);\
    transform: scale(-1, 1);\
    filter: FlipH;";
}
</style>
