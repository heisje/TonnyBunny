import http from "@/common/axios";

export default {
    /*
        GET 방식 : [ @RequestParam ] 
        url 방식 : baseUrl/user?id=1
    */
    async getTemp1(context) {
        let params = {
            limit: this.state.temp.limit,
            offset: this.state.temp.offset,
        };

        try {
            let { data } = await http.get("/temp", { params });

            // service logic
            if (data.resultCode == "success") {
                context.commit("SET_TEMP_LIST", data.tempList);
            } else if (data.resultCode == "fail") {
                // exception
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
        try {
            let { data } = await http.get(`/temp/${id}`);

            // service logic
            if (data.resultCode == "success") {
                context.commit("SET_TEMP_LIST", data.tempList);
            } else if (data.resultCode == "fail") {
                // exception
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
        let { data } = await http.post(`/temp`, tempJson);

        try {
            // service logic
            if (data.resultCode == "success") {
                context.commit("SET_TEMP_LIST", data.tempList);
            } else if (data.resultCode == "fail") {
                // exception
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
        let { data } = await http.put(`/temp`, tempJson);

        try {
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
        let { data } = await http.delete(`/temp/${id}`);

        try {
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
