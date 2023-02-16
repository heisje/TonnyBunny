<template>
    <div class="tonnyContainer container">
        <title-banner
            title="Let's Tonny !"
            text="헬퍼에게 통역 서비스를 신청해볼까요?"></title-banner>
        <div class="tonnyWrap">
            <div class="tonnyPage row">
                <div class="tonnyCreate col-md-2 col-12">
                    <div class="d-flex flex-column w-100 px-2">
                        <div class="d-flex">
                            <span class="material-symbols-outlined fs-5 mb-3"> add </span>
                            <div>신청하기</div>
                        </div>
                        <hr />

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

                <div class="col-md-5 col-12" style="padding-right: 32px">
                    <div style="min-height: 300px">
                        <title-text title="오늘 일정" top="0"></title-text>
                        <hr />
                        <div v-if="todayScheduleList.length != 0">
                            <div v-for="(schedule, index) in todayScheduleList" :key="index">
                                <v-lazy>
                                    <schedule-list-item
                                        @click="getScheduleDetail(schedule.scheduleSeq)"
                                        :startDateTime="schedule.startDateTime"
                                        :endDateTime="schedule.endDateTime"
                                        :text="schedule.content"
                                        today></schedule-list-item>
                                </v-lazy>
                            </div>
                        </div>
                        <div v-else style="color: var(--sub-color)">오늘 일정이 없습니다.</div>
                    </div>
                    <!-- <div>
                        <title-text title="히스토리"></title-text>
                        <hr />

                        <div v-if="false" class="history">히스토리</div>
                        <div v-else style="color: var(--sub-color)">히스토리 내역이 없습니다.</div>
                    </div> -->
                </div>

                <div class="col-md-5 col-12">
                    <title-text title="모집중인 예약 통역 목록" top="0"></title-text>
                    <hr />
                    <!-- <div>정렬버튼</div> -->
                    <div class="">
                        <div v-for="(yTonny, index) in yTonnyList" :key="index">
                            <div v-show="!yTonny.isDeleted">
                                <div class="yTonny d-flex justify-space-between m-0 mb-2 p-0 pb-1">
                                    <div class="d-flex">
                                        <!-- <square-mini-tag
                                        class="pe-2"
                                        :text="yTonny.estimateTime"
                                        sub></square-mini-tag> -->
                                        <!-- <square-mini-tag
                                        class="pe-2"
                                        :text="yTonny.estimatePrice + ' CRT'"
                                        white></square-mini-tag> -->

                                        <router-link
                                            class="pe-2"
                                            :to="{
                                                name: 'YTonnyDetailPage',
                                                params: { id: yTonny.seq },
                                            }">
                                            <div class="title">{{ yTonny.title }}</div>
                                        </router-link>
                                        <div class="label" style="color: var(--sub-color)">
                                            by {{ yTonny.client.nickName }}
                                        </div>
                                    </div>
                                    <!-- <div>{{ yTonny.content }}</div> -->
                                    <div class="d-flex">
                                        <square-mini-tag
                                            class="pe-2"
                                            white
                                            :text="
                                                getTonnySituCode(yTonny.tonnySituCode)
                                            "></square-mini-tag>
                                        <div class="" style="color: var(--sub-color)">
                                            {{ yTonny.startDateTime.split("T")[0] }}
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
/* eslint-disable */
import { mapGetters } from "vuex";

import MediumBtn from "@/components/common/button/MediumBtn.vue";
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import ScheduleListItem from "@/components/common/ScheduleListItem.vue";
import ScheduleModal from "@/components/common/modal/ScheduleModal.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import SquareMiniTag from "@/components/common/tag/SquareMiniTag.vue";

export default {
    name: "TonnyPage",

    components: {
        MediumBtn,
        TitleText,
        TitleBanner,
        ScheduleListItem,
        ScheduleModal,
        SquareTag,
        SquareMiniTag,
    },

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
            tonnySituCode: "getTonnySituCode",
        }),

        getTonnySituCode() {
            return (tonnySituCode) => {
                let code;
                this.tonnySituCode.forEach((e) => {
                    if (e.value == tonnySituCode) code = e.name;
                });
                return code;
            };
        },
    },

    methods: {
        async nextPage() {
            await this.$store.dispatch("getYTonnyList", this.currentPage);
            window.scrollTo(0, 0);
        },

        async getScheduleDetail(scheduleSeq) {
            await this.$store.dispatch("getScheduleDetail", scheduleSeq);
            this.$store.commit("OPEN_SCHEDULE_MODAL");
        },
    },

    async created() {
        window.scrollTo(0, 0);
        await this.$store.dispatch("getYTonnyListTotalCount");
        await this.$store.dispatch("getYTonnyList", this.currentPage);
        await this.$store.dispatch("getTodayScheduleList", this.userInfo.seq);

        this.$store.commit("CLOSE_SCHEDULE_MODAL");
    },
};
</script>

<style lang="scss" scoped>
.tonnyWrap {
    margin-top: 88px;
    margin-bottom: 200px;
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

.yTonny {
    display: flex;

    padding: 12px 0;
    border-bottom: 1px solid var(--thin-color);

    a {
        text-decoration: none;

        &:hover {
            text-decoration: underline;
            text-decoration-color: var(--active-color);
        }
    }

    .title {
        // background-color: red;
        font-size: 1rem;
    }
}
</style>
