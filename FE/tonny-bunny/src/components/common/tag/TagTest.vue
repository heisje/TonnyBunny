<template>
	<div>
		<h1>Tag Test</h1>
		<br />

		<square-tag text="하이루" sub></square-tag>
		<square-tag success></square-tag>
		<square-tag info></square-tag>
		<square-tag white></square-tag>

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
