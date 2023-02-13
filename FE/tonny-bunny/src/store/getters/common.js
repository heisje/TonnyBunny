export default {
    /* Loading */
    getIsLoading(state) {
        return state.common.isLoading;
    },

    /* Modal */
    getIsAlarmModalOpen(state) {
        return state.common.isAlarmModalOpen;
    },
    getIsTonnyModalOpen(state) {
        return state.common.isTonnyModalOpen;
    },
    getIsBunnyModalOpen(state) {
        return state.common.isBunnyModalOpen;
    },
    getIsScheduleModalOpen(state) {
        return state.common.isScheduleModalOpen;
    },

    /* Code */
    getAllCode(state) {
        return state.common.allCode;
    },

    getUserCode(state) {
        return state.common.userCode;
    },
    getLangCode(state) {
        return state.common.langCode;
    },
    getTaskCode(state) {
        return state.common.taskCode;
    },
    getTonnySituCode(state) {
        return state.common.tonnySituCode;
    },
    getBunnySituCode(state) {
        return state.common.bunnySituCode;
    },
    getBunnyStateCode(state) {
        return state.common.bunnyStateCode;
    },
    getQuotationStateCode(state) {
        return state.common.quotationStateCode;
    },
    getTaskStateCode(state) {
        return state.common.taskStateCode;
    },
    getPointLogStateCode(state) {
        return state.common.pointLogStateCode;
    },
    getHourCodeList(state) {
        return state.common.hourCodeList;
    },
    getMinuteCodeList(state) {
        return state.common.minuteCodeList;
    },
};
