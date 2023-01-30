<template>
    <div class="">
        <div class="btn large" :class="color">
            <span :class="disable ? 'disable' : font">{{ text }}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "LargeBtn",

    props: {
        text: {
            type: String,
            default: "라지 버튼",
            description: "button tag",
        },

        color: {
            type: String,
            default: "main",
            description: "button color",
        },

        font: {
            type: String,
            default: "white",
            description: "font color",
        },

        disable: {
            type: Boolean,
            default: false,
            description: "button disable ",
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/button.scss";
</style>
