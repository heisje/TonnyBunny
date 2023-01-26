export default {
    /*
		[대문자 작성]
		INT, SET.. + (data 명)
	*/
    INIT_TEMP_LIST(state) {
        state.temp.limit = 5;
        state.temp.offset = 5;
    },

    SET_TEMP_LIST(state, payload) {
        state.temp.tempList = payload.tempList;
    },
};
