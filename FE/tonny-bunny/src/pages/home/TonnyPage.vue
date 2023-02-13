<template>
    <div class="tonnyContainer container">
        <title-banner
            title="Let's Tonny!"
            text="헬퍼에게 통역 서비스를 신청해볼까요?"></title-banner>
        <div class="tonnyWrap">
            <div class="tonnyPage row">
                <div class="tonnyCreate col-md-2 col-12">
                    <div class="d-flex flex-column w-100 px-2">
                        <div class="d-flex">
                            <span class="material-symbols-outlined fs-5 mb-3"> add </span>
                            <div>신청하기</div>
                        </div>
                        <router-link :to="{ name: 'JTonnyApplyPage' }" v-show="isHelper">
                            <medium-btn
                                text="즉시통역 받기"
                                class="w-100"
                                color="success"
                                font="active"></medium-btn>
                        </router-link>
                        <router-link :to="{ name: 'JTonnyFormPage' }">
                            <medium-btn
                                text="즉시통역"
                                class="w-100"
                                color="carrot"
                                font="white"></medium-btn>
                        </router-link>
                        <router-link :to="{ name: 'YTonnyFormPage' }">
                            <medium-btn
                                text="예약통역"
                                class="w-100"
                                color="carrot"
                                font="white"></medium-btn>
                        </router-link>
                    </div>
                </div>

                <div class="col-md-4 col-12">
                    <div style="min-height: 300px">
                        <title-text title="오늘 일정" top="0"></title-text>
                        <hr />
                        <v-lazy>
                            <div
                                v-for="(schedule, index) in todayScheduleList"
                                :key="index"
                                @click="test">
                                <schedule-list-item
                                    :startDateTime="schedule.startDateTime"
                                    :endDateTime="schedule.endDateTime"
                                    :text="schedule.content"
                                    today></schedule-list-item>
                            </div>
                        </v-lazy>
                    </div>
                    <div>
                        <title-text title="히스토리"></title-text>
                        <hr />

                        <div class="history">히스토리</div>
                        <v-lazy></v-lazy>
                    </div>
                </div>

                <div class="col-md-6 col-12">
                    <title-text title="모집중인 예약 통역 목록" top="0"></title-text>
                    <hr />
                    <div>정렬버튼</div>
                    <div v-for="(yTonny, index) in yTonnyList" :key="index">
                        <v-lazy
                            v-model="yTonnyList"
                            :options="{ threshold: 0.5 }"
                            transition="fade-transition">
                            <!-- {{ yTonnyList }} -->
                            <div class="contentWrap" v-show="!yTonny.isDeleted">
                                <div class="d-flex">
                                    <div class="pe-2">{{ yTonny.tonnySituCode }}</div>
                                    <router-link
                                        :to="{
                                            name: 'YTonnyDetailPage',
                                            params: { id: yTonny.seq },
                                        }">
                                        <div>{{ yTonny.title }}</div>
                                    </router-link>
                                </div>
                                <div>{{ yTonny.content }}</div>
                                <div>{{ yTonny.startDateTime }}</div>
                                <div>{{ yTonny.estimateTime }}</div>
                                <div>{{ yTonny.estimatePrice }}</div>
                                <div>{{ yTonny.createdAt }}</div>
                            </div>
                        </v-lazy>
                    </div>

                    <v-pagination
                        v-model="currentPage"
                        :length="yTonnyListTotalCount"
                        rounded="circle"
                        :total-visible="5"
                        class="mt-5"
                        prev-icon="mdi-menu-left"
                        next-icon="mdi-menu-right"
                        @click="nextPage"></v-pagination>
                </div>
            </div>
        </div>

        <schedule-modal></schedule-modal>
    </div>
</template>

<script>
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import ScheduleListItem from "@/components/common/ScheduleListItem.vue";
import ScheduleModal from "@/components/common/modal/ScheduleModal.vue";

export default {
    name: "TonnyPage",

    components: { MediumBtn, TitleText, TitleBanner, ScheduleListItem, ScheduleModal },

    data() {
        return {
            currentPage: 1,
        };
    },

    computed: {
        ...mapGetters({
            yTonnyList: "getYTonnyList",
            yTonnyListTotalCount: "getYTonnyListTotalCount",
            userInfo: "getUserInfo",
            isHelper: "getIsHelper",
            todayScheduleList: "getTodayScheduleList",
        }),
    },

    methods: {
        async nextPage() {
            await this.$store.dispatch("getYTonnyList", this.currentPage);
            window.scrollTo(0, 0);
        },

        test() {
            console.log("hihi");
            this.$store.commit("OPEN_SCHEDULE_MODAL");
        },
    },

    async created() {
        window.scrollTo(0, 0);
        await this.$store.dispatch("getYTonnyListTotalCount");
        await this.$store.dispatch("getYTonnyList", this.currentPage);
        await this.$store.dispatch("getTodayScheduleList", this.userInfo.seq);

        this.$store.commit("CLOSE_SCHEDULE_MODAL");

        console.log("userInfo:", this.userInfo);
        console.log(this.yTonnyListTotalCount);
    },
};
</script>

<style lang="scss" scoped>
.tonnyWrap {
    margin-top: 88px;
}
.tonnyPage {
    display: flex;
    // max-height: 1000px;
}

.tonnyCreate {
    display: flex;
    height: 220px;
    // background-color: var(--thin-color);
    padding: 0 10px 0 0;

    a {
        margin: 0 10px 10px 0px;
    }
}

.history {
    // max-height: 400px;
}

.contentWrap {
    padding: 24px 24px;

    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;
}

nav {
    :nth-child(1) {
        padding: 0;
    }
}
</style>
