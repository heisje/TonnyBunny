<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner
                title="비밀번호 찾기"
                text="🐰가입한 이메일과 휴대폰 인증으로 비밀번호를 변경합니다." />

            <div>
                <!-- 이메일 -->
                <label for="email">아이디</label><br />
                <input type="text" id="email" v-model="email" placeholder="이메일" /><br /><br />

                <!-- 휴대폰 번호 -->
                <label for="phoneNum">휴대폰 번호</label>
                <input
                    type="text"
                    id="phoneNum"
                    v-model="phoneNum"
                    placeholder="휴대폰 번호"
                    @input="changePhoneInput" />
                <smallBtn @click.prevent="sendAuthCode" text="인증 요청" class="my-2"></smallBtn
                ><br />
                <div v-show="noticeAuth" style="color: red">{{ noticeAuth }}</div>
                <br />

                <!-- 인증코드 -->
                <div v-show="!isCheckAuthCode">
                    <div v-show="isSendAuthCode">
                        <input
                            type="text"
                            id="authCode"
                            v-model="authCode"
                            placeholder="인증 번호" />
                        <smallBtn text="확인" @click="checkAuthCode"></smallBtn><br />
                        <div v-show="noticeAuth2" style="color: red">{{ noticeAuth2 }}</div>
                        <br />
                    </div>
                </div>
            </div>

            <smallBtn style="width: 100%" text="다음" @click="goResetPwPage"></smallBtn>

            <alarm-modal
                v-show="isOpen"
                :isOpen="isOpen"
                title="경고"
                type="danger"
                btnText2="닫기"
                btnColor2="carrot"
                btnFontColor2="white"
                @clickBtn2="closeModal">
                <template #content> 가입된 명의의 이메일이 일치하지 않습니다 </template>
            </alarm-modal>
        </div>
    </div>
</template>

<script>
import TitleBanner from "@/components/common/TitleBanner.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import http from "@/common/axios";

export default {
    components: {
        TitleBanner,
        smallBtn,
        AlarmModal,
    },

    data() {
        return {
            isOpen: false,
            email: "",

            // 휴대폰
            phoneNum: "",
            isSendAuthCode: false,
            noticeAuth: "",

            authCode: "",
            isCheckAuthCode: false,
            noticeAuth2: "",
        };
    },

    methods: {
        // 휴대폰번호 입력
        changePhoneInput() {
            this.isCheckAuthCode = false;
            this.isSendAuthCode = false;
            this.noticeAuth = "";
            this.noticeAuth2 = "";
        },

        // 휴대폰 인증번호 발송
        async sendAuthCode() {
            // 1. 유효성 검사
            // 2. 인증 코드 발송 axios 요청
            try {
                let res = await http.post("/send/authcode", { to: this.phoneNum });

                if (res.data.data) {
                    console.log(res.data.data);
                    this.isSendAuthCode = true;
                    this.noticeAuth = "인증번호가 발송되었습니다";
                }
            } catch (error) {
                console.log(error);
            }
        },

        // 인증코드 확인
        async checkAuthCode(event) {
            event.preventDefault();

            try {
                let res = await http.post("/check/authcode", {
                    authCode: this.authCode,
                    phoneNumber: this.phoneNum,
                });
                if (res.data.data) {
                    this.isCheckAuthCode = true;
                    this.noticeAuth = "인증이 완료되었습니다";
                    this.noticeAuth2 = "";
                } else {
                    this.noticeAuth2 = "인증번호가 일치하지 않습니다";
                }
            } catch (error) {
                alert("인증번호가 일치하지 않습니다.");
                console.log(error);
            }
        },

        // 완료 버튼 시
        async goResetPwPage(event) {
            event.preventDefault();

            if (!this.isSendAuthCode) {
                this.noticeAuth = "인증코드 전송이 완료되지 않았습니다";
                return;
            }

            if (!this.isCheckAuthCode) {
                this.noticeAuth2 = "인증이 완료되지 않았습니다";
                return;
            }
            console.log("phoneNum:" + this.phoneNum);
            console.log("email : " + this.email);
            try {
                let res = await http.post("/login/find/password", {
                    phoneNumber: this.phoneNum,
                    email: this.email,
                    isAuthed: true,
                });
                if (res.data.data) {
                    this.$router.push({
                        name: "ResetPwPage",
                        params: { userSeq: res.data.data },
                    });
                }
            } catch (error) {
                console.log(error);
            }
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};
</script>

<style></style>
