<template>
    <div>
        <h1>채팅 - 채팅 내역 조회 페이지</h1>
        <h2>roomseq : {{ chatRoomSeq }}</h2>
        <div class="chat-detail-view viewport-height-80 overflow-auto">
            <div v-for="chatData in chatDatas" :key="chatData">
                <router-link
                    v-if="chatData.messageType == 'url'"
                    :to="{
                        name: chatData.urlPage,
                        params: { id: chatData.urlPageSeq },
                    }">
                    <chat-bubble-item
                        :other="chatData.userSeq == chatAnotherUserSeq"
                        :name="getUserName(chatData)"
                        :text="chatData.message"
                        :time="getChatTime(chatData)"
                        :messageType="chatData.messageType" />
                </router-link>

                <chat-bubble-item
                    v-else
                    :other="chatData.userSeq == chatAnotherUserSeq"
                    :name="getUserName(chatData)"
                    :text="chatData.message"
                    :time="getChatTime(chatData)"
                    :messageType="chatData.messageType" />
            </div>
        </div>

        <div>
            <!-- <form action=""> -->
            <!-- <button>파일 업로드 버튼</button> -->
            <input v-model="insertMessageValue" type="text" />
            {{ insertMessageValue }}
            <medium-btn @click="sendMessage" text=">"></medium-btn>
            <!-- </form> -->
        </div>
    </div>
</template>

<script>
import SockJS from "sockjs-client";
import ChatBubbleItem from "@/components/chat/ChatBubbleItem.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import http from "@/common/axios";
import { mapGetters } from "vuex";

export default {
    components: { ChatBubbleItem, MediumBtn },
    data() {
        return {
            insertMessageValue: "",
            chatDatas: [],
            // socket 연결
            socket: "",
        };
    },
    // 스크롤 하단 고정 설정
    // watch: {
    //     chatDatas: {
    //         deep: true,
    //         handler() {
    //             this.setScrollBottom();
    //         },
    //     },
    // },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
            chatRoomInfo: "getChatRoomInfo",
        }),
        chatRoomSeq() {
            return this.$store.getters.getChatRoomInfo.roomSeq;
        },
        chatUserSeq() {
            return this.$store.getters.getUserInfo.seq;
        },
        chatAnotherUserSeq() {
            return this.$store.getters.getChatRoomInfo.anotherUserInfo.userSeq;
        },
        chatAnotherUserInfo() {
            return this.$store.getters.getChatRoomInfo.anotherUserInfo;
        },
    },
    methods: {
        getChatTime(chatData) {
            let chatDate = chatData.date;
            let [year, month, day, hour, minute] = chatDate;
            // console.log([year, month, day].join("/") + [hour, minute].join(":"));
            let ampm;
            if (hour > 12) {
                ampm = "오후";
                hour = hour - 12;
            } else {
                ampm = "오전";
            }
            return String(
                [year, month, day].join(".") + " " + ampm + " " + [hour, minute].join(":")
            );
        },
        getUserName(chatData) {
            if (chatData.userSeq == this.chatAnotherUserSeq)
                return this.chatAnotherUserInfo.nickName;
            return "";
        },
        setScrollBottom() {
            var scrollView = document.querySelector(".chat-detail-view");
            scrollView.scrollTop = scrollView.scrollHeight;
        },
        async getPreviousChatLog() {
            try {
                const { data } = await http.get("/chat/log/" + this.chatRoomSeq);
                // console.log(data);
                if (data.resultCode == "SUCCESS") {
                    this.chatDatas = [];
                    data.data.forEach((chat) => {
                        // console.log("no parse : ", chat);
                        console.log("Chat Log : ", JSON.parse(chat));
                        this.chatDatas.push(JSON.parse(chat));
                    });
                } else {
                    console.log("[error] in ChatDetailPage-getPreviousChatLog()");
                }
            } catch (err) {
                console.error(err);
            }
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
                    roomSeq: this.chatRoomSeq,
                    userSeq: this.chatUserSeq,
                    anotherSeq: this.chatAnotherUserSeq,
                };
                this.socket.send(JSON.stringify(chatchat));

                // "상담하기" 버튼을 누르고 들어온 경우
                const sendUrlMessage = this.$store.getters.getSendUrlMessage;
                if (sendUrlMessage == true) {
                    this.$store.commit("SET_FALSE_SEND_URL_MESSAGE");
                    const urlChat = {
                        type: "message",
                        messageType: "url",
                        roomSeq: this.chatRoomSeq,
                        userSeq: this.chatUserSeq,
                        ...this.$store.getters.getUrlMessage, // {message, urlPage, urlPageSeq}
                    };
                    console.log("urlChat: ", urlChat);
                    this.socket.send(JSON.stringify(urlChat));
                }
            };
            // 소켓으로 받은 메세지를 출력
            this.socket.onmessage = (e) => {
                console.log(e.data);
                const message = JSON.parse(e.data);
                console.log("Receive Message: ", message);
                this.chatDatas.push(message); // {roomSeq, userSeq, type, messageType, message, urlPage, urlPageSeq}
                // this.setScrollBottom();
            };
        },
        sendMessage(event) {
            event.preventDefault();

            // [3] 메세지 전달
            const chatchat = {
                type: "message",
                messageType: "text",
                roomSeq: this.chatRoomSeq,
                userSeq: this.chatUserSeq,
                message: this.insertMessageValue,
            };
            this.socket.send(JSON.stringify(chatchat));
            this.insertMessageValue = "";
        },
        closeChatRoom() {
            const chat = {
                type: "exit",
                roomSeq: this.chatRoomSeq,
                userSeq: this.chatUserSeq,
            };
            this.socket.send(JSON.stringify(chat));
            this.socket.close();
            this.socket.onclose = () => {
                console.log("퇴장했습니다.");
            };
        },
    },
    beforeMount() {
        this.getPreviousChatLog();
    },
    mounted() {
        this.enterChatRoom();
    },
    beforeUnmount() {
        this.closeChatRoom();
    },
};
</script>

<style lang="scss" scoped>
.viewport-height-80 {
    height: 80vh;
}
</style>
