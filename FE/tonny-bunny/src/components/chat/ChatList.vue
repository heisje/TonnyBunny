<template lang="">
    <div>
        <div v-for="(chatRoom, index) in chatRoomList" :key="index">
            <div @click="setEnterRoomInfo($event, chatRoom)" class="nav-link" aria-current="page">
                <chat-item
                    v-bind:imagePath="chatRoom.anotherUserInfo?.imagePath"
                    v-bind:nickName="chatRoom.anotherUserInfo?.nickName"
                    v-bind:recentMessage="chatRoom.recentMessage"
                    v-bind:notReadCount="chatRoom.notReadCount" />
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

            this.$store.dispatch("setChatRoomInfo", chatRoom);
            this.$router.push({ name: "ChatDetailPage" });
        },
    },
    mounted() {
        this.$store.dispatch("getChatRoomList", this.userInfo);
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
