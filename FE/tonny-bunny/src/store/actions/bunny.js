import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
    async insertBunny(context, json) {
        console.log("예약통역 공고 생성");

        let res = await http.post(`/bunny`, json);

        try {
            console.log("async function : ", res);

            // service logic
            switch (res.resultCode) {
                case SUCCESS:
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
};
