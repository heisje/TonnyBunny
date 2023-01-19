<template>
    <div>
        <h1>회원관리 - 약관 동의 페이지</h1>

        <TitleText />
        <SubText />

        <DescriptionItem />
        <div>약관동의 체크 라인</div>

        <DescriptionItem />
        <div>약관동의 체크 라인</div>

        <button>다음 버튼</button>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import SubText from "@/components/common/SubText.vue";
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
