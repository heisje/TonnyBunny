<template>
	<div class="yTonnyUpdateContainer">
		<div class="yTonnyUpdateWrap">
			<div class="yTonnyUpdateContent">
				<title-banner title="예약통역 수정" text="예약통역 수정 폼"></title-banner>
				<y-tonny-update-form></y-tonny-update-form>
			</div>
		</div>
	</div>
</template>

<script>
import YTonnyUpdateForm from "@/components/ytonny/YTonnyUpdateForm.vue";
import { mapGetters } from "vuex";
import TitleBanner from "@/components/common/TitleBanner.vue";

export default {
	name: "YTonnyUpdatePage",

	components: {
		YTonnyUpdateForm,
		TitleBanner
	},

	computed: {
		...mapGetters({
			userInfo: "getUserInfo"
		})
	},

	created() {
		let yTonnySeq = this.$route.params.id;
		let userSeq = this.userInfo.seq;

		let payload = {
			yTonnySeq,
			userSeq
		};

		this.$store.commit("SET_Y_TONNY_SEQ", yTonnySeq);
		this.$store.dispatch("getYTonnyDetail", payload);
	}
};
</script>

<style lang="scss" scoped></style>
