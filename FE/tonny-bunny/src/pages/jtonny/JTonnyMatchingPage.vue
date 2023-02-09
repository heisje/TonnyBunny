<template>
    <div>
        <title-text title="고객에게 제안하기" text="5분당 가격을 제안해보세요!" />

        <helper-card />

        <JTonnyHelperForm></JTonnyHelperForm>
        <small-btn color="light" font="main" text="취소" @click.prevent="openModalCancel" />
        <small-btn
            color="carrot"
            font="white"
            text="고객에게 제안하기"
            @click.prevent="openModalSuccess" />

        <AlarmModal
            v-show="isOpenCancel"
            :isOpen="isOpenCancel"
            title="취소하시겠습니까?"
            type="warning"
            btnText1="아니오"
            btnText2="예"
            btnColor1="light"
            btnColor2="active"
            btnFontColor1="main"
            btnFontColor2="white"
            @close-modal="closeModalCancel"
            :link="{ name: 'JTonnyApplyPage' }">
            <template #content> 저장된 양식이 삭제됩니다. </template>
        </AlarmModal>

        <AlarmModal
            v-show="isOpenSuccess"
            :isOpen="isOpenSuccess"
            title="제안 보냄"
            type="success"
            btnText2="예"
            btnColor1="light"
            btnColor2="active"
            btnFontColor1="main"
            btnFontColor2="white"
            @close-modal="closeModalSuccess"
            :to="{ name: 'JTonnyApplyPage' }">
            <template #content>
                고객에게 제안을 성공적으로 보냈습니다.<br />
                고객이 거절할 수도 있어요!
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import JTonnyHelperForm from "@/components/jtonny/JTonnyHelperForm.vue";
import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    data() {
        return {
            isOpenCancel: false,
            isOpenSuccess: false,
        };
    },
    components: {
        JTonnyHelperForm,
        TitleText,
        HelperCard,
        SmallBtn,
        AlarmModal,
    },
    methods: {
        openModalCancel(e) {
            e.preventDefault();
            this.isOpenCancel = true;
        },

        closeModalCancel() {
            this.isOpenCancel = false;
        },
        openModalSuccess(e) {
            e.preventDefault();
            this.isOpenSuccess = true;
        },

        closeModalSuccess() {
            this.isOpenSuccess = false;
        },
    },
};

// import JTonnyHelperForm from '@/components/jtonny/JTonnyHelperForm.vue';
// export default {
//   components: { JTonnyHelperForm },};
</script>

<style></style>
