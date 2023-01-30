export default {
	/*
        Board
    */
	getBoardList(state) {
		return state.board.boardList;
	},
	getBoardTotalListCount(state) {
		return state.board.boardListTotalCount;
	},
	getBoardDetail(state) {
		return state.board.boardDetail;
	}
};
