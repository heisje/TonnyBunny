import temp from "./actions/temp";
import account from "./actions/account";
import board from "./actions/board";
import tonny from "./actions/tonny";
import bunny from "./actions/bunny";
import common from "./actions/common";
import alert from "./actions/alert";

export default {
    // 각 파일 만들어서 모듈화 사용
    ...temp,
    ...account,
    ...board,
    ...tonny,
    ...bunny,
    ...common,
    ...alert,
};
