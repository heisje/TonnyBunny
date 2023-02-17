<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <!-- 아이디 찾기 전 -->
            <div v-if="findedId == ''">
                <title-banner
                    title="아이디 찾기"
                    text="🐰등록하신 휴대폰 번호로 이메일 주소를 찾을 수 있습니다."
                />

                <!-- 휴대폰 번호 -->
                <label for="phoneNum">휴대폰 번호</label>
                <input
                    type="text"
                    id="phoneNum"
                    v-model="phoneNum"
                    placeholder="휴대폰 번호"
                    @input="changePhoneInput"
                />
                <smallBtn @click.prevent="sendAuthCode" text="인증 요청"></smallBtn><br />
                <div v-show="noticeAuth" style="color: red">{{ noticeAuth }}</div>
                <br />

                <!-- 인증코드 -->
                <div v-show="!isCheckAuthCode">
                    <div v-show="isSendAuthCode">
                        <input
                            type="text"
                            id="authCode"
                            v-model="authCode"
                            placeholder="인증 번호"
                        />
                        <smallBtn text="확인" @click="checkAuthCode"></smallBtn><br />
                        <div v-show="noticeAuth2" style="color: red">{{ noticeAuth2 }}</div>
                        <br />
                    </div>
                </div>

                <alarm-modal
                    v-show="isOpen"
                    :isOpen="isOpen"
                    title="경고"
                    type="danger"
                    btnText2="닫기"
                    btnColor2="carrot"
                    btnFontColor2="white"
                    @clickBtn2="closeModal"
                >
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

                <div style="margin-top: 8px">
                    <router-link :to="{ name: 'HomePage' }">
                        <smallBtn
                            font="main"
                            color="outline"
                            style="width: 100%"
                            text="홈으로 돌아가기"
                        ></smallBtn>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import http from "@/common/axios";

export default {
    components: {
        TitleText,
        TitleBanner,
        smallBtn,
        AlarmModal,
    },

    data() {
        return {
            isOpen: false,
            findedId: "",
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
        },

        // 휴대폰 인증번호 발송
        async sendAuthCode() {
            // console.log(this.phoneNum);
            // 1. 유효성 검사
            // 2. 인증 코드 발송 axios 요청
            try {
                let res = await http.post("/send/authcode", { to: this.phoneNum });
                if (res.data.data) {
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

            if (!this.isSendAuthCode) {
                this.noticeAuth = "인증코드 전송이 완료되지 않았습니다";
                return;
            }

            try {
                let res = await http.post("/check/authcode", {
                    authCode: this.authCode,
                    phoneNumber: this.phoneNum,
                });
                if (res.data.data) {
                    try {
                        let res = await http.post("/login/find/email", {
                            phoneNumber: this.phoneNum,
                            isAuthed: true,
                        });
                        if (res.data.data) {
                            this.isCheckAuthCode = true;
                            this.noticeAuth = "인증이 완료되었습니다";
                            this.findedId = res.data.data;
                        }
                    } catch (error) {
                        console.log(error);
                        this.isCheckAuthCode = true;
                        this.noticeAuth = "회원정보가 없습니다.";
                        this.findedId = "등록된 계정이 존재하지 않습니다.";
                    }
                } else {
                    this.noticeAuth2 = "인증번호가 일치하지 않습니다";
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
