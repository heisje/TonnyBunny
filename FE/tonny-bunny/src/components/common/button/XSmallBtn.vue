<template>
    <div class="btnWrap">
        <a class="btn xsmall" :class="color" :href="link">
            <span :class="disable ? 'disable' : font">{{ text }}</span>
        </a>
    </div>
</template>

<script>
export default {
    name: "SmallBtn",

    props: {
        text: {
            type: String,
            default: "미니 버튼",
            description: "button tag",
        },

        link: {
            type: String,
            default: "/",
            description: "button link",
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
