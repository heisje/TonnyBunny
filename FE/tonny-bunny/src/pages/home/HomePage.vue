<template>
    <div>
        <h1>홈 - 메인</h1>

        <!-- part1 -->
        <!-- <tag-test></tag-test> -->
        <modal-test></modal-test>
        <!-- <button-test></button-test> -->

        <!-- part2 -->
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
import TagTest from "@/components/common/tag/TagTest.vue";
import ModalTest from "@/components/common/modal/ModalTest.vue";
import ButtonTest from "@/components/common/button/ButtonTest.vue";
// import MainBtn from "@/components/home/MainBtn.vue";
// import QuestList from "@/components/home/QuestList.vue";
// import ScheduleList from "@/components/home/ScheduleList.vue";
// import BoardList from "@/components/home/BoardList.vue";
// import QuickBtn from "@/components/home/QuickBtn.vue";
// import ButtonTest from "@/components/common/button/ButtonTest.vue";

export default {
    name: "HomePage",

    components: {
        TagTest,
        ModalTest,
        ButtonTest,
        // MainBtn,
        // QuestList,
        // ScheduleList,
        // BoardList,
        // QuickBtn,
        // ButtonTest,
    },
};
</script>

<style></style>
