<template>
	<div>마이페이지 - 캐럿 관리</div>
	보유캐럿 120개 포인트관리
	<PointManagementItem/>
	<button class="smallBtn">충전</button>

	<button class="smallBtn">캐럿 환전</button>
	<br>
	<button class="middelBtn">결제 수단 관리</button>

	사용내역
	<PointHistoryList/>
</template>

<script>
import PointManagementItem from "@/components/mypage/PointManagementItem.vue"
import PointHistoryList from "@/components/mypage/PointHistoryList.vue"
	export default {
		name: "CarrotPage",

		components: {
			PointManagementItem,
			PointHistoryList,
		},
	};
</script>

<style></style>
