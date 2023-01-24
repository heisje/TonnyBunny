<template>
	<div>마이페이지 - 이용내역 상세 조회</div>
	<button>작업종류</button> <button>작업상태</button>
	작업내역
	진행결과
	체결단가

	진행날짜

	진행시간

	헬퍼프로필카드

	---
	리뷰내역
	<div v-if="isReviewed===true">
		리뷰내역 출력(자주 쓸거니까 카드로 만들듯? 프로필페이지에도 들어감)
		<!-- <ReviewItem/> -->
	</div>
	<div v-else>
		<button>large버튼 - 리뷰쓰기</button>
	</div>

</template>

<script>
	export default {
		name: "HelperChangePage",

		components: {
			ReviewItem
		},
	};
</script>

<style></style>
