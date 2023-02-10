import http from "@/common/axios";

export default {
    /*
        리뷰 관련
    */
    // /api/review/user/{userSeq} 개별 유저의 리뷰 리스트를 조회합니다.
    async getUserReview(context, userSeq) {
        try {
            let { data } = await http.get(`/review/user/${userSeq}`);
            console.log("async function : ", data);
            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("SET_REVIEW", data.data);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },
};
