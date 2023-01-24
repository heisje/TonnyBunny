<template>
	<div>마이페이지 - 캐럿 관리</div>
	보유캐럿 120개 포인트관리
	<button class="smallBtn">충전</button>

	<button class="smallBtn">캐럿 환전</button>
	<br />
	<button class="middelBtn">결제 수단 관리</button>

	사용내역
	<CarrotHistoryList />
</template>

<script>
import CarrotHistoryList from "@/components/mypage/CarrotHistoryList.vue";

export default {
	name: "CarrotPage",

	components: {
		CarrotHistoryList
	}
};
</script>

<style></style>
