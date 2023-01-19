<template>
	<h1>라이브 - on air 페이지</h1>
	<div>
		요금확인아이템
		<TonyInfo />
	</div>
	<div>
		<button class="bottomBtn">채팅버튼</button>
	</div>
	<div>
		채팅모달
		<ChatModal />
	</div>
	<div v-if="isHelper === True">종료요청모달</div>
	<div v-else>종료확인모달</div>
</template>

<script>
import TonyInfo from "@/components/live/TonyInfo.vue";
import ChatModal from "@/components/live/ChatModal.vue";

export default {
	name: "LivePage",

	components: {
		TonyInfo,
		ChatModal
	}
};
</script>

<style></style>
