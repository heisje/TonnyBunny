<template>
    <router-view name="header"></router-view>
    <main class="container">
        <router-view></router-view>
        <bottom-navbar />
        <login-modal></login-modal>
        <tonny-modal></tonny-modal>
        <bunny-modal></bunny-modal>
        <AppLoading />
    </main>
    <router-view name="footer"></router-view>
</template>

<script>
import BottomNavbar from "@/pages/common/BottomNavbar.vue";
import LoginModal from "@/components/common/modal/LoginModal.vue";
import TonnyModal from "@/components/common/modal/TonnyModal.vue";
import BunnyModal from "@/components/common/modal/BunnyModal.vue";
import AppLoading from "./pages/common/AppLoading.vue";

export default {
    name: "App",

    components: {
        BottomNavbar,
        LoginModal,
        TonnyModal,
        BunnyModal,
        AppLoading,
    },

    mounted() {
        this.$store.dispatch("setCommonCode");
        // window.addEventListener("beforeunload", (event) => {
        //     // 명세에 따라 preventDefault는 호출해야하며, 기본 동작을 방지합니다.
        //     // event.preventDefault();

        //     console.log("reload!!!");
        //     console.log("reload Object: ", this.$store.getters.getUserInfo);
        //     console.log("reload Socket: ", this.$store.getters.getChatSocket);
        //     console.log("reload Stomp Socket: ", this.$store.getters.getChatStompSocket);
        //     // 대표적으로 Chrome에서는 returnValue 설정이 필요합니다.
        //     event.returnValue = "aa";
        // });
        window.addEventListener("load", () => {
            // window.onload = function () {
            console.log("after reload!!!");
            if (this.$store.getters.getIsLogin == true) {
                // let socket = this.$store.getters.getChatSocket;
                this.$store.commit(
                    "RECONNECT_CHAT_STOMP_SOCKET",
                    this.$store.getters.getUserInfo.seq
                );
                // let stompSocket = this.$store.getters.getChatStompSocket;
                // stompSocket.connect({}, );
            }
            console.log("load Object: ", this.$store.getters.getUserInfo);
            console.log("load Socket: ", this.$store.getters.getChatSocket);
            console.log("load Stomp Socket: ", this.$store.getters.getChatStompSocket);
        });
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/global.scss";
</style>
