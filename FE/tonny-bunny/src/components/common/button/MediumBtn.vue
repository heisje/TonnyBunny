<template>
    <div class="btnWrap">
        <div class="btn medium" :class="color">
            <span :class="disable ? 'disable' : font">{{ text }}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "MediumBtn",

    props: {
        text: {
            type: String,
            default: "중간 버튼",
            description: "button tag",
        },

        link: {
            type: Object,
            default: () => {
                return { name: "HomePage" };
            },
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
