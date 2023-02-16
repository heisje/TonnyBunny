import Toastify from "toastify-js";

// import VueRouter from "vue-router";
import router from "@/routers/router";

import http from "@/common/axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

// let $this = this;

export default {
	/*
        Alert
    */
	// alert list 변경
	SET_ALERT_LIST(state, alertList) {
		state.alert.alertList = alertList;
	},

	CONNECT_ALERT_STOMP_SOCKET(state) {
		const serverURL = http.getUri() + "/stomp";
		let socket = new SockJS(serverURL);
		let stompClient = Stomp.over(socket);

		// state.alert.socket = socket;
		state.alert.stompClient = stompClient;
		stompClient.hasDebug = false;

		let seq = state.account.userInfo.seq;
		let isHelper = state.account.isHelper;

		// FIXME: chat 이랑 공통으로 사용해도 될듯?
		stompClient.connect(
			{},
			() => {
				console.log("알람 소켓 연결");

				// 고객이 수락
				stompClient.subscribe(`/sub/alert/accept/${seq}`, (res) => {
					let request = JSON.parse(res.body);
					let clientNickname = request.clientNickname;
					let helperNickname = request.helperNickname;
					let helperSeq = request.helperSeq;
					let title = request.title;
					let ytonnySeq = request.ytonnySeq;
					// let bunnySeq = request.bunnySeq;
					// console.log("찍히나", ytonnySeq, bunnySeq);

					let text;
					if (helperSeq == seq) {
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `[${clientNickname}]님이 즉시통역 신청을 수락했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `[${clientNickname}]님이 [${title}]건 통역 예약 신청을 수락했습니다.`;
						} else {
							// 번역
							text = `[${clientNickname}]님이 [${title}]건 번역 신청을 수락했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();

						// setTimeout(() => window.location.reload(), 1000);

						// 만약 router 가 본 페이지라면
						// $this.$router.push({
						// name: "YTonnyDetailPage",
						// params: { id: $this.yTonnyDetail.seq }
						// });

						if (request.taskCode == "0030001") {
							// 즉시통역
							// text = `[${clientNickname}]님이 즉시통역 신청을 수락했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							this.dispatch("getYTonnyDetail", ytonnySeq);
						} else {
							// 번역
							// text = `[${clientNickname}]님이 [${title}]건 번역 신청을 수락했습니다.`;
						}
					} else if (!isHelper) {
						let text;
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `[${helperNickname}]님의 즉시 통역 신청을 수락했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `[${helperNickname}]님의 통역 예약 신청을 수락했습니다.`;
						} else {
							// 번역
							text = `[${helperNickname}]님의 번역 신청을 수락했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();
					}
				});

				// 고객이 거절
				stompClient.subscribe(`/sub/alert/reject/${seq}`, (res) => {
					let request = JSON.parse(res.body);
					let clientNickname = request.clientNickname;
					let helperNickname = request.helperNickname;
					// let helperSeq = request.helperSeq;
					let title = request.title;
					let ytonnySeq = request.ytonnySeq;
					// let bunnySeq = request.bunnySeq;
					// console.log("찍히나2", ytonnySeq, bunnySeq);

					let text;
					if (isHelper) {
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `[${clientNickname}]님이 즉시통역 신청을 거절했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `[${clientNickname}]님이 [${title}]건 통역 예약 신청을 거절했습니다.`;
						} else {
							// 번역
							text = `[${clientNickname}]님이 [${title}]건 번역 신청을 거절했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();

						if (request.taskCode == "0030001") {
							// 즉시통역
						} else if (request.taskCode == "0030002") {
							// 통역예약
							this.dispatch("getYTonnyDetail", ytonnySeq);
						} else {
							// 번역
						}
					} else if (!isHelper) {
						let text;
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `[${helperNickname}]님의 즉시 통역 신청을 거절했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `[${helperNickname}]님의 통역 예약 신청을 거절했습니다.`;
						} else {
							// 번역
							text = `[${helperNickname}]님의 번역 신청을 거절했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();
					}
				});

				// 헬퍼가 신청
				stompClient.subscribe(`/sub/alert/apply/${seq}`, async (res) => {
					let request = JSON.parse(res.body);
					// let clientNickname = request.clientNickname;
					let helperNickname = request.helperNickname;
					// let helperSeq = request.helperSeq;
					// let title = request.title;
					let ytonnySeq = request.ytonnySeq;
					let bunnySeq = request.bunnySeq;
					// console.log("찍히나??", ytonnySeq, bunnySeq);

					let text;
					if (isHelper) {
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `즉시통역 신청을 성공했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `통역 예약 신청을 성공했습니다.`;
						} else {
							// 번역
							text = `번역 신청을 성공했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 3px 1px rgba(0, 0, 0, 0.1)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();
					} else if (!isHelper) {
						let text;
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `[${helperNickname}]님이 즉시 통역 신청을 했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `[${helperNickname}]님이 통역 예약 신청을 했습니다.`;
						} else {
							// 번역
							text = `[${helperNickname}]님이 번역 신청을 했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							// backgroundColor: "var(--info-color)",
							style: {
								// background: "linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();

						if (request.taskCode == "0030001") {
							// 즉시통역
							// text = `즉시통역 신청을 성공했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							await this.dispatch("getYTonnyApplyList", {
								page: 1,
								size: 5,
								yTonnySeq: ytonnySeq
							});
							await this.dispatch("getYTonnyApplyListTotalCount", ytonnySeq);
						} else {
							// 번역
							// text = `번역 신청을 성공했습니다.`;
							await this.dispatch("getBunnyDetail", bunnySeq);
						}
					}
				});

				stompClient.subscribe(`/sub/alert/apply/${seq}/cancel`, async (res) => {
					let request = JSON.parse(res.body);
					// let clientNickname = request.clientNickname;
					let helperNickname = request.helperNickname;
					let helperSeq = request.helperSeq;
					// let title = request.title;
					let ytonnySeq = request.ytonnySeq;
					let bunnySeq = request.bunnySeq;

					let text;
					if (isHelper && helperSeq != seq) {
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `즉시통역 신청을 취소했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `통역 예약 신청을 취소했습니다.`;
						} else {
							// 번역
							text = `번역 신청을 취소했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();

						// 고객이라면
					} else if (!isHelper) {
						let text;
						if (request.taskCode == "0030001") {
							// 즉시통역
							text = `[${helperNickname}]님이 즉시 통역 신청을 취소했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							text = `[${helperNickname}]님이 통역 예약 신청을 취소했습니다.`;
						} else {
							// 번역
							text = `[${helperNickname}]님이 번역 신청을 취소했습니다.`;
						}

						Toastify({
							text: text,
							duration: 5000,
							newWindow: true,
							close: true,
							gravity: "top", // `top` or `bottom`
							position: "right", // `left`, `center` or `right`
							stopOnFocus: true, // Prevents dismissing of toast on hover
							style: {
								background:
									"linear-gradient(45deg, rgba(128,193,254,1) 0%, rgba(88,194,237,1) 100%)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.1rem",
								fontWeight: "600"
							},
							onClick: function () {
								router.replace({ name: "AlertPage" });
							} // Callback after click
						}).showToast();

						if (request.taskCode == "0030001") {
							// 즉시통역
							// text = `즉시통역 신청을 성공했습니다.`;
						} else if (request.taskCode == "0030002") {
							// 통역예약
							await this.dispatch("getYTonnyApplyList", {
								page: 1,
								size: 5,
								yTonnySeq: ytonnySeq
							});
							await this.dispatch("getYTonnyApplyListTotalCount", ytonnySeq);
						} else {
							// 번역
							await this.dispatch("getBunnyDetail", bunnySeq);
						}
					}
				});
			},
			(error) => {
				// 소켓 연결 실패
				console.log("알람 소켓 연결 실패", error);
				this.connected = false;
			}
		);
	},

	DISCONNECT_ALERT_STOMP_SOCKET(state) {
		if (state.alert.stompClient != null) {
			state.alert.stompClient.disconnect();
		}
	}
};
