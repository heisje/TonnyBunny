import http from "@/common/axios";

export default {
    async getChatRoomList(context, userInfo) {
        // 참가한 채팅방 목록 불러오기
        try {
            const { data } = await http.post("/chat/room/" + userInfo.seq);
            if (data.resultCode == "SUCCESS") {
                context.commit("SET_CHAT_ROOM_LIST", data.data);
            } else {
                console.log("[error] fail to get data in getChatRoom()");
            }
        } catch (err) {
            console.error(err);
        }
        // this.chatRoomList = data.data;
        // console.log(data);
    },
    setChatRoomInfo(context, chatRoomInfo) {
        context.commit("SET_CHAT_ROOM_INFO", chatRoomInfo);
    },
};
