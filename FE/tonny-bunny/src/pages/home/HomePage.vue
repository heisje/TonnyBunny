<template>
    <div>
        <home-banner></home-banner>
        <home-contents></home-contents>
    </div>
</template>

<script>
/* eslint-disable */
import HomeBanner from "@/components/home/HomeBanner.vue";
import HomeContents from "@/components/home/HomeContents.vue";
import LoginModal from "@/components/common/modal/LoginModal.vue";

import ModalTest from "@/components/common/modal/ModalTest.vue";
import CardTest from "@/components/common/card/CardTest.vue";

export default {
    name: "HomePage",

    components: {
        HomeBanner,
        HomeContents,
        LoginModal,
        ModalTest,
        CardTest,
    },

    mounted() {
        this.$store.dispatch("getBoardList", 0);
        console.log("%cWelcome To TonnyBunny! 🐰 ", "color: #ff7765");
    },
};
</script>

<style lang="scss" scoped></style>
