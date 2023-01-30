<template>
    <title-text title="회원 정보 수정" />
    <img src="@/assets/noProfile.png" alt="" />

    <div>
        프로필사진선택모달

        <ImageUpdateModal />
    </div>
    <div>
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" />
    </div>
    <div>
        <label for="password1">현재 비밀번호</label>
        <input type="password" id="password1" />
    </div>
    <div>
        <label for="password2">새 비밀번호</label>
        <input type="password" id="password2" />
    </div>
    <div>
        <label for="password3">새 비밀번호 확인</label>
        <input type="password" id="password3" />
    </div>

    <medium-btn text="완료" @click="openModal"></medium-btn>
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
        :link="{ name: 'MyPage' }">
        <template #content> 완료되었습니다 </template>
    </AlarmModal>
</template>

<script>
import ImageUpdateModal from "@/components/mypage/ImageUpdateModal.vue";
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
    name: "InfoUpdatePage",
    data() {
        return {
            isOpen: false,
        };
    },
    components: {
        ImageUpdateModal,
        TitleText,
        AlarmModal,
        MediumBtn,
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
