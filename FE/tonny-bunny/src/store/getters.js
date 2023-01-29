import temp from "./getters/temp";
import account from "./getters/account";

export default {
	// 각 파일 만들어서 모듈화 사용
	...temp,
	...account
};
