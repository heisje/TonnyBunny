<template>
    <div>
        <title-text>{{ boardDetail?.title }}</title-text>
        <h1>커뮤니티 - 게시글 상세 조회</h1>

        <div>{{ boardDetail?.title }}</div>
        <div>{{ boardDetail?.createdAt }}</div>
        <div>{{ boardDetail?.user }}</div>
        <div>{{ boardDetail?.content }}</div>

        <div>
            <div>{{ boardDetail?.boardImageList }}</div>
            {{ boardDetail?.boardCommentList }}
        </div>
        <div>
            <CommentListItem />
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import CommentListItem from "@/components/board/CommentListItem.vue";
import TitleText from "@/components/common/TitleText.vue";

export default {
    components: {
        CommentListItem,
        TitleText,
    },

    computed: {
        ...mapGetters({ boardDetail: "getBoardDetail" }),
    },

    created() {
        console.log(this.boardDetail.seq);
        this.$store.dispatch("getBoardDetail", this.boardDetail.seq);
    },
};
</script>

<style></style>
