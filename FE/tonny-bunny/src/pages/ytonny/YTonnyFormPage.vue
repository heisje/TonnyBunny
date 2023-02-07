<template>
	<div>
		<title-banner title="예약통역 서비스 신청" text="헬퍼에게 통역 서비스를 예약합니다." />

		<YTonnyClientForm />

		<AlarmModal
			title="성공"
			type="success"
			btnText2="확인"
			btnColor2="main"
			btnFontColor1="white"
			btnFontColor2="white"
			@clickBtn2="successYtonnyCreate">
			<template #content>통역예약이 게시되었습니다.</template>
		</AlarmModal>
	</div>
</template>

<script>
import YTonnyClientForm from "@/components/ytonny/YTonnyClientForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import { mapGetters } from "vuex";

export default {
	name: "YTonnyFormPage",

	components: {
		YTonnyClientForm,
		TitleBanner,
		AlarmModal
	},

	computed: {
		...mapGetters({ yTonnySeq: "getYTonnySeq" })
	},

	methods: {
		successYtonnyCreate() {
			this.$router.push({ name: "YTonnyDetailPage", params: { id: this.yTonnySeq } });
			this.$store.commit("CLOSE_ALARM_MODAL");
		}
	},

	created() {
		window.scrollTo(0, 0);
	}
};
</script>

<style lang="scss" scoped></style>
