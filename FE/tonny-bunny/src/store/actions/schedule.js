import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
    async insertSchedule(context, payload) {
        let { data } = await http.post(`/schedules`, payload);

        try {
            console.log("async insertSchedule : ", data);

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

    async getScheduleList(context, params) {
        let { data } = await http.get("/schedules", { params });

        try {
            console.log("async getScheduleList : ", data);

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

    async getTodayScheduleList(context, userSeq) {
        let today = new Date();

        let params = {
            userSeq: userSeq,
            startYear: today.getFullYear(),
            startMonth: today.getMonth() + 1,
            startDay: today.getDate(),
            endYear: today.getFullYear(),
            endMonth: today.getMonth() + 1,
            endDay: today.getDate(),
        };

        let { data } = await http.get("/schedules", { params });

        try {
            console.log("async getScheduleList : ", data);

            // service logic
            switch (data.resultCode) {
                case SUCCESS:
                    context.commit("SET_TODAY_SCHEDULE_LIST", data.data);
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
