<template>
	<div>
		<div>실시간 동시통역 - 대기 페이지</div>
		<div>상단(box)</div>
		<JTonnyLoading></JTonnyLoading>
		<div>버튼</div>
		<div>타이틀: 통역 가능한 헬퍼가 도착했어요</div>
		<div>서브: 통역 가능한 헬퍼가 도착했어요</div>
		<div>헬퍼리스트</div>
		<div>경고모달</div>
		<div>성공모달</div>
	</div>
</template>

<script>
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";

export default {
	components: {
		JTonnyLoading
	}
};
</script>

<style></style>
