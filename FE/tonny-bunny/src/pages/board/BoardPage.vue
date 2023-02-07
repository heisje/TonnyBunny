<template>
    <div>
        <div>
            <title-text title="자유게시판" />
            <router-link :to="{ name: 'BoardCreatePage' }"><small-btn text="글쓰기" /></router-link>

            <!-- <form class="customForm">
                <input type="text" />
                <medium-btn text="검색하기" />
            </form> -->

            <!-- <small-btn text="필터" /> -->
            <board-list />
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import BoardList from "@/components/board/BoardList.vue";
export default {
    components: { TitleText, SmallBtn, BoardList },
    name: "BoardPage",

    methods: {},

    mounted() {
        this.$store.dispatch("getBoardList");
    },
};
</script>

<style></style>
