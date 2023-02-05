<template>
    <div class="bunnyModalContainer modalContainer" @click="closeModal">
        <Transition name="bounce" v-show="isBunnyModalOpen">
            <div class="bunnyModalWrap modalWrap">
                <div class="bunnyModal modalCustom">
                    <div class="closeBtn" @click.stop="toggleBunnyModalOpen">
                        <span class="material-symbols-outlined"> close </span>
                    </div>

                    <div class="title">
                        <title-text
                            title="어떤 번역 서비스가 필요하신가요?"
                            type="h1-small"
                            top="24"></title-text>
                        <span class="mt-1 d-flex justify-content-center align-items-center">
                            <span>Let's TonnyBunny !</span>&nbsp;
                            <img src="@/assets/emoji/sparkles.png" alt="bunny" width="20" />
                        </span>
                    </div>
                    <div class="btns d-flex mt-5">
                        <router-link
                            :to="{ name: 'NBunnyFormPage' }"
                            @click.stop="this.$store.commit('TOGGLE_BUNNY_MODAL')">
                            <div class="nbunnyBtn">번역의뢰</div>
                        </router-link>

                        <router-link :to="{ name: 'YTonnyFormPage' }">
                            <div class="ytonnyBtn">번역상담</div>
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

export default {
    name: "BunnyModal",

    components: {
        TitleText,
    },

    computed: {
        ...mapGetters({ isBunnyModalOpen: "getIsBunnyModalOpen" }),
    },

    methods: {
        toggleBunnyModalOpen(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_BUNNY_MODAL");
        },

        closeModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_BUNNY_MODAL");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";

.bunnyModal {
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

        .nbunnyBtn {
            width: 150px;
            height: 100px;
            background-color: var(--light-color);
            border-radius: 6px;
            margin-right: 12px;
            font-weight: 500;

            display: flex;
            justify-content: center;
            align-items: center;

            font-size: 1.5rem;
            transition: all 0.13s;

            &:hover {
                box-shadow: 1px 1px 5px rgba(0, 0, 0, 0.3);
                background: var(--carrot-color);
                color: #fff;
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
