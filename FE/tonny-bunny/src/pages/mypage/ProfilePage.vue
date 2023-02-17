<template>
    <title-banner :title="getTitle" text="헬퍼 프로필을 확인해볼까요?"></title-banner>

    <div style="margin-bottom: 80px"></div>

    <div class="d-flex justify-content-center align-items-center">
        <div class="profileWrap">
            <medium-btn
                v-if="userInfo?.seq == myInfo.seq"
                text="수정하기"
                class="w-100 mb-5"
                color="carrot"
                @click="goProfileUpdate"></medium-btn>
            <helper-profile :userInfo="userInfo" />

            <!-- <title-text v-else title="헬퍼 프로필" center></title-text> -->
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
// import TitleText from "@/components/common/TitleText.vue";
// import CertifyItem from "@/components/mypage/CertifyItem.vue";
// import ReviewItem from "@/components/mypage/ReviewItem.vue";
import HelperProfile from "@/components/common/helperProfile.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
    name: "ProfilePage",

    components: {
        // CertifyItem,
        // ReviewItem,
        HelperProfile,
        // TitleText,
        TitleBanner,
        MediumBtn,
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

        getTitle() {
            return this.userInfo.nickName + "님의 헬퍼 프로필";
        },
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

<style lang="scss" scoped>
.profileWrap {
    width: 450px;
    padding: 32px 24px;
    border: 1px solid rgba(0, 0, 0, 0.08);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--background-color);
    border-radius: 6px;
}
</style>
