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

    /* Code */
    getLangCodeList(state) {
        return state.common.langCodeList;
    },
    getTaskCodeList(state) {
        return state.common.taskCodeList;
    },
    getTonnySituCodeList(state) {
        return state.common.tonnySituCodeList;
    },
    getQuotationStateCodeList(state) {
        return state.common.quotationStateCodeList;
    },
    getTaskStateCodeList(state) {
        return state.common.taskStateCodeList;
    },
    getHourCodeList(state) {
        return state.common.hourCodeList;
    },
    getMinuteCodeList(state) {
        return state.common.minuteCodeList;
    },
    getLangCode(state) {
        return state.common.langCode;
    },
    getBunnySituCode(state) {
        return state.common.bunnySituCode;
    },
};
