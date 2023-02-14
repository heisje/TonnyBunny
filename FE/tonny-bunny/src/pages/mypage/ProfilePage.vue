<template>
    <title-text title="헬퍼 프로필" center text="수정하기" @click="goProfileUpdate"></title-text>


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
    methods:{
        goProfileUpdate(event){
            event.preventDefault();
            this.$router.push({ name: "ProfileUpdatePage", params:{ userSeq:this.userInfo.seq} });
        }
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
