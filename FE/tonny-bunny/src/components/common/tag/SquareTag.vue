<template>
	<div class="tagWrap">
		<div class="squareTag" :class="color">
			<span :class="disable ? 'disable' : font">{{ text }}</span>
		</div>
	</div>
</template>

<script>
export default {
	name: "SquareTag",

	props: {
		text: {
			type: String,
			default: "모집중",
			description: "tag text"
		},

		color: {
			type: String,
			default: "success",
			description: "tag color"
		},

		font: {
			type: String,
			default: "white",
			description: "tag font color"
		},

		disable: {
			type: Boolean,
			default: false,
			description: "tag font disable"
		}
	}
};
</script>

<style lang="scss" scoped>
@import "@/scss/tag.scss";
</style>
