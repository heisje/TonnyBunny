<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div>
                <title-banner title="히스토리" text="이용 내역을 조회합니다" />
            </div>
            <!-- {{ historyList }} -->
            <history-list :historyList="historyList" />
        </div>
    </div>
</template>

<script>
// import SwitchTaps from "@/components/mypage/SwitchTaps.vue";

import HistoryList from "@/components/mypage/HistoryList.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import { mapGetters } from "vuex";

export default {
    name: "HelperChangePage",

    components: {
        // SwitchTaps,
        HistoryList,
        TitleBanner,
    },

    computed: {
        ...mapGetters({
            historyList: "getHistoryList",
            allCode: "getAllCode",
        }),
    },

    methods: {
        postHistoryList() {
            const clientSeq = this.$store.state.account.userInfo.seq;
            const payload = {
                clientSeq: clientSeq,
            };
            this.$store.dispatch("postHistoryList", payload);
        },
    },

    mounted() {
        this.postHistoryList();
    },
};
</script>

<style></style>
