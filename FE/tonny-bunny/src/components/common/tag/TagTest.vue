<template>
	<div>
		<h1>Tag Test</h1>
		<br />

		<square-tag></square-tag>
		<square-tag color="info" text="진행중"></square-tag>
		<square-tag color="white" text="완료됨"></square-tag>

		<circle-tag text="활성태그"></circle-tag>
		<circle-tag text="비활성태그" disable></circle-tag>
	</div>
</template>

<script>
import CircleTag from "./CircleTag.vue";
import SquareTag from "./SquareTag.vue";

export default {
	name: "TagTest",

	components: {
		SquareTag,
		CircleTag
	}
};
</script>

<style></style>
