import http from "@/common/axios";

export default {
    /* Loading */
    setIsLoading(context, bool) {
        if (bool) {
            context.commit("SET_ISLOADING", true);
        } else {
            context.commit("SET_ISLOADING", false);
        }
    },

    /* Code */
    setCommonCode(context) {
        http.get("/common")
            .then(({ data }) => {
                // all code로 통합하는 코드
                let allCommonCode = new Object();
                for (let key in data.data) {
                    data.data[key].forEach((e) => {
                        allCommonCode[e.value] = e.name;
                    });
                }
                // console.log(allCommonCode);

                context.commit("SET_ALL_CODE", allCommonCode);

                // 기존 개별코드
                let {
                    userCode,
                    langCode,
                    taskCode,
                    tonnySituCode,
                    bunnySituCode,
                    quotationStateCode,
                    taskStateCode,
                    pointLogStateCode,
                } = {
                    ...data.data,
                };

                context.commit("SET_USER_CODE", userCode);
                context.commit("SET_LANG_CODE", langCode);
                context.commit("SET_TASK_CODE", taskCode);
                context.commit("SET_TONNY_SITU_CODE", tonnySituCode);
                context.commit("SET_BUNNY_SITU_CODE", bunnySituCode);
                context.commit("SET_QUOTATION_STATE_CODE", quotationStateCode);
                context.commit("SET_TASK_STATE_CODE", taskStateCode);
                context.commit("SET_POINT_LOG_STATE_CODE", pointLogStateCode);
            })
            .catch((err) => console.log(err));
    },

    async getHelperModalInfo(context, userSeq) {
        try {
            let { data } = await http.get(`/mypage/${userSeq}`);

            // service logic
            switch (data.resultCode) {
                case "SUCCESS":
                    context.commit("TOGGLE_HELPER_MODAL", data.data);
                    break;
                case "FAIL":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },
};
