<template>
    <h1>홈 - 메인</h1>

    <div>
        통역
        <MainBtn />
        번역
        <MainBtn />
        <span>하이루</span>
        <span>하이루2</span>
        <XSmallBtn />
        <XSmallBtn />
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
    </div>

    <button-test></button-test>
</template>

<script>
import MainBtn from "@/components/home/MainBtn.vue";
import QuestList from "@/components/home/QuestList.vue";
import ScheduleList from "@/components/home/ScheduleList.vue";
import BoardList from "@/components/home/BoardList.vue";
import QuickBtn from "@/components/home/QuickBtn.vue";
import ButtonTest from "@/components/common/button/ButtonTest.vue";
import XSmallBtn from "@/components/common/button/XSmallBtn.vue";

export default {
    name: "HomePage",

    components: {
        MainBtn,
        QuestList,
        ScheduleList,
        BoardList,
        QuickBtn,
        ButtonTest,
        XSmallBtn,
    },
};
</script>
, ButtonTest

<style></style>
