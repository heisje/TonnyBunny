import http from "@/common/axios";
// import utils from "@/common/utils";

export default {
	/*
        SignUp
    */
	async isEmailOk() {},
	async isPwOk() {},
	async isNicknameOk() {},
	async isAuthCodeOk() {},
	async signUpClent() {},
	async signUpHelper() {},

	/*
        Login
    */
	async login(context, loginInfo) {
		console.log("async login !");

		let { data } = await http.post(`/login`, loginInfo);

		try {
			console.log("async login function : ", data);

			// service logic
			if (data.resultCode == "success") {
				context.commit("SET_USER_INFO", data.userResponseDto);
			} else if (data.resultCode == "fail") {
				//
			}

			return data.resultCode;
		} catch (err) {
			console.error(err);

			// exception
			// if (err.response.status == 403) {
			// 	alert("로그인 하세요");
			// }

			return data.resultCode;
		}
	},

	githubLogin() {},
	kakaoLogin() {},
	naverLogin() {},

	/*
        Find ID/PW
    */
	findUserId() {},
	findUserPw() {}
};
