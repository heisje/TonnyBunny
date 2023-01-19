<template>
	<h1>라이브 - on air 페이지</h1>
	<div>
		요금확인아이템
		<CheckCostItem/>
	</div>
	<div>
		<button class="bottomBtn">채팅버튼</button>
	</div>
	<div>
		채팅모달
		<ChatModal/>
	</div>
	<div v-if="isHelper===True">
		종료요청모달
		<ExitRequestModal/>
	</div>
	<div v-else>
		종료확인모달
		<ExitConfirmModal/>
	</div>
</template>

<script>
import CheckCostItem from "@/components/live/CheckCostItem.vue"
import ChatModal from "@/components/live/ChatModal.vue"
import ExitRequestModal from "@/components/live/ExitRequestModal.vue"
import ExitConfirmModal from "@/components/live/ExitConfirmModal.vue"

	export default {
		name: "LivePage",

		components: {
			CheckCostItem,
			ChatModal,
			ExitRequestModal,
			ExitConfirmModal,
		},
	};
</script>

<style></style>
