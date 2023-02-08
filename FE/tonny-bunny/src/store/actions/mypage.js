import http from "@/common/axios";
import utils from "@/common/utils";
export default {
    /*
        회원 정보 관련
    */
    // /api/mypage/{userSeq} 회원정보를 조회합니다.
    async getMypage(context) {
        try {
            let { data } = await http.get(`/mypage/${context.state.account.userInfo.seq}`);
            console.log("async function : ", data);
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("SET_USER_INFO", data.data);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    /*
        프로필 이미지
    */
    // /api/mypage/{userSeq}/profileImage  프로필사진을 수정합니다
    async putProfileImage(context, payload) {
        const formData = new FormData();
        formData.append("file", payload);
        try {
            let { data } = await http.put(
                `/mypage/${context.state.account.userInfo.seq}/profileImage`,
                formData,
                {
                    headers: {
                        "Content-Type": "multipart/form-data",
                    },
                }
            );
            console.log("async function : ", data);
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

    /*
        포인트 관련
    */
    // /api/point-log 대상 유저의 포인트 로그 목록 조회
    async postPointLog(context, payload) {
        // 불러올 개수 = logCount
        // 유저seq = userSeq

        //유저 시퀀스
        try {
            let { data } = await http.post("/point-log", payload);
            console.log("async function : ", data);
            let loadData = data.data;
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    console.log(loadData);
                    loadData.forEach((element) => {
                        console.log(element.pointRequestType);
                        element["pointRequestCodeTrans"] =
                            context.state.common.allCode[element.pointRequestType];
                        element["createdAt"] = utils.setDate(element.createdAt);
                    });
                    context.commit("SET_POINT_LIST", loadData);

                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // /api/points 포인트 변동(충전, 출금, 거래)
    async putPoints(context, payload) {
        try {
            let { data } = await http.put("/points", payload);
            console.log("async function : ", data);

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

    /*
        히스토리 관련
    */
    async getHistoryList(context, payload) {
        try {
            let { data } = await http.get(`/mypage/${payload.userSeq}/history`, payload);
            console.log("async function : ", data);

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
};
