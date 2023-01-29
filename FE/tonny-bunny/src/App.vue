<template>
	<router-view name="header"></router-view>
	<main class="container">
		<router-view></router-view>
		<bottom-navbar />
		<login-modal></login-modal>
	</main>
	<router-view name="footer"></router-view>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";

import BottomNavbar from "@/pages/common/BottomNavbar.vue";
import LoginModal from "./components/common/modal/LoginModal.vue";

export default {
	name: "App",

	components: {
		BottomNavbar,
		LoginModal
	},

	data() {
		return {
			tt: 200
		};
	},

	setup() {
		const store = useStore();

		// console.log(store);
		const counter = computed(() => store.state.counter);
		const count = store.state.temp.count;

		return { counter, count };
	}
};
</script>

<style lang="scss">
@import "@/scss/global.scss";
</style>
