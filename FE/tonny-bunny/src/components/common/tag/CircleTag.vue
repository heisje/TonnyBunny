<template>
	<div class="tagWrap">
		<div class="circleTag" :class="{ disable: disable }">
			<span>{{ text }}</span>
		</div>
	</div>
</template>

<script>
export default {
	name: "CircleTag",

	props: {
		text: {
			type: String,
			default: "태그명",
			description: "tag text"
		},

		disable: {
			type: Boolean,
			default: false,
			description: "circle tag 활성 / 비활성"
		}
	}
};
</script>

<style lang="scss" scoped>
@import "@/scss/tag.scss";
</style>
