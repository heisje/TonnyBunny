<template>
    <div>
        <h1>회원가입 랜딩 페이지</h1>

        <TitleText />
        <SubText />

        <div>헬퍼 부가 설명</div>
        <DescriptionItem />

        <div>일반 부가 설명</div>
        <DescriptionItem />
    </div>
</template>

<script>
import TitleText from "@/components/account/TitleText.vue";
import SubText from "@/components/account/SubText.vue";
import DescriptionItem from "@/components/account/DescriptionItem.vue";

export default {
    components: {
        TitleText,
        SubText,
        DescriptionItem,
    },
};
</script>

<style></style>
