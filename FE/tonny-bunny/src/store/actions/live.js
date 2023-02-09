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

        let { res } = await http.post(`/live/complete`, payload);

        try {
            console.log("async completeLive : ", res);

            // service logic
            switch (res.resultCode) {
                case SUCCESS:
                    // context.commit("SET_Y_TONNY_SEQ", data.data); 리뷰 모달 띄우나?
                    return res.data;
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

        let { res } = await http.post(`/live/jtonny/start`, payload);

        try {
            console.log("async startJTonnyLive : ", res);

            // service logic
            switch (res.resultCode) {
                case SUCCESS:
                    context.commit("getStartResData", res.data);
                    return res.data;
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
    async liveComplete(context, payload) {
        console.log("예약통역 라이브 시작");

        let { res } = await http.post(`/live/ytonny/start`, payload);

        try {
            console.log("async liveComplete : ", res);

            // service logic
            switch (res.resultCode) {
                case SUCCESS:
                    context.commit("getStartResData", res.data);
                    return res.data;
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
