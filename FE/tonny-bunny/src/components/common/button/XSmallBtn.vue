<template>
    <div class="btnWrap">
        <router-link :to="link" class="btn xsmall" :class="color" @click="clickBtn">
            <span :class="disable ? 'disable' : font">{{ text }}</span>
        </router-link>
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
            type: Object,
            default: () => {
                return { name: "MyPage" };
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
    methods: {
        clickBtn(e) {
            this.$emit("clickBtn", e);
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/button.scss";
</style>
