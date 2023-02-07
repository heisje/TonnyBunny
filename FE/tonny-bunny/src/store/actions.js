import temp from "./actions/temp";
import account from "./actions/account";
import board from "./actions/board";
import jtonny from "./actions/jtonny";
import ytonny from "./actions/ytonny";
import bunny from "./actions/bunny";
import common from "./actions/common";
import alert from "./actions/alert";
import mypage from "./actions/mypage";
import chat from "./actions/chat";

export default {
    // 각 파일 만들어서 모듈화 사용
    ...temp,
    ...account,
    ...board,
    ...jtonny,
    ...ytonny,
    ...bunny,
    ...common,
    ...alert,
    ...mypage,
    ...chat,
};
