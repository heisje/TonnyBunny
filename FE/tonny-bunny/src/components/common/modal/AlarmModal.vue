<template>
    <Transition name="bounce">
        <div class="modalContainer">
            <div class="modalWrap">
                <div class="modalCustom">
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

                        <h2 class="title">{{ title }}</h2>
                    </div>

                    <div class="contentWrap">
                        <div class="content">
                            <slot name="content"></slot>
                        </div>
                    </div>

                    <div class="modalBtnWrap">
                        <div v-if="btnText1">
                            <small-btn
                                :color="btnColor1"
                                :font="btnFontColor1"
                                :text="btnText1"
                                @click="closeModal"></small-btn>
                        </div>
                        <router-link :to="to">
                            <small-btn
                                :color="btnColor2"
                                :font="btnFontColor2"
                                :text="btnText2"></small-btn>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </Transition>
</template>

<script>
/* eslint-disable */
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
            default: "success",
            description: "title icon [danger, warning, success]",
        },

        btnText1: {
            type: String,
            default: "",
            description: "left button text",
        },

        btnText2: {
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

        to: {
            type: Object,
            default: { name: "HomePage" },
            description: "라우터링크to",
        },
    },

    methods: {
        closeModal(e) {
            e.preventDefault();
            this.$emit("closeModal");
        },
    },

    // emits: ["isOpen"],

    // setup(props, context) {
    // 	const { emit } = context;
    // 	const openModal = (e) => {
    // 		e.preventDefault();
    // 		// emit("isOpen");
    // 		console.log("open");
    // 	};
    // 	const closeModal = () => {};

    // 	return { openModal, closeModal };
    // }
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
</style>
