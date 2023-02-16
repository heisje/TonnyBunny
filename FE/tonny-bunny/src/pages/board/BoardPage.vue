<template>
    <div class="d-flex justify-content-center">
        <div class="customForm">
            <title-banner title="자유게시판" text="자유롭게 이야기를 나누어보아요 ^.^" />
            <div style="mwidth: 100%; margin-top: 80px; margin-bottom: 24px">
                <div style="margin-left: auto">
                    <router-link :to="{ name: 'BoardCreatePage' }">
                        <small-btn text="글쓰기" color="primary" />
                    </router-link>
                </div>
            </div>
            <br />
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
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import BoardList from "@/components/board/BoardList.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
export default {
    components: { SmallBtn, BoardList, TitleBanner },
    name: "BoardPage",

    methods: {},

    mounted() {
        this.$store.dispatch("getBoardList", 0);
    },
};
</script>

<style></style>
