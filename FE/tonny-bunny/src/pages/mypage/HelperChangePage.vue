<template>
    <div class="container w-75">
        <title-banner title="🐰 헬퍼 전환하기" text="당신도 헬퍼로 활동할 수 있습니다!" />
        <hr />
        <title-text title="헬퍼 이용약관" center text="이용약관을 자세히 살펴본 후 체크해주세요." />
        <div class="p-2 my-3 d-flex justify-content-center">
            <input type="checkbox" name="" @click="changeTerm" />
            <span> 중계 서비스 이용에 동의합니다. (필수)</span>
            <div class="ml-2" style="color: gray">자세히</div>
        </div>

        <small-btn class="d-flex justify-content-center" text="확인" @click="openModal"></small-btn>

        <alarm-modal
            title="확인"
            type="success"
            btnText1="취소"
            btnText2="예"
            btnColor2="carrot"
            btnFontColor2="white"
            @clickBtn1="closeModal"
            @clickBtn2="clickBtn2"
        >
            <template #content>
                헬퍼 신청
                <br />
                헬퍼로 변경하시겠습니까?
            </template>
        </alarm-modal>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import http from "@/common/axios";

export default {
    name: "HelperChangePage",

    components: {
        TitleText,
        smallBtn,
        TitleBanner,
        AlarmModal,
    },
    data() {
        return {
            helperTerm: false,
        };
    },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),
    },

    methods: {
        async submitForm() {
            if (!this.helperTerm) {
                alert("약관 동의는 필수입니다.");
            }

            try {
                let res = await http.put(`/mypage/${this.userInfo.seq}/userCode`);
                console.log(res.data);
                if (res.data.resultCode == "SUCCESS") {
                    console.log("헬퍼전환완료");
                    this.$store.commit("TOGGLE_ALARM_MODAL");
                    this.$router.push({
                        name: "AbilityPage",
                        params: { userSeq: this.userInfo.seq },
                    });
                }
            } catch (error) {
                console.log(error);
            }
        },

        openModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        clickBtn2() {
            this.submitForm();
        },
        changeTerm() {
            this.helperTerm = !this.helperTerm;
        },
    },
};
</script>

<style></style>
