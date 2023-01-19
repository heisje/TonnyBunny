<template>
    <a class="btn xsmall" :class="color">
        <span :href="link">{{ text }}</span>
    </a>
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

        disable: {
            type: Boolean,
            default: false,
            description: "button disable ",
        },
    },
};
</script>

<style lang="scss" scoped></style>
