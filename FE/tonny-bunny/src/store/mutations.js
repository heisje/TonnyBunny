import temp from "./mutations/temp";
import account from "./mutations/account";
import board from "./mutations/board";
import jtonny from "./mutations/jtonny";
import ytonny from "./mutations/ytonny";
import live from "./mutations/live";
import bunny from "./mutations/bunny";
import common from "./mutations/common";
import alert from "./mutations/alert";
import mypage from "./mutations/mypage";
import schedule from "./mutations/schedule";

export default {
    // 각 파일 만들어서 모듈화 사용
    ...temp,
    ...account,
    ...board,
    ...jtonny,
    ...ytonny,
    ...live,
    ...bunny,
    ...common,
    ...alert,
    ...mypage,
    ...schedule,
};
