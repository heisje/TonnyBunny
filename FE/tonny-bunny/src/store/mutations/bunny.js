export default {
    /*
        NBunny
     */
    TOGGLE_BUNNY_MODAL(state) {
        state.bunny.isBunnyModalOpen = !state.bunny.isBunnyModalOpen;
    },

    SET_BUNNY_SEQ(state, data) {
        state.bunny.bunnySeq = data;
    },

    SET_BUNNY_DETAIL(state, data) {
        state.bunny.bunnyDetail = data;
    },

    SET_BUNNY_LIST(state, data) {
        state.bunny.bunnyList = data;
    },

    SET_CREATED_QUOTATION_SEQ(state, data) {
        state.bunny.createdQuotationSeq = data;
    },

    SET_HISTORY_SEQ(state, data) {
        state.bunny.historySeq = data;
    },
};
