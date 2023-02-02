export default {
    /*
        Login
    */
    // isLoginModalOpen on/off 토글 하기
    TOGGLE_LOGIN_MODAL(state) {
        state.account.isLoginModalOpen = !state.account.isLoginModalOpen;
    },
    // userInfo 정보 세팅하기
    SET_USER_INFO(state, payload) {
        console.log("state.account.userInfo", payload);
        state.account.userInfo = payload;
        this.commit("TOGGLE_LOGIN_MODAL");
        console.log("state.account.userInfo", state.account.userInfo);
    },
    // token 정보 세팅하기
    SET_TOKENS(state, payload) {
        console.log(payload);
        const { ACCESS_TOKEN, REFRESH_TOKEN } = payload;
        state.account.ACCESS_TOKEN = ACCESS_TOKEN;
        state.account.REFRESH_TOKEN = REFRESH_TOKEN;
        console.log(state.account.ACCESS_TOKEN, state.account.REFRESH_TOKEN);
    },
    // logout
    SET_LOG_OUT(state) {
        state.account.userInfo = {};
        state.account.isLogin = false;
    },
};
