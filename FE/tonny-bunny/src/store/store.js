import { createStore } from "vuex";

import getters from "./getters";
import mutations from "./mutations";
import actions from "./actions";

import temp from "./state/temp";
import account from "./state/account";
import board from "./state/board";
import tonny from "./state/tonny";
import bunny from "./state/bunny";
import common from "./state/common";

export default createStore({
    state: {
        // 공통 state
        //

        // 각 파일 만들어서 모듈화 사용
        temp,
        account,
        board,
        tonny,
        bunny,
        common,
    },

    getters,
    mutations,
    actions,
});
