<template>
	<h1>마이페이지 - 차단 목록</h1>
	긴 유저 막대바 나열
	<UserProfileItem/>
</template>

<script>
import UserProfileItem from '@/components/mypage/UserProfileItem.vue'
	export default {
		name: "BlockListPage",

		components: {
			UserProfileItem
		},
	};
</script>

<style></style>
