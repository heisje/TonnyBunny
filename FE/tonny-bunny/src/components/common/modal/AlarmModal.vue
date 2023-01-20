<template>
    <div class="modalContainer">
        <div class="modalWrap">
            <div class="modal">
                <div class="titleWrap">
                    <span
                        class="material-symbols-outlined icon"
                        :class="{ danger: type === 'danger' }">
                        error
                    </span>
                    <span
                        class="material-symbols-outlined icon"
                        :class="{ warning: type === 'warning' }">
                        warning
                    </span>
                    <span
                        class="material-symbols-outlined icon"
                        :class="{ success: type === 'success' }">
                        check_circle
                    </span>

                    <h1 class="title">{{ title }}</h1>
                </div>

                <div class="contentWrap">
                    <div class="content">
                        <slot name="content"></slot>
                    </div>
                </div>

                <div class="modalBtnWrap">
                    <small-btn
                        :color="btnColor1"
                        :font="btnFontColor1"
                        :text="bntText1"
                        @click="openModal"></small-btn>
                    <small-btn
                        :color="btnColor2"
                        :font="btnFontColor2"
                        :text="bntText2"
                        @click="closeModal"></small-btn>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SmallBtn from "../button/SmallBtn.vue";

export default {
    components: {
        SmallBtn,
    },

    props: {
        title: {
            type: String,
            default: "경고",
            description: "modal title",
        },

        content: {
            type: String,
            default: "설명 라인",
            description: "content description",
        },

        type: {
            type: String,
            default: "danger",
            description: "title icon [danger, warning, success]",
        },

        bntText1: {
            type: String,
            default: "취소",
            description: "left button text",
        },

        bntText2: {
            type: String,
            default: "확인",
            description: "right button text",
        },

        btnColor1: {
            type: String,
            default: "thin",
            description: "left button color",
        },

        btnColor2: {
            type: String,
            default: "carrot",
            description: "right button color",
        },

        btnFontColor1: {
            type: String,
            default: "sub",
            description: "left button color",
        },

        btnFontColor2: {
            type: String,
            default: "white",
            description: "right button color",
        },
    },

    emits: ["isOpen"],

    setup(props, context) {
        const { emit } = context;
        const openModal = () => {
            emit("isOpen");
        };
        const closeModal = () => {};

        return { openModal, closeModal };
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
</style>
