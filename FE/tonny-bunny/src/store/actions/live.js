import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
    /* 
        live complete
    */

    // POST /api/live/complete 고객의 라이브 종료 후 히스토리 저장 요청
    async completeLive(context, payload) {
        console.log("라이브 완료");

        let { data } = await http.post(`/live/complete`, payload);

        try {
            console.log("async completeLive : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    return data.data;
                case FAIL:
                    return -1;
            }
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }
        }
    },

    // POST /api/live/jtonny/start 고객의 즉시 통역 라이브 시작
    async startJTonnyLive(context, payload) {
        console.log("즉시통역 라이브 시작");

        let { data } = await http.post(`/live/jtonny/start`, payload);

        try {
            console.log("async startJTonnyLive : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("getStartResData", data.data);
                    return data.data;
                case FAIL:
                    return -1;
            }
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }
        }
    },

    // POST /api/live/ytonny/start 고객의 예약 통역 라이브 시작
    async startYTonnyLive(context, payload) {
        console.log("예약통역 라이브 시작");

        let { data } = await http.post(`/live/ytonny/${payload.yTonnySeq}/start`);

        try {
            console.log("async liveComplete : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_HISTORY_SEQ", data.data);
                    return data.data;
                case FAIL:
                    return -1;
            }
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }
        }
    },
};
