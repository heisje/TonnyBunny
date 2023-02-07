<template>
    <div>
        <h1>채팅 - 채팅 내역 조회 페이지</h1>
        <h2>roomseq : {{ chatRoomInfo.roomSeq }}</h2>
        <div v-for="chatData in chatDatas" :key="chatData">
            <chat-bubble-item other />
        </div>

        <div>
            <form action="">
                <!-- <button>파일 업로드 버튼</button> -->
                <input v-model="insertMessageValue" type="text" />
                {{ insertMessageValue }}
                <button>메시지 날리기 버튼</button>
            </form>
        </div>
    </div>
</template>

<script>
import SockJS from "sockjs-client";
import ChatBubbleItem from "@/components/chat/ChatBubbleItem.vue";
import http from "@/common/axios";
import { mapGetters } from "vuex";
export default {
    components: { ChatBubbleItem },
    data() {
        return {
            otherInfo: {
                name: "상대방이름",
                img: "",
            },
            insertMessageValue: "",
            chatDatas: [
                {
                    name: "",
                },
            ],
            // socket 연결
            socket: "",
            chatRoomSeq: this.chatRoomInfo.roomSeq,
            chatUserSeq: this.userInfo.seq,
            chatAnotherSeq: this.chatRoomInfo.anotherUserInfo.userSeq,
        };
    },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
            chatRoomInfo: "getChatRoomInfo",
        }),
    },
    methods: {
        async getPreviousChatLog() {
            const { data } = await http.get("/chat/log/" + this.roomSeq);
            // console.log(data);
            this.chatDatas = data.data;
        },
        // async insertMessage() {},
        enterChatRoom() {
            // [2] 서버와 연결
            const serverURL = http.getUri() + "/chat";
            this.socket = new SockJS(serverURL);
            console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

            // 서버와 소켓 연결
            this.socket.onopen = () => {
                console.log("연결되었습니다.");
                const chatchat = {
                    type: "enter",
                    roomId: this.chatRoomSeq,
                    userSeq: this.chatUserSeq,
                    anotherSeq: this.chatAnotherSeq,
                };
                this.socket.send(JSON.stringify(chatchat));
            };
            // 소켓으로 받은 메세지를 출력
            this.socket.onmessage = (e) => {
                console.log(e.data);
            };
        },
        sendMessage() {
            // [3] 메세지 전달
            const chatchat = {
                type: "message",
                roomId: this.chatRoomSeq,
                message: this.insertMessageValue,
                userSeq: this.chatUserSeq,
            };
            this.socket.send(JSON.stringify(chatchat));
        },
        closeChatRoom() {
            const chat = {
                type: "exit",
                roomId: this.chatRoomSeq,
                userSeq: this.chatUserSeq,
            };
            this.socket.send(JSON.stringify(chat));
            this.socket.close();
            this.socket.onclose = () => {
                console.log("퇴장했습니다.");
            };
        },
    },
    mounted() {
        this.getPreviousChatLog();
        this.enterChatRoom();
    },
};
</script>

<style></style>
