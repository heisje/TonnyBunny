<template>
	<hr />
	<helper-card
		oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"></helper-card>
	<hr />
	<helper-card></helper-card>
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
