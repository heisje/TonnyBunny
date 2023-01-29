<template>
    <div>
        <!-- 아이디 찾기 전 -->
        <div v-if="findedId == ''">
            <h1>회원관리 - 아이디 찾기 페이지</h1>

            <TitleText
                title="아이디 찾기"
                center
                text="등록한 휴대폰 번호로 계정 이메일 주소를 알려드립니다" />

            <input type="text" id="phoneNum" v-model="phoneNum" placeholder="휴대폰 번호" />
            <smallBtn @click.prevent="sendAuthCode" text="인증 요청"></smallBtn>
            <br /><br />

            <div v-if="isSendAuthCode">
                <input type="text" id="authCode" v-model="authCode" placeholder="인증 번호" />
                <smallBtn text="확인" @click="checkAuthCode"></smallBtn><br /><br />
            </div>

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
                <template #content>
                    인증에 실패했습니다. 인증번호를 다시 한번 확인해주세요.
                </template>
            </alarm-modal>
        </div>

        <!-- 아이디 찾은 후 -->
        <div v-else>
            <TitleText title="인증 성공" center text="휴대폰 인증에 성공하셨습니다." />

            <TitleText :title="findedId" center text="회원님의 이메일 정보 입니다." />

            <br /><br /><br /><br />

            <smallBtn style="width: 100%" text="로그인 하러가기"></smallBtn><br /><br />
            <smallBtn style="width: 100%" text="홈으로 돌아가기"></smallBtn>
        </div>
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
            phoneNum: "",
            isCheckNickname: false,
            isSendAuthCode: false,
            authCode: "",
            findedId: "",
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

            // 인증 실패 시 모달
            if (this.authCode == "") {
                this.isOpen = true;
            } else {
                this.findedId = "ssafy@ssafy.com";
            }
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};
</script>

<style></style>
