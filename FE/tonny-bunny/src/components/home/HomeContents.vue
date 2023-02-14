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

                <hr />

                <div class="row sub">
                    <h1 class="animate__animated animate__bounce">An animated element</h1>
                    <div class="brief">
                        토니버니 서비스 간단 설명
                        <img src="@/assets/emoji/speak.png" alt="" />
                    </div>
                    <div>통역 서비스 설명</div>
                    <div>번역 서비스 설명</div>
                    <div>마무리</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";

export default {
    name: "HomeContents",

    components: {
        TitleText,
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

.brief {
    img {
        position: relative;
        top: 0;
        left: -100px;
        background-color: red;
    }
}
</style>
