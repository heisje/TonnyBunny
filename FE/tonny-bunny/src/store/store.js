import { createStore } from "vuex";

import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";

import temp from "./state/temp";
import account from "./state/account";
import board from "./state/board";

export default createStore({
	state: {
		// 공통 state
		//

		// 각 파일 만들어서 모듈화 사용
		temp,
		account,
		board
	},

	getters,
	mutations,
	actions
});
