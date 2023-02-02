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

        let { data } = await http.post(`/signin`, loginInfo);

        try {
            console.log("async login function : ", data);

            // service logic
            if (data.resultCode == "SUCCESS") {
                const { access_TOKEN, refresh_TOKEN, ...userInfo } = data.data;
                console.log(access_TOKEN);
                console.log(refresh_TOKEN);
                console.log("userInfo", userInfo);

                context.commit("SET_TOKENS", { access_TOKEN, refresh_TOKEN });
                http.defaults.headers.common["ACCESS_TOKEN"] = access_TOKEN;
                context.commit("SET_USER_INFO", userInfo);
            } else if (data.resultCode == "FAIL") {
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

    /*
		Auth Login
	*/
    githubLogin() {},
    kakaoLogin() {},
    naverLogin() {},

    /*
        Find ID/PW
    */
    findUserId() {},
    findUserPw() {},
};
