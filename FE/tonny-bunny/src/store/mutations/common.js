export default {
    /* Loading */
    SET_ISLOADING(state) {
        state.common.isLoading = !state.common.isLoading;
    },

    /* Modal */
    TOGGLE_ALARM_MODAL(state) {
        state.common.isAlarmModalOpen = !state.common.isAlarmModalOpen;
    },
    OPEN_ALARM_MODAL(state) {
        state.common.isAlarmModalOpen = true;
    },
    CLOSE_ALARM_MODAL(state) {
        state.common.isAlarmModalOpen = false;
    },
    TOGGLE_TONNY_MODAL(state) {
        state.common.isTonnyModalOpen = !state.common.isTonnyModalOpen;
    },
    TOGGLE_BUNNY_MODAL(state) {
        state.common.isBunnyModalOpen = !state.common.isBunnyModalOpen;
    },
    TOGGLE_SCHEDULE_MODAL(state) {
        state.common.isScheduleModalOpen = !state.common.isScheduleModalOpen;
    },
    OPEN_SCHEDULE_MODAL(state) {
        state.common.isScheduleModalOpen = true;
    },
    CLOSE_SCHEDULE_MODAL(state) {
        state.common.isScheduleModalOpen = false;
    },

    /* CommonCode */
    SET_USER_CODE(state, userCode) {
        state.common.userCode = userCode;
    },
    SET_LANG_CODE(state, langCode) {
        state.common.langCode = langCode;
    },
    SET_TASK_CODE(state, taskCode) {
        state.common.taskCode = taskCode;
    },
    SET_TONNY_SITU_CODE(state, tonnySituCode) {
        state.common.tonnySituCode = tonnySituCode;
    },
    SET_BUNNY_SITU_CODE(state, bunnySituCode) {
        state.common.bunnySituCode = bunnySituCode;
    },
    SET_QUOTATION_STATE_CODE(state, quotationStateCode) {
        state.common.quotationStateCode = quotationStateCode;
    },
    SET_TASK_STATE_CODE(state, taskStateCode) {
        state.common.taskStateCode = taskStateCode;
    },
    SET_POINT_LOG_STATE_CODE(state, pointLogStateCode) {
        state.common.pointLogStateCode = pointLogStateCode;
    },

    SET_ALL_CODE(state, payload) {
        state.common.allCode = payload;
    },
};
