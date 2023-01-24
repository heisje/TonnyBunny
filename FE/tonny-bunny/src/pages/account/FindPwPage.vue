<template>
    <div>
        <h1>회원관리 - 비밀번호 찾기 페이지</h1>

        <TitleText />
        <SubText />

        <div>
            <div>
                휴대폰 인증
                <FindInputForm />
            </div>
            <div>
                이메일 인증
                <FindInputForm />
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import SubText from "@/components/common/SubText.vue";
import FindInputForm from "@/components/account/FindInputForm.vue";

export default {
    components: {
        TitleText,
        SubText,
        FindInputForm,
    },
};
</script>

<style></style>
