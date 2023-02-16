<template lang="">
	<div>
		<title-banner title="알림" text="알림을 확인해보세요"></title-banner>
		<div class="alertContainer container d-flex justify-content-center align-items-center">
			<div class="alertWrap">
				<!-- <div class="noReadContent">읽지 않은 알림 5개</div> -->

				<div v-if="alertList.length > 0">
					<div
						class="alertContent"
						v-for="alertItem in alertList"
						:key="alertItem.alertLogSeq">
						<router-link :to="{ name: 'HistoryPage' }" style="text-decoration: none">
							<AlertItem
								:alertItem="alertItem"
								@deleteAlert="deleteAlert(alertItem.alertLogSeq)" />
						</router-link>
					</div>
				</div>
				<div v-else style="color: var(--sub-color)">알림이 없습니다</div>
			</div>
		</div>
	</div>
</template>
<script>
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlertItem from "@/components/home/AlertItem.vue";
import { mapGetters } from "vuex";

export default {
	name: "AlertPage",
	data() {
		return {};
	},
	components: {
		TitleBanner,
		AlertItem
	},

	computed: {
		...mapGetters({
			alertList: "getAlertList",
			userInfo: "getUserInfo"
		})
	},

	methods: {
		putAlert(alertLogSeq) {
			this.$store.dispatch("putAlert", {
				userSeq: this.userInfo.seq,
				alertLogSeq: alertLogSeq
			});
		},
		deleteAlert(alertLogSeq) {
			this.$store.dispatch("deleteAlert", {
				userSeq: this.userInfo.seq,
				alertLogSeq: alertLogSeq
			});
		}
	},

	mounted() {
		this.$store.dispatch("getAlertList", this.userInfo.seq);
		console.log("hihi", this.alertList);
	}
};
</script>
<style lang="scss" scoped>
.alertContainer {
	margin-top: 100px;
	margin-bottom: 150px;
}
.alertWrap {
	width: calc(100% - 200px);
}
</style>
