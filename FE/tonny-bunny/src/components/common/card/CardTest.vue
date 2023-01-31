<template>
    <hr />
    <helper-card
        nickName="아스파라거스"
        oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
        fareText="2000"
        starText="4.0"
        countText="100"
        likeText="10"
        isLikeEmpty
        rightBtnText="상담하기"
        @remove-card="close"
        @toggle-like-btn="toggleLike"
        @click-btn1="check1"
        @click-btn2="check2"></helper-card>
    <helper-card></helper-card>
    <helper-card></helper-card>
    <hr />

    <client-card></client-card>

    <quest-card :questDetail="questDetail" @click-btn1="check1" @click-btn2="check2"></quest-card>
    <quest-card :questDetail="questDetail" @click-btn1="check1" @click-btn2="check2"></quest-card>
</template>

<script>
import ClientCard from "./ClientCard.vue";
import HelperCard from "./HelperCard.vue";
import QuestCard from "./QuestCard.vue";

export default {
    name: "CardTest",

    components: {
        ClientCard,
        HelperCard,
        QuestCard,
    },

    data() {
        return {
            questDetail: {
                clientNickname: "hi",
            },
        };
    },

    methods: {
        close() {
            console.log("close btn click");
        },

        toggleLike() {
            console.log("toggle like btn");
        },

        check1() {
            console.log("left btn click");
        },

        check2() {
            console.log("right btn click");
        },
    },
};
</script>

<style></style>
