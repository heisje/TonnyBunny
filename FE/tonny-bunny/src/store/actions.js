import temp from "./actions/temp";
import account from "./actions/account";
import board from "./actions/board";
import tonny from "./actions/tonny";
import bunny from "./actions/bunny";

export default {
    // 각 파일 만들어서 모듈화 사용
    ...temp,
    ...account,
    ...board,
    ...tonny,
    ...bunny,
};
