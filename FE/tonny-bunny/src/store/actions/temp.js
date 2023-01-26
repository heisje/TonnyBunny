/* eslint-disable */
import http from "@/common/axios";

export default {
    /*
        GET 방식 : [ @RequestParam ] 
        url 방식 : baseUrl/user?id=1
    */
    async getTemp1(context) {
        console.log("async function name !");

        let params = {
            limit: this.state.temp.limit,
            offset: this.state.temp.offset,
        };

        try {
            let { data } = await http.get("/temp", { params });
            console.log("async function : ", data);

            // service logic
            if (data.resultCode == "success") {
                context.commit("SET_TEMP_LIST", data.tempList);
            } else if (data.resultCode == "fail") {
            }
        } catch (err) {
            console.error(err);

            // exception
            // if (err.response.status == 403) {
            //     alert("로그인 하세요");
            // }
        }
    },

    /*
        GET 방식 : [ @ParamValue ] 사용
        url 방식 : baseUrl/user/{id}
    */
    async getTemp2(context, id) {
        console.log("async function name !");

        try {
            let { data } = await http.get(`/temp/${id}`);
            console.log("async function : ", data);

            // service logic
            if (data.resultCode == "success") {
                context.commit("SET_TEMP_LIST", data.tempList);
            } else if (data.resultCode == "fail") {
            }
        } catch (err) {
            console.error(err);

            // exception
            // if (err.response.status == 403) {
            //     alert("로그인 하세요");
            // }
        }
    },

    /*
        POST 방식 : [ @RequestBody ] 사용

        @return post 성공 여부 
    */
    async postTemp(context, tempJson) {
        console.log("async function name !");

        try {
            let { data } = await http.post(`/temp`, tempJson);
            console.log("async function : ", data);

            // service logic
            if (data.resultCode == "success") {
                context.commit("SET_TEMP_LIST", data.tempList);
            } else if (data.resultCode == "fail") {
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
        PUT 방식 : [ @RequestBody ] 사용

        @return put 성공 여부 
    */
    async putTemp(context, tempJson) {
        console.log("async function name !");

        try {
            let { data } = await http.put(`/temp`, tempJson);
            console.log("async function : ", data);

            // service logic

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
        DELETE 방식 : [ @ParamValue ] 사용
        url 방식 : baseUrl/user/{id}

        @return delete 성공 여부 
    */
    async deleteTemp(context, id) {
        console.log("async function name !");

        try {
            let { data } = await http.delete(`/temp/${id}`);
            console.log("async function : ", data);

            // service logic

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
