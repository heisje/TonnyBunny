export default {
    /*
        Login
    */
    getIsLogin(state) {
        return state.account.isLogin;
    },
    getIsLoginModalOpen(state) {
        return state.account.isLoginModalOpen;
    },
    getUserInfo(state) {
        return state.account.userInfo;
    },
    getIsHelper(state) {
        return state.account.isHelper;
    },
};
