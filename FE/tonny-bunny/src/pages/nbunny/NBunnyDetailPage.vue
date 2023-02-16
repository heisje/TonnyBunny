<template>
	<v-container
		class="d-flex justify-content-center customFormWrap w-100 flex-column"
		style="margin-top: 40px">
		<!-- <div> -->
		<!-- <title-banner title="🐰 번역 의뢰 요청" text="의뢰의 상세내용을 확인할 수 있습니다" /> -->
		<!-- </div> -->
		<div class="d-flex justify-content-center row">
			<div class="customForm bunnyDetail col-md-6 col-12" style="margin-right: 30px">
				<div class="d-flex justify-content-between">
					<SquareTag text="번역의뢰" sub></SquareTag>
					<SquareTag
						v-if="allCode[getBunnyDetail?.taskStateCode] == `모집중`"
						success></SquareTag>
					<SquareTag
						v-if="allCode[getBunnyDetail?.taskStateCode] == `진행중`"
						info></SquareTag>
					<SquareTag
						v-if="allCode[getBunnyDetail?.taskStateCode] == `완료됨`"
						white></SquareTag>
				</div>
				<title-text
					type="h1"
					:title="getBunnyDetail?.title"
					top="10"
					bottom="10"></title-text>
				<div class="label">
					{{
						getBunnyDetail?.createdAt.substr(0, 10) +
						" " +
						getBunnyDetail?.createdAt.substr(11, 12)
					}}
				</div>

				<div class="d-flex align-items-center mt-5 mb-5">
					<div class="d-flex align-items-center justify-space-between w-100">
						<div class="d-flex align-items-center">
							<user-profile-img
								class="profileImg me-3"
								width="40"
								height="40"
								:profileImagePath="getBunnyDetail?.client?.profileImagePath" />

							<div>
								<h4 class="overflow" v-if="getBunnyDetail?.client?.nickName">
									{{ getBunnyDetail?.client?.nickName }}
								</h4>
								<h4 class="overflow" v-else>미지정</h4>
							</div>
						</div>

						<div class="edits" v-show="isCreator">
							<div
								@click.prevent="toggleEditOpen"
								v-click-outside="onClickOutside"
								style="cursor: pointer">
								<span class="material-symbols-outlined fs-4"> more_vert </span>
							</div>
							<div class="editPopOverWrap" style="cursor: pointer">
								<div class="editPopOver" v-show="isEditOpen">
									<div @click="openRemoveBunnyModal">의뢰 삭제</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="contents">
					<!-- {{ getBunnyDetail }} -->
					<v-table>
						<thead>
							<tr class="">
								<th class="col-4 fw-bold">제목</th>
								<th class="col-8 fw-bold">내용</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>언어</td>
								<td class="d-flex flex-row align-items-center">
									<square-tag
										:text="getStartLangCode"
										sub
										class="me-2"></square-tag>
									<div class="me-2">
										<span class="material-symbols-outlined">
											compare_arrows
										</span>
									</div>
									<square-tag :text="getEndLangCode" sub></square-tag>
								</td>
							</tr>

							<tr>
								<td>예약 날짜</td>
								<td>{{ startDate }}</td>
							</tr>

							<tr>
								<td>마감 날짜</td>
								<td>{{ endDate }}</td>
							</tr>

							<tr>
								<td>지불 캐럿</td>
								<td>
									{{ getBunnyDetail.estimatePrice }}
									<span class="label">캐럿</span>
								</td>
							</tr>

							<tr>
								<td>번역 카테고리</td>
								<td>
									<div
										v-if="getBunnyDetail.bunnySituCode == ''"
										class="fst-italic"
										style="color: var(--sub-color)">
										번역 카테고리가 없습니다.
									</div>
									<square-tag
										v-else
										:text="allCode[getBunnyDetail?.bunnySituCode]"
										sub></square-tag>
								</td>
							</tr>

							<tr>
								<td>상황 설명</td>
								<td class="">
									<div
										v-if="getBunnyDetail.content == ''"
										class="fst-italic"
										style="color: var(--sub-color)">
										상황 설명이 없습니다.
									</div>
									<div v-else class="mt-3">{{ getBunnyDetail.content }}</div>
								</td>
							</tr>
						</tbody>
					</v-table>
				</div>

				<!-- 신청자면서 의뢰가 완료되지 않으면 삭제하기 버튼 보임 -->
				<!-- <div v-if="isCreator && allCode[getBunnyDetail.taskStateCode] != `완료됨`">
					<br /><br />
					<medium-btn
						class="w-100"
						color="outline"
						font="active"
						text="의뢰 삭제하기"
						@click.prevent="openRemoveBunnyModal"></medium-btn>
				</div> -->

				<!-- 헬퍼면서 아직 모집중이면 신청하기 or 신청 삭제하기 -->
				<!-- <div
                    v-else-if="
                        $store.state.account.userInfo.userCode === `0010002` &&
                        allCode[getBunnyDetail.taskStateCode] == `모집중`
                    ">
                    <div v-if="isApplyed(getBunnyDetail?.bunnyApplyList)">
                        <br /><br />
                        <medium-btn
                            class="w-100"
                            color="main"
                            font="white"
                            text="신청 삭제하기"
                            @click.prevent="openRemoveApplyModal"></medium-btn>
                    </div>
                    <div v-else>
                        <br /><br />
                        <medium-btn
                            class="w-100"
                            color="carrot"
                            font="white"
                            text="신청 제안하기"
                            @click.prevent="goToBunnyApplyPage"></medium-btn>
                    </div>
                </div> -->
			</div>
			<!-- 모집중일때 -->
			<div
				class="col-md-6 col-12 apply customForm"
				style="margin-left: 30px"
				v-show="allCode[getBunnyDetail.taskStateCode] == `모집중`">
				<!-- 작성자는 헬퍼 신청 목록 열람가능 -->
				<div v-show="isCreator">
					<div class="w-100">
						<div class="d-flex justify-content-between">
							<div class="w-75">
								<div class="d-flex align-items-center justify-content-between">
									<h1>가격을 제안한 헬퍼들</h1>
									<!-- <div class="label">더보기</div> -->
								</div>

								<hr />
								<!-- <TitleText
                                    type="h2"
                                    top="0"
                                    bottom="0"
                                    :title="`가격을 제안한 헬퍼들(${getBunnyDetail?.bunnyApplyList.length})`"></TitleText> -->
							</div>
							<div>
								<!-- <router-link
                                    :to="{
                                        name: 'NBunnyHelperListPage',
                                        params: { id: getBunnyDetail?.seq },
                                    }"
                                    style="color: var(--sub-text)"
                                    >더보기</router-link
                                > -->
							</div>
						</div>
						<div v-if="getBunnyDetail?.bunnyApplyList.length > 0" class="customScroll">
							<div
								v-for="(apply, index) in getBunnyDetail?.bunnyApplyList"
								:key="index"
								class="w-100">
								<helper-card
									removeClose
									class="w-100 mb-3"
									:userInfo="apply"
									:fareText="apply.estimatePrice"
									rightBtnText="상담하기"
									@remove-card="close"
									@toggle-like-btn="toggleLike"
									@click-btn1="goHelperProfile"
									@click-btn2="enterChatRoom"></helper-card>
							</div>
						</div>
						<div v-else style="color: var(--sub-color)">
							가격을 제안한 헬퍼가 없습니다.
						</div>
					</div>
				</div>
				<!-- 헬퍼는 본인의 신청글만 보임 -->
				<div class="col-md-6 col-12 apply" v-if="isApplyed(getBunnyDetail?.bunnyApplyList)">
					<h1>나의 신청 히스토리</h1>
					<hr />

					<div class="w-100">
						<div
							v-for="(apply, index) in getBunnyDetail?.bunnyApplyList"
							:key="index"
							class="w-100">
							<helper-card
								v-show="apply.userSeq == $store.state.account.userInfo.seq"
								class="w-100 mb-3"
								:userInfo="apply"
								:fareText="apply.estimatePrice"
								rightBtnText=" 고객과 상담하기 "
								@remove-card="openRemoveApplyModal"
								@toggle-like-btn="toggleLike"
								@click-btn1="goHelperProfile"
								@click-btn2="enterChatRoom"></helper-card>
						</div>
					</div>
				</div>
				<div v-else v-show="this.getBunnyDetail.client.seq != this.userInfo.seq">
					<div class="applys" v-show="isHelper">
						<h1 class="mb-4">
							<i class="fa-solid fa-carrot fs-4 ms-1 me-1"></i>
							가격 제안하기
						</h1>

						<div>
							<div class="d-flex align-items-center mb-3">
								<input
									type="text"
									placeholder="제안할 캐럿을 입력해주세요. ex) 1000"
									v-model="unitPrice"
									@keydown.enter="goToBunnyApplyPage" />
							</div>
							<medium-btn
								class="w-100"
								color="outline"
								font="active"
								text="헬퍼 신청하기"
								@click.prevent="goToBunnyApplyPage"></medium-btn>
							<!-- <large-btn text="헬퍼 신청하기" class="d-lg-none"></large-btn> -->
						</div>
					</div>

					<h1>나의 신청 히스토리</h1>
					<hr />

					<div style="color: var(--sub-color)">번역 신청을 하지 않았습니다.</div>
				</div>
			</div>
			<!-- 진행중일때 -->
			<div
				class="col-md-6 col-12 apply"
				v-show="allCode[getBunnyDetail.taskStateCode] == `진행중`">
				<!-- 작성자는 수락한 견적서 내용 보임 -->
				<div v-show="isCreator">
					<div class="w-100">
						<div
							v-for="(quotation, index) in getBunnyDetail?.bunnyQuotationList"
							:key="index"
							class="w-100">
							<helper-card
								v-show="allCode[quotation?.quotationStateCode] == `선택`"
								class="w-100 mb-3"
								:userInfo="quotation"
								:fareText="quotation.totalPrice"
								rightBtnText="상담하기"
								@remove-card="close"
								@toggle-like-btn="toggleLike"
								@click-btn1="goHelperProfile"
								@click-btn2="enterChatRoom"></helper-card>
						</div>
					</div>
					<medium-btn
						class="w-100"
						color="carrot"
						font="white"
						text="의뢰 완료하기"
						@click.prevent="openCompleteModal(index)"></medium-btn>
				</div>
				<!-- 담당자는 본인의 견적서가 보임  -->
				<div class="col-md-6 col-12 apply" v-show="isManager">
					<div class="w-100">
						<div
							v-for="(quotation, index) in getBunnyDetail?.bunnyQuotationList"
							:key="index"
							class="w-100">
							<helper-card
								v-show="allCode[quotation?.quotationStateCode] == `선택`"
								class="w-100 mb-3"
								:userInfo="quotation"
								:fareText="quotation.totalPrice"
								rightBtnText="고객과 상담하기"
								@remove-card="close"
								@toggle-like-btn="toggleLike"
								@click-btn1="goHelperProfile"
								@click-btn2="enterChatRoom"></helper-card>
						</div>
					</div>
				</div>
			</div>
			<div
				class="col-md-6 col-12 apply"
				v-show="allCode[getBunnyDetail.taskStateCode] == `완료됨`"></div>
			<AlarmModal
				v-show="modalName == `removeBunnyModal`"
				title="주의"
				type="danger"
				btnText1="확인"
				btnText2="취소"
				btnColor1="carrot"
				btnColor2="outline"
				btnFontColor1="white"
				btnFontColor2="active"
				@clickBtn1="deleteBunny(getBunnyDetail?.seq)"
				@clickBtn2="closeModal">
				<template #content> 의뢰를 삭제하시겠습니까? </template>
			</AlarmModal>
			<AlarmModal
				v-show="modalName == `removeApplyModal`"
				title="주의"
				type="danger"
				btnText1="확인"
				btnText2="취소"
				btnColor1="carrot"
				btnColor2="outline"
				btnFontColor1="white"
				btnFontColor2="active"
				@clickBtn1="deleteApply"
				@clickBtn2="closeModal">
				<template #content> 제안을 취소하시겠습니까? </template>
			</AlarmModal>
			<AlarmModal
				v-show="modalName == `completeBunnyModal`"
				title="완료"
				type="success"
				btnText1="확인"
				btnText2="취소"
				btnColor1="carrot"
				btnColor2="outline"
				btnFontColor1="white"
				btnFontColor2="active"
				@clickBtn1="completeBunny"
				@clickBtn2="closeModal">
				<template #content>
					의뢰는 해결되셨나요? <br /><br />
					의뢰를 완료하시겠습니까?
				</template>
			</AlarmModal>
		</div>
	</v-container>
</template>

<script>
import utils from "@/common/utils";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
// import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";
import UserProfileImg from "@/components/common/UserProfileImg.vue";

export default {
	name: "NBunnyDetailPage",

	components: {
		SquareTag,
		TitleText,
		HelperCard,
		// TitleBanner,
		AlarmModal,
		MediumBtn,
		// eslint-disable-next-line
		UserProfileImg
	},

	computed: {
		...mapGetters({
			getBunnyDetail: "getBunnyDetail",
			allCode: "getAllCode",
			userInfo: "getUserInfo",
			getTaskStateCode: "getTaskStateCode",
			langCode: "getLangCode",
			taskStateCode: "getTaskStateCode",
			bunnySituCode: "getBunnySituCode",
			isHelper: "getIsHelper"
		}),

		getStartLangCode() {
			let code;
			this.langCode.forEach((e) => {
				if (e.value == this.getBunnyDetail.startLangCode) code = e.name;
			});
			return code;
		},
		getEndLangCode() {
			let code;
			this.langCode.forEach((e) => {
				if (e.value == this.getBunnyDetail.endLangCode) code = e.name;
			});
			return code;
		},
		getBunnySituCode() {
			let code;
			this.bunnySituCode.forEach((e) => {
				if (e.value == this.getBunnyDetail.bunnySituCode) code = e.name;
			});
			return code;
		},
		startDate() {
			let date = utils.dateSplit(this.getBunnyDetail.startDateTime);
			return date.year + "년 " + date.month + "월 " + date.day + "일";
		},
		startTime() {
			let time = utils.dateTimeSplit(this.getBunnyDetail.startDateTime);
			return time.hour + "시 " + time.minute + "분";
		},
		endDate() {
			let date = utils.dateSplit(this.getBunnyDetail.endDateTime);
			return date.year + "년 " + date.month + "월 " + date.day + "일";
		}
	},

	data() {
		return {
			startLangCode: "",
			endLangCode: "",
			bunnySituCode: "",

			// startDate: "",
			// endDate: "",

			unitPrice: "",
			estimatePrice: "",
			title: "",
			content: "",

			agreeValue: false,
			isCreator: false,
			isManager: false,
			modalName: "",

			isEditOpen: false
		};
	},

	async created() {
		await this.$store.dispatch("getBunnyDetail", this.$route.params.id);
		console.log("getBunnyDetail:::::", this.getBunnyDetail);
		this.checkIsCreator();
		this.checkIsManager();
		this.$store.commit("CLOSE_ALARM_MODAL");
	},

	methods: {
		toggleEditOpen() {
			this.isEditOpen = !this.isEditOpen;
		},
		onClickOutside() {
			this.isEditOpen = false;
		},

		goHelperProfile() {
			// TODO 헬퍼 프로필 페이지로 이동하기
		},
		openCompleteModal() {
			this.modalName = "completeBunnyModal";
			this.$store.commit("OPEN_ALARM_MODAL");
		},
		openRemoveBunnyModal() {
			this.modalName = "removeBunnyModal";
			this.$store.commit("OPEN_ALARM_MODAL");
		},
		openRemoveApplyModal() {
			this.modalName = "removeApplyModal";
			this.$store.commit("OPEN_ALARM_MODAL");
		},

		checkIsCreator() {
			let bunnyCreatorSeq = this.getBunnyDetail.client.seq;

			if (bunnyCreatorSeq == this.$store.state.account.userInfo.seq) {
				this.isCreator = true;
			}
		},
		checkIsManager() {
			let bunnyManagerSeq = this.getBunnyDetail.helper.seq;

			if (bunnyManagerSeq == this.$store.state.account.userInfo.seq) {
				this.isManager = true;
			}
		},

		getKeyByValue(obj, value) {
			const findObj = obj.find((e) => e.value == value);
			return findObj.name;
		},
		isApplyed(ApplyList) {
			return ApplyList.find(
				(apply) => apply.userSeq === this.$store.state.account.userInfo.seq
			);
		},
		async completeBunny() {
			this.$store.commit("OPEN_ALARM_MODAL");
			const s_quotation = this.getBunnyDetail.bunnyQuotationList.find(
				(quotation) => this.allCode[quotation.quotationStateCode] == `선택`
			);

			console.log(s_quotation);

			await this.$store.dispatch("completeBunny", {
				bunnySeq: this.getBunnyDetail.seq,
				seq: s_quotation.seq
			});

			// this.$router.go(0);

			// 리뷰 페이지로 이동
			this.$store.commit("CLOSE_ALARM_MODAL");
			this.$router.push({
				name: "ReviewPage",
				params: {
					helperSeq: s_quotation.helperSeq,
					historySeq: this.$store.state.bunny.historySeq
				}
			});
		},
		async deleteBunny(bunnySeq) {
			this.$store.commit("OPEN_ALARM_MODAL");
			await this.$store.dispatch("removeBunny", bunnySeq);
			this.$router.push({ name: "HomePage" });
		},
		async deleteApply() {
			this.$store.commit("CLOSE_ALARM_MODAL");
			const payload = {
				bunnySeq: this.getBunnyDetail?.seq,
				bunnyApplySeq: 0
			};
			this.getBunnyDetail?.bunnyApplyList.forEach((apply) => {
				if (apply.userSeq == this.$store.state.account.userInfo.seq) {
					payload.bunnyApplySeq = apply.seq;
				}
			});
			await this.$store.dispatch("removeBunnyApply", payload);
			this.$store.dispatch("getBunnyDetail", this.$route.params.id);

			let alert = {
				userSeq: this.userInfo.seq,
				clientSeq: this.getBunnyDetail.client.seq,
				helperSeq: this.userInfo.seq,
				taskCode: this.getBunnyDetail.taskCode,
				bunnySeq: this.getBunnyDetail.seq,
				title: this.getBunnyDetail.title,
				content: '"' + this.getBunnyDetail.title + '" 건에 헬퍼 신청했습니다.',
				clientNickname: this.getBunnyDetail.client.nickName,
				helperNickname: this.userInfo.nickName
			};

			await this.$store.dispatch("applyCancelAlert", alert);
		},
		async goToBunnyApplyPage() {
			// this.$router.push({
			//     name: "NBunnyMatchingPage",
			// });

			if (this.unitPrice === "") {
				alert("금액을 입력해주세요!");
				return;
			}

			const payload = {
				userSeq: this.$store.state.account.userInfo.seq,
				bunnySeq: this.getBunnyDetail.seq,
				estimatePrice: this.unitPrice
			};

			this.$store.dispatch("insertBunnyApply", payload);

			let alert = {
				userSeq: this.userInfo.seq,
				clientSeq: this.getBunnyDetail.client.seq,
				helperSeq: this.userInfo.seq,
				taskCode: this.getBunnyDetail.taskCode,
				bunnySeq: this.getBunnyDetail.seq,
				title: this.getBunnyDetail.title,
				content: '"' + this.getBunnyDetail.title + '" 건에 헬퍼 신청했습니다.',
				clientNickname: this.getBunnyDetail.client.nickName,
				helperNickname: this.userInfo.nickName
			};

			let clientAlert = {
				userSeq: this.getBunnyDetail.client.seq,
				clientSeq: this.getBunnyDetail.client.seq,
				helperSeq: this.userInfo.seq,
				taskCode: this.getBunnyDetail.taskCode,
				title: this.getBunnyDetail.title,
				content: '"' + this.getBunnyDetail.title + '" 건에 헬퍼 신청이 들어왔습니다.',
				clientNickname: this.getBunnyDetail.client.nickName,
				helperNickname: this.userInfo.nickName
			};

			await this.$store.dispatch("insertAlert", alert);
			await this.$store.dispatch("insertAlert", clientAlert);
			await this.$store.dispatch("applyAlert", alert);

			// window.location.reload();

			await this.$store.dispatch("getBunnyDetail", this.$route.params.id);

			// this.$store.commit("OPEN_ALARM_MODAL");
			// this.$router.replace({
			//     name: "NBunnyDetailPage",
			//     params: { id: this.getBunnyDetail.seq },
			// });

			this.unitPrice = "";
		},
		closeModal() {
			this.$store.commit("CLOSE_ALARM_MODAL");
		},
		async enterChatRoom(helperSeq) {
			const clientSeq = this.getBunnyDetail.client.seq;
			const userSeq = this.$store.getters.getUserInfo.seq;
			const anotherUserSeq = userSeq == clientSeq ? helperSeq : clientSeq;

			await this.$store.dispatch("getChatRoomInfo", {
				userSeq: userSeq,
				anotherUserSeq: anotherUserSeq
			});
			if (userSeq == clientSeq) {
				this.$store.commit("SET_TRUE_SEND_URL_MESSAGE");
				this.$store.commit("SET_URL_MESSAGE", {
					urlPage: "NBunnyDetailPage",
					urlPageSeq: this.getBunnyDetail.seq,
					message:
						"'" +
						this.getBunnyDetail.title +
						"' 을/를 통해서 온 고객입니다. 상담을 요청합니다!"
				});
			}
			this.$router.push({ name: "ChatDetailPage" });
		}
	}
};
</script>

<style lang="scss" scoped>
.applys {
	// margin-top: 40px;
	margin-bottom: 40px;

	// padding: 100px;
	padding: 32px 24px;
	border: 1px solid rgba(0, 0, 0, 0.08);
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
	background-color: var(--thin-color);
	border-radius: 6px;
}

.editPopOverWrap {
	position: relative;
	right: 120px;

	.editPopOver {
		position: absolute;
		// left: 100px;
		// right: 535px;
		// right: 100px;
		width: 130px;
		padding: 10px;

		background-color: #fff;
		border: 1px solid rgba(0, 0, 0, 0.2);
		box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
		z-index: 99;

		div:nth-child(1) {
			margin-top: 0;
		}
		> div {
			// border-bottom: 1px solid var(--light-color);
			margin-top: 4px;
			// line-height: 20px;
			// margin-bottom: 3px;

			&:hover {
				text-decoration: underline;
			}
		}
	}
}
.bunnyDetail {
	cursor: default;
	padding: 32px 24px;
	margin-right: 24px;

	// box-shadow: 1px 1px 1px black;
	border: 1px solid rgba(0, 0, 0, 0.13);
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
	border-radius: 6px;

	.infos {
		.tag {
			display: flex;
			justify-content: space-between;
		}

		.titles {
			word-break: break-all;
		}

		.metas {
			display: flex;
			justify-content: space-between;
			align-items: center;
			height: 54px;

			.writers {
				display: flex;
				align-items: center;
				cursor: pointer;
			}
			.edits {
				// display: flex;
				// align-items: center;
				cursor: pointer;
				// position: absolute;

				.editPopOverWrap {
					position: relative;
					right: 120px;

					.editPopOver {
						position: absolute;
						// left: 100px;
						// right: 535px;
						// right: 100px;
						width: 130px;
						padding: 10px;

						background-color: #fff;
						border: 1px solid rgba(0, 0, 0, 0.2);
						box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
						z-index: 99;

						div:nth-child(1) {
							margin-top: 0;
						}
						> div {
							// border-bottom: 1px solid var(--light-color);
							margin-top: 4px;
							// line-height: 20px;
							// margin-bottom: 3px;

							&:hover {
								text-decoration: underline;
							}
						}
					}
				}
			}
		}

		.contents {
			padding-right: 16px;

			table {
				th,
				td,
				tr {
					font-size: 1rem;
				}
			}

			.langs {
				display: flex;
				flex-direction: column;
				// justify-content: center;
				// align-items: center;
			}
		}
	}
}

.apply {
	width: 500px;
	padding: 0px;
	margin-right: 24px;
}
</style>
