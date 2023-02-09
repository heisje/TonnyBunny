<template lang="">
	<div id="titleBanner">
		<div class="bannerWrap">
			<div class="fullSizeBanner">
				<div>
					<h1 class="h1-small">{{ title }}</h1>
					<p>{{ text }}</p>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	name: "TitleBanner",
	props: {
		title: {
			type: String,
			default: "title",
			description: "button tag"
		},
		text: {
			type: String,
			default: "description",
			description: "button link"
		}
	}
};
</script>

<style lang="scss" scoped>
@import "@/scss/variable.scss";

#titleBanner {
	// position: static;
	width: 100%;
	height: 120px;
	left: 0;

	margin-bottom: 64px;

	cursor: default;

	.fullSizeBanner {
		height: 150px;

		display: flex;
		align-items: center;
		justify-content: center;
		text-align: center;

		h1 {
			margin-bottom: 4px;
			color: var(--active-color);
			// color: #fff;
			color: var(--primary-color);
			text-shadow: 1px 1px 1px 3px var(--sub-color);
			font-weight: 800;
			font-size: 1.5rem;
			// letter-spacing: 2px;
			// letter-spacing: -2px;
		}

		p {
			color: var(--main-color);
			// color: #fff;
			// @extend .label;
		}
	}
}

.bannerWrap {
	// background: var(--light-color);
	// background: var(--warning-color);
	background: linear-gradient(rgba(255, 255, 255, 0.7), rgba(255, 255, 255, 0.7)),
		url("@/assets/images/banner4.jpg") no-repeat center;
	background-size: cover;
	// background-image: linear-gradient(
	//     to right top,
	//     #e8edef,
	//     #e8edef,
	//     #e8edef,
	//     #e8edef,
	//     #e8edef,
	//     #d6ecf2,
	//     #c2ebf3,
	//     #adebf0,
	//     #7fe9dd,
	//     #64e5b8,
	//     #6add85,
	//     #87d146
	// );
	// background: var(--banner-color2);
	// background-color: var(--light-color);
	position: absolute;
	left: 0;
	width: 100%;
}
</style>
