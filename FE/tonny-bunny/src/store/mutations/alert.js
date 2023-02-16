import Toastify from "toastify-js";

import http from "@/common/axios";
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

let $this = this;

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

		let seq = state.account.userInfo.seq;
		let isHelper = state.account.isHelper;

		console.log("알람 소켓 연결");

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
					let title = request.title;
					let yTonnySeq = request.yTonnySeq;
					let bunnySeq = request.bunnySeq;

					let text;
					if (isHelper) {
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
								background: "linear-gradient(to right, #00b09b, #96c93d)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.3rem",
								fontWeight: "600"
							},
							onClick: function () {
								if (request.taskCode == "0030001") {
									// 즉시통역
									$this.$router.push({ name: "JTonnyApplyPage" });
								} else if (request.taskCode == "0030002") {
									// 통역예약
									$this.$router.push({
										name: "YTonnyDetailPage",
										params: { id: yTonnySeq }
									});
								} else {
									// 번역
									$this.$router.push({
										name: "NBunnyDetailPage",
										params: { id: bunnySeq }
									});
								}
							} // Callback after click
						}).showToast();

						// 만약 router 가 본 페이지라면
						// $this.$router.push({
						// name: "YTonnyDetailPage",
						// params: { id: $this.yTonnyDetail.seq }
						// });
					} else {
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
								background: "linear-gradient(to right, #00b09b, #96c93d)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.3rem",
								fontWeight: "600"
							},
							onClick: function () {} // Callback after click
						}).showToast();
					}

					// window.location.reload();
				});

				// 헬퍼가 신청
				stompClient.subscribe(`/sub/alert/apply/${seq}`, (res) => {
					let request = JSON.parse(res.body);
					// let clientNickname = request.clientNickname;
					let helperNickname = request.helperNickname;
					// let title = request.title;
					let yTonnySeq = request.yTonnySeq;
					let bunnySeq = request.bunnySeq;

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
								background: "linear-gradient(to right, #00b09b, #96c93d)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.3rem",
								fontWeight: "600"
							},
							onClick: function () {
								if (request.taskCode == "0030001") {
									// 즉시통역
									// $this.$router.push({ name: "JTonnyApplyPage" });
								} else if (request.taskCode == "0030002") {
									// 통역예약
									$this.$router.push({
										name: "YTonnyDetailPage",
										params: { id: yTonnySeq }
									});
								} else {
									// 번역
									$this.$router.push({
										name: "NBunnyDetailPage",
										params: { id: bunnySeq }
									});
								}
							} // Callback after click
						}).showToast();

						// 만약 router 가 본 페이지라면
						// $this.$router.push({
						// name: "YTonnyDetailPage",
						// params: { id: $this.yTonnyDetail.seq }
						// });
					} else {
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
							style: {
								background: "linear-gradient(to right, #00b09b, #96c93d)",
								boxShadow: "1px 1px 2px rgba(0, 0, 0, 0.08)",
								borderRadius: "6px",
								fontSize: "1.3rem",
								fontWeight: "600"
							},
							onClick: function () {
								if (request.taskCode == "0030001") {
									// 즉시통역
									// $this.$router.push({ name: "JTonnyApplyPage" });
								} else if (request.taskCode == "0030002") {
									// 통역예약
									$this.$router.push({
										name: "YTonnyDetailPage",
										params: { id: yTonnySeq }
									});
								} else {
									// 번역
									$this.$router.push({
										name: "NBunnyDetailPage",
										params: { id: bunnySeq }
									});
								}
							} // Callback after click
						}).showToast();
					}

					// window.location.reload();
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
			console.log("멍미");
			state.alert.stompClient.disconnect();
		}
	}
};
