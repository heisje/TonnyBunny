<template>
    <div>
        <title-banner title="🐰 통역예약" text="헬퍼에게 통역 서비스를 예약합니다." />

        <YTonnyClientForm @toggleSubmit="openModal" />

        <AlarmModal
            v-show="isOpen"
            :isOpen="isOpen"
            title="성공"
            type="success"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal"
            :to="{ name: 'HistoryPage' }">
            <template #content>
                통역예약이 게시되었습니다. <br />
                헬퍼의 제안 중 가장 좋은 제안을 수락하세요!
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import YTonnyClientForm from "@/components/ytonny/YTonnyClientForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
export default {
    name: "YTonnyFormPage",
    data() {
        return {
            isOpen: false,
        };
    },
    components: { YTonnyClientForm, TitleBanner, AlarmModal },
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
