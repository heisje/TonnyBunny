<template>
	<div class="acceptContainer container">
		<div class="acceptWrap">
			<div class="acceptContent row">
				<div class="customForm col-md-6 col-12 d-flex flex-column justify-content-center">
					<title-text
						title="즉시통역 신청이 들어왔어요!"
						text="고객들이 신청한 즉시통역 내역이에요" />
					<hr />
					<v-alert
						v-show="isAlertOpen"
						closable
						icon="mdi-alert"
						text="즉시통역 신청은 3개까지만 가능합니다."
						type="error"
						variant="tonal"
						class="alert"></v-alert>
					<v-lazy
						v-model="jtonnyQuestList"
						:options="{ threshold: 0.5 }"
						transition="fade-transition">
						<div v-if="jtonnyQuestListLength > 0" class="mb-5">
							<transition-group name="slide-up">
								<div v-for="(quest, index) in jtonnyQuestList" :key="index">
									<!-- {{ quest }} -->

									<div class="quest">
										<div class="questProfileWrap">
											<div class="questProfile mt-3">
												<div class="profile ms-3 me-4">
													<img
														class="profileImg"
														src="@/assets/noProfile_white.png"
														width="70"
														height="70" />
													<div class="userType ms-2">
														<span class="label">고객</span>
														<h4 class="" v-if="quest?.client?.nickName">
															{{ quest?.client?.nickName }}
														</h4>
														<h4 class="" v-else>미지정</h4>
													</div>
												</div>

												<div class="d-flex flex-column">
													<div class="d-flex flex-row align-items-center">
														<square-tag
															:text="quest.startLangCode"
															sub
															class="me-2"></square-tag>
														<div class="me-2">
															<span class="material-symbols-outlined">
																compare_arrows
															</span>
														</div>
														<square-tag
															:text="quest.endLangCode"
															sub></square-tag>
													</div>
													<div>{{ quest.unitPrice }}</div>
													<div>{{ quest.estimateTime }}</div>
													<div>{{ quest.tonnySituCode }}</div>
													<!-- <div class="userType me-2">
														<span class="label text-end">헬퍼</span>
														<h4
															class="text-end"
															v-if="quest?.helper?.nickName">
															{{ quest?.helper?.nickName }}
														</h4>
														<h4 class="" v-else>미지정</h4>
													</div> -->
												</div>
											</div>
										</div>

										<medium-btn
											text="더보기"
											color="outline"
											font="active"
											class="w-100"
											@click="toggleMoreOpen"></medium-btn>
										<v-table v-show="isMoreOpen">
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
															:text="quest.startLangCode"
															sub
															class="me-2"></square-tag>
														<div class="me-2">
															<span class="material-symbols-outlined">
																compare_arrows
															</span>
														</div>
														<square-tag
															:text="quest.endLangCode"
															sub></square-tag>
													</td>
												</tr>

												<tr>
													<td>예상 소요 시간</td>
													<td>
														{{
															"대략 " +
															quest.estimateTime.split(":")[0] +
															"시간 " +
															quest.estimateTime.split(":")[1] +
															"분"
														}}
													</td>
												</tr>

												<tr>
													<td>지불 캐럿</td>
													<td>
														{{ quest.unitPrice }}
														<span class="label">CRT</span>
													</td>
												</tr>

												<tr>
													<td>상황 카테고리</td>
													<td>
														<square-tag :text="quest.tonnySituCode" sub>
														</square-tag>
													</td>
												</tr>

												<tr>
													<td>상황 설명</td>
													<td class="pt-3">{{ quest.content }}</td>
												</tr>
											</tbody>
										</v-table>
									</div>
									<div class="d-flex btns row ms-0 me-0 ps-0 pe-0">
										<div
											class="col-6 reject"
											@click="cancelApply(quest.client.seq)">
											거절
										</div>
										<div class="col-6 accept" @click="apply(quest.client.seq)">
											신청
										</div>
									</div>
								</div>

								<!-- <div
                                    v-for="jtonny in Object.values(jtonnyQuestList)"
                                    :key="jtonny.client.seq">
                                    <quest-card
                                        :questDetail="jtonny"
                                        rightBtnText="신청하기"
                                        @clickBtn2="apply(jtonny.client.seq)" />
                                </div> -->
							</transition-group>
						</div>
						<div v-else>들어온 즉시통역 신청 카드가 없습니다.</div>
					</v-lazy>

					<!-- <JTonnyLoading /> -->

					<!-- <router-link :to="{ name: 'JTonnyMatchingPage' }">
						<button>다음페이지</button>
					</router-link> -->
				</div>
				<div class="customForm col-md-6 col-12">
					<div>
						<title-text
							title="신청한 즉시통역 목록"
							text="현재 신청한 즉시통역 내역이에요"></title-text>
						<hr />

						<transition-group name="slide-up">
							<div
								v-for="(apply, index) in Object.values(jtonnyApplyQuestList)"
								:key="index">
								<div v-if="index < 3" class="row px-3">
									<!-- {{ apply }} -->

									<div class="d-flex flex-row align-items-center quest py-3 px-2">
										<div
											class="col-2 d-flex flex-column align-items-center justify-content-center">
											<img
												src="@/assets/noProfile.png"
												width="50"
												height="50" />

											<!-- <img :src="apply.helper.profileImagePath" /> -->
											<!-- <div>{{ apply.helper.nickName }}</div> -->
											<!-- <div>{{ apply.helper.helperInfo.oneLineIntroduction }}</div> -->
										</div>
										<div class="col-9 helperInfo">
											<div class="d-flex flex-row">
												<div>{{ apply.client.nickName }}</div>
											</div>
											<div class="d-flex">
												<div>{{ apply.startLangCode }}</div>
												<div>{{ apply.endLangCode }}</div>
											</div>
											<div class="d-flex">
												<div>{{ apply.estimateTime }}</div>
												<div>{{ apply.tonnySituCode }}</div>
												<div>{{ apply.unitPrice }}</div>
											</div>

											<!-- <div>{{ apply.helper.helperInfo.avgScore }}</div> -->
											<!-- <div>{{ apply.helper.helperInfo.reviewCount }}</div> -->
											<!-- <div>{{ apply.totalPrice }}</div> -->
										</div>

										<div
											class="closeBtn col-1"
											@click="cancelApply(apply.client.seq)">
											<span class="material-symbols-outlined"> close </span>
										</div>
									</div>
								</div>
							</div>
						</transition-group>
					</div>
					<!-- <div>
                        <title-text
                            title="통역 예약 목록"
                            text="통역 예약도 받아볼까요?"></title-text>
                        <hr />

                        <v-lazy
                            v-model="yTonnyList"
                            :options="{ threshold: 0.5 }"
                            transition="fade-transition">
                            <div v-if="yTonnyList.length > 0">
                                <transition-group name="slide-up">
                                    <div
                                        v-for="(yTonny, index) in yTonnyList"
                                        :key="index"
                                        class="w-100 row">
                                        {{ yTonny }} 

                                        <div class="d-flex flex-row align-items-center yTonny">
                                            <div class="col-12">
                                                <div class="fs-6 d-flex">
                                                    <square-tag
                                                        :text="yTonny.tonnySituCode"
                                                        sub
                                                        class="me-1"></square-tag>
                                                    <router-link
                                                        :to="{
                                                            name: 'YTonnyDetailPage',
                                                            params: { id: yTonny.seq },
                                                        }">
                                                        <div>{{ yTonny.title }}</div>
                                                    </router-link>
                                                </div>
                                                <div>{{ yTonny.content }}</div>
                                                <div class="d-flex justify-space-between">
                                                    <div>{{ yTonny.estimatePrice }} CRT</div>
                                                    <div class="">{{ yTonny.createdAt }}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </transition-group>
                            </div>
                            <div v-else class="mt-5">가격을 제안한 헬퍼가 없습니다.</div>
                        </v-lazy>
                    </div> -->
				</div>
			</div>

			<AlarmModal
				title="주의"
				type="warning"
				btnText1="취소"
				btnText2="확인"
				btnColor1="outline"
				btnColor2="carrot"
				btnFontColor1="main"
				btnFontColor2="white"
				@clickBtn1="this.$store.commit('CLOSE_ALARM_MODAL')"
				@clickBtn2="onAir">
				<template #content>
					수락하시면 바로 즉시통역이 진행됩니다.<br />진행하시겠습니까?
				</template>
			</AlarmModal>
		</div>

		<!-- <div v-if="isFind">
            <title-text title="즉시 통역 찾기" text="지금 당장 번역이 필요한 고객을 찾습니다" />
            <JTonnyLoading />
        </div>
        <div v-else>
            <title-text
                title="즉시 통역 요청이 왔어요!"
                text="통역 요청글을 자세히 보고 가능한 통역을 신청하세요!" />
            <JTonnyLoading />
        </div> -->
		<!-- </div> -->
		<!-- <router-link :to="{ name: 'JTonnyMatchingPage' }"><button>다음페이지</button></router-link> -->

		<!-- <div v-for="jtonny in Object.values(jtonnyList)" :key="jtonny.client.seq"> -->
		<!-- 누르면 취소하기로 바뀌고 cancel? -->
		<!-- <quest-card -->
		<!-- :questDetail="jtonny" -->
		<!-- rightBtnText="신청하기" -->
		<!-- @clickBtn2="apply(jtonny.client.seq)" /> -->
		<!-- </div> -->

		<!-- 얘도 click 하면 unsubscribe() -->
		<!-- <large-btn text="대기 취소" color="carrot" font="white" @click.prevent="openModal" /> -->
	</div>
</template>

<script>
import { mapGetters } from "vuex";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

import SquareTag from "@/components/common/tag/SquareTag.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
	components: {
		TitleText,
		AlarmModal,
		SquareTag,
		MediumBtn
	},

	data() {
		return {
			isMoreOpen: false,
			isAlertOpen: false,
			applyCount: 0,

			stompClient: null, // 페이지 이탈할 때 끊어주기
			jtonnyQuestList: {},
			jtonnyApplyQuestList: {}, // 현재 신청한 목록
			jtonnyRequest: {},
			subs: [], // 페이지 이탈할 때 unsubscribe()

			sessionName: ""
		};
	},

	computed: {
		...mapGetters({
			userInfo: "getUserInfo",
			yTonnyList: "getYTonnyList"
		}),

		jtonnyQuestListLength() {
			return Object.keys(this.jtonnyQuestList).length;
		}
	},

	methods: {
		onAir() {
			this.$store.commit("CLOSE_ALARM_MODAL");
			this.$router.replace({ name: "LivePage", params: { sessionName: this.sessionName } });
		},

		toggleMoreOpen() {
			this.isMoreOpen = !this.isMoreOpen;
		},

		apply(seq) {
			// this.jtonnyRequest 에 단가, this.userInfo.seq 넣기
			let jtonnyApply = this.jtonnyQuestList[seq];

			jtonnyApply.helper = {
				seq: this.userInfo.seq,
				nickName: this.userInfo.nickName
			};

			jtonnyApply.unitPrice = this.userInfo.helperInfo.unitPrice;
			this.jtonnyApplyQuestList[seq] = jtonnyApply;
			this.applyCount++;

			if (this.applyCount > 3) {
				this.applyCount = true;
				this.applyCount = 3;
			}

			this.stompClient.send("/pub/jtonny/apply", JSON.stringify(jtonnyApply), {});
		},

		cancelApply(seq) {
			let jtonnyApply = this.jtonnyQuestList[seq];

			this.stompClient.send("/pub/jtonny/apply/cancel", JSON.stringify(jtonnyApply), {});
			delete this.jtonnyQuestList[seq];
			delete this.jtonnyApplyQuestList[seq];
		},

		unsubscribe() {
			this.subs.forEach((sub) => {
				sub.unsubscribe();
			});
		}
	},

	mounted() {
		const serverURL = "http://localhost:8080/api/stomp";
		let possibleLanguageList = this.userInfo.helperInfo.possibleLanguageList;
		let socket = new SockJS(serverURL);

		this.stompClient = Stomp.over(socket);

		console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

		this.stompClient.connect(
			{},
			() => {
				// 소켓 연결 성공
				this.connected = true;
				console.log("소켓 연결 성공");

				// 본인 seq 를 구독합니다.
				possibleLanguageList.forEach((lang) => {
					let sub = this.stompClient.subscribe(
						`/sub/jtonny/request/${lang.value}`,
						(res) => {
							console.log(`즉시통역(${lang.name}) 요청이 도착했습니다.`, res.body);

							// 받은 데이터를 json 으로 파싱하고 dictionary 에 넣어줍니다.
							let request = JSON.parse(res.body);
							this.jtonnyQuestList[request.client.seq] = request;
						}
					);
					this.subs.push(sub);

					sub = this.stompClient.subscribe(
						`/sub/jtonny/request/${lang.value}/cancel`,
						(res) => {
							console.log("즉시통역 요청이 취소되었습니다.", res.body);

							let request = JSON.parse(res.body);
							delete this.jtonnyQuestList[request.client.seq];
							delete this.jtonnyApplyQuestList[request.client.seq];
						}
					);
					this.subs.push(sub);
				});

				this.stompClient.subscribe(`/sub/jtonny/accept/${this.userInfo.seq}`, (res) => {
					console.log("즉시통역 매칭 완료. 오픈비두 이동", res.body);

					/* 
                        let jtonny = JSON.parse(res.body);
                        
                        오픈비두 이동 router.PUSH 
                        param? query? 는 jtonny

						
                    */

					let data = JSON.parse(res.body);
					this.sessionName = data.uuid;

					this.$store.commit("TOGGLE_ALARM_MODAL");
				});

				this.stompClient.subscribe(`/sub/jtonny/reject/${this.userInfo.seq}`, (res) => {
					let jtonny = JSON.parse(res.body);

					delete this.jtonnyQuestList[jtonny.client.seq];
					delete this.jtonnyApplyQuestList[jtonny.client.seq];
				});
			},
			(error) => {
				// 소켓 연결 실패
				console.log("소켓 연결 실패", error);
				this.connected = false;
			}
		);
	},

	beforeUnmount() {
		this.unsubscribe();
		this.stompClient.disconnect();
	},

	created() {
		window.scrollTo(0, 0);

		this.$store.dispatch("getYTonnyList");
	}
};
</script>

<style lang="scss" scoped>
.acceptContent {
	justify-content: space-evenly;
	align-items: flex-start;
}

.quest {
	cursor: default;
	padding: 24px 24px;

	border: 1px solid rgba(0, 0, 0, 0.13);
	box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
	border-radius: 6px;

	.questProfileWrap {
		background-color: var(--thin-color);
		border-radius: 8px 8px 0 0;
		padding: 8px 8px;
		margin-top: 8px;
	}

	.questProfile {
		display: flex;

		align-items: center;
		width: 100%;
		height: 100px;

		.profile {
			display: flex;
			align-items: center;
			max-width: 140px;

			img {
				// width: 32px;
				// height: 32px;
				border-radius: 100%;
				box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
			}
		}

		.arrow {
			color: var(--sub-color);
		}

		.userType {
			display: flex;
			flex-direction: column;
			width: 100%;
			.overflow {
				width: 80px;
				overflow: hidden; // 을 사용해 영역을 감출 것
				text-overflow: ellipsis; // 로 ... 을 만들기
				white-space: nowrap;
			}
		}

		div:nth-child(1) {
			img {
				margin-right: 8px;
			}
		}
		div:nth-child(3) {
			img {
				margin-left: 8px;
			}
		}
	}
}

.btns {
	cursor: pointer;

	div {
		border: 1px solid var(--light-color);
		padding: 4px;
		text-align: center;
		font-weight: 600;
		transition: all 0.13s;
		font-size: 0.9rem;
		color: var(--active-color);

		&:hover {
			opacity: 0.9;
		}
	}

	div:nth-child(1) {
		background-color: var(--light-color);
		border-radius: 0 0 0 8px;
	}

	div:nth-child(2) {
		background-color: var(--success-color);
		border-radius: 0 0 8px 0;
	}
}

.yTonny {
	border-bottom: 1px solid var(--light-color);
	padding-bottom: 10px;
}
.closeBtn {
	position: relative;
	// right: 24px;
	z-index: 99;
	cursor: pointer;

	span {
		font-variation-settings: "wght" 300;
		transition: all 0.13s;
		color: var(--sub-color);
	}

	&:hover {
		span {
			color: var(--danger-color);
		}
	}
}
</style>
