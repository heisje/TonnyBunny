<template>
	<div class="cardContainer">
		<div class="cardWrap">
			<div class="helperCard">
				<!-- 1. x 버튼 라인 -->
				<div class="closeBtn">
					<span class="material-symbols-outlined"> close </span>
				</div>

				<!-- 2. 정보 라인 -->
				<div class="helperInfo">
					<!-- 1.1 프로필 이미지, 닉네임, 한줄 소개 라인 -->
					<div class="helperProfile">
						<img
							class="profileImg"
							src="@/assets/noProfile_white.png"
							width="50"
							height="50" />
						<div class="likeBtn">
							<span
								v-if="isLikeEmpty"
								class="material-symbols-outlined likeIcon empty">
								favorite
							</span>
							<span v-else class="material-symbols-outlined likeIcon">
								favorite
							</span>
							<span class="likeText">{{ likeText }}</span>
						</div>
					</div>

					<!-- 1.2 헬퍼 요금 소개 라인 -->
					<div class="helperDesc">
						<!-- 1.2.1 닉네임 & 한 줄 소개 -->
						<div class="helperNick">
							<h3 class="nickName">{{ nickName }}</h3>
							<div class="oneLine label">{{ oneLine }}</div>
						</div>
						<!-- 1.2.2 실력 표시 라인 -->
						<div class="helperSkill">
							<div class="fare">
								<div class="label">기본요금</div>
								<div class="fareText">
									{{ fareText }}
									<span class="label" style="display: inline">캐럿</span>
								</div>
							</div>
							<div class="reviewGrade">
								<div class="label">평점</div>
								<div class="star">
									<span class="material-symbols-outlined starIcon"> star </span>
									<div class="starText">
										{{ starText }}
										<div class="label" style="display: inline">점</div>
									</div>
								</div>
							</div>
							<div class="reviewCount">
								<div class="label">리뷰건수</div>
								<div class="countText">
									{{ countText }}
									<div class="label" style="display: inline">건</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 3. 하단 버튼 라인 -->
				<div class="helperBtns">
					<XSmallBtn text="자세히보기" />
					<XSmallBtn :text="rightBtnText" color="carrot" />
				</div>
			</div>
		</div>
	</div>
</template>

<script>
import XSmallBtn from "@/components/common/button/XSmallBtn.vue";

export default {
	name: "HelperCard",

	components: {
		XSmallBtn
	},

	props: {
		nickName: {
			type: String,
			default: "닉네임"
		},

		oneLine: {
			type: String,
			default: "헬퍼 한 줄 소개"
		},

		fareText: {
			type: String,
			default: "1000"
		},
		starText: {
			type: String,
			default: "5.0"
		},
		countText: {
			type: String,
			default: "1000"
		},

		likeText: {
			type: String,
			default: "like",
			description: "몇명이나 해당 헬퍼를 좋아하는지 [ex. 10, 100K ..]"
		},

		isLikeEmpty: {
			type: Boolean,
			default: false,
			description: "like(favorite) 하트 아이콘 비었는가 아닌가"
		},

		rightBtnText: {
			type: String,
			default: "수락하기",
			description: "오른쪽 버튼 텍스트 [수락하기, 상담하기 etc]"
		}
	}
};
</script>

<style lang="scss" scoped>
.cardContainer {
	// position: relative;
	display: inline-block;
	background-color: var(--thin-color);

	width: 332px;
	border-radius: 8px;
	margin: 8px;
	padding: 16px;

	box-shadow: 1px 2px 1px rgba(0, 0, 0, 0.08);
}

.cardWrap {
	// display: grid;

	.closeBtn {
		position: absolute;
		// top: 0;
		// z-index: 99;
		float: right;
		top: -6px;
		right: -6px;

		cursor: pointer;

		span {
			transition: all 0.13s;
			color: var(--sub-color);
		}

		&:hover {
			// opacity: 0.8;

			span {
				color: var(--main-color);
			}
		}
	}
}
.helperCard {
	display: flex;
	flex-direction: column;
	position: relative;

	.helperInfo {
		display: flex;
		cursor: default;
		align-items: center;
		.helperProfile {
			display: flex;
			flex-direction: column;

			.profileImg {
				margin: 0 16px 8px 0;
				cursor: pointer;
				border-radius: 100%;
				box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
				// border: 2px solid var(--main-color);
			}

			.likeBtn {
				display: flex;
				width: 50px;
				justify-content: center;
				align-items: center;
				cursor: pointer;

				.likeIcon {
					color: var(--danger-color);
					font-size: 1.25rem;
					font-variation-settings: "FILL" 1, "wght" 400, "GRAD" 0, "opsz" 10;
					margin-right: 4px;
					// cursor: pointer;
					transition: all 0.13s;
				}
				.empty {
					// color: var(--sub-color);
					font-variation-settings: "FILL" 0;
				}

				.likeText {
					color: var(--sub-color);
					// cursor: pointer;
					transition: all 0.13s;
				}

				&:hover {
					.likeIcon {
						color: #fc5148;
						font-variation-settings: "FILL" 1;
					}

					.likeText {
						color: var(--main-color);
					}
				}
			}
		}

		.helperDesc {
			display: flex;
			flex-direction: column;
			width: 100%;

			.helperNick {
				display: flex;
				flex-direction: column;
				margin-top: 8px;

				.nickName {
					margin-bottom: 4px;
				}

				.oneLine {
					color: var(--sub-color);
					width: 200px;
					word-break: break-all;
					overflow: hidden;
					text-overflow: ellipsis;
					white-space: nowrap;
					margin-bottom: 4px;
				}
			}

			.helperSkill {
				display: flex;
				// justify-content: space-between;
				justify-content: space-around;
				width: 94%;
				margin-top: 4px;
				margin-bottom: 16px;

				background-color: var(--light-color);
				border-radius: 8px;
				padding: 8px 0px;

				// div {
				// 	margin-right: 8px;
				// }

				.fare {
					h5 {
						// color: var(--sub-color);
					}

					.fareText {
						font-size: 1rem;
						font-weight: 600;
						// color: var(--primary-color);
					}
				}

				.reviewGrade {
					.star {
						display: flex;
						.starIcon {
							font-size: 1.1rem;
							font-variation-settings: "FILL" 1, "wght" 700;
							margin-right: 4px;
						}

						.starText {
							font-size: 1rem;
							font-weight: 600;
							// color: var(--primary-color);
						}
					}
				}

				.reviewCount {
					.countText {
						font-size: 1rem;
						font-weight: 600;
						// color: var(--primary-color);
					}
				}
			}
		}
	}
	.helperBtns {
		display: flex;
		justify-content: flex-end;

		div {
			padding-left: 8px;
		}
	}
}
</style>
