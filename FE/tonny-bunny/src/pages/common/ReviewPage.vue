<template>
    <div>
        <TitleText title="리뷰하기" text="매칭된 번역가는 어떠셨나요?" center />

        <img src="@/assets/noProfile.png" alt="" />
        <div>닉네임</div>

        <TitleText title="헬퍼님은 어떠셨나요?" type="h2" center />

        <div>star</div>
        <form class="customForm">
            <textarea></textarea>
        </form>

        <router-link :to="{ name: 'HomePage' }">
            <small-btn text="완료" />
        </router-link>
        <router-link :to="{ name: 'ReportPage' }">
            <small-btn text="신고하기" />
        </router-link>
        <router-link :to="{ name: 'HomePage' }">
            <small-btn text="다음에 리뷰쓰기" />
        </router-link>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";

export default {
    components: {
        TitleText,
        SmallBtn,
    },
};
</script>

<style></style>
