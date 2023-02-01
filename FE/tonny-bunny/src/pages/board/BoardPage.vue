<template>
    <div>
        <div>
            <title-text title="자유게시판" />

            <input type="text" />

            <div>정렬 버튼 라인</div>

            <div v-for="i in 3" :key="i">
                <board-list-item />
            </div>

            <div>페이지네이션</div>

            <div>게시글 작성 버튼 라인</div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import BoardListItem from "@/components/common/BoardListItem.vue";
export default {
    components: { TitleText, BoardListItem },
    name: "BoardPage",

    methods: {},

    mounted() {
        this.$store.dispatch("getBoardList");
    },
};
</script>

<style></style>
