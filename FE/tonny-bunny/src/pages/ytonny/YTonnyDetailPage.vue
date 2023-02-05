<template>
	<div class="yTonnyDetailWrap">
		<!-- yTonny Detail View -->
		<div class="yTonnyDetail">
			<!-- yTonny 공고 정보 라인 -->
			<div>
				{{ yTonnyDetail }}
			</div>
			<div class="infos">
				<div>
					<square-tag sub></square-tag>
					<square-tag sub :text="yTonnyDetail.taskStateCode"></square-tag>
					<square-tag success></square-tag>
					<div>{{ yTonnyDetail.taskCode }}</div>
				</div>

				<div>
					<title-text
						type="h1"
						:title="yTonnyDetail.title"
						top="10"
						bottom="10"></title-text>
				</div>

				<div>
					<img :src="yTonnyDetail.client.profileImagePath" />
					<div>{{ yTonnyDetail.client.nickName }}</div>
					<div>{{ yTonnyDetail.createdAt }}</div>
				</div>

				<div>
					<square-tag :text="yTonnyDetail.startLangCode"></square-tag>
					<square-tag :text="yTonnyDetail.endLangCode"></square-tag>
				</div>

				<div>
					<div>{{ yTonnyDetail.startDateTime }}</div>
					<div>{{ yTonnyDetail.estimateTime }}</div>
				</div>

				<div>
					<div>{{ yTonnyDetail.estimatePrice }}</div>
				</div>

				<div>
					<div>
						{{ yTonnyDetail.tonnySituCode }}
					</div>
				</div>

				<div>
					<div>{{ yTonnyDetail.content }}</div>
				</div>
			</div>

			<!-- yTonny 공고 신청 버튼 라인 -->
			<div class="applyBtns" v-show="isHelper">
				<div v-if="isApply">
					<div>
						<input
							type="text"
							placeholder="제안할 가격을 입력해주세요. ex) 1000"
							v-model="totalPrice" />
						<div>캐럿/5분당</div>
					</div>
					<medium-btn text="통역 헬퍼 신청하기" @click="insertYTonnyApply"></medium-btn>
				</div>
				<div v-else>
					<medium-btn text="헬퍼 신청 취소하기"></medium-btn>
				</div>
			</div>
		</div>

		<!-- yTonny Applu List View -->
		<div class="yTonnyApplyList">
			<div>가격을 제안한 헬퍼들</div>

			<div v-if="yTonnyApplyList">
				<div v-for="(apply, index) in yTonnyApplyList" :key="index">
					{{ apply }}
					<helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card>
				</div>
			</div>
			<div v-else>가격을 제안한 헬퍼가 없습니다.</div>
		</div>
	</div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
	name: "YTonnyDetailPage",

	components: {
		TitleText,
		HelperCard,
		SquareTag,
		MediumBtn
	},

	data() {
		return {
			isHelper: true,
			isApply: true,

			totalPrice: 0
		};
	},

	computed: {
		...mapGetters({
			yTonnySeq: "getYTonnySeq",
			yTonnyDetail: "getYTonnyDetail",
			yTonnyApplyList: "getYTonnyApplyList"
		})
	},

	methods: {
		startChat() {
			console.log("start chat");
			this.$router.push({ name: "ChatDetailPage" });
		},

		insertYTonnyApply(e) {
			e.preventDefault();

			let payload = {
				helperSeq: 1,
				ytonnySeq: this.yTonnySeq,
				totalPrice: this.totalPrice
			};

			this.$store.dispatch("insertYTonnyApply", payload);
		}
	},

	created() {
		// detail 정보 가져오기
		this.$store.commit("SET_Y_TONNY_SEQ", this.$route.params.id);
		this.$store.dispatch("getYTonnyDetail", this.yTonnySeq);
		this.$store.dispatch("getYTonnyApplyList", this.yTonnySeq);
	}
};
</script>

<style></style>
