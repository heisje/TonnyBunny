import { createStore } from "vuex";

import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";

import temp from "./state/temp";

export default createStore({
	state: {
		// 공통 state
		counter: 10,

		// 각 파일 만들어서 모듈화 사용
		temp: temp
	},

	getters,
	mutations,
	actions
});
