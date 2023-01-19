<template>
    <a class="btn large" :class="color">
        <span :href="link" :class="font">{{ text }}</span>
    </a>
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

<style lang="scss" scoped></style>
