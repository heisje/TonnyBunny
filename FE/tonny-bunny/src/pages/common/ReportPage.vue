<template>
    <div>
        <h1>신고 작성 페이지</h1>

        <TitleText />
        <SubText />

        <div>카테고리 설정 라인</div>

        <div>신고 사유 text area</div>

        <div>약관 동의 라인</div>
        <button>취소 버튼</button>
        <button>신고하기 버튼</button>
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
