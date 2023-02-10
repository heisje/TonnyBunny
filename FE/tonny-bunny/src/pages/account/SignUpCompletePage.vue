<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <TitleText title="회원가입 완료!" center text="가입을 축하합니다" />
            <br /><br /><br />
            <div style="text-align: center">
                <img src="@/assets/logo.png" alt="" />
            </div>

            <TitleText
                title="이제 다양한 토니버니 서비스를 이용하실 수 있습니다."
                type="h2"
                center />
            <br /><br /><br /><br />

            <div>
                <smallBtn
                    color="carrot"
                    style="width: 100%"
                    text="로그인 하기"
                    @click="toggleLoginModal"></smallBtn>
            </div>

            <div style="margin-top: 8px">
                <router-link :to="{ name: 'HomePage' }">
                    <smallBtn
                        color="outline"
                        font="main"
                        style="width: 100%"
                        text="홈으로 돌아가기"></smallBtn>
                </router-link>
            </div>
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
    methods: {
        toggleLoginModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_LOGIN_MODAL");
            this.$router.push({ name: "HomePage" });
        },
    },
};
</script>

<style></style>
