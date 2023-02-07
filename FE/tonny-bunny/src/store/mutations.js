import temp from "./mutations/temp";
import account from "./mutations/account";
import board from "./mutations/board";
import jtonny from "./mutations/jtonny";
import ytonny from "./mutations/ytonny";
import bunny from "./mutations/bunny";
import common from "./mutations/common";
import alert from "./mutations/alert";
import mypage from "./mutations/mypage";

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
};
