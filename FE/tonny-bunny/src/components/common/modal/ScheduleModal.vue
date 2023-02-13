<template>
    <div class="modalContainer" @click="closeModal">
        <Transition name="bounce" v-show="isScheduleModalOpen">
            <div class="modalWrap">
                <div class="modalCustom">
                    <h1>일정</h1>
                    <div class="scheduleContent" v-show="scheduleDetail">
                        <div class="d-flex mt-5">
                            <div class="me-1">
                                <square-tag :text="getTaskCode" sub></square-tag>
                            </div>
                            <div>
                                <square-tag
                                    v-if="!scheduleDetail.isCompleted"
                                    text="진행중"
                                    info></square-tag>
                                <square-tag v-else text="완료됨" white></square-tag>
                            </div>
                        </div>
                        <div class="mt-5 d-flex">
                            <div class="fs-5">{{ startDate }} {{ startTime }}</div>
                            &nbsp; ~ &nbsp;
                            <div class="fs-5">{{ endDate }} {{ endTime }}</div>
                        </div>
                        <div class="mt-5">{{ scheduleDetail.content }}</div>
                    </div>
                </div>
            </div>
        </Transition>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import utils from "@/common/utils";
import SquareTag from "../tag/SquareTag.vue";

export default {
    components: { SquareTag },
    computed: {
        ...mapGetters({
            isScheduleModalOpen: "getIsScheduleModalOpen",
            scheduleDetail: "getScheduleDetail",
            taskCode: "getTaskCode",
        }),

        getTaskCode() {
            let code;
            this.taskCode.forEach((e) => {
                if (e.value == this.scheduleDetail.taskCode) code = e.name;
            });
            return code;
        },
        startDate() {
            let date = utils.dateSplit(this.scheduleDetail.startDateTime);
            return date.year + "." + date.month + "." + date.day;
        },
        startTime() {
            let time = utils.dateTimeSplit(this.scheduleDetail.startDateTime);
            return time.hour + ":" + time.minute;
        },
        endDate() {
            let date = utils.dateSplit(this.scheduleDetail.endDateTime);
            return date.year + "." + date.month + "." + date.day;
        },
        endTime() {
            let time = utils.dateTimeSplit(this.scheduleDetail.endDateTime);
            return time.hour + ":" + time.minute;
        },
    },

    methods: {
        closeModal(e) {
            e.preventDefault();
            this.$store.commit("CLOSE_SCHEDULE_MODAL");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";

.scheduleContent {
}
</style>
