<template>
	<div class="modalContainer" @click="closeModal">
		<Transition name="bounce" v-show="isScheduleModalOpen">
			<div class="modalWrap">
				<div class="modalCustom">
					<h2>일정 상세</h2>
					<div class="scheduleContent" v-if="scheduleDetail.startDateTime">
						<div class="d-flex mt-5">
							<div class="me-1">
								<square-tag :text="getTaskCode" sub></square-tag>
							</div>
							<div>
								<square-tag
									v-if="!scheduleDetail.isCompleted"
									text="진행중"
									info></square-tag>
								<square-tag v-else text="완료됨" white></square-tag>
							</div>
						</div>
						<div class="mt-5 d-flex">
							<div class="fs-6">{{ startDate }} {{ startTime }}</div>
							&nbsp; ~ &nbsp;
							<div class="fs-6">{{ endDate }} {{ endTime }}</div>
						</div>
						<div class="mt-5">{{ scheduleDetail.content }}</div>
					</div>
				</div>
			</div>
		</Transition>
	</div>
</template>

<script>
import { mapGetters } from "vuex";

// import utils from "@/common/utils";
import SquareTag from "../tag/SquareTag.vue";

export default {
	components: { SquareTag },

	computed: {
		...mapGetters({
			isScheduleModalOpen: "getIsScheduleModalOpen",
			scheduleDetail: "getScheduleDetail",
			taskCode: "getTaskCode"
		}),

		getTaskCode() {
			let code;
			this.taskCode.forEach((e) => {
				if (e.value == this.scheduleDetail.taskCode) code = e.name;
			});
			return code;
		},
		startDate() {
			let date = this.scheduleDetail.startDateTime?.split("T")[0].split("-");
			return date[0] + "." + date[1] + "." + date[2];
		},
		startTime() {
			let date = this.scheduleDetail.startDateTime?.split("T")[1].split(":");
			return date[0] + ":" + date[1];
		},
		endDate() {
			let date = this.scheduleDetail.endDateTime?.split("T")[0].split("-");
			return date[0] + "." + date[1] + "." + date[2];
		},
		endTime() {
			let date = this.scheduleDetail.endDateTime?.split("T")[0].split("-");
			return date[0] + "." + date[1];
		}
	},

	methods: {
		closeModal(e) {
			e.preventDefault();
			this.$store.commit("CLOSE_SCHEDULE_MODAL");
		}
	},

	created() {}
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
</style>
