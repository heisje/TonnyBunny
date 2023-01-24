<template>
    <div>
        <h1>회원관리 - 아이디 찾기 페이지</h1>

        <TitleText />
        <SubText />

        <FindInputForm />
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
