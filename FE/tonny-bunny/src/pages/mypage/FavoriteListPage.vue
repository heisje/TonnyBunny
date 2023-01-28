<template>
    <h1>마이페이지 - 즐겨찾기</h1>
    긴 유저 막대바 나열
    <div>
        <input type="text" />
    </div>
    <div v-for="i in 10" :key="i">
        <helper-card />
    </div>
</template>

<script>
import HelperCard from "@/components/common/card/HelperCard.vue";
export default {
    name: "FavoriteListPage",

    components: {
        HelperCard,
    },
};
</script>

<style></style>
