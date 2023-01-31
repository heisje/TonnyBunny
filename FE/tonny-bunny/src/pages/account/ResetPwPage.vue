<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <!-- 비밀번호 재설정 전 -->
            <div v-if="!isComplete">
                <TitleText title="비밀번호 재설정" center text="새로운 비밀번호를 입력해주세요" />

                <div>
                    <!-- 비밀번호 -->
                    <label for="password">비밀번호</label>
                    <input
                        type="password"
                        id="password"
                        v-model="password"
                        placeholder="비밀번호"
                        @input="changePwInput"
                    /><br />
                    <div v-show="noticePw" style="color: red">{{ noticePw }}</div>
                    <br />

                    <!-- 비밀번호 확인 -->
                    <label for="password2">비밀번호 확인</label>
                    <input
                        type="password"
                        id="password2"
                        v-model="password2"
                        @input="changePw2Input"
                        placeholder="비밀번호 확인"
                    /><br />
                    <div v-show="noticePw2" style="color: red">{{ noticePw2 }}</div>
                    <br />
                </div>

                <smallBtn style="width: 100%" text="완료" @click="updatePw"></smallBtn>
            </div>

            <!-- 비밀번호 재설정 후 -->
            <div v-else>
                <TitleText
                    title="비밀번호 번경 완료"
                    center
                    text="비밀번호 변경이 완료되었습니다."
                />

                <br /><br /><br /><br />

                <smallBtn style="width: 100%" text="로그인 하러가기"></smallBtn><br /><br />
                <smallBtn style="width: 100%" text="홈으로 돌아가기"></smallBtn>
            </div>
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
            isComplete: false,

            // 비밀번호
            password: "",
            password2: "",
            validatePw: /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/, // 8~18글자 영문, 숫자, 특수문자 조합
            isValidPw: false,
            noticePw: "",
            noticePw2: "",
            isValidPw2: false,
        };
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

        // 비밀번호 변경 완료
        updatePw(event) {
            event.preventDefault();

            if (this.password == "") {
                this.noticePw = "비밀번호를 입력해 주세요";
                return;
            }

            if (!this.isValidPw) {
                return;
            }

            this.changePw2Input();

            if (!this.isValidPw2) {
                return;
            }

            // 비밀번호 변경 axios 요청
            this.isComplete = true;
        },
    },
};
</script>

<style></style>
