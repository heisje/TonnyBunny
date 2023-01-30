<template>
    <div class="loginModalContainer modalContainer">
        <Transition name="bounce" v-show="isLoginModalOpen">
            <div class="loginModalWrap modalWrap">
                <div class="loginModal modalCustom">
                    <div class="closeBtn" @click="toggleLoginModalOpen">
                        <span class="material-symbols-outlined"> close </span>
                    </div>

                    <div class="title">
                        <img src="@/assets/logo3.png" alt="logo" width="300" />
                    </div>
                    <div class="form mt-3">
                        <div class="inputs w-100">
                            <input />
                            <input />
                        </div>
                        <div class="finds mt-2">
                            <div class="find">
                                <router-link :to="{ name: 'FindIdPage' }" class="me-2">
                                    <div>아이디 찾기</div>
                                </router-link>
                                <router-link :to="{ name: 'FindPwPage' }">
                                    <div>비밀번호 찾기</div>
                                </router-link>
                            </div>
                            <div class="signUpBtn">
                                <router-link :to="{ name: 'SignUpPage' }">
                                    <div>회원가입</div>
                                </router-link>
                            </div>
                        </div>
                        <div class="loginBtn w-100 mt-3">
                            <medium-btn class="w-100" text="로그인"></medium-btn>
                        </div>
                    </div>
                    <div class="auth mt-5">
                        <div>간편 로그인</div>
                        <div class="icons mt-3">
                            <router-link :to="{ name: 'NotFoundPage' }">
                                <div>깃허브</div>
                            </router-link>
                            <router-link :to="{ name: 'NotFoundPage' }">
                                <div>네이버</div>
                            </router-link>
                            <router-link :to="{ name: 'NotFoundPage' }">
                                <div>카카오</div>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </Transition>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import MediumBtn from "../button/MediumBtn.vue";

export default {
    name: "LoginModal",

    components: {
        MediumBtn,
    },

    props: {},

    computed: {
        ...mapGetters({ isLoginModalOpen: "getIsLoginModalOpen" }),
    },

    methods: {
        toggleLoginModalOpen(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_LOGIN_MODAL");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
@import "@/scss/input.scss";
.loginModalContainer {
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

        width: 350px;
    }
    .form {
        width: 350px;
        .inputs {
            display: flex;
            flex-direction: column;
        }

        .finds {
            display: flex;
            justify-content: space-between;

            .find {
                display: flex;
            }

            .signUpBtn {
            }
        }

        .loginBtn {
            width: 350px;
        }
    }
    .auth {
        width: 350px;
        display: flex;
        flex-direction: column;
        align-items: center;

        .icons {
            display: flex;

            div {
                width: 50px;
                height: 50px;
                border-radius: 100%;
                background-color: var(--main-color);
                margin-right: 6px;
                text-align: center;
                color: white;
            }
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
