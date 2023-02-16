<template lang="">
    <div>
        <div v-for="(history, index) in historyList" :key="index">
            <HistoryCard @clickRightBtn="clickRightBtn" :historyDetail="history" />
            <br /><br />
        </div>
    </div>
</template>
<script>
import HistoryCard from "../common/card/HistoryCard.vue";
import { mapGetters } from "vuex";

export default {
    components: {
        HistoryCard,
    },

    props: {
        historyList: Array,
    },

    data() {
        return {};
    },

    computed: {
        ...mapGetters({
            allCode: "getAllCode",
        }),
    },

    methods: {
        clickRightBtn() {
            this.$router.push({ name: "YTonnyDetailPage", params: { id: 1 } });
        },
    },
};
</script>
<style lang=""></style>
