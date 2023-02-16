<template>
    <div class="loginModalContainer modalContainer">
        <Transition name="bounce" v-show="isLoginModalOpen">
            <div class="loginModalWrap modalWrap">
                <div class="loginModal modalCustom">
                    <div class="closeBtn" @click="toggleHelperModalOpen">
                        <span class="material-symbols-outlined"> close </span>
                    </div>
                    <title-text center top="0" :title="`${helperModalInfo?.nickName}의 프로필`" />
                    <helper-profile class="helperProfile" :userInfo="helperModalInfo" />
                </div>
            </div>
        </Transition>
    </div>
</template>

<script>
import { mapGetters } from "vuex";
import HelperProfile from "../helperProfile.vue";
import TitleText from "../TitleText.vue";

export default {
    name: "HelperModal",

    data() {
        return {
            email: "",
            password: "",
        };
    },

    components: {
        HelperProfile,
        TitleText,
    },

    props: {},

    computed: {
        ...mapGetters({ isLoginModalOpen: "getIsHelperModalOpen" }),
        ...mapGetters({ helperModalInfo: "getHelperModalInfo" }),
    },

    methods: {
        toggleHelperModalOpen(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_HELPER_MODAL", {});
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
@import "@/scss/input.scss";
.loginModalContainer {
    .helperProfile {
        height: 60vh;
        overflow: scroll;
        overflow-x: hidden;
        padding: 0rem 1rem;
    }
}

.loginModalWrap {
}

.loginModal {
    padding: 60px 0 40px 0;
    display: flex;
    flex-direction: column;
    align-items: center;

    .title {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 50%;
        margin-bottom: 1rem;
        img {
            width: 100%;
        }
    }
}

.closeBtn {
    position: absolute;
    float: right;
    top: 12px;
    right: 8px;

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
