<template>
    <div>
        <h1>회원가입 완료 페이지</h1>

        <TitleText title="회원가입 완료!" center text="가입을 축하합니다" />
        <br /><br /><br />
        <div style="text-align: center">
            <img src="@/assets/logo.png" alt="" />
        </div>

        <TitleText title="이제 다양한 토니버니 서비스를 이용하실 수 있습니다" type="h2" center />
        <br /><br /><br /><br />
        <smallBtn style="width: 100%" text="로그인 하러가기"></smallBtn><br /><br />
        <smallBtn style="width: 100%" text="홈으로 돌아가기"></smallBtn>
        <!-- <FindInputForm /> -->
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
// import FindInputForm from "@/components/account/FindInputForm.vue";

export default {
    components: {
        TitleText,
        smallBtn,
        // FindInputForm,
    },
};
</script>

<style></style>
