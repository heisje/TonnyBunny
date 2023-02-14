<template>
    <div class="homeContentsContainer">
        <div class="homeContentsWrap">
            <div class="homeContents">
                <div class="main d-flex flex-column justify-content-center align-items-center">
                    <div class="mainDesc d-flex flex-column animate__animated animate__fadeInDown">
                        <title-text
                            title="토니버니의 서비스를 이용해보세요"
                            type="h1"
                            top="0"
                            bottom="0"></title-text>
                        <span class="mt-1 d-flex justify-content-center align-items-center">
                            <span>Let's TonnyBunny !</span>&nbsp;
                            <img src="@/assets/emoji/sparkles.png" alt="bunny" width="20" />
                        </span>
                    </div>

                    <div class="iconsContainer animate__animated animate__fadeInDown">
                        <div class="iconsWarp">
                            <!-- <div class="iconsContent" @click="toggleTonnyModalOpen">
								<div>
									<img src="@/assets/homeTonnyIcon.png" alt="" />
								</div>
								<div>
									<h1 class="h3">#통역</h1>
								</div>
							</div> -->
                            <router-link :to="{ name: 'TonnyPage' }" class="nav-link">
                                <div class="iconsContent">
                                    <div>
                                        <img src="@/assets/roket.png" alt="" />
                                    </div>
                                    <div>
                                        <h1 class="h3">#통역</h1>
                                    </div>
                                </div>
                            </router-link>
                        </div>
                        <div class="iconsWarp">
                            <div class="iconsContent" @click="toggleBunnyModalOpen">
                                <div>
                                    <img src="@/assets/book.png" alt="" />
                                </div>
                                <div>
                                    <h1 class="h3">#번역</h1>
                                </div>
                            </div>
                        </div>
                        <div class="iconsWarp" v-if="true">
                            <div class="iconsContent searchIcon" @click="toggleHelperBtn">
                                <div>
                                    <img src="@/assets/bag.png" alt="" />
                                </div>
                                <div>
                                    <h1 class="h3">#헬퍼 페이지</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- <hr /> -->
                <div style="margin-top: 200px"></div>

                <div class="row sub contentAni">
                    <div class="brief1 position-relative">
                        <img
                            src="@/assets/emoji/speak2.png"
                            class="animate__animated animate__pulse animate__slower animate__infinite"
                            alt=""
                            width="300" />
                        <div class="briefText1">
                            <img
                                class=""
                                style="margin-top: 100px"
                                src="@/assets/logo2_white.png" />
                            <div class="">토니버니란?</div>
                            <div class="">실시간 즉시 통역/번역 서비스</div>
                        </div>
                    </div>

                    <div class="brief2 position-relative">
                        <img
                            src="@/assets/emoji/thumbsup2.png"
                            class="animate__animated animate__pulse animate__slower animate__infinite"
                            alt=""
                            width="300" />
                        <div
                            class="briefText1 d-flex justify-space-evenly align-items-center h-100">
                            <div class="text1">
                                <h1>통역, TONNY</h1>
                                <div>해외에서도 간편하고 빠르게!</div>
                                <div class="mb-3">토니버니의 실시간 통역 서비스</div>
                                <!-- <div>해외에서도 빠르고 안전하게!</div> -->
                                <medium-btn
                                    style="width: 300px"
                                    text="서비스 만나러 가기"
                                    color="outline"
                                    font="active"></medium-btn>
                            </div>
                            <div>
                                <Vue3Lottie
                                    :animationLink="`https://assets7.lottiefiles.com/packages/lf20_ihspk1vg.json`"
                                    style="width: 350px"
                                    background="transparent"
                                    loop
                                    autoplay>
                                </Vue3Lottie>
                                <!-- <img class="briefImg1" src="@/assets/brief1.png" width="500" /> -->
                            </div>

                            <!-- <div class="briefText1">즉시통역</div> -->
                            <!-- <div class="briefText2">예약통역</div> -->
                        </div>
                    </div>

                    <div class="brief3 position-relative">
                        <img
                            src="@/assets/emoji/lighthing.png"
                            class="animate__animated animate__pulse animate__slower animate__infinite"
                            alt=""
                            width="300" />
                        <!-- <h1>Let's Bunny !</h1> -->
                        <!-- <div class="briefText1 text-center">번역 서비스 설명</div> -->
                        <div
                            class="briefText2 d-flex justify-space-evenly align-items-center h-100">
                            <div class="mb-3">
                                <Vue3Lottie
                                    :animationLink="`https://assets4.lottiefiles.com/packages/lf20_puavctbl.json`"
                                    style="width: 350px"
                                    background="transparent"
                                    loop
                                    autoplay>
                                </Vue3Lottie>
                                <!-- <img class="" src="@/assets/brief1.png" width="500" /> -->
                            </div>
                            <div class="text1">
                                <h1 style="padding-left: 150px">번역, Bunny</h1>
                                <div>간단하고 깔끔한 번역이 필요하다면?</div>
                                <div class="mb-3">토니버니의 번역 서비스</div>
                                <medium-btn
                                    style="width: 300px"
                                    text="서비스 만나러 가기"
                                    color="outline"
                                    font="active"></medium-btn>
                            </div>
                        </div>
                    </div>
                    <div class="brief4">마무리</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "../common/button/SmallBtn.vue";
import MediumBtn from "../common/button/MediumBtn.vue";

export default {
    name: "HomeContents",

    components: {
        TitleText,
        SmallBtn,
        MediumBtn,
    },

    computed: {
        ...mapGetters({ isLogin: "getIsLogin", userInfo: "getUserInfo" }),
    },

    methods: {
        toggleTonnyModalOpen(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_TONNY_MODAL");
        },

        toggleBunnyModalOpen(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_BUNNY_MODAL");
        },

        toggleHelperBtn(e) {
            e.preventDefault();
            this.$router.push({ name: "HelperHomePage" });
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/home.scss";

.homeContentsContainer {
    margin-top: 500px;
}
.main {
    margin-top: 100px;
    margin-bottom: 100px;

    .mainDesc {
        margin-bottom: 54px;
        // padding: 40px 0;
        cursor: default;

        span {
            text-align: center;
        }

        span:nth-child(1) {
            font-size: 1.6rem;
            font-weight: 700;
        }
        span > span:nth-child(1) {
            font-size: 1rem;
            font-weight: 400;
            color: var(--sub-color);
        }
    }
    .iconsContainer {
        display: flex;
        justify-content: center;
        width: 100%;

        .iconsWarp {
            position: relative;
            width: 30%;
            max-width: 150px;
            padding-bottom: #{"min(150px, 30%)"};
            margin: 2%;

            .iconsContent {
                position: absolute;
                display: flex;
                width: 100%;
                height: 100%;

                max-height: 150px;
                flex-direction: column;
                justify-content: center;
                border-radius: 10%;
                border: 1px solid var(--light-color);
                cursor: pointer;

                transition: all 0.13s;
                &:hover {
                    background-color: var(--primary-color-20);
                    h1 {
                        margin-top: 10px;
                        width: 100%;
                        text-align: center;
                        color: var(--primary-color);
                    }
                }
                div {
                    width: 100%;
                    display: flex;
                }
                img {
                    display: inline-block;
                    margin: auto;
                    width: 50%;
                }
                h1 {
                    margin-top: 10px;
                    width: 100%;
                    text-align: center;
                }
                &.searchIcon {
                    background: var(--carrot-color);
                    color: var(--white-color);
                    h1 {
                        color: var(--white-color);
                        font-weight: 400;
                        letter-spacing: -0.05rem;
                    }
                    &:hover {
                        background: var(--primary-color-50);
                        h1 {
                            color: var(--primary-color);
                        }
                    }
                }
            }
        }
    }
    .mainBtns {
        display: flex;
        justify-content: center;
        justify-content: space-evenly;
        width: 60%;

        div {
            margin-right: 12px;
        }
    }
}

.sub {
    margin-top: 100px;
}
</style>
