<template>
    <div class="homeContentsContainer">
        <div class="homeContentsWrap">
            <div class="homeContents">
                <div class="main d-flex flex-column justify-content-center align-items-center">
                    <div class="mainDesc d-flex flex-column">
                        <title-text
                            title="토니버니만의 서비스를 이용해보세요"
                            type="h1-small"
                            top="0"
                            bottom="0"></title-text>
                        <span class="mt-1 d-flex justify-content-center align-items-center">
                            <span>Let's TonnyBunny !</span>&nbsp;
                            <img src="@/assets/emoji/sparkles.png" alt="bunny" width="20" />
                        </span>
                    </div>
                    <div class="mainBtns">
                        <main-btn></main-btn>
                        <main-btn></main-btn>
                    </div>
                </div>

                <hr />

                <div class="row sub">
                    <div class="col-12 col-lg-6">
                        <schedule-list></schedule-list>
                    </div>
                    <div class="col-12 col-lg-6">
                        <board-list></board-list>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import TitleText from "../common/TitleText.vue";
import MainBtn from "./MainBtn.vue";
import ScheduleList from "./ScheduleList.vue";
import BoardList from "./BoardList.vue";

export default {
    name: "HomeContents",

    components: {
        TitleText,
        MainBtn,
        ScheduleList,
        BoardList,
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

    .mainBtns {
        display: flex;
        justify-content: center;

        div {
        }
    }
}

.sub {
    margin-top: 100px;
}
</style>
