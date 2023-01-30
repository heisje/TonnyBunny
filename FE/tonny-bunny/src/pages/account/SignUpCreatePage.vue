<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div v-if="select == `helper`">
                <TitleText title="헬퍼 회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
                <!-- <SubText /> -->
            </div>
            <div v-else>
                <TitleText title="일반 회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
            </div>

            <TitleText title="간편 로그인" type="h2" center text="카카오, 구글, 네이버" />

            <label for="email">아이디</label>
            <input type="text" id="email" v-model="email" placeholder="이메일" /><br /><br />

            <label for="password">비밀번호</label>
            <input
                type="password"
                id="password"
                v-model="password"
                placeholder="비밀번호"
                @input="changePwInput" /><br />
            <div v-show="noticePw" style="color: red">{{ noticePw }}</div>
            <br />

            <label for="password2">비밀번호 확인</label>
            <input
                type="password"
                id="password2"
                v-model="password2"
                placeholder="비밀번호 확인"
                @input="changePw2Input" /><br />
            <div v-show="noticePw2" style="color: red">{{ noticePw2 }}</div>
            <br />

            <label for="nickname">닉네임</label>
            <input type="text" id="nickname" v-model="nickname" placeholder="닉네임" />
            <smallBtn @click.prevent="checkDuplication" text="중복 확인"></smallBtn><br />
            <div v-if="isCheckNickname" style="color: red">{{ noticeNick }}</div>
            <br />

            <label for="phoneNum">휴대폰 번호</label>
            <input type="text" id="phoneNum" v-model="phoneNum" placeholder="휴대폰 번호" />
            <smallBtn @click.prevent="sendAuthCode" text="인증 요청"></smallBtn>
            <div v-if="isCheckAuthCode" style="color: red">인증 완료</div>
            <br /><br />

            <div v-if="isSendAuthCode">
                <input type="text" id="authCode" v-model="authCode" placeholder="인증 번호" />
                <smallBtn text="확인" @click="checkAuthCode"></smallBtn><br /><br />
            </div>

            <smallBtn style="width: 100%" text="회원 가입" @click="submitForm"></smallBtn>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
// import http from "@/common/axios";

export default {
    components: {
        TitleText,
        smallBtn,
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
            isValidPw2: "",

            // 닉네임
            nickname: "",
            isCheckNickname: false,
            validateNick: /^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$/, // 2~16자 초성 안됨
            noticeNick: "",

            // 휴대폰
            phoneNum: "",
            isSendAuthCode: false,
            authCode: "",
            isCheckAuthCode: false,
        };
    },

    props: {
        select: {
            type: String,
            default: "",
        },
    },

    methods: {
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

        // 닉네임 중복 확인
        async checkDuplication() {
            // 1. 유효성 검사
            if (!this.validateNick.test(this.nickname)) {
                this.isValidPw = false;
                this.noticeNick = "2~16자, 영어 또는 숫자 또는 한글로 구성해주세요";
            } else {
                this.noticeNick = "";
            }

            // 2. 중복 체크 axios 요청
            this.isCheckNickname = true;
            // try {
            //     let { data } = await http.post("/signup/nickname", { nickname: this.nickname });
            //     console.log("async function : ", data);
            // } catch (err) {
            //     console.error(err);
            // }
        },

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

        // 폼 제출
        submitForm(event) {
            event.preventDefault();

            // 모두 참일 때 폼 제출 가능
            // this.isValidPw
            // this.isValidPw2
            // this.isCheckNickname
            // this.isCheckAuthCode

            if (this.$route.params.select == "client") {
                this.$router.push({ name: "SignUpCompletePage" });
            } else if (this.$route.params.select == "helper") {
                this.$router.push({ name: "AbilityPage" });
            }
        },
    },
};
</script>

<style></style>
