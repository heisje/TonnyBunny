<template>
    <div class="tonnyModalContainer modalContainer" @click="closeModal">
        <Transition name="bounce" v-show="isTonnyModalOpen">
            <div class="tonnyModalWrap modalWrap">
                <div class="tonnyModal modalCustom">
                    <div class="closeBtn" @click.stop="toggleTonnyModalOpen">
                        <span class="material-symbols-outlined"> close </span>
                    </div>

                    <div class="title">
                        <title-text
                            title="어떤 통역 서비스가 필요하신가요?"
                            type="h1-small"
                            top="24"></title-text>
                        <span class="mt-1 d-flex justify-content-center align-items-center">
                            <span>Let's TonnyBunny !</span>&nbsp;
                            <img src="@/assets/emoji/sparkles.png" alt="bunny" width="20" />
                        </span>
                    </div>
                    <div class="btns d-flex mt-5">
                        <router-link
                            :to="{ name: 'JTonnyFormPage' }"
                            @click.stop="this.$store.commit('TOGGLE_TONNY_MODAL')">
                            <div class="jtonnyBtn">즉시통역</div>
                        </router-link>

                        <router-link
                            :to="{ name: 'YTonnyFormPage' }"
                            @click.stop="this.$store.commit('TOGGLE_TONNY_MODAL')">
                            <div class="ytonnyBtn">통역예약</div>
                        </router-link>
                    </div>
                </div>
            </div>
        </Transition>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
// import MainBtn from "@/components/home/MainBtn.vue";

export default {
    name: "TonnyModal",

    components: {
        TitleText,
        // MainBtn,
    },

    computed: {
        ...mapGetters({ isTonnyModalOpen: "getIsTonnyModalOpen" }),
    },

    methods: {
        toggleTonnyModalOpen(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_TONNY_MODAL");
        },

        closeModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_TONNY_MODAL");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";

.tonnyModal {
    width: 500px;

    .title {
        margin-bottom: 12px;

        & > div {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    }

    .btns {
        padding-bottom: 50px;

        display: flex;
        justify-content: center;
        align-items: center;

        a {
            text-decoration: none;
        }

        .jtonnyBtn,
        .ytonnyBtn {
            width: 150px;
            height: 100px;
            // background: var(--carrot-color);
            // border: 1px solid var(--light-color);
            border-radius: 6px;
            margin-right: 12px;
            font-weight: 500;

            display: flex;
            justify-content: center;
            align-items: center;

            font-size: 1.5rem;
            transition: all 0.13s;

            background: var(--carrot-color);
            color: white;
            // opacity: 0.8;

            &:hover {
                // opacity: 1;
                // background: var(--carrot-color);
                box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
                // background-color: var(--primary-color-20);
                // color: #fff;
                // color: var(--primary-color);

                // color: var(--carrot-color);
            }
        }
    }
}

.closeBtn {
    position: absolute;
    float: right;
    top: 12px;
    right: 12px;

    cursor: pointer;

    .material-symbols-outlined {
        font-family: "Material Symbols Outlined";
        font-variation-settings: "wght" 300;
        font-size: 2rem;
    }

    span {
        transition: all 0.13s;
        color: var(--sub-color);
    }

    &:hover {
        // opacity: 0.8;

        span {
            color: var(--main-color);
        }
    }
}
</style>
