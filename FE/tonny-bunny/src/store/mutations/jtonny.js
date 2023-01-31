export default {
    /*
        JTonny
    */
    SET_JTONNY_LIST(state, payload) {
        state.jtonny.jtonnyList = payload;
    },
    SET_JTONNY_LIST_TOTAL_COUNT(state, payload) {
        state.jtonny.jtonnyListTotalCount = payload;
    },
    SET_JTONNY_DETAIL(state, payload) {
        state.jtonny.jtonnyDetail = payload;
    },
};
