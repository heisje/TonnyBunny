import http from "@/common/axios";

export default {
    async getChatRoomList(context, userInfo) {
        // 참가한 채팅방 목록 불러오기
        try {
            const { data } = await http.post("/chat/room/" + userInfo.seq);
            if (data.resultCode == "SUCCESS") {
                // console.log("Data: ", data.data);
                let roomMap = data.data.reduce((map, obj) => {
                    map.set(obj.roomSeq, obj);
                    return map;
                }, new Map());
                // console.log("Map : ", roomMap);
                context.commit("SET_CHAT_ROOM_LIST", roomMap);
            } else {
                console.log("[error] fail to get data in getChatRoomList()");
            }
        } catch (err) {
            console.error(err);
        }
        // this.chatRoomList = data.data;
        // console.log(data);
    },
    async getChatRoomInfo(context, { userSeq, anotherUserSeq }) {
        // const { userSeq, anotherUserSeq } = payload;
        try {
            const { data } = await http.post("/chat/room/" + userSeq + "/" + anotherUserSeq);
            if (data.resultCode == "SUCCESS") {
                let chatRoomInfo = data.data;

                return context.commit("SET_CHAT_ROOM_INFO", chatRoomInfo);
            } else {
                console.log("[error] fail to get data in getChatRoomInfo()");
            }
        } catch (err) {
            console.error(err);
        }
    },
};
