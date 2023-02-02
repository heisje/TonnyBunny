<template>
    <div>
        <board-detail-content />

        <CommentListItem />
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import CommentListItem from "@/components/board/CommentListItem.vue";
import BoardDetailContent from "@/components/board/BoardDetailContent.vue";

export default {
    components: {
        CommentListItem,
        BoardDetailContent,
    },

    computed: {
        ...mapGetters({ boardDetail: "getBoardDetail" }),
    },

    created() {
        this.$store.dispatch("getBoardDetail", this.$route.params.id);
    },
};
</script>

<style></style>
