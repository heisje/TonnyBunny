import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
    /* 
        예약 통역 CREATE
    */

    // POST /api/ytonny 고객의 예약 통역 공고 생성
    async insertYTonny(context, payload) {
        console.log("예약통역 공고 생성");

        let { data } = await http.post(`/ytonny`, payload);

        try {
            console.log("async insertYTonny : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_Y_TONNY_SEQ", data.data);
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

    // POST /api/ytonny 고객의 예약 통역 공고 생성
    async insertYTonnyApply(context, payload) {
        console.log("예약통역 공고 신청 생성");

        console.log(payload);
        let yTonnySeq = payload.ytonnySeq;
        let { data } = await http.post(`/ytonny/${yTonnySeq}/apply`, payload);

        try {
            console.log("async insertYTonnyApply : ", data);

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

    // POST /api/ytonny 고객의 예약 통역 견적서 생성
    async insertYTonnyQuotation(context, formData) {
        console.log("예약통역 공고 견적서 생성");
        console.log(formData);

        let yTonnySeq = formData.get("yTonnySeq");

        let options = {
            headers: { "Content-Type": "multipart/form-data" },
        };

        let { data } = await http.post(`/ytonny/${yTonnySeq}/quotation`, formData, options);

        try {
            console.log("async insertYTonnyQuotation : ", data);

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

    /* 
        예약 통역 READ
    */
    // GET /api/ytonny 예약 통역 공고 목록 조회
    async getYTonnyList(context) {
        console.log("예약 통역 공고 목록 조회");

        try {
            let { data } = await http.get(`/ytonny`);
            console.log("async getYTonnyList : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_Y_TONNY_LIST", data.data);
                    break;
                case FAIL:
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // GET /api/ytonny/{yTonnySeq} 예약 통역 공고 상세 조회
    async getYTonnyDetail(context, yTonnySeq) {
        console.log("예약 통역 공고 상세 조회");

        try {
            let { data } = await http.get(`/ytonny/${yTonnySeq}`);
            console.log("async getYTonnyDetail : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_Y_TONNY_DETAIL", data.data);
                    break;
                case FAIL:
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // GET /api/ytonny/{yTonnySeq}/apply 예약 통역 공고 목록 조회
    async getYTonnyApplyList(context, yTonnySeq) {
        console.log("예약 통역 공고 목록 신청 조회");
        let params = {
            page: 0,
            size: 4,
        };

        try {
            let { data } = await http.get(`/ytonny/${yTonnySeq}/apply`, { params });
            console.log("async getYTonnyApplyList : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_Y_TONNY_APPLY_LIST", data.data);
                    break;
                case FAIL:
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // PUT /api/ytonny/{yTonnyNotiSeq} 게시글을 수정합니다.
    async updateYTonny(context, payload) {
        console.log("예약통역 수정");

        let { data } = await http.put(`/ytonny/${payload.ytonnySeq}`, payload);

        try {
            console.log("async updateYTonny : ", data);

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

    // DELETE /api/ytonny/{yTonnyNotiSeq} 고객의 예약 통역 공고 취소
    async removeYTonny(context, yTonnySeq) {
        console.log("에약통역 삭제");

        let { data } = await http.delete(`/ytonny/${yTonnySeq}`);

        try {
            console.log("async removeYtonny : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    return data.data;
                case FAIL:
                    return -1;
            }
            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    // DELETE /api/ytonny/enroll/{yTonnyNotiHelperSeq} 헬퍼의 예약 통역 신청 취소
    async removeYTonnyApply(context, payload) {
        console.log("헬퍼의 예약 통역 신청 취소");

        let { data } = await http.delete(
            `/ytonny/${payload.yTonnySeq}/apply/${payload.yTonnyApplySeq}`
        );

        try {
            console.log("async removeYtonnyApply : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    break;
                case FAIL:
                    break;
            }
            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    // POST /api/ytonny/match/{yTonnyNotiSeq}/{yTonnyNotiHelperSeq} 예약 통역 공고에서 헬퍼의 신청을 수락
    async insertYtonnyMatch(context, yTonnyNotiSeq, yTonnyNotiHelperSeq) {
        console.log("예약 통역 공고에서 헬퍼의 신청을 수락");

        let { data } = await http.post(
            `/ytonny/match/${yTonnyNotiSeq}/${yTonnyNotiHelperSeq}`,
            yTonnyNotiSeq,
            yTonnyNotiHelperSeq
        );

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    break;
                case FAIL:
                    break;
            }

            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },
};
