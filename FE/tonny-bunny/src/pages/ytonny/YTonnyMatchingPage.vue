<template>
    <div>
        <title-text
            title="통역 예약 제안"
            text="예약통역 요청글을 자세히 보고 가능한 통역을 신청하세요!" />

        <quest-card />

        <title-text
            type="h2"
            title="의뢰 해결 시 받을 금액을 설정해주세요"
            text="주의: 의뢰 미응답 및 분쟁사항에 대해서 법적 조치될 수 있습니다." />

        <form class="customForm">
            <input type="text" />
            <span>캐럿/5분당</span>
        </form>

        <agree-input />
        <medium-btn color="carrot" text="가격제안하기" @click="openModal" />

        <alarm-modal
            v-show="isOpen"
            :isOpen="isOpen"
            title="번역 제안 신청됨"
            type="success"
            btnText2="다음"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal"
            :link="{ name: 'YTonnyMatchingPage' }">
            <template #content>
                예약을 신청하셨습니다.<br />
                고객이 응답을 주면 예약이 완료됩니다!<br />
                고객이 거절 또는 응답 시 알림을 받을 수 있습니다.
            </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import QuestCard from "@/components/common/card/QuestCard.vue";
import AgreeInput from "@/components/common/input/AgreeInput.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
export default {
    data() {
        return {
            isOpen: false,
        };
    },

    components: { TitleText, QuestCard, AgreeInput, MediumBtn, AlarmModal },

    methods: {
        openModal(e) {
            e.preventDefault();
            this.isOpen = true;
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};
</script>

<style></style>
