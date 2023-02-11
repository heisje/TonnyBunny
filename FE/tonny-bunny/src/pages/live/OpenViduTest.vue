<template>
    <div class="openviduContainer">
        <div class="openviduWrap">
            <div class="openvidu">안뇽</div>
        </div>
    </div>
</template>

<script>
import { OpenVidu } from "openvidu-browser";

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

            mySessionId: "SessionA", // nickname
            myUserName: "Participant" + Math.floor(Math.random() * 100), // nickname
        };
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
    },

    created() {
        // OV 객체 받기
        this.OV = new OpenVidu();

        // session 세팅
        this.session = this.OV.initSession();

        // console.log(this.OV);
        // console.log("session: ", this.session);
    },
};
</script>

<style></style>
