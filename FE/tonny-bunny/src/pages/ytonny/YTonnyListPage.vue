<template>
    <div>
        <title-text
            title="모집 중인 예약 통역"
            text="예약통역 요청글을 자세히 보고 가능한 통역을 신청하세요!" />

        <div>필터</div>

        <quest-list />
        <quest-card :questDetail="[]" />
        <div>퀘스트리스트</div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import QuestList from "@/components/home/QuestList.vue";
import QuestCard from "@/components/common/card/QuestCard.vue";
export default {
    components: {
        TitleText,
        QuestList,
        QuestCard,
    },
};
</script>

<style></style>
