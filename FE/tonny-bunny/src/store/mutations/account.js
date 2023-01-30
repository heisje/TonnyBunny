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
		state.account.userInfo = payload;
	},
	// logout
	SET_LOG_OUT(state) {
		state.account.userInfo = {};
		state.account.isLogin = false;
	}
};
