<template>
    <div class="">
        <router-link :to="link" class="btn large" :class="color" @click="clickBtn">
            <span :class="disable ? 'disable' : font">{{ text }}</span>
        </router-link>
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
