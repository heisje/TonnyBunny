<template lang="">
    <div>
        <div v-for="chatRoom in chatRoomList" :key="chatRoom">
            <div
                @click="setEnterRoomInfo($event, chatRoom[1])"
                class="nav-link"
                aria-current="page">
                {{ chatRoom }}
                <chat-item
                    v-bind:imagePath="chatRoom[1].anotherUserInfo?.imagePath"
                    v-bind:nickName="chatRoom[1].anotherUserInfo?.nickName"
                    v-bind:recentMessage="chatRoom[1].recentMessage"
                    v-bind:notReadCount="chatRoom[1].notReadCount" />
            </div>
        </div>
    </div>
</template>
<script>
import ChatItem from "./ChatItem.vue";
import { mapGetters } from "vuex";
// import http from "@/common/axios";

export default {
    components: {
        ChatItem,
    },
    data() {
        return {};
    },
    methods: {
        setEnterRoomInfo(event, chatRoom) {
            event.preventDefault();

            // let stompSocket = this.$store.getters.getChatStompSocket;
            // stompSocket.send("/sub/chat/5", "msg");
            this.$store.dispatch("setChatRoomInfo", chatRoom);
            this.$router.push({ name: "ChatDetailPage" });
        },
    },
    mounted() {
        this.$store.dispatch("getChatRoomList", this.userInfo);
        console.log("GET: ", this.$store.getters.getChatRoomList);
    },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
            chatRoomList: "getChatRoomList",
        }),
    },
};
</script>
<style lang=""></style>
