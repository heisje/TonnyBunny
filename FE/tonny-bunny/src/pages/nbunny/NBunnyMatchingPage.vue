<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner title="번역 헬퍼 신청" text="번역 의뢰에 견적서를 작성하고 요청합니다" />
            <TitleText
                title="고객에게 제안하기"
                text="의뢰 캐럿을 지정하여 고객에게 제안해볼까요?"
                top="100"
                bottom="54"></TitleText>

            <NBunnyHelperForm></NBunnyHelperForm>

            <AlarmModal
                title="제안 보냄"
                type="success"
                btnText2="다음"
                btnColor1="main"
                btnColor2="main"
                btnFontColor1="white"
                btnFontColor2="white"
                @click-btn2="clickBtn2">
                <template #content> 고객에게 제안을 성공적으로 보냈습니다. </template>
            </AlarmModal>
        </div>
    </div>
</template>

<script>
import NBunnyHelperForm from "@/components/nbunny/NBunnyHelperForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import { mapGetters } from "vuex";

export default {
    components: {
        NBunnyHelperForm,
        TitleBanner,
        TitleText,
        AlarmModal,
        // QuestCard,
    },
    data() {
        return {
            title: "주의",
            type: "danger",
            btnText1: "취소",
            btnText2: "제안하기",
            btnColor1: "thin",
            btnColor2: "main",
            btnFontColor1: "white",
            btnFontColor2: "white",
        };
    },
    computed: {
        ...mapGetters({ getBunnyDetail: "getBunnyDetail" }),
    },

    methods: {
        async clickBtn2() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
            const bunnySeq = this.getBunnyDetail.seq;
            this.$router.push({ name: "NBunnyDetailPage", params: { id: bunnySeq } });

            // let alert = {
            //     userSeq: this.userInfo.seq,
            //     clientSeq: this.getBunnyDetail.client.seq,
            //     helperSeq: this.userInfo.seq,
            //     taskCode: this.getBunnyDetail.taskCode,
            //     content: "번역 신청",
            //     clientNickname: this.getBunnyDetail.client.nickName,
            //     helperNickname: this.userInfo.nickName,
            // };

            // await this.$store.dispatch("applyAlert", alert);
        },
    },
};
</script>

<style></style>
