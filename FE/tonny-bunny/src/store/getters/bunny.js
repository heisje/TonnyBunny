export default {
    /*
        NBunny
    */
    getIsBunnyModalOpen(state) {
        return state.bunny.isBunnyModalOpen;
    },
    getBunnyDetail(state) {
        return state.bunny.bunnyDetail;
    },
    getBunnyList(state) {
        return state.bunny.bunnyList;
    },
    getBunnyStateCode(state) {
        return state.bunny.getBunnyStateCode;
    },
    getHistorySeq(state) {
        return state.bunny.historySeq;
    },
};
