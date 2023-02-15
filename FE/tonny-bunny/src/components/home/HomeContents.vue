<template>
    <v-container class="homeContentsContainer" v-scroll="onScroll">
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
                            <router-link :to="{ name: 'BunnyPage' }" class="nav-link">
                                <div class="iconsContent">
                                    <div>
                                        <img src="@/assets/book.png" alt="" />
                                    </div>
                                    <div>
                                        <h1 class="h3">#번역</h1>
                                    </div>
                                </div>
                            </router-link>
                        </div>
                        <div class="iconsWarp" v-if="isHelper">
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
                <div style="margin-top: 150px"></div>

                <div class="row sub contentAni">
                    <div class="brief4 d-flex flex-column justify-space-evenly align-items-center">
                        <img
                            src="@/assets/emoji/speak2.png"
                            class="animate__animated animate__pulse animate__slower animate__infinite"
                            alt=""
                            width="300" />
                        <div class="text-center sa">
                            이제껏 경험해보지 못했던 실시간 통역서비스
                            <br /><span>토니버니</span>와 함께라면~~ 당신의 일상이 새로워질거에요
                            <br />한번 써보삼~~
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
                            <div class="text1 saLeft">
                                <h1>통역, Tonny</h1>
                                <div>해외에서도 간편하고 빠르게!</div>
                                <div class="mb-3">토니버니의 실시간 통역 서비스</div>
                                <!-- <div>해외에서도 빠르고 안전하게!</div> -->
                                <router-link :to="{ name: 'TonnyPage' }">
                                    <medium-btn
                                        style="width: 300px"
                                        text="서비스 만나러 가기"
                                        color="outline"
                                        font="active"></medium-btn>
                                </router-link>
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
                        <!-- <img
							src="@/assets/emoji/lighthing.png"
							class="animate__animated animate__pulse animate__slower animate__infinite"
							alt=""
							width="250" /> -->
                        <!-- <h1>Let's Bunny !</h1> -->
                        <!-- <div class="briefText1 text-center">번역 서비스 설명</div> -->
                        <div
                            class="briefText2 d-flex justify-space-evenly align-items-center h-100">
                            <div class="mb-3">
                                <Vue3Lottie
                                    :animationLink="`https://assets4.lottiefiles.com/packages/lf20_puavctbl.json`"
                                    style="width: 330px"
                                    background="transparent"
                                    loop
                                    autoplay>
                                </Vue3Lottie>
                                <!-- <img class="" src="@/assets/brief1.png" width="500" /> -->
                            </div>
                            <div class="text1 saRight">
                                <h1 style="padding-left: 150px">번역, Bunny</h1>
                                <div>간단하고 깔끔한 번역이 필요하다면?</div>
                                <div class="mb-3">토니버니의 번역 서비스</div>
                                <router-link :to="{ name: 'BunnyPage' }">
                                    <medium-btn
                                        style="width: 300px"
                                        text="서비스 만나러 가기"
                                        color="outline"
                                        font="active"></medium-btn>
                                </router-link>
                            </div>
                        </div>
                    </div>

                    <div style="margin-top: 150px"></div>
                    <div class="brief1 position-relative">
                        <!-- <img
							src="@/assets/emoji/speak2.png"
							class="animate__animated animate__pulse animate__slower animate__infinite"
							alt=""
							width="250" /> -->
                        <div
                            class="briefText1 d-flex flex-column justify-content-center align-items-center">
                            <!-- <img
								class="animate__animated animate__jello"
								src="@/assets/logo2.png" /> -->
                            <div class="text1">당신의 귀여운 통∙번역가 친구 :)</div>
                            <!-- <div class="text1">TONNYBUNNY</div> -->
                            <!-- <div class="text2">실시간 즉시 통역/번역 서비스</div> -->
                            <!-- <div class="text3">당신의 귀여운 통∙번역가 친구 :)</div> -->
                        </div>
                    </div>

                    <!-- <div style="margin-top: 100px"></div> -->
                </div>
            </div>
        </div>
    </v-container>
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
        ...mapGetters({
            isLogin: "getIsLogin",
            userInfo: "getUserInfo",
            isHelper: "getIsHelper",
        }),
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

        onScroll() {
            const saElementList = document.querySelectorAll(".sa");

            for (const element of saElementList) {
                if (!element.classList.contains("animate__animated")) {
                    if (window.innerHeight + 50 > element.getBoundingClientRect().top) {
                        element.classList.add("animate__animated", "animate__fadeInDown");
                    }
                }
            }

            const saLeftElementList = document.querySelectorAll(".saLeft");

            for (const element of saLeftElementList) {
                if (!element.classList.contains("animate__animated")) {
                    if (window.innerHeight + 50 > element.getBoundingClientRect().top) {
                        element.classList.add("animate__animated", "animate__fadeInLeft");
                    }
                }
            }

            const saRightElementList = document.querySelectorAll(".saRight");

            for (const element of saRightElementList) {
                if (!element.classList.contains("animate__animated")) {
                    if (window.innerHeight + 50 > element.getBoundingClientRect().top) {
                        element.classList.add("animate__animated", "animate__fadeInRight");
                    }
                }
            }
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
    // margin-top: 100px;
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
