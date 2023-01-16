import { createRouter, createWebHashHistory } from "vue-router";

import HomePage from "@/pages/home/HomePage";

export default createRouter({
	history: createWebHashHistory(),
	routes: [
		{
			path: "/",
			name: "components",
			components: {
				// header: AppHeader,
				default: HomePage
				// footer: AppFooter
			}
		}
	]
});
