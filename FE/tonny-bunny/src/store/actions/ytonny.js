import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
    /* 
        예약 통역 CREATE
    */

    // POST /api/ytonny 고객의 예약 통역 공고 생성
    async insertYTonny(context, payload) {
        let { data } = await http.post(`/ytonny`, payload);

        try {
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
        console.log(payload);
        let yTonnySeq = payload.ytonnySeq;
        let { data } = await http.post(`/ytonny/${yTonnySeq}/apply`, payload);

        try {
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
        let yTonnySeq = formData.get("yTonnySeq");

        let options = {
            headers: { "Content-Type": "multipart/form-data" },
        };

        let { data } = await http.post(`/ytonny/${yTonnySeq}/quotation`, formData, options);

        try {
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
    async getYTonnyList(context, page) {
        let params = {
            page: page - 1,
            size: context.state.ytonny.yTonnySize,
        };

        try {
            let { data } = await http.get(`/ytonny`, { params });

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

    async getYTonnyListTotalCount(context) {
        try {
            let { data } = await http.get(`/ytonny/count`);
            context.commit("SET_Y_TONNY_LIST_TOTAL_COUNT", data.data);
        } catch (err) {
            console.error(err);
        }
    },
    async getYTonnyApplyListTotalCount(context, yTonnySeq) {
        try {
            let { data } = await http.get(`/ytonny/${yTonnySeq}/apply/count`);
            context.commit("SET_Y_TONNY_APPLY_LIST_TOTAL_COUNT", data.data);
        } catch (err) {
            console.error(err);
        }
    },

    // GET /api/ytonny/{yTonnySeq} 예약 통역 공고 상세 조회
    async getYTonnyDetail(context, yTonnySeq) {
        try {
            let { data } = await http.get(`/ytonny/${yTonnySeq}`);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_Y_TONNY_DETAIL", data.data);
                    context.commit("SET_START_RES_DATA", data.data);
                    break;
                case FAIL:
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // GET /api/ytonny/{yTonnySeq}/apply 예약 통역 공고 목록 조회
    async getYTonnyApplyList(context, payload) {
        let params = {
            page: payload.page - 1,
            size: payload.size,
        };

        try {
            let { data } = await http.get(`/ytonny/${payload.yTonnySeq}/apply`, { params });

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
        let { data } = await http.put(`/ytonny/${payload.ytonnySeq}`, payload);

        try {
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
        let { data } = await http.delete(`/ytonny/${yTonnySeq}`);

        try {
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
        let { data } = await http.delete(
            `/ytonny/${payload.yTonnySeq}/apply/${payload.yTonnyApplySeq}`
        );

        try {
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
    async acceptYTonnyApply(context, payload) {
        let params = {
            ytonnyApplySeq: payload.yTonnyApplySeq,
            ytonnySeq: payload.yTonnySeq,
            helperSeq: payload.helperSeq,
            unitPrice: payload.unitPrice,
        };

        try {
            let { data } = await http.put(
                `/ytonny/${payload.yTonnySeq}/apply/${payload.yTonnyApplySeq}/accept`,
                params
            );

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

            return -1;
        }
    },
};
