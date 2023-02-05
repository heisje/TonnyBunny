import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
    // POST /api/bunny 번역 공고 등록
    async insertBunny(context, json) {
        let res = await http.post(`/bunny`, json);

        try {
            console.log("async function : ", res);

            // service logic
            switch (res.data.resultCode) {
                case SUCCESS:
                    context.commit("SET_BUNNY_SEQ", res.data.data);
                    console.log(res.data.data);
                    // context.commit("TOGGLE_ALARM_MODAL");
                    break;
                case FAIL:
                    break;
            }
            return res.data;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return res.data;
        }
    },

    // GET /api/bunny 번역 상세 조회
    async getBunnyDetail(context, bunnySeq) {
        console.log("번역 상세 조회");

        this.dispatch("setIsLoading", true);
        try {
            let { data } = await http.get(`/bunny/${bunnySeq}`);
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("SET_BUNNY_DETAIL", data.data);
                    this.dispatch("setIsLoading", false);
                    break;
                case "FAIL":
                    this.dispatch("setIsLoading", false);
                    break;
            }
        } catch (err) {
            console.error(err);
        }
        this.dispatch("setIsLoading", false);
    },
};
