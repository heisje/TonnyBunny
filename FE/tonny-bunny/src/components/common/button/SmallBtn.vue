<template>
    <div class="btnWrap">
        <div class="btn small" :class="color">
            <span :class="disable ? 'disable' : font">{{ text }}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "smallBtn",

    props: {
        text: {
            type: String,
            default: "작은 버튼",
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
