<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <!-- 비밀번호 재설정 전 -->
            <div v-if="!isComplete">
                <title-banner title="비밀번호 재설정" text="새로운 비밀번호를 입력해주세요." />

                <div>
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
                </div>

                <smallBtn style="width: 100%" text="완료" @click="updatePw"></smallBtn>
            </div>

            <!-- 비밀번호 재설정 후 -->
            <div v-else>
                <TitleText
                    title="비밀번호 번경 완료"
                    center
                    text="비밀번호 변경이 완료되었습니다." />

                <br /><br /><br /><br />

                <div style="margin-top: 8px">
                    <router-link :to="{ name: 'HomePage' }">
                        <smallBtn
                            font="main"
                            color="outline"
                            style="width: 100%"
                            text="홈으로 돌아가기"></smallBtn>
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
import http from "@/common/axios";

export default {
    components: {
        TitleText,
        TitleBanner,
        smallBtn,
    },

    props: {
        userSeq: {
            type: String,
            default: "0",
        },
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
        async updatePw(event) {
            event.preventDefault();
            let userSeq = this.userSeq * 1;
            userSeq;

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
            try {
                let res = await http.post(`login/find/password/${this.userSeq}`, {
                    password: this.password,
                    checkPassword: this.password2,
                });
                if (res.data.data) {
                    this.isComplete = true;
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
    created() {
        // console.log(this.userSeq);
    },
};
</script>

<style></style>
