<template>
    <div>
        <h1>Button Test</h1>
        <MediumBtn color="lightgray" disable />
        <hr />
        <MediumBtn color="lightgray" link="/bunny" />
        <hr />

        <MediumBtn color="white" />
        <hr />

        <LargeBtn text="라지" link="/tonny" />

        <SmallBtn color="carrot" />
        <hr />

        <XSmallBtn color="dark" />
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

<style></style>
