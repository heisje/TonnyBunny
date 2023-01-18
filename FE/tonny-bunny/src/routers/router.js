import { createRouter, createWebHistory } from "vue-router";

import AppHeader from "@/pages/common/AppHeader";
import AppFooter from "@/pages/common/AppFooter";

// home
import HomePage from "@/pages/home/HomePage";
import IntroPage from "@/pages/home/IntroPage";
import BunnyPage from "@/pages/home/BunnyPage";
import TonnyPage from "@/pages/home/TonnyPage";
import MyPage from "@/pages/home/MyPage";
import NoticePage from "@/pages/home/NoticePage";

export default createRouter({
	history: createWebHistory(),

	routes: [
		{
			path: "/",
			name: "home",
			components: {
				header: AppHeader,
				default: HomePage,
				footer: AppFooter
			}
		},

		{
			path: "/intro",
			name: "intro",
			components: {
				header: AppHeader,
				default: IntroPage,
				footer: AppFooter
			}
		},

		{
			path: "/bunny",
			name: "bunny",
			components: {
				header: AppHeader,
				default: BunnyPage,
				footer: AppFooter
			}
		},

		{
			path: "/tonny",
			name: "tonny",
			components: {
				header: AppHeader,
				default: TonnyPage,
				footer: AppFooter
			}
		},

		{
			path: "/mypage",
			name: "mypage",
			components: {
				header: AppHeader,
				default: MyPage,
				footer: AppFooter
			}
		},

		{
			path: "/notice",
			name: "notice",
			components: {
				header: AppHeader,
				default: NoticePage,
				footer: AppFooter
			}
		}
	]
});
