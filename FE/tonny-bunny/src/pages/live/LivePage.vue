<template>
	<h1>라이브 - on air 페이지</h1>
	<div>
		요금확인아이템
		<CheckCostItem/>
	</div>
	<div>
		채팅버튼
		<BottomBtn>
	</div>
	<div>
		채팅모달
		<ChatModal>
	</div>
	<div v-if="isHelper===True">
		종료요청모달
		<ExitRequestModal>
	</div>
	<div v-else>
		종료확인모달
		<ExitConfirmModal>
	</div>
</template>

<script>
	export default {};
</script>

<style></style>
