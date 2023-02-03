<template>
    <div class="modalContainer" @click="closeModal">
        <Transition name="bounce" v-show="isAlarmModalOpen">
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
                        <div v-if="btnText1" class="d-inline">
                            <small-btn
                                :color="btnColor1"
                                :font="btnFontColor1"
                                :text="btnText1"
                                @click.stop="clickBtn1"></small-btn>
                        </div>
                        <small-btn
                            :color="btnColor2"
                            :font="btnFontColor2"
                            :text="btnText2"
                            @click.stop="clickBtn2"></small-btn>
                    </div>
                </div>
            </div>
        </Transition>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
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
    },

    computed: {
        ...mapGetters({ isAlarmModalOpen: "getIsAlarmModalOpen" }),
    },

    methods: {
        clickBtn1(e) {
            e.preventDefault();
            this.$emit("clickBtn1");
        },

        clickBtn2(e) {
            e.preventDefault();
            this.$emit("clickBtn2");
        },

        closeModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
</style>
