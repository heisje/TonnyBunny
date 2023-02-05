import { createApp } from "vue";
import App from "./App.vue";

import router from "@/routers/router";
import store from "@/store/store";

import Vue3Lottie from "vue3-lottie";
import "vue3-lottie/dist/style.css";

const app = createApp(App);

app.use(store);
app.use(router);
app.use(Vue3Lottie);
app.mount("#app");
