<template>
    <div>
        <div>완료 페이지</div>

        <TitleText />
        <SubText />

        <div>로고 라인</div>

        <div>
            버튼 라인
            <button>버튼1</button>
            <button>버튼2</button>
        </div>
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
