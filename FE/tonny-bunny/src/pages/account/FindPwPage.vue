<template>
    <div>
        <h1>회원관리 - 비밀번호 찾기 페이지</h1>

        <TitleText
            title="비밀번호 찾기"
            center
            text="가입한 이메일과 휴대폰 인증을 통해 비밀번호를 찾아드립니다" />

        <div>
            <label for="email">아이디</label><br />
            <input type="text" id="email" v-model="email" placeholder="이메일" /><br /><br />

            <label for="phoneNum">휴대폰 번호</label><br />
            <input type="text" id="phoneNum" v-model="phoneNum" placeholder="휴대폰 번호" />
            <smallBtn @click.prevent="sendAuthCode" text="인증 요청"></smallBtn>
            <div v-if="isCheckAuthCode" style="color: red">인증 완료</div>
            <br /><br />

            <div v-if="isSendAuthCode">
                <input type="text" id="authCode" v-model="authCode" placeholder="인증 번호" />
                <smallBtn text="확인" @click="checkAuthCode"></smallBtn><br /><br />
            </div>
        </div>

        <smallBtn style="width: 100%" text="다음" @click="goResetPwPage"></smallBtn>

        <alarm-modal
            v-show="isOpen"
            :isOpen="isOpen"
            title="경고"
            type="danger"
            btnText1="닫기"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal">
            <template #content> 가입된 명의의 이메일이 일치하지 않습니다 </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    components: {
        TitleText,
        smallBtn,
        AlarmModal,
    },

    data() {
        return {
            isOpen: false,
            email: "",
            phoneNum: "",
            isCheckNickname: false,
            isSendAuthCode: false,
            authCode: "",
            isCheckAuthCode: false,
        };
    },

    methods: {
        // 휴대폰 인증번호 발송
        sendAuthCode() {
            console.log("인증요청");
            this.isSendAuthCode = true;
            // 1. 유효성 검사
            // 2. 인증 코드 발송 axios 요청
        },

        // 인증코드 확인
        checkAuthCode(event) {
            event.preventDefault();

            console.log("인증코드 확인");
            this.isCheckAuthCode = true;
            this.isSendAuthCode = false;
        },

        // 완료 버튼 시
        goResetPwPage(event) {
            event.preventDefault();

            // 휴대폰 인증완료와 가입된 명의의 이메일이 일치할 경우
            if (this.isCheckAuthCode) {
                this.$router.push({ name: "ResetPwPage" });
            } else {
                this.isOpen = true;
            }
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};
</script>

<style></style>
