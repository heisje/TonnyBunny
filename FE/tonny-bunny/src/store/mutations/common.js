export default {
    /* Loading */
    SET_ISLOADING(state) {
        state.common.isLoading = !state.common.isLoading;
    },

    /* Modal */
    TOGGLE_ALARM_MODAL(state) {
        state.common.isAlarmModalOpen = !state.common.isAlarmModalOpen;
    },
    TOGGLE_TONNY_MODAL(state) {
        state.common.isTonnyModalOpen = !state.common.isTonnyModalOpen;
    },
    TOGGLE_BUNNY_MODAL(state) {
        state.common.isBunnyModalOpen = !state.common.isBunnyModalOpen;
    },
};
