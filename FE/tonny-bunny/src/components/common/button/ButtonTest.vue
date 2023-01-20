<template>
    <div>
        <h1>Button Test</h1>
        <MediumBtn color="sub" disable />
        <hr />
        <MediumBtn color="thin" font="active" link="/bunny" />
        <hr />

        <MediumBtn color="outline" font="active" link="/mypage" text="마이 페이지" />
        <hr />

        <LargeBtn text="라지" link="/tonny" />

        <SmallBtn color="carrot" />
        <SmallBtn color="primary" />
        <hr />

        <XSmallBtn color="thin" font="active" />
    </div>
</template>

<script>
/* eslint-disable */
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import XSmallBtn from "@/components/common/button/XSmallBtn.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";

export default {
    components: {
        MediumBtn,
        LargeBtn,
        XSmallBtn,
        SmallBtn,
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/button.scss";
</style>
