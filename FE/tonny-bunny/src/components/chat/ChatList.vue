<template lang="">
    <div class="chat-list-view">
        <div v-show="chatRoomList.size == 0">
            <div class="no-chat-list fs-5 fw-bold text-center">생성된 채팅방이 없어요!</div>
            <Vue3Lottie
                :animationLink="`https://lottie.host/489fd0fe-d872-4b65-bf80-d22bafe814c0/fJD3A7hPRX.json`"
                width="50%"
                height="50%"
                background="transparent"
                loop
                autoplay>
            </Vue3Lottie>
        </div>
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
        stompSocket;

        await this.$store.dispatch("getChatRoomList", this.userInfo);
        this.$store.commit("CONNECT_CHAT_STOMP_SOCKET", this.userInfo.seq);
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
.no-chat-list {
    margin-top: 10%;
}
.chat-item:hover {
    border-radius: 10px;
    background: var(--light-color);
}
</style>
