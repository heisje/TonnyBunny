<template>
	<div>
		<h1>실시간 동시통역 - 가격 제안 페이지</h1>

		<div>고객에게 제안하기</div>
		<div>단가와 한 줄 설명으로 고객에게 제안해보세요!</div>

		<div>헬퍼카드</div>

		<div>매칭폼</div>
		<JTonnyHelperForm></JTonnyHelperForm>
		<div>취소모달</div>
		<div>성공모달</div>
	</div>
</template>

<script>
import JTonnyHelperForm from "@/components/jtonny/JTonnyHelperForm.vue";

export default {
	components: {
		JTonnyHelperForm
	}
};
</script>

<style></style>
