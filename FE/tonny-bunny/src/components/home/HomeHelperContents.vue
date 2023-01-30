<template lang="">
    <div class="homeContentsContainer">
        <div class="homeContentsWrap">
            <div class="homeContents">
                <title-text title="🐰도움이 필요한 고객을 찾아보세요!"></title-text>
                <main-btn text="즉시통역찾기" @click="toggleBtn1">
                    <template #icon>
                        <span class="material-symbols-outlined"> interpreter_mode </span>
                    </template>
                </main-btn>
                <main-btn text="예약통역찾기" @click="toggleBtn2">
                    <template #icon>
                        <span class="material-symbols-outlined"> interpreter_mode </span>
                    </template>
                </main-btn>
                <main-btn text="번역요청찾기" @click="toggleBtn3">
                    <template #icon>
                        <span class="material-symbols-outlined"> interpreter_mode </span>
                    </template>
                </main-btn>
            </div>
        </div>

        <div class="row sub">
            <div class="col-12 col-lg-6">
                <home-schedule-list></home-schedule-list>
            </div>
        </div>
        <div class="row sub">
            <div class="col-12 col-lg-6">
                <TitleText title="내 의뢰" type="h1-small" />
                <router-link :to="{ name: 'HistoryPage' }">더보기</router-link>
                <QuestCard questDetail="[]" />
            </div>
        </div>
    </div>
</template>
<script>
import TitleText from "@/components/common/TitleText.vue";
import MainBtn from "@/components/home/MainBtn.vue";
import HomeScheduleList from "./HomeScheduleList.vue";
import QuestCard from "../common/card/QuestCard.vue";

export default {
    components: {
        TitleText,
        MainBtn,
        HomeScheduleList,
        QuestCard,
    },

    methods: {
        toggleBtn1(e) {
            e.preventDefault();
            this.$router.push({ name: "JTonnyAcceptPage" });
        },
        toggleBtn2(e) {
            e.preventDefault();
            this.$router.push({ name: "YTonnyListPage" });
        },
        toggleBtn3(e) {
            e.preventDefault();
            this.$router.push({ name: "NBunnyListPage" });
        },
    },
};
</script>

<style lang="scss" scoped>
.homeContentsContainer {
    margin-top: 32px;
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
