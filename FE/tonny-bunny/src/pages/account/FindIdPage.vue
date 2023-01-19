<template>
    <div>
        <div>회원관리 - 아이디 찾기 페이지</div>

        <TitleText />
        <SubText />

        <FindInputForm />
    </div>
</template>

<script>
import TitleText from "@/components/account/TitleText.vue";
import SubText from "@/components/account/SubText.vue";
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
