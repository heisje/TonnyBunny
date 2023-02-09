<template>
    <div>
        <home-banner></home-banner>
        <home-contents></home-contents>
        <!-- <title-banner
            title="🐰 실시간 동시 통역"
            text="실시간으로 필요한 통역 서비스를 신청합니다" />

        <title-text important type="h1" title="Default Title" text="Description" bottom="12" />
        <title-text title="Default Title" text="Description" />
        -->
        <!-- <h1>홈 - 메인</h1> -->

        <!-- part1 -->
        <!-- <tag-test></tag-test> -->
        <!-- <hr /> -->
        <!-- <modal-test></modal-test> -->
        <!-- <hr /> -->
        <!-- <button-test></button-test> -->
        <!-- <hr /> -->
        <!-- <card-test></card-test> -->
        <!-- <hr /> -->

        <!-- part2 -->
        <!-- <input-test /> -->
        <!-- <tag-test></tag-test> -->
    </div>

    <!-- <div>
        통역
        <MainBtn />
        번역
        <MainBtn />
    </div>

    <div v-if="isHelper === true">
        헬퍼페이지
        <MainBtn />
    </div>
    <div v-if="isHelper === true">
        나의 의뢰
        <QuestList />
    </div>
    <div>
        오늘 일정
        <ScheduleList />
    </div>
    <div>
        커뮤니티
        <BoardList />
    </div>
    <div>
        즉시대기
        <QuickBtn />
        헬퍼페이지
        <QuickBtn />
    </div> -->

    <!-- <button-test></button-test> -->
</template>

<script>
/* eslint-disable */
import HomeBanner from "@/components/home/HomeBanner.vue";
import HomeContents from "@/components/home/HomeContents.vue";
import LoginModal from "@/components/common/modal/LoginModal.vue";

// import TagTest from "@/components/common/tag/TagTest.vue";
import ModalTest from "@/components/common/modal/ModalTest.vue";
// import ButtonTest from "@/components/common/button/ButtonTest.vue";
// import InputTest from "@/components/common/input/InputTest.vue";
import CardTest from "@/components/common/card/CardTest.vue";
// import TitleBanner from "@/components/common/TitleBanner.vue";
// import TitleText from "@/components/common/TitleText.vue";
// import MainBtn from "@/components/home/MainBtn.vue";
// import QuestList from "@/components/home/QuestList.vue";
// import ScheduleList from "@/components/home/ScheduleList.vue";
// import BoardList from "@/components/home/BoardList.vue";
// import QuickBtn from "@/components/home/QuickBtn.vue";
// import ButtonTest from "@/components/common/button/ButtonTest.vue";

export default {
    name: "HomePage",

    components: {
        HomeBanner,
        HomeContents,
        LoginModal,

        // TagTest,
        ModalTest,
        // ButtonTest,
        // InputTest,
        CardTest,
        // TitleBanner,
        // TitleText,
        // MainBtn,
        // QuestList,
        // ScheduleList,
        // BoardList,
        // QuickBtn,
        // ButtonTest,
    },
};
</script>

<style lang="scss" scoped></style>
