<template>
    <title-text
        v-if="userInfo?.seq == myInfo.seq"
        title="헬퍼 프로필"
        center
        text="수정하기"
        @click="goProfileUpdate"
    ></title-text>
    <title-text v-else title="헬퍼 프로필" center></title-text>

    <helper-profile :userInfo="userInfo" />
</template>

<script>
import { mapGetters } from "vuex";
import TitleText from "@/components/common/TitleText.vue";
// import CertifyItem from "@/components/mypage/CertifyItem.vue";
// import ReviewItem from "@/components/mypage/ReviewItem.vue";
import HelperProfile from "@/components/common/helperProfile.vue";
export default {
    name: "ProfilePage",

    components: {
        // CertifyItem,
        // ReviewItem,
        HelperProfile,
        TitleText,
    },
    methods: {
        goProfileUpdate(event) {
            event.preventDefault();
            this.$router.push({
                name: "ProfileUpdatePage",
                params: { userSeq: this.userInfo.seq },
            });
        },
    },

    computed: {
        ...mapGetters({ userInfo: "getHelperInfo" }),
        ...mapGetters({ myInfo: "getUserInfo" }),
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
