<template>
	<hr />
	<helper-card
		nickName="아스파라거스"
		oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
		fareText="2000"
		starText="4.0"
		countText="100"
		likeText="10"
		isLikeEmpty
		rightBtnText="상담하기"></helper-card>
	<helper-card></helper-card>
	<hr />

	<client-card></client-card>
	<quest-card></quest-card>
</template>

<script>
import ClientCard from "./ClientCard.vue";
import HelperCard from "./HelperCard.vue";
import QuestCard from "./QuestCard.vue";

export default {
	name: "CardTest",

	components: {
		ClientCard,
		HelperCard,
		QuestCard
	}
};
</script>

<style></style>
