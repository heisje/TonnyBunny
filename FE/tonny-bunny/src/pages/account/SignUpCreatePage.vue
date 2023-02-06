<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div v-if="select == `helper`">
                <TitleText title="헬퍼 회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
            </div>
            <div v-else>
                <TitleText title="일반 회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
            </div>

            <TitleText title="간편 로그인" type="h2" center text="카카오, 구글, 네이버" />

            <!-- 이메일 -->
            <label for="email">아이디</label>
            <input type="email" id="email" v-model="email" placeholder="이메일" /><br /><br />

            <!-- 비밀번호 -->
            <label for="password">비밀번호</label>
            <input
                type="password"
                id="password"
                v-model="password"
                placeholder="비밀번호"
                @input="changePwInput" /><br />
            <div v-show="noticePw" style="color: red">{{ noticePw }}</div>
            <br />

            <!-- 비밀번호 확인 -->
            <label for="password2">비밀번호 확인</label>
            <input
                type="password"
                id="password2"
                v-model="password2"
                @input="changePw2Input"
                placeholder="비밀번호 확인" /><br />
            <div v-show="noticePw2" style="color: red">{{ noticePw2 }}</div>
            <br />

            <!-- 닉네임 -->
            <label for="nickname">닉네임</label>
            <input
                type="text"
                id="nickname"
                v-model="nickname"
                placeholder="닉네임"
                @input="changeNickInput" />
            <smallBtn @click.prevent="checkDuplication" text="중복 확인"></smallBtn><br />
            <div v-show="noticeNick" style="color: red">{{ noticeNick }}</div>
            <br />

            <!-- 휴대폰 번호 -->
            <label for="phoneNum">휴대폰 번호</label>
            <input
                type="text"
                id="phoneNum"
                v-model="phoneNum"
                placeholder="휴대폰 번호"
                @input="changePhoneInput" />
            <smallBtn @click.prevent="sendAuthCode" text="인증 요청"></smallBtn><br />
            <div v-show="noticeAuth" style="color: red">{{ noticeAuth }}</div>
            <br />

            <!-- 인증코드 -->
            <div v-show="!isCheckAuthCode">
                <div v-show="isSendAuthCode">
                    <input type="text" id="authCode" v-model="authCode" placeholder="인증 번호" />
                    <smallBtn text="확인" @click="checkAuthCode"></smallBtn><br />
                    <div v-show="noticeAuth2" style="color: red">{{ noticeAuth2 }}</div>
                    <br />
                </div>
            </div>

            <TitleText title="약관 동의" text="약관을 모두 읽고 동의해주세요." type="h2" />
            <div class="p-4" style="border: 1px solid var(--light-color)">
                <!-- 공통 약관 -->
                <div v-for="(term, index) in clientTerms" :key="index">
                    <div class="p-2 m-2">
                        <input type="checkbox" name="color" @click="clientTermToggle(index)" />
                        <span> {{ term.title }}<span>(필수)</span> </span>
                    </div>
                </div>
                <!-- 헬퍼 약관 -->
                <div v-show="select == 'helper'">
                    <div v-for="(term, index) in helperTerms" :key="index">
                        <div class="p-2 m-2">
                            <input type="checkbox" name="color" @click="helperTermToggle(index)" />
                            <span> {{ term.title }}<span>(필수)</span> </span>
                        </div>
                    </div>
                </div>
            </div>
            <alarm-modal
                title="실패"
                type="danger"
                btnText2="닫기"
                btnColor2="carrot"
                btnFontColor2="white"
                @clickBtn2="closeModal">
                <template #content> {{ exception }} </template>
            </alarm-modal>
            <smallBtn style="width: 100%" text="회원 가입" @click="submitForm"></smallBtn>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import http from "@/common/axios";
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

            // 이메일
            email: "",

            // 비밀번호
            password: "",
            password2: "",
            validatePw: /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/, // 8~18글자 영문, 숫자, 특수문자 조합
            isValidPw: false,
            noticePw: "",
            noticePw2: "",
            isValidPw2: false,

            // 닉네임
            nickname: "",
            isCheckNickname: false,
            validateNick: /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/, // 2~16자 초성 안됨
            noticeNick: "",

            // 휴대폰
            phoneNum: "",
            isSendAuthCode: false,
            noticeAuth: "",

            authCode: "",
            isCheckAuthCode: false,
            noticeAuth2: "",

            // 약관정보
            clientTerms: [
                {
                    title: "만 14세 이상입니다.",
                    isAgree: false,
                },
                {
                    title: "서비스 이용약관에 동의합니다.",
                    isAgree: false,
                },
                {
                    title: "개인정보 수집·이용에 동의합니다.",
                    isAgree: false,
                },
                {
                    title: "개인정보 제3자 제공에 동의합니다.",
                    isAgree: false,
                },
            ],
            helperTerms: [
                {
                    title: "중계서비스 이용약관에 동의합니다.",
                    isAgree: false,
                },
            ],

            exception: "해주세요",
        };
    },

    props: {
        select: {
            type: String,
            default: "",
        },
    },

    methods: {
        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        // 비밀번호 유효성 검사
        changePwInput() {
            // 유효성 검사
            if (this.validatePw.test(this.password)) {
                this.isValidPw = true;
                this.noticePw = "";
            } else {
                this.isValidPw = false;
                this.noticePw = "8~16자, 숫자와 문자, 특수문자를 포함한 비밀번호를 입력해주세요.";
            }
        },

        // 비밀번호 일치 검사
        changePw2Input() {
            if (this.password == this.password2) {
                this.isValidPw2 = true;
                this.noticePw2 = "";
            } else {
                this.isValidPw2 = false;
                this.noticePw2 = "비밀번호가 일치하지 않습니다";
            }
        },

        // 닉네임 입력
        changeNickInput() {
            this.isCheckNickname = false;
            this.noticeNick = "";
        },

        // 닉네임 중복 확인
        async checkDuplication() {
            // 1. 유효성 검사
            if (!this.validateNick.test(this.nickname)) {
                this.isCheckNickname = false;
                this.noticeNick = "2~16자, 영어 또는 숫자 또는 한글로 구성해주세요";
                return;
            }
            // 2. 중복 체크 axios 요청

            try {
                let res = await http.post("/signup/nickname", { nickname: this.nickname });
                console.log(res);
                if (res.data.data) {
                    this.isCheckNickname = true;
                    this.noticeNick = "사용가능한 닉네임입니다";
                } else {
                    this.isCheckNickname = false;
                    this.noticeNick = "사용중인 닉네임입니다";
                }
            } catch (error) {
                console.log(error);
            }
        },

        // 휴대폰번호 입력
        changePhoneInput() {
            this.isCheckAuthCode = false;
            this.isSendAuthCode = false;
            this.noticeAuth = "";
        },

        // 휴대폰 인증번호 발송
        async sendAuthCode() {
            console.log(this.phoneNum);
            // 1. 유효성 검사
            // 2. 인증 코드 발송 axios 요청
            try {
                let res = await http.post("/send/authcode", { phoneNumber: this.phoneNum });
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

            try {
                let res = await http.post("/check/authcode", { authCode: this.authCode });
                if (res.data.data) {
                    this.isCheckAuthCode = true;
                    this.noticeAuth = "인증이 완료되었습니다";
                    this.noticeAuth2 = "";
                } else {
                    this.noticeAuth2 = "인증번호가 일치하지 않습니다";
                }
            } catch (error) {
                console.log(error);
            }
        },

        // 약관 토글시
        clientTermToggle(index) {
            if (this.clientTerms[index].isAgree) {
                this.clientTerms[index].isAgree = false;
            } else {
                this.clientTerms[index].isAgree = true;
            }
        },

        helperTermToggle(index) {
            if (this.helperTerms[index].isAgree) {
                this.helperTerms[index].isAgree = false;
            } else {
                this.helperTerms[index].isAgree = true;
            }
        },

        // 폼 제출
        async submitForm(event) {
            event.preventDefault();
            if (!this.email) {
                this.exception = "이메일을 입력해 주세요.";
                this.$store.commit("TOGGLE_ALARM_MODAL");
                return;
            }
            if (this.password == "") {
                this.noticePw = "비밀번호를 입력해 주세요.";
                this.exception = "비밀번호를 입력해 주세요.";
                this.$store.commit("TOGGLE_ALARM_MODAL");
                return;
            }

            if (!this.isValidPw) {
                this.exception = "패스워드가 정상적이지 않습니다.";
                this.$store.commit("TOGGLE_ALARM_MODAL");
                return;
            }

            this.changePw2Input();

            if (!this.isValidPw2) {
                this.exception = "비밀번호 확인이 틀립니다.";
                this.$store.commit("TOGGLE_ALARM_MODAL");
                return;
            }

            // if (!this.isCheckNickname) {
            //     this.noticeNick = "닉네임 중복확인을 해주세요.";
            //     this.exception = "닉네임 중복확인을 해주세요.";
            //     this.$store.commit("TOGGLE_ALARM_MODAL");
            //     return;
            // }

            // if (!this.isSendAuthCode) {
            //     this.noticeAuth = "인증코드 전송이 완료되지 않았습니다.";
            //     this.exception = "인증코드 전송이 완료되지 않았습니다.";
            //     this.$store.commit("TOGGLE_ALARM_MODAL");
            //     return;
            // }

            // if (!this.isCheckAuthCode) {
            //     this.noticeAuth2 = "인증이 완료되지 않았습니다.";
            //     this.exception = "인증이 완료되지 않았습니다.";
            //     this.$store.commit("TOGGLE_ALARM_MODAL");
            //     return;
            // }

            // // 약관동의
            // let checkTerms = true;
            // this.clientTerms.forEach((term) => {
            //     if (!term.isAgree) {
            //         checkTerms = false;
            //     }
            // });

            // if (this.$route.params.select == "helper") {
            //     this.helperTerms.forEach((term) => {
            //         if (!term.isAgree) {
            //             checkTerms = false;
            //         }
            //     });
            // }
            // if (!checkTerms) {
            //     this.exception = "약관에 동의하지 않았습니다.";
            //     this.$store.commit("TOGGLE_ALARM_MODAL");
            // }

            // 모두 참일 때 폼 제출 가능

            const userCode = this.$route.params.select;

            try {
                let res = await http.post("/signup", {
                    email: this.email,
                    checkPassword: this.password2,
                    nickName: this.nickname,
                    password: this.password,
                    phoneNumber: this.phoneNum,
                    userCode: userCode,
                });

                if (res.data.resultCode == "SUCCESS") {
                    // 회원가입 성공
                    console.log("dd", res.data);
                    console.log(typeof res.data.data);
                    if (this.$route.params.select == "client") {
                        this.$router.push({ name: "SignUpCompletePage" });
                    } else if (this.$route.params.select == "helper") {
                        this.$router.push({
                            name: "AbilityPage",
                            params: { userSeq: res.data.data },
                        });
                    }
                } else {
                    this.exception = "회원가입에 실패하였습니다.";
                    this.$store.commit("TOGGLE_ALARM_MODAL");
                    // 회원가입 실패
                    console.log("회원가입 실패");
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
};
</script>

<style></style>
