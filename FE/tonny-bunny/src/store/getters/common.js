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
    /*
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
    }, */
};
