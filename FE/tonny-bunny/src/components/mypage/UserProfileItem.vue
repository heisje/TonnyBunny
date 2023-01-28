<template>
    <img src="@/assets/noProfile.png" alt="" />
    <div>name</div>
    <x-small-btn text="차단해제" />
</template>

<script>
import XSmallBtn from "../common/button/XSmallBtn.vue";
export default {
    name: "UserProfileItem",
    components: {
        XSmallBtn,
    },
};
</script>

<style></style>
