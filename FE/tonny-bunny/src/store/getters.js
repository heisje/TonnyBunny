import temp from "./getters/temp";
import account from "./getters/account";
import board from "./getters/board";
import jtonny from "./getters/jtonny";
import ytonny from "./getters/ytonny";
import bunny from "./getters/bunny";
import common from "./getters/common";
import alert from "./getters/alert";
import mypage from "./getters/mypage";
import chat from "./getters/chat";

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
