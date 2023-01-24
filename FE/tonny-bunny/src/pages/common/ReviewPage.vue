<template>
    <div>
        <h1>리뷰 작성 페이지</h1>

        <TitleText />
        <SubText />

        <div>아바타</div>
        <div>별점 컴포넌트</div>
        <div>리뷰 text area</div>

        <button>리뷰 작성 완료 버튼</button>
        <button>신고하기 버튼</button>
    </div>
</template>

<script>
import TitleText from "@/components/account/TitleText.vue";
import SubText from "@/components/account/SubText.vue";

export default {
    components: {
        TitleText,
        SubText,
    },
};
</script>

<style></style>
