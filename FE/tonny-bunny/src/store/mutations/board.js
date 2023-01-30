export default {
	/*
        Board
    */
	SET_BOARD_LIST(state, payload) {
		state.board.boardList = payload;
	},
	SET_BOARD_LIST_TOTAL_COUNT(state, payload) {
		state.board.boardListTotalCount = payload;
	},
	SET_BOARD_DETAIL(state, payload) {
		state.board.boardDetail = payload;
	}
};
