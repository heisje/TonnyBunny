<template>
    <div class="d-flex flex-column align-items-center">
        <TitleText title="리뷰하기" text="매칭된 번역가는 어떠셨나요?" center />

        <div class="d-flex justify-content-center">
            <!-- <span v-if="this.$store.state.mypage.otherHelperInfo.profileImagePath">
                <img :src="this.$store.state.mypage.otherHelperInfo.profileImagePath" alt="" />
            </span> -->
            <span>
                <user-profile-img
                    :profileImagePath="this.$store.state.mypage.otherHelperInfo.profileImagePath"
                    width="80" />
            </span>
        </div>
        <div class="d-flex justify-content-center">
            <h2 class="mt-3">{{ this.$store.state.mypage.otherHelperInfo.nickName }}</h2>
        </div>
        <TitleText title="헬퍼님은 어떠셨나요?" type="h2" center />

        <div class="d-flex justify-content-center">
            <v-rating
                background-color="warning lighten-1"
                color="warning"
                hover
                length="5"
                size="64"
                v-model="rating"
                @change="checkValue"></v-rating>
        </div>
        <br />
        <form class="customForm d-flex justify-content-center">
            <textarea v-model="comment"></textarea>
        </form>

        <div class="d-flex justify-content-center">
            <small-btn class="mr-3" text="완료" @click="openModal" />

            <router-link :to="{ name: 'ReportPage' }">
                <small-btn class="mr-3" text="신고하기" />
            </router-link>

            <router-link :to="{ name: 'HomePage' }">
                <small-btn class="mr-3" text="다음에 리뷰쓰기" />
            </router-link>
        </div>

        <alarm-modal
            title="확인"
            type="success"
            btnText2="예"
            btnColor2="carrot"
            btnFontColor2="white"
            @clickBtn1="closeModal"
            @clickBtn2="clickBtn2">
            <template #content>
                리뷰 작성
                <br />
                리뷰를 작성하시겠습니까?
            </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import http from "@/common/axios";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import UserProfileImg from "@/components/common/UserProfileImg.vue";

export default {
    components: {
        TitleText,
        SmallBtn,
        AlarmModal,
        UserProfileImg,
    },

    data() {
        return {
            isOpen: false,
            score: 5,
            rating: 5,
            comment: "많은 도움이 되었습니다 감사합니다.",
        };
    },

    props: {
        historySeq: {
            type: Number,
            default: 1,
        },

        helperSeq: {
            type: Number,
            default: 1,
        },
    },

    methods: {
        async submitForm() {
            try {
                let res = await http.post("/review", {
                    historySeq: this.historySeq,
                    helperSeq: this.helperSeq,
                    score: this.rating,
                    comment: this.comment,
                });
                if (res.data.resultCode == "SUCCESS") {
                    this.$store.commit("TOGGLE_ALARM_MODAL");
                    this.$router.push({ name: "HomePage" });
                } else {
                    this.$store.commit("TOGGLE_ALARM_MODAL");
                    alert("리뷰 작성이 실패하였습니다.");
                }
            } catch (error) {
                console.log(error);
                this.$store.commit("TOGGLE_ALARM_MODAL");
                alert("리뷰 작성이 실패하였습니다.");
            }
        },

        openModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        closeModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        clickBtn2() {
            this.submitForm();
        },
    },
    mounted() {
        this.$store.dispatch("getOtherHelper", this.helperSeq).then(() => {});
    },
};
</script>

<style></style>
