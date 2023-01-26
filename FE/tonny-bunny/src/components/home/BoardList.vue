<template>
    <div class="boardContainer">
        <div class="boardWrap">
            <div class="board">
                <div class="title">
                    <title-text title="커뮤니티" type="h1-small" top="0" bottom="0"></title-text>
                </div>
                <div class="item">
                    <board-list-item></board-list-item>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "../common/TitleText.vue";
import BoardListItem from "@/components/home/BoardListItem.vue";

export default {
    name: "BoardList",

    components: {
        BoardListItem,
        TitleText,
    },
};
</script>

<style></style>
