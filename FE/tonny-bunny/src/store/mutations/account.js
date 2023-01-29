export default {
	// isLoginModalOpen on/off 토글 하기
	TOGGLE_LOGIN_MODAL(state) {
		state.account.isLoginModalOpen = !state.account.isLoginModalOpen;
	}
};
