<template>
    <title-text title="내 정보" type="h1-small" />
    <HelperCard rightBtnText="" @clickBtn2="[]" />

    <home-helper-contents />
</template>

<script>
import HelperCard from "@/components/common/card/HelperCard.vue";
import HomeHelperContents from "@/components/home/HomeHelperContents.vue";
import TitleText from "@/components/common/TitleText.vue";
// import ScheduleList from "@/components/home/ScheduleList.vue";

export default {
    name: "HelperHomePage",

    components: {
        HelperCard,
        HomeHelperContents,
        TitleText,
        // ScheduleList,
    },
};
</script>

<style lang="scss" scoped></style>
