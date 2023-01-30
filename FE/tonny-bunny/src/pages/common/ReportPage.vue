<template>
    <div>
        <TitleText title="신고하기" center />

        <img src="@/assets/noProfile.png" alt="" />
        <div>닉네임</div>

        <TitleText title="신고하시는 이유를 골라주세요" type="h2" center />

        <form class="customForm">
            <DropdownInput
                :dropdownArray="['아이템1', '아이템2', '아이템3']"
                placeholder="상황 선택"
                @toggle="(e) => (dropdownValue = e)" />
            <TitleText title="자세한 내용을 적어주세요" type="h2" center />
            <textarea></textarea>
        </form>
        <div>신고 사유 text area</div>

        <agree-input @toggle="(e) => (isAgree = e)" />
        <small-btn text="취소" />

        <small-btn text="신고하기" @click.prevent="openModal" />

        <AlarmModal
            v-show="isOpen"
            :isOpen="isOpen"
            title="완료"
            type="success"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal"
            :link="this.$router.options.history.state.back">
            <template #content> 신고가 완료되었습니다 </template>
        </AlarmModal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import DropdownInput from "@/components/common/input/DropdownInput.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import AgreeInput from "@/components/common/input/AgreeInput.vue";

export default {
    data() {
        return {
            isOpen: false,
            isAgree: false,
            dropdownValue: "",
        };
    },
    components: {
        TitleText,
        DropdownInput,
        SmallBtn,
        AlarmModal,
        AgreeInput,
    },
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
