<template>
    <div class="tonnyContainer">
        <div class="tonnyWrap">
            <div class="tonnyPage">
                <div>
                    <router-link :to="{ name: 'JTonnyFormPage' }">즉시통역</router-link>
                </div>
                <div>
                    <router-link :to="{ name: 'YTonnyFormPage' }">예약통역</router-link>
                </div>
                <div>내가 한 예약통역</div>
                <div>예약통역 리스트</div>
                <div v-for="(yTonny, index) in yTonnyList" :key="index">
                    <!-- {{ yTonny }} -->
                    <router-link
                        class="d-flex"
                        :to="{
                            name: 'YTonnyDetailPage',
                            params: { id: yTonny.seq, userSeq: this.userInfo.seq },
                        }">
                        <div>{{ yTonny.title }}</div>
                        <div>{{ yTonny.content }}</div>
                        <div>{{ yTonny.startDateTime }}</div>
                        <div>{{ yTonny.estimateTime }}</div>
                        <div>{{ yTonny.estimatePrice }}</div>
                        <div>{{ yTonny.createdAt }}</div>
                    </router-link>
                </div>
                <div>히스토리</div>
                <div>찜한 헬퍼 리스트 조회</div>
                <div>오늘 일정</div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name: "TonnyPage",

    compontents: {},

    computed: {
        ...mapGetters({ yTonnyList: "getYTonnyList", userInfo: "getUserInfo" }),
    },

    created() {
        this.$store.dispatch("getYTonnyList");
    },
};
</script>

<style lang="scss" scoped></style>
