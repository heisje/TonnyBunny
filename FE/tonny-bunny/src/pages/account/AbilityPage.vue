<template>
    <div>
        <h1>회원관리 - 능력 어필 페이지</h1>

        <TitleText />
        <SubText />

        <div>언어선택 라인</div>
        <div>자격증 추가</div>

        <div>확인 버튼</div>
        <div>건너뛰기 버튼</div>
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
