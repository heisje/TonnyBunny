export default {
    /*
        live
    */
    SET_START_RES_DATA(state, data) {
        state.live.startResData = data;
    },
    SET_COMPLETE_DATA(state, data) {
        state.live.completeData = data;
    },
    SET_HISTORY_SEQ(state, data) {
        state.live.historySeq = data;
    },
};
