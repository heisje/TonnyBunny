<template>
	<h1>라이브 - 라이브 종료 페이지</h1>
	<h2>동시통역 종료</h2>
	동시통역이 종료되었습니다.

	<div>
		통역 결과창
		<TonnyResultItem/>
	</div>

	<div v-if="isHelper==true">
		이용내역확인
		<MediumBtn>
	</div>
	<div v-else>
		리뷰쓰기  
		<MediumBtn>
		<button>다음에 작성하기</button>
	</div> 




</template>

<script>
	export default {};
</script>

<style></style>
