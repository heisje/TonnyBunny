<template>
    <div class="loginModalContainer modalContainer">
        <Transition name="bounce" v-show="isLoginModalOpen">
            <div class="loginModalWrap modalWrap">
                <div class="loginModal modalCustom">
                    <div class="closeBtn" @click="toggleLoginModalOpen">
                        <span class="material-symbols-outlined"> close </span>
                    </div>

                    <div class="title">
                        <img src="@/assets/logo3.png" alt="logo" />
                    </div>
                    <div class="form mt-3" @keyup.enter="clickLoginBtn">
                        <div class="inputs w-100">
                            <input class="input" type="email" id="email" v-model="email" />
                            <input
                                class="input password"
                                type="password"
                                id="password"
                                v-model="password" />
                        </div>
                        <div class="finds">
                            <div class="find">
                                <div>
                                    <router-link
                                        :to="{ name: 'FindIdPage' }"
                                        class="me-3"
                                        @click="toggleLoginModalOpen">
                                        아이디 찾기
                                    </router-link>
                                    <router-link
                                        :to="{ name: 'FindPwPage' }"
                                        @click="toggleLoginModalOpen">
                                        비밀번호 찾기
                                    </router-link>
                                </div>
                                <div class="signUpBtn">
                                    <router-link
                                        :to="{ name: 'SignUpPage' }"
                                        @click="toggleLoginModalOpen">
                                        회원가입
                                    </router-link>
                                </div>
                            </div>
                        </div>
                        <div class="loginBtn w-100">
                            <small-btn
                                class="w-100"
                                text="로그인"
                                color="carrot"
                                @click="clickLoginBtn"></small-btn>
                        </div>
                    </div>
                    <div v-if="false" class="auth mt-5">
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

// import MediumBtn from "../button/MediumBtn.vue";
import SmallBtn from "../button/SmallBtn.vue";

export default {
    name: "LoginModal",

    data() {
        return {
            email: "",
            password: "",
        };
    },

    components: {
        // MediumBtn,
        SmallBtn,
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

        async clickLoginBtn(e) {
            e.preventDefault();
            console.log("login btn click");
            const loginInfo = {
                email: this.email,
                password: this.password,
            };

            const resultCode = await this.$store.dispatch("login", loginInfo);

            if (resultCode == "SUCCESS") {
                this.$store.commit("TOGGLE_LOGIN_MODAL");
            }
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
        width: 50%;
        margin-bottom: 1rem;
        img {
            width: 100%;
        }
    }

    .form {
        margin: auto;
        width: 80%;
        .inputs {
            display: flex;
            flex-direction: column;
        }

        .password {
            margin-top: 0.6rem;
        }

        .finds {
            margin-top: 1rem;
            margin-bottom: 3rem;
            width: 100%;
            a {
                text-decoration: none;
                border-bottom: 1px solid var(--sub-color);
            }
            .signUpBtn {
                display: inline-block;
                margin-left: auto;
            }
            .find {
                display: flex;
            }
        }

        .loginBtn {
            margin-bottom: 8px;
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
