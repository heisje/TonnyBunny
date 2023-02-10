<template>
    <div class="boardContainer">
        <div class="boardWrap">
            <div class="board">
                <div class="title">
                    <title-text title="커뮤니티" type="h1-small" top="0" bottom="8"></title-text>
                    <div class="more">
                        <router-link :to="{ name: 'BoardPage' }">더보기</router-link>
                    </div>
                </div>
                <div class="item" v-for="boardItem in boardList.slice(0, 3)" :key="boardItem">
                    <board-list-item :boardItem="boardItem"></board-list-item>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import TitleText from "../common/TitleText.vue";
import BoardListItem from "@/components/common/BoardListItem.vue";

import { mapGetters } from "vuex";
export default {
    name: "BoardList",

    components: {
        BoardListItem,
        TitleText,
    },

    computed: {
        ...mapGetters({ boardList: "getBoardList" }),
    },
};
</script>

<style lang="scss" scoped>
// .boardContainer {
//     margin-left: 50px;
// }

.title {
    display: flex;
}

.more {
    display: inline-block;
    width: 40px;
    text-decoration: underline;
    cursor: pointer;

    a {
        color: var(--sub-color);
    }
}
</style>
