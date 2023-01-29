<template>
    <div>
        <div v-if="select == `helper`">
            <TitleText title="헬퍼 회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
            <!-- <SubText /> -->
        </div>
        <div v-else>
            <TitleText title="일반 회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
        </div>

        <TitleText title="간편 로그인" type="h2" center text="1, 2, 3" />

        <label for="email">아이디</label><br />
        <input type="text" id="email" v-model="email" placeholder="이메일" /><br /><br />

        <label for="password">비밀번호</label><br />
        <input
            type="password"
            id="password"
            v-model="password"
            placeholder="비밀번호" /><br /><br />

        <label for="checkPassword">비밀번호 확인</label><br />
        <input
            type="password"
            id="checkPassword"
            v-model="checkPassword"
            placeholder="비밀번호 확인" /><br /><br />

        <label for="nickname">닉네임</label><br />
        <input type="text" id="nickname" v-model="nickname" placeholder="닉네임" />
        <smallBtn @click.prevent="checkDuplication" text="중복 확인"></smallBtn>
        <div v-if="isCheckNickname" style="color: red">사용 가능한 닉네임입니다</div>
        <br /><br />

        <label for="phoneNum">휴대폰 번호</label><br />
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
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
// import SubText from "@/components/common/SubText.vue";

export default {
    components: {
        TitleText,
        smallBtn,
        // SubText,
    },

    data() {
        return {
            isOpen: false,
            email: "",
            password: "",
            checkPassword: "",
            nickname: "",
            phoneNum: "",
            isCheckNickname: false,
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
        // 닉네임 중복 확인
        checkDuplication() {
            console.log("중복확인");
            // 1. 유효성 검사
            // 2. 중복 체크 axios 요청
            this.isCheckNickname = true;
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
            // 1. 유효성 검사 (이메일, 패스워드)
            // 2. 닉네임 중복 확인 여부
            // 3. 휴대폰 인증 확인 여부

            console.log("회원가입 완료");

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
