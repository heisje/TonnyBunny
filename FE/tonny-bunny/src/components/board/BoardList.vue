<template lang="">
    <div>
        <div v-for="boardItem in boardList" :key="boardItem">
            <board-list-item :boardItem="boardItem" />
        </div>

        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li v-for="i in 5" :key="i" class="page-item">
                    <a class="page-link" @click="getBoardList(i)">{{ i }}</a>
                </li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

import BoardListItem from "@/components/common/BoardListItem.vue";
export default {
    components: { BoardListItem },

    computed: {
        ...mapGetters({ boardList: "getBoardList" }),
    },

    methods: {
        getBoardList() {
            this.$store.dispatch("getBoardList");
        },
    },
};
</script>
<style lang=""></style>
