<template>
    <h1>헬퍼 프로필</h1>
    <helper-profile :userInfo="userInfo" />
</template>

<script>
import { mapGetters } from "vuex";

// import CertifyItem from "@/components/mypage/CertifyItem.vue";
// import ReviewItem from "@/components/mypage/ReviewItem.vue";
import HelperProfile from "@/components/common/helperProfile.vue";
export default {
    name: "ProfilePage",

    components: {
        // CertifyItem,
        // ReviewItem,
        HelperProfile,
    },

    computed: {
        ...mapGetters({ userInfo: "getHelperInfo" }),
    },

    props: {
        id: {
            type: Number,
        },
    },

    mounted() {
        this.$store.dispatch("getHelper", this.id).then(() => {});
    },
};
</script>

<style></style>
