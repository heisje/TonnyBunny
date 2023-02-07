import { createApp } from "vue";
import App from "./App.vue";

import router from "@/routers/router";
import store from "@/store/store";

import Vue3Lottie from "vue3-lottie";
import "vue3-lottie/dist/style.css";

// Vuetify
import "vuetify/styles";
import { createVuetify } from "vuetify";
import * as components from "vuetify/components";
import * as directives from "vuetify/directives";

const vuetify = createVuetify({
	ssr: true,

	components,
	directives
});

const app = createApp(App);

app.use(store);
app.use(router);
app.use(Vue3Lottie);
app.use(vuetify);
app.mount("#app");
