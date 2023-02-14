<template lang="">
    <div class="chat-list-view">
        <div v-for="chatRoom in chatRoomList" :key="chatRoom">
            <div @click="enterChatRoom($event, chatRoom[1])" class="chat-item" aria-current="page">
                <!-- {{ chatRoom }} -->
                <chat-item
                    v-bind:nickName="chatRoom[1].anotherUserInfo?.nickName"
                    v-bind:profileImagePath="chatRoom[1].anotherUserInfo?.profileImagePath"
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
        enterChatRoom(event, chatRoomInfo) {
            event.preventDefault();

            // stompSocket.send("/sub/chat/5", "msg");
            this.$store.commit("SET_CHAT_ROOM_INFO", chatRoomInfo);
            this.$router.push({ name: "ChatDetailPage" });
        },
    },
    async mounted() {
        let stompSocket = this.$store.getters.getChatStompSocket;
        console.log("stompSocket: ", stompSocket);
        console.log("UserInfo: ", this.userInfo);

        await this.$store.dispatch("getChatRoomList", this.userInfo);
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
<style lang="scss" scoed>
.chat-item:hover {
    border-radius: 10px;
    background: var(--light-color);
}
</style>
