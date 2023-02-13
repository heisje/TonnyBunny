import axios from "axios";

const serverUrl =
    process.env.VUE_APP_NODE_ENV == "production"
        ? process.env.VUE_APP_SERVER_URL
        : "http://localhost:8080";

// axios 객체 생성 export
export default axios.create({
    // 백엔드 localhost:8080
    baseURL: serverUrl + "/api",

    headers: {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Credentials": true,
    },

    withCredentials: true,
});
