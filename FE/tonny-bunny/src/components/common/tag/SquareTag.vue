<template>
	<div class="tagWrap">
		<div class="squareTag" :class="[{ success, info, white, sub }]">
			<span :class="font">
				{{ success ? "모집중" : info ? "진행중" : white ? "완료됨" : text }}
			</span>
		</div>
	</div>
</template>

<script>
export default {
	name: "SquareTag",

	props: {
		success: {
			type: Boolean,
			default: false
		},

		info: {
			type: Boolean,
			default: false
		},

		white: {
			type: Boolean,
			default: false
		},

		sub: {
			type: Boolean,
			default: false
		},

		text: {
			type: String,
			default: "텍스트",
			description: "tag text"
		},

		// color: {
		// 	type: String,
		// 	default: "success",
		// 	description: "tag color"
		// },

		font: {
			type: String,
			default: "white",
			description: "tag font color"
		}
	}
};
</script>

<style lang="scss" scoped>
@import "@/scss/tag.scss";
</style>
