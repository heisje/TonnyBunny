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
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/global.scss";
</style>
