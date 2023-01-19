<template>
    <a class="btn medium" :class="color">
        <span :href="link" :class="font">{{ text }}</span>
    </a>
</template>

<script>
export default {
    name: "MediumBtn",

    props: {
        text: {
            type: String,
            default: "작은 버튼",
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
