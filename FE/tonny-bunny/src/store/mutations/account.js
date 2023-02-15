export default {
    /*
        Login
    */
    // isLoginModalOpen on/off 토글 하기
    TOGGLE_LOGIN_MODAL(state) {
        state.account.isLoginModalOpen = !state.account.isLoginModalOpen;
    },
    SET_LOG_IN(state) {
        state.account.isLogin = true;
    },

    // userInfo 정보 세팅하기
    SET_USER_INFO(state, payload) {
        const { ...userInfo } = payload;
        state.account.isLogin = true;
        state.account.userInfo = userInfo;
    },
    // token 정보 세팅하기
    SET_TOKENS(state, payload) {
        const { access_TOKEN, refresh_TOKEN } = payload;
        state.account.ACCESS_TOKEN = access_TOKEN;
        state.account.REFRESH_TOKEN = refresh_TOKEN;
    },

    SET_IS_HELPER(state, isHelper) {
        state.account.isHelper = isHelper;
    },

    // logout
    SET_LOG_OUT(state) {
        state.account.userInfo = {};
        state.account.isHelper = false;
        state.account.isLogin = false;
        state.account.ACCESS_TOKEN = "";
        state.account.REFRESH_TOKEN = "";
    },
};
