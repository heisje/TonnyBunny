<template>
    <title-text title="회원 정보 수정" />
    <img src="@/assets/noProfile.png" alt="" />
    {{ profileImg }}
    <medium-btn text="프로필 변경" @click="clickInputProfile" />
    <input type="file" accept="image/*" ref="click" @change="insertImage" />

    <div>
        <label for="nickname">닉네임</label>
        <input type="text" id="nickname" v-modal="nickName" />
    </div>
    <medium-btn text="닉네임 수정" @click="putNickName"></medium-btn>
    <div>
        <label for="password1">현재 비밀번호</label>
        <input type="password" id="password1" v-modal="prePassword" />
    </div>
    <div>
        <label for="password2">새 비밀번호</label>
        <input type="password" id="password2" v-modal="prePassword" />
    </div>
    <div>
        <label for="password3">새 비밀번호 확인</label>
        <input type="password" id="password3" v-modal="prePassword" />
    </div>
    <medium-btn text="비밀번호 수정" @click="openModal"></medium-btn>

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
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
    name: "InfoUpdatePage",
    data() {
        return {
            isOpen: false,
            profileImg: null,
            nickName: "",
            prePassword: "",
        };
    },
    components: {
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

        insertImage(e) {
            this.profileImg = e.target.files[0];
            const payload = this.profileImg;
            this.$store.dispatch("putProfileImage", payload);
        },

        clickInputProfile() {
            this.$refs.click.click();
        },

        putNickName() {},
    },
};
</script>

<style></style>
