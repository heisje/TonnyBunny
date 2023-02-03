<template>
    <div class="homeContentsContainer">
        <div class="homeContentsWrap">
            <div class="homeContents">
                <div class="main d-flex flex-column justify-content-center align-items-center">
                    <div class="mainDesc d-flex flex-column">
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
                    <div class="iconsContainer">
                        <div class="iconsWarp">
                            <div class="iconsContent" @click="toggleTonnyModalOpen">
                                <div>
                                    <img src="@/assets/homeTonnyIcon.png" alt="" />
                                </div>
                                <div>
                                    <h1 class="h1-small">#통역</h1>
                                </div>
                            </div>
                        </div>
                        <div class="iconsWarp">
                            <div class="iconsContent" @click="toggleBunnyModalOpen">
                                <div>
                                    <img src="@/assets/homeBunnyIcon.png" alt="" />
                                </div>
                                <div>
                                    <h1 class="h1-small">#번역</h1>
                                </div>
                            </div>
                        </div>
                        <div class="iconsWarp">
                            <div class="iconsContent" @click="toggleBunnyModalOpen">
                                <div>
                                    <img src="@/assets/homeBunnyIcon.png" alt="" />
                                </div>
                                <div>
                                    <h1 class="h1-small">#헬퍼 전용 페이지</h1>
                                </div>
                            </div>
                        </div>
                    </div>
                    <title-text
                        title="헬퍼전용 페이지"
                        type="h1"
                        top="60"
                        bottom="20"
                        center></title-text>
                    <div class="mainBtns">
                        <!-- <main-btn text="통역" @click="toggleTonnyModalOpen">
                            <template #icon>
                                <span class="material-symbols-outlined"> interpreter_mode </span>
                            </template>
                        </main-btn>
                        <main-btn text="번역" @click="toggleBunnyModalOpen">
                            <template #icon>
                                <span class="material-symbols-outlined"> g_translate </span>
                            </template>
                        </main-btn> -->
                        <main-btn text="헬퍼페이지" @click="toggleHelperBtn">
                            <template #icon>
                                <span class="material-symbols-outlined"> g_translate </span>
                            </template>
                        </main-btn>
                    </div>
                </div>

                <hr />

                <div class="row sub">
                    <div class="col-12 col-lg-6">
                        <home-schedule-list></home-schedule-list>
                    </div>
                    <div class="col-12 col-lg-6">
                        <home-board-list></home-board-list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import MainBtn from "./MainBtn.vue";
import HomeScheduleList from "./HomeScheduleList.vue";
import HomeBoardList from "./HomeBoardList.vue";

export default {
    name: "HomeContents",

    components: {
        TitleText,
        MainBtn,
        HomeScheduleList,
        HomeBoardList,
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
        justify-content: space-evenly;
        width: 100%;

        .iconsWarp {
            position: relative;
            width: 40%;
            max-width: 200px;
            padding-bottom: 40%;
            .iconsContent {
                position: absolute;
                display: flex;
                width: 100%;
                height: 100%;

                max-height: 200px;
                flex-direction: column;
                justify-content: center;
                border-radius: 10%;
                border: 1px solid var(--light-color);
                cursor: pointer;
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
