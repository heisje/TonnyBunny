<template>
    <div class="d-flex align-items-center justify-content-between">
        <div class="d-flex justify-content-around align-items-center mb-3">
            <img src="@/assets/noProfile.png" alt="" class="mr-5" style="width: 4rem" />
            <h2>헬퍼이름</h2>
        </div>
        <x-small-btn :text="btnText" color="carrot" font="white" />
    </div>
</template>

<script>
import XSmallBtn from "../common/button/XSmallBtn.vue";
export default {
    name: "UserProfileItem",
    components: {
        XSmallBtn,
    },
    data() {
        return {};
    },
    props: {
        helperInfo: {
            type: Object,
        },
        btnText: {
            type: String,
            default: "Click!",
        },
    },
    methods: {
        clickBtn() {},
    },
};
</script>

<style></style>
