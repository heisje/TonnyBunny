export default {
	/* yTonny Form */
	SET_Y_TONNY_FORM(state, payload) {
		state.ytonny.ytonnyForm = payload;
	},
	SET_Y_TONNY_SEQ(state, payload) {
		state.ytonny.yTonnySeq = payload;
	},
	SET_Y_TONNY_DETAIL(state, payload) {
		state.ytonny.yTonnyDetail = payload;
	},
	SET_Y_TONNY_APPLY_LIST(state, payload) {
		state.ytonny.yTonnyApplyList = payload;
	},
	SET_Y_TONNY_LIST(state, payload) {
		state.ytonny.yTonnyList = payload;
	}
};
