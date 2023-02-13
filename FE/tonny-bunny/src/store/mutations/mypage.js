export default {
    /*
        JTonny
    */
    SET_POINT_LIST(state, payload) {
        state.mypage.pointList = payload;
    },

    SET_HELPER_INFO(state, payload) {
        state.mypage.helperInfo = payload;
    },

    SET_OTHER_HELPER_INFO(state, payload) {
        state.mypage.otherHelperInfo = payload;
    },
};
