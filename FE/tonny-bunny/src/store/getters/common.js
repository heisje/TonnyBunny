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
    getLangCode(state) {
        return state.common.langCode;
    },
    getBunnySituCode(state) {
        return state.common.bunnySituCode;
    },
};
