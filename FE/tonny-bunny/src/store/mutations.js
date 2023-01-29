import temp from "./mutations/temp";
import account from "./mutations/account";

export default {
	// 각 파일 만들어서 모듈화 사용
	...temp,
	...account
};
