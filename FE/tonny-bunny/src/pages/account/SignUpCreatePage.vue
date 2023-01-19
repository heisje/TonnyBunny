<template>
    <div>
        <TitleText />
        <SubText />

        <div>간편 회원 가입 인증 라인</div>
        <div>이메일 input</div>
        <div>비밀번호 input</div>
        <div>비밀번호 확인 input</div>
        <div>닉네임 input</div>
        <div>휴대폰 인증 input</div>

        <div>회원가입 버튼</div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import SubText from "@/components/common/SubText.vue";

export default {
    components: {
        TitleText,
        SubText,
    },
};
</script>

<style></style>
