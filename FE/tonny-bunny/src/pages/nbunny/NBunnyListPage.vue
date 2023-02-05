<template>
    <div>번역 요청 - 의뢰 목록</div>
    <div>상단정보</div>
    <div>필터</div>
    <div>퀘스트카드리스트</div>

    <div v-for="(bunny, index) in getBunnyList" :key="index">
        {{ buuny }}

        <br />
        <br />
    </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    data() {
        return {
            lang: null,
            category: null,
        };
    },

    computed: {
        ...mapGetters({ getBunnyList: "getBunnyList" }),
    },

    created() {
        const payload = {
            lang: this.lang,
            category: this.category,
        };
        this.$store.dispatch("getBunnyList", payload);
    },
};
</script>

<style></style>
