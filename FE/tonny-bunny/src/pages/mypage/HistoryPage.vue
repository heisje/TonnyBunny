<template>
    <h1>마이페이지 - 이용내역</h1>
    <div v-if="isHelper === true">
        <SwitchTaps />
    </div>

    <div>
        <div>
            <div></div>
        </div>
    </div>
    <button>필터버튼</button>
    필터선택
    <select name="" id="">
        전체
    </select>
    <option value="">신청중</option>
    <option value="">진행중</option>
    <option value="">완료됨</option>
    <history-list />
</template>

<script>
import SwitchTaps from "@/components/mypage/SwitchTaps.vue";

import HistoryList from "@/components/mypage/HistoryList.vue";

export default {
    name: "HelperChangePage",

    components: {
        SwitchTaps,
        HistoryList,
    },

    methods: {
        getHistoryList() {
            const clientSeq = this.$store.state.account.userInfo.seq;
            const payload = {
                clientSeq: clientSeq,
                // endDateTime: "2023-02-07T07:02:06.723Z",
                // helperSeq: 0,
                // langCode: "string",
                // notiSeq: 0,
                orderByCreatedAtAsc: true,
                // startDateTime: "2023-02-07T07:02:06.723Z",
                taskCode: "string",
            };
            this.$store.dispatch("getHistoryList", payload);
        },
    },

    mounted() {
        this.getHistoryList();
    },
};
</script>

<style></style>
