<template lang="">
    <div>
        <div v-for="boardItem in boardList" :key="boardItem">
            <board-list-item
                :boardItem="boardItem"
                @toggleDetailPage="toggleDetailPage(boardItem.seq)" />
        </div>

        <div class="text-center">
            <v-pagination
                v-model="page"
                :length="totalPages"
                circle
                @click="getBoardList"></v-pagination>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

import BoardListItem from "@/components/common/BoardListItem.vue";
export default {
    components: { BoardListItem },

    data() {
        return {
            page: 1,
        };
    },

    computed: {
        ...mapGetters({ boardList: "getBoardList" }),
        ...mapGetters({ totalPages: "getBoardTotalPages" }),
    },

    methods: {
        getBoardList() {
            this.$store.dispatch("getBoardList", this.page);
        },

        toggleDetailPage(idx) {
            this.$router.push({ name: "BoardDetailPage", params: { id: idx } });
        },
    },
};
</script>
<style lang=""></style>
