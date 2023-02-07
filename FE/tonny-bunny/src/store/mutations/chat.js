export default {
    SET_ENTER_CHAT_ROOM(state) {
        state.chat.isEnterRoom = true;
    },
    SET_EXIT_CHAT_ROOM(state) {
        state.chat.isEnterRoom = false;
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
};
