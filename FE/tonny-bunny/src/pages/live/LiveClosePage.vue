<template>
	<h1>라이브 - 라이브 종료 페이지</h1>
	<h2>동시통역 종료</h2>
	동시통역이 종료되었습니다.

	<div>
		통역 결과창
		<TonnyResult />
	</div>

	<div v-if="isHelper == true">
		<button class="mediumBtn">이용내역확인</button>
	</div>
	<div v-else>
		<button class="mediumBtn">리뷰쓰기</button>
		<button>다음에 작성하기</button>
	</div>
</template>

<script>
import TonnyResult from "@/components/live/TonnyResult.vue";

export default {
	name: "LiveClosePage",

	components: {
		TonnyResult
	}
};
</script>

<style></style>
