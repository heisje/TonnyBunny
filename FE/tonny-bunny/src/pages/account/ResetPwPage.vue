<template>
    <div>
        <!-- 비밀번호 재설정 전 -->
        <div v-if="!isComplete">
            <h1>회원관리 - 비밀번호 재설정 페이지</h1>

            <TitleText title="비밀번호 재설정" center text="새로운 비밀번호를 입력해주세요" />

            <div>
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
            </div>

            <smallBtn style="width: 100%" text="완료" @click="updatePw"></smallBtn>
        </div>

        <!-- 비밀번호 재설정 후 -->
        <div v-else>
            <TitleText title="비밀번호 번경 완료" center text="비밀번호 변경이 완료되었습니다." />

            <br /><br /><br /><br />

            <smallBtn style="width: 100%" text="로그인 하러가기"></smallBtn><br /><br />
            <smallBtn style="width: 100%" text="홈으로 돌아가기"></smallBtn>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";

export default {
    components: {
        TitleText,
        smallBtn,
    },

    data() {
        return {
            password: "",
            checkPassword: "",
            isComplete: false,
        };
    },

    methods: {
        updatePw(event) {
            event.preventDefault();

            // 1. 비밀번호 유효성 검사
            // 2. 비밀번호 일치 검사

            this.isComplete = true;
        },
    },
};
</script>

<style></style>
