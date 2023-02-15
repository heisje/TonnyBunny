import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import http from "@/common/axios";
import utils from "@/common/utils";

export default {
    SET_CHAT_ROOM_INFO(state, data) {
        state.chat.chatRoomInfo = data;
    },
    SET_CHAT_USER_SEQ(state, data) {
        state.chat.chatUserSeq = data;
    },
    SET_CHAT_ROOM_LIST(state, data) {
        state.chat.chatRoomList = data;
    },
    SET_TRUE_SEND_URL_MESSAGE(state) {
        state.chat.sendUrlMessage = true;
    },
    SET_FALSE_SEND_URL_MESSAGE(state) {
        state.chat.sendUrlMessage = false;
    },
    SET_URL_MESSAGE(state, data) {
        state.chat.urlMessage = data;
    },
    CONNECT_CHAT_STOMP_SOCKET(state, userSeq) {
        const serverURL = http.getUri() + "/stomp";
        let socket = new SockJS(serverURL);
        let stompClient = Stomp.over(socket);
        console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

        // state.chat.chatSocket = socket;
        // state.chat.chatStompSocket = stompClient;
        stompClient.connect(
            {},
            () => {
                console.log("stompClient: ", stompClient);
                // 소켓 연결 성공
                console.log("소켓 연결 성공");
                console.log("구독 시도합니다 :", `/sub/chat/${userSeq}`);
                // 본인 id 를 구독합니다.
                stompClient.subscribe(`/sub/chat/${userSeq}`, (res) => {
                    console.log("구독으로 받은 메시지 입니다.", res.body);

                    let alert = JSON.parse(res.body);
                    console.log(state.chat.chatRoomList);
                    if (state.chat.chatRoomList.size > 0) {
                        state.chat.chatRoomList.get(alert.roomSeq).notReadCount =
                            alert.notReadCount;
                        state.chat.chatRoomList.get(alert.roomSeq).recentMessage = alert.message;
                    }

                    // 알림에 추가
                    state.alert.alertList.push({
                        alertLogSeq: -1,
                        chatRoomSeq: alert.alertLogSeq, // 채팅용
                        content: `${alert.senderUserNickname} : ${alert.message}`,
                        tonnySityCode: "채팅",
                        createdAt: utils.setDate(new Date().toISOString()),
                        isRead: false,
                        taskCode: "채팅",
                        updatedAt: utils.setDate(new Date().toISOString()),
                    });
                    console.log("alertList에 추가: ", state.alert.alertList);
                });
            },
            (error) => {
                // 소켓 연결 실패
                console.log("소켓 연결 실패", error);
            }
        );
    },
    DISCONNECT_CHAT_STOMP_SOCKET(state) {
        let stompSocket = state.chat.chatStompSocket;
        stompSocket.disconnect(() => {
            console.log("소켓 연결을 종료하였습니다.");
        });
    },
};
