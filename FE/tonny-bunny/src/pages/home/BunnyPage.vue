<template>
    <div class="bunnyContainer container">
        <title-banner
            title="Let's Bunny !"
            text="헬퍼에게 번역 서비스를 신청해볼까요?"></title-banner>
        <div class="bunnyWrap">
            <div class="bunnyPage row w-100">
                <div class="col-md-2 col-12">
                    <div class="d-flex flex-column w-100 px-2">
                        <div class="d-flex">
                            <span class="material-symbols-outlined fs-5 mb-3"> add </span>
                            <div>신청하기</div>
                        </div>
                        <hr />

                        <router-link :to="{ name: 'NBunnyFormPage' }">
                            <medium-btn
                                text="번역"
                                class="w-100 mb-10"
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
                    <div>
                        <title-text title="히스토리"></title-text>
                        <hr />

                        <div v-if="false" class="history">히스토리</div>
                        <div v-else style="color: var(--sub-color)">히스토리 내역이 없습니다.</div>
                    </div>
                </div>

                <div class="col-md-5 col-12">
                    <title-text title="모집중인 번역 목록" top="0"></title-text>
                    <hr />
                    <div class="d-flex flex-row w-100 mb-3">
                        <div class="col-3 me-3">
                            <DropdownInput
                                class="w-100"
                                :dropdownArray="getLangCode"
                                placeholder="언어"
                                @toggle="(e) => (lang = e)" />
                        </div>
                        <div class="col-3">
                            <DropdownInput
                                class="w-100"
                                :dropdownArray="getBunnySituCode"
                                placeholder="카테고리"
                                @toggle="(e) => (category = e)" />
                        </div>
                        <medium-btn
                            class="ms-3"
                            text="검색"
                            color="carrot"
                            @click.prevent="search" />
                    </div>
                    <div class="d-flex flex-wrap col-12">
                        <div v-for="(bunny, index) in getBunnyList" :key="index" class="mt-3 w-100">
                            <!-- {{ bunny }} -->
                            <quest-card
                                class="d-inline-block w-100 bunnyQuestCard"
                                :questDetail="bunny"
                                rightBtnText="신청하기"
                                @clickBtn2="clickHelperBtn(bunny)" />
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <schedule-modal></schedule-modal>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleBanner from "@/components/common/TitleBanner.vue";
import DropdownInput from "@/components/common/input/DropdownInputCode.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import TitleText from "@/components/common/TitleText.vue";
import QuestCard from "@/components/common/card/QuestCard.vue";
import ScheduleListItem from "@/components/common/ScheduleListItem.vue";
import ScheduleModal from "@/components/common/modal/ScheduleModal.vue";

export default {
    components: {
        TitleBanner,
        DropdownInput,
        MediumBtn,
        TitleText,
        QuestCard,
        ScheduleListItem,
        ScheduleModal,
    },

    computed: {
        ...mapGetters({
            bunnyList: "getBunnyList",
            getLangCode: "getLangCode",
            getBunnySituCode: "getBunnySituCode",
            todayScheduleList: "getTodayScheduleList",
            userInfo: "getUserInfo",
            isHelper: "getIsHelper",
        }),
    },

    data() {
        return {
            lang: "",
            category: "",
            getBunnyList: {},
        };
    },

    methods: {
        getdatas() {
            const payload = {
                lang: this.lang,
                category: this.category,
            };

            this.$store.dispatch("getBunnyList", payload).then((d) => {
                d.data.forEach((element) => {
                    element.startDateTime = element.startDateTime.slice(0, 10);
                    element.endDateTime = element.endDateTime.slice(0, 10);
                });
                this.getBunnyList = d.data;
            });
        },

        async search() {
            const payload = {
                lang: this.lang,
                category: this.category,
            };
            if (this.lang == "0020000") {
                payload.lang = "";
            }
            if (this.category == "0050003") {
                payload.category = "";
            }

            let { data } = await this.$store.dispatch("getBunnyList", payload);
            this.getBunnyList = data;
        },

        async clickHelperBtn(bunny) {
            this.$store.state.bunny.bunnyDetail = bunny;
            this.$router.push({ name: "NBunnyMatchingPage" });
        },

        async getScheduleDetail(scheduleSeq) {
            await this.$store.dispatch("getScheduleDetail", scheduleSeq);
            this.$store.commit("OPEN_SCHEDULE_MODAL");
        },
    },

    async created() {
        this.getdatas();
        window.scrollTo(0, 0);
        await this.$store.dispatch("getTodayScheduleList", this.userInfo.seq);
        this.$store.commit("CLOSE_SCHEDULE_MODAL");
    },
};
</script>

<style lang="scss" scoped>
.bunnyWrap {
    margin-top: 88px;
    margin-bottom: 200px;
}

.bunnyQuestCard {
    :nth-child(1) {
        :nth-child(1) .questCard {
            width: 100%;
        }
    }
}
</style>
