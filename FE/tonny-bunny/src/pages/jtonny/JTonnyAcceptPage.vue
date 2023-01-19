<template>
	<div>
		<div>실시간 동시통역 - 응답 페이지</div>

		<div>즉시 통역 찾기</div>
		<div>지금 당장 번역이 필요한 고객을 찾습니다.</div>

		<JTonnyLoading></JTonnyLoading>

		<div>라지버튼</div>

		<div>즉시 통역 요청이 왔어요!</div>
		<div>통역 요청글을 자세히 보고 가능한 통역을 신청하세요!</div>

		<div>헬퍼카드리스트</div>
		<div>취소모달</div>
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
