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

    // GET /api/bunny/{bunnySeq} 번역 상세 조회
    async getBunnyDetail(context, bunnySeq) {
        console.log("번역 상세 조회");

        try {
            let { data } = await http.get(`/bunny/${bunnySeq}`);
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("SET_BUNNY_DETAIL", data.data);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);

            if (err.response.status == 404) {
                alert("조회에 실패했습니다!");
            }
        }
    },

    // GET /api/bunny 번역 목록 조회
    async getBunnyList(context, payload) {
        console.log("번역 목록 조회");
        console.log(payload);
        try {
            let { data } = await http.get(`/bunny`, { params: payload });
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("SET_BUNNY_LIST", data.data);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // DELETE /api/bunny/{bunnySeq} 번역 공고 삭제
    async removeBunny(context, bunnySeq) {
        console.log("번역 공고 삭제");

        try {
            let { data } = await http.delete(`/bunny/${bunnySeq}`);
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("SET_BUNNY_DETAIL", {});
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);

            if (err.response.status == 404) {
                alert("삭제에 실패했습니다!");
            }
        }
    },

    // DELETE /api/bunny/{bunnySeq}/apply/{bunnyApplySeq} 번역 공고 신청 삭제
    async removeBunnyApply(context, payload) {
        console.log("번역 공고 신청 삭제");
        console.log(payload);
        try {
            let { data } = await http.delete(
                `/bunny/${payload.bunnySeq}/apply/${payload.bunnyApplySeq}`,
                { data: payload }
            );
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // POST /api/bunny/{bunnySeq}/apply 번역 공고 신청 생성
    async insertBunnyApply(context, payload) {
        console.log("번역 공고 신청 생성");
        console.log(payload);
        try {
            let { data } = await http.post(`/bunny/${payload.bunnySeq}/apply`, payload);
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    console.log(context);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // POST /api/bunny/{bunnySeq}/apply 번역 견적서 생성
    async insertBunnyQuotation(context, payload) {
        console.log("번역 견적서 생성");
        console.log(payload);
        try {
            let { data } = await http.post(`/bunny/${payload.bunnySeq}/quotation`, payload);
            console.log("async function : ", data);

            context;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    console.log(context);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },
};
