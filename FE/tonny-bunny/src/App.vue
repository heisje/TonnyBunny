<template>
    <router-view name="header"></router-view>
    <main class="container" v-on:mousemove="test">
        <router-view></router-view>
        <bottom-navbar />
        <login-modal></login-modal>
        <helper-modal></helper-modal>
        <tonny-modal></tonny-modal>
        <bunny-modal></bunny-modal>
        <AppLoading />
        <!-- <div class="circle"></div> -->
    </main>
    <router-view name="footer"></router-view>
</template>

<script>
import BottomNavbar from "@/pages/common/BottomNavbar.vue";
import LoginModal from "@/components/common/modal/LoginModal.vue";
import HelperModal from "@/components/common/modal/HelperModal.vue";
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
        HelperModal,
    },

    methods: {
        // test(e) {
        //     const circle = document.querySelector(".circle");
        //     const mouseX = e.clientX;
        //     const mouseY = e.clientY;
        //     circle.style.left = mouseX + "px";
        //     circle.style.top = mouseY + "px";
        // },
    },

    mounted() {
        this.$store.dispatch("setCommonCode");
        window.addEventListener("beforeunload", () => {
            this.$store.commit("DISCONNECT_CHAT_STOMP_SOCKET");
            this.$store.commit("DISCONNECT_ALERT_STOMP_SOCKET");
        });
        window.addEventListener("load", () => {
            // window.onload = function () {
            if (this.$store.getters.getIsLogin == true) {
                this.$store.commit(
                    "CONNECT_CHAT_STOMP_SOCKET",
                    this.$store.getters.getUserInfo.seq
                );
                this.$store.commit("CONNECT_ALERT_STOMP_SOCKET");
            }
        });
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/global.scss";
</style>
