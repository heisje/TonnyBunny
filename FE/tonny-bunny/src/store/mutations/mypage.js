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
    SET_PROFILE_IMAGE_PATH(state, payload) {
        state.account.userInfo.profileImagePath = payload;

    },
};
