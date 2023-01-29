import temp from "./getters/temp";
import account from "./getters/account";
import board from "./getters/board";

export default {
	// 각 파일 만들어서 모듈화 사용
	...temp,
	...account,
	...board
};
