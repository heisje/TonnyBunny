<template lang="">
    <div>
        <div v-for="(chatRoom, index) in chatRoomList" :key="index">
            <router-link
                @click="setEnterRoomInfo(chatRoom)"
                :to="{ name: 'ChatDetailPage' }"
                class="nav-link"
                aria-current="page">
                <chat-item
                    v-bind:imagePath="chatRoom.anotherUserInfo?.imagePath"
                    v-bind:nickName="chatRoom.anotherUserInfo?.nickName"
                    v-bind:recentMessage="chatRoom.recentMessage" />
            </router-link>
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
        setEnterRoomInfo(chatRoom) {
            this.$store.dispatch("setChatRoomInfo", chatRoom);
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
