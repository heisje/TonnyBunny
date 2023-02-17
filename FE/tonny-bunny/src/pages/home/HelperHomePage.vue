<template>
    <div>
        <title-banner title="헬퍼 페이지" text="도움을 필요한 고객을 찾아볼까요?" />
        <div class="helperHomePageContainer">
            <div class="helperHomePageWrap">
                <title-text title="도움이 필요한 고객을 찾아보세요!" center></title-text>
                <div class="iconsContainer">
                    <div class="iconsWarp">
                        <div class="iconsContent" @click="toggleBtn1">
                            <div>
                                <img src="@/assets/homeTonnyIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">통역 고객 찾기</h1>
                            </div>
                        </div>
                    </div>
                    <div class="iconsWarp">
                        <div class="iconsContent" @click="toggleBtn3">
                            <div>
                                <img src="@/assets/book.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">번역 고객 찾기</h1>
                            </div>
                        </div>
                    </div>
                </div>

                <div style="margin-top: 54px"></div>

                <helper-card
                    class="helperCard"
                    :userInfo="userInfo"
                    rightBtnText="수정하기"
                    removeClose
                    @remove-card="close"
                    @toggle-like-btn="toggleLike"
                    @click-btn2="clickBtn2"></helper-card>
            </div>
        </div>
    </div>
</template>

<script>
import HelperCard from "@/components/common/card/HelperCard.vue";
// import HomeHelperContents from "@/components/home/HomeHelperContents.vue";
import TitleText from "@/components/common/TitleText.vue";
// import ScheduleList from "@/components/home/ScheduleList.vue";
import { mapGetters } from "vuex";
import TitleBanner from "@/components/common/TitleBanner.vue";

export default {
    name: "HelperHomePage",

    components: {
        HelperCard,
        // HomeHelperContents,
        TitleText,
        TitleBanner,
        // ScheduleList,
    },

    methods: {
        toggleBtn1(e) {
            e.preventDefault();
            this.$router.push({ name: "TonnyPage" });
        },
        toggleBtn2(e) {
            e.preventDefault();
            this.$router.push({ name: "YTonnyListPage" });
        },
        toggleBtn3(e) {
            e.preventDefault();
            this.$router.push({ name: "BunnyPage" });
        },
        clickBtn2() {
            this.$router.push({
                name: "HelperChangePage",
                params: { userSeq: this.$store.state.account.userInfo.seq },
                query: { mypage: true },
            });
        },
    },

    computed: {
        ...mapGetters({ userInfo: "getUserInfo" }),
    },
};
</script>

<style lang="scss" scoped>
.helperHomePageContainer {
    width: 600px;
    margin: auto;

    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;

    .helperHomePageWrap {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding-top: 40px;

        // padding: 32px 24px;
        border: 1px solid rgba(0, 0, 0, 0.08);
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
        background-color: var(--background-color);
        border-radius: 6px;
        width: 500px;
        height: 600px;

        .helperCard {
            max-width: 400px;
        }
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
</style>
