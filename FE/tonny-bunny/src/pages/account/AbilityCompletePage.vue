<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner title="헬퍼 정보 등록 완료" text="🐰헬퍼가 되신 것을 축하합니다!" />
            <br /><br /><br />
            <div style="text-align: center">
                <img src="@/assets/logo.png" alt="" />
            </div>

            <TitleText title="이제 다양한 헬퍼 활동을 진행할 수 있습니다." type="h2" center />
            <br /><br /><br /><br />

            <div style="margin-top: 8px">
                <router-link :to="{ name: 'HomePage' }">
                    <smallBtn
                        color="outline"
                        font="main"
                        style="width: 100%"
                        text="홈으로 돌아가기"
                    ></smallBtn>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner";
import smallBtn from "@/components/common/button/SmallBtn.vue";

export default {
    components: {
        TitleText,
        TitleBanner,
        smallBtn,
    },
    methods: {},
    mounted() {
        this.$store.dispatch("getMypage");
    },
};
</script>

<style></style>
