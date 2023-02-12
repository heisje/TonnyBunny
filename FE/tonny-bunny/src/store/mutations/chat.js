import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import http from "@/common/axios";

export default {
    SET_ENTER_CHAT_ROOM(state) {
        state.chat.chatRoomConntected = true;
    },
    SET_EXIT_CHAT_ROOM(state) {
        state.chat.chatRoomConntected = false;
    },
    SET_CHAT_ROOM_INFO(state, data) {
        state.chat.chatRoomInfo = data;
    },
    SET_CHAT_USER_SEQ(state, data) {
        state.chat.chatUserSeq = data;
    },
    SET_CHAT_ROOM_LIST(state, data) {
        state.chat.chatRoomList = data;
    },
    SET_CHAT_STOMP_SOCKET(state, data) {
        state.chat.chatStompSocket = data;
    },
    // SET_CHAT_STOMP_SOCKET_CONNECTED(state) {
    //     state.chat.chatStompSocketConnected = true;
    // },
    // SET_CHAT_STOMP_SOCKET_DISCONNECTED(state) {
    //     state.chat.chatStompSocketConnected = false;
    // },
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

        state.chat.chatSocket = socket;
        state.chat.chatStompSocket = stompClient;
        stompClient.connect(
            { user: userSeq },
            () => {
                // 소켓 연결 성공
                // context.commit("SET_CHAT_STOMP_SOCKET", stompClient);
                // context.commit("SET_CHAT_STOMP_SOCKET_CONNECTED");
                state.chat.chatStompSocketConnected = true;
                console.log("소켓 연결 성공");
                console.log("구독 시도합니다 :", `/sub/chat/${userSeq}`);
                // 본인 id 를 구독합니다.
                stompClient.subscribe(`/sub/chat/${userSeq}`, (res) => {
                    console.log("구독으로 받은 메시지 입니다.", res.body);

                    let alert = JSON.parse(res.body);
                    console.log(state.chat.chatRoomList);
                    state.chat.chatRoomList.get(alert.roomSeq).notReadCount = alert.notReadCount;
                    state.chat.chatRoomList.get(alert.roomSeq).recentMessage = alert.message;

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    // this.chat_messages.push(res.body);
                });
            },
            (error) => {
                // 소켓 연결 실패
                console.log("소켓 연결 실패", error);
                state.chat.chatStompSocketConnected = false;
                // context.commit("SET_CHAT_STOMP_SOCKET_DISCONNECTED");
            }
        );
    },
    DISCONNECT_CHAT_STOMP_SOCKET(state) {
        let stompSocket = state.chat.chatStompSocket;
        stompSocket.disconnect(() => {
            console.log("소켓 연결을 종료하였습니다.");
            state.chat.chatStompSocketConnected = false;
        });
    },
};
