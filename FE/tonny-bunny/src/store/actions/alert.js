import http from "@/common/axios";

export default {
    /*
        알림 관련 API
        Alert Controller
    */
    // /api/alerts/log 알림 목록 반환 API
    async getAlertList(context, userSeq) {
        console.log("알림 목록 반환 API", userSeq);
        let params = {
            userSeq: userSeq,
        };

        try {
            let { data } = await http.get("/alerts/log", { params });
            console.log("async function : ", data);
            const currentTime = Date.now();
            const options = {
                year: "numeric",
                month: "short",
                day: "numeric",
            };
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    data.data.forEach((d) => {
                        const oldTime = new Date(d.createdAt);
                        const pastTime = new Date(currentTime - oldTime);
                        const sec = Math.floor(pastTime / 1000);

                        if (sec >= 86400) {
                            d.createdAt = oldTime.toLocaleDateString("ko-KR", options);
                        } else if (sec >= 3600) {
                            d.createdAt = Math.floor(sec / 3600) + "시간 지남";
                        } else {
                            d.createdAt = Math.floor(sec / 60) + "분 지남";
                        }
                    });
                    context.commit("SET_ALERT_LIST", data.data);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // /api/alerts/log 알림 생성 API
    async postAlert(context, json) {
        console.log("알림 생성 API");

        let { data } = await http.post(`/api/alerts/log`, json);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    break;
                case "fail":
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

    // /api/alerts/log/{alertLogSeq} 알림 읽음 확인 수정 API
    async putAlert(context, payload) {
        const { userSeq, alertLogSeq } = payload;
        console.log("알림 읽음 확인 수정 API", userSeq, alertLogSeq);
        console.log(payload);

        let { data } = await http.put(`/alerts/log/${alertLogSeq}`);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    this.dispatch("getAlertList", userSeq);
                    break;
                case "fail":
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

    // /api/alerts/log/{alertLogSeq} 알림 삭제 API
    async deleteAlert(context, payload) {
        const { userSeq, alertLogSeq } = payload;
        console.log("삭제 API", userSeq, alertLogSeq);
        console.log(payload);

        let { data } = await http.delete(`/alerts/log/${alertLogSeq}`);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    this.dispatch("getAlertList", userSeq);
                    break;
                case "fail":
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
    /*
        즉시통역 CRUD
    */

    // GET /api/jtonny 즉시 통역 공고 목록 조회
    async getJtonnyList(context) {
        console.log("즉시 통역 공고 목록 조회");
        let params = {};

        try {
            let { data } = await http.get("/jtonny", { params });
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    context.commit("SET_JTONNY_LIST", data.data);
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // POST /api/jtonny 즉시 통역 공고 생성
    async insertJtonny(context, json) {
        console.log("즉시 통역 공고 생성");

        let { data } = await http.post(`/jtonny`, json);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
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

    // GET /api/jtonny/{jTonnyHelperSeq}/accept 즉시 통역 공고 신청 수락
    async getJtonnyAccept(context, jTonnyHelperSeq) {
        console.log("즉시 통역 공고 신청 수락");

        try {
            let { data } = await http.get(`/jtonny/${jTonnyHelperSeq}/accept`);
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // GET /api/jtonny/{jTonnyHelperSeq}/reject 즉시 통역 공고 신청 거절
    async getJtonnyReject(context, jTonnyHelperSeq) {
        console.log("즉시 통역 공고 신청 거절");

        try {
            let { data } = await http.get(`/jtonny/${jTonnyHelperSeq}/reject`);
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // GET /api/jtonny/{jTonnySeq} 즉시 통역 공고 상세 조회
    async getJtonnyDetail(context, jTonnySeq) {
        console.log("즉시 통역 공고 신청 수락");

        try {
            let { data } = await http.get(`/api/jtonny/${jTonnySeq}`);
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    context.commit("SET_JTONNY_DETAIL", data.data);
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // DELETE /api/jtonny/{jTonnySeq} 즉시 통역 공고 삭제
    async removeJtonny(context, jTonnySeq) {
        console.log("즉시 통역 공고 삭제");

        let { data } = await http.delete(`/jtonny/${jTonnySeq}`);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
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

    /*
        기타
    */
    // GET /api/jtonny/enroll 예약 통역 신청 목록 조회
    async getJtonnyEnroll(context) {
        console.log("예약 통역 공고 상세 조회");

        try {
            let { data } = await http.get(`/jtonny/enroll`);
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    context.commit("SET_JTONNY_DETAIL", data.data);
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // POST /api/jtonny/enroll 헬퍼의 예약 통역 신청 등록
    async insertJtonnyEnroll(context, json) {
        console.log("헬퍼의 예약 통역 신청 등록");

        let { data } = await http.post(`/jtonny/enroll`, json);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
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

    // DELETE /api/jtonny/enroll/{yTonnyNotiHelperSeq} 헬퍼의 예약 통역 신청 취소
    async removeJtonnyEnroll(context, yTonnyNotiHelperSeq) {
        console.log("헬퍼의 예약 통역 신청 취소");

        let { data } = await http.delete(`/jtonny/enroll/${yTonnyNotiHelperSeq}`);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
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

    // POST /api/jtonny/match/{yTonnyNotiSeq}/{yTonnyNotiHelperSeq} 예약 통역 공고에서 헬퍼의 신청을 수락
    async insertJtonnyMatch(context, yTonnyNotiSeq, yTonnyNotiHelperSeq) {
        console.log("예약 통역 공고에서 헬퍼의 신청을 수락");

        let { data } = await http.post(
            `/jtonny/match/${yTonnyNotiSeq}/${yTonnyNotiHelperSeq}`,
            yTonnyNotiSeq,
            yTonnyNotiHelperSeq
        );

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
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
