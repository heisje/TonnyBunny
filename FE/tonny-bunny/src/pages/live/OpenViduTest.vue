<template>
    <div class="openviduContainer">
        <div class="openviduWrap">
            <div class="openvidu">안뇽</div>
            <div id="video-container" class="col-md-6" style="background-color: gray">
                하이루
                <user-video
                    :stream-manager="publisher"
                    @click="updateMainVideoStreamManager(publisher)" />
                <user-video
                    v-for="sub in subscribers"
                    :key="sub.stream.connection.connectionId"
                    :stream-manager="sub"
                    @click="updateMainVideoStreamManager(sub)" />
            </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import { mapGetters } from "vuex";

export default {
    name: "OpenViduTest",

    components: {},

    data() {
        return {
            OV: undefined, // session object
            session: undefined,
            mainStreamManager: undefined, // main cam
            publisher: undefined, // my stream
            subscribers: [], // other stream

            myUserName: "Participant" + Math.floor(Math.random() * 100), // nickname
        };
    },

    computed: {
        ...mapGetters({
            startResData: "getStartResData",
        }),

        mySessionName() {
            return this.startResData.uuid;
        },
    },

    methods: {
        async getToken(sessionName) {
            const res = await axios.post(
                process.env.VUE_APP_OPENVIDU_URL + "recording-node/api/get-token",
                { sessionName: sessionName },
                { headers: { "Content-Type": "application/json" } }
            );
            return res;
        },
    },

    mounted() {
        console.log("하이하이");

        // session 에 누군가가 들어올 때
        this.session.on("streamCreated", ({ stream }) => {
            let subscriber = this.session.subscribe(stream, undefined);
            console.log("누구긔: ", subscriber);
            this.subscribers.push(subscriber);
        });

        // session 에 누군가가 나갈 때
        this.session.on("streamDestroyed", (e) => {
            console.log("누군데: ", e.streamManager);
            const index = this.subscribers.indexOf(e.streamManager, 0);
            if (index >= 0) {
                this.subscribers.splice(index, 1);
            }
        });

        console.log("publisher: ", this.publisher);
        console.log("sub:", this.subscribers);
    },

    async created() {
        // OV 객체 받기
        this.OV = new OpenVidu();
        // console.log("resdata", this.startResData);

        // session 세팅
        this.session = this.OV.initSession();
        // console.log("session: ", this.session);

        let token = await this.getToken(this.mySessionName);
        console.log("token:", token);
    },
};
</script>

<style></style>
