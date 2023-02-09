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
	},
	SET_Y_TONNY_LIST_TOTAL_COUNT(state, payload) {
		let share = parseInt(payload / state.ytonny.yTonnySize);
		let left = payload % state.ytonny.yTonnySize;

		if (left == 0) state.ytonny.yTonnyListTotalCount = share;
		else state.ytonny.yTonnyListTotalCount = share + 1;
	},
	SET_Y_TONNY_APPLY_LIST_TOTAL_COUNT(state, payload) {
		let share = parseInt(payload / state.ytonny.yTonnyApplySize);
		let left = payload % state.ytonny.yTonnyApplySize;

		if (left == 0) state.ytonny.yTonnyApplyListTotalCount = share;
		else state.ytonny.yTonnyApplyListTotalCount = share + 1;
	},
	SET_Y_TONNY_APPLY_SIZE(state, payload) {
		state.ytonny.yTonnyApplySize = payload;
	}
};
