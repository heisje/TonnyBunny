<template>
    <div class="chat-detail-view">
        <!-- <h1>채팅 - 채팅 내역 조회 페이지</h1>
        <h2>roomseq : {{ chatRoomSeq }}</h2> -->
        <div class="back d-flex" @click="goBack">
            <span class="material-symbols-outlined"> arrow_back_ios_new </span>
        </div>
        <div class="chat-detail-box viewport-height-80 overflow-auto">
            <div v-for="chatData in chatDatas" :key="chatData" class="chat">
                <ul>
                    <li
                        :class="
                            chatData.userSeq == chatAnotherUserSeq
                                ? 'another-user-chat'
                                : 'self-user-chat'
                        ">
                        <router-link
                            v-if="chatData.messageType == 'url'"
                            :to="{
                                name: chatData.urlPage,
                                params: { id: chatData.urlPageSeq },
                            }">
                            <chat-bubble-item
                                class="chat-bubble-item"
                                :other="chatData.userSeq == chatAnotherUserSeq"
                                :name="getUserName(chatData)"
                                :text="chatData.message"
                                :time="getTime(chatData.date)"
                                :messageType="chatData.messageType"
                                :profileImageLink="chatAnotherUserInfo?.profileImagePath" />
                        </router-link>

                        <chat-bubble-item
                            v-else
                            class="chat-bubble-item"
                            :other="chatData.userSeq == chatAnotherUserSeq"
                            :name="getUserName(chatData)"
                            :text="chatData.message"
                            :time="getTime(chatData.date)"
                            :messageType="chatData.messageType"
                            :profileImageLink="chatAnotherUserInfo?.profileImagePath" />
                    </li>
                </ul>
            </div>
        </div>

        <div class="chat-input-group input-group px-5">
            <medium-btn
                v-show="userInfo.userCode == '0010002'"
                @click="moveToQuotationCreatePage"
                color="carrot"
                text="견적서 작성">
            </medium-btn>
            <input
                class="insert-message form-control"
                v-model="insertMessageValue"
                @keyup.enter="sendMessage"
                placeholder="채팅을 입력하세요."
                type="text"
                autocomplete="off"
                autofocus />
            <medium-btn type="button" text="전송" color="carrot" @click="sendMessage"></medium-btn>
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
        getUserName(chatData) {
            if (chatData.userSeq == this.chatAnotherUserSeq)
                return this.chatAnotherUserInfo.nickName;
            return "";
        },
        moveToQuotationCreatePage() {
            // console.log("userInfo: ", this.userInfo);
            // console.log("commonUserCode: ", this.$store.getters.getUserCode);
            // 헬퍼만 보이는 버튼 -> 상대방이 client
            this.$router.push({
                name: "NBunnyQuotationForm",
                params: { clientSeq: this.chatAnotherUserSeq },
            });
        },
        setScrollBottom() {
            var scrollView = document.querySelector(".chat-detail-box");
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
                    this.socket.send(JSON.stringify(urlChat));
                }
            };
            // 소켓으로 받은 메세지를 출력
            this.socket.onmessage = (e) => {
                const message = JSON.parse(e.data);
                this.chatDatas.push(message); // {roomSeq, userSeq, type, messageType, message, urlPage, urlPageSeq}
                // this.setScrollBottom();
            };
        },
        sendMessage(event) {
            event.preventDefault();

            if (this.insertMessageValue.trim() == "") {
                return;
            }
            // [3] 메세지 전달
            const chatchat = {
                type: "message",
                messageType: "text",
                roomSeq: this.chatRoomSeq,
                userSeq: this.chatUserSeq,
                message: this.insertMessageValue,
            };
            this.socket.send(JSON.stringify(chatchat));

            // [4] 알림 전달
            const chatAlert = {
                type: "alert",
                alertLogSeq: new Date().toISOString(),
                task: "Chat",
                detailTask: "Chat",
                pageSeq: this.chatRoomSeq, //=> 알림을 눌렀을 때 해당 페이지로 이동할수 있는 페이지 ID (선택) // 채팅에선 roomSeq
                receivedUserSeq: this.chatAnotherUserSeq,
                message: this.insertMessageValue,
                senderUserSeq: this.chatUserSeq,
                senderUserNickname: this.userInfo.nickName,
            };
            this.socket.send(JSON.stringify(chatAlert));

            // 입력창 초기화
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
        getTime(chatDate) {
            let [year, month, day, hour, minute] = chatDate;
            // console.log([year, month, day].join("/") + [hour, minute].join(":"));
            year;
            month;
            day;
            let ampm;
            if (hour > 12) {
                ampm = "오후";
                hour = hour - 12;
            } else {
                ampm = "오전";
            }
            if (hour < 10) hour = "0" + hour;
            if (minute < 10) minute = "0" + minute;

            return String(ampm + " " + hour + ":" + minute);
            // return String(
            //     [year, month, day].join(".") + " " + ampm + " " + [hour, minute].join(":")
            // );
        },
        async deleteChatAlertLogByRoomSeq() {
            let { data } = await http.delete(`/chat/alert/${this.chatUserSeq}/${this.chatRoomSeq}`);
            if (data.resultCode == "SUCCESS") {
                console.log("채팅 삭제 성공");
            } else {
                console.log("채팅 삭제 실패");
            }
        },
        goBack() {
            this.$router.go(-1);
        },
    },
    updated() {
        let chat_detail_view = document.querySelector(".chat-detail-box");
        chat_detail_view.scrollTop = chat_detail_view.scrollHeight;
    },
    mounted() {
        this.getPreviousChatLog();
        this.enterChatRoom();
        this.deleteChatAlertLogByRoomSeq();
    },
    beforeUnmount() {
        this.closeChatRoom();
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/variable.scss";
body {
    height: 100vh;
    // position: relative;
}
@media (min-width: 1200px) {
    .chat-detail-view {
        width: 50vw;
        max-width: 800px;
        height: calc(100vh - 80px);
    }
}
@media (max-width: 1200px) {
    .chat-detail-view {
        max-width: 800px;
        height: calc(100vh - 80px - 60px);
    }
}
@media (max-width: 768px) {
    .chat-detail-view {
        height: calc(100vh - 72px - 60px);
    }
}
@media only screen and (min-device-width: 100px) and (max-device-width: 1200px) {
    .chat-detail-view {
        height: calc(100vh - 72px - 60px);
    }
}
// 뒤로가기 버튼 div
.back {
    cursor: pointer;
    width: 60px;
    height: 60px;
    margin-top: 10px;
    margin-left: 10px;
    border-radius: 30px;
    align-items: center;
    justify-content: center;
    background: var(--carrot-color);
    position: fixed;
    z-index: 1;
}

.chat-detail-view {
    margin: 0 auto;
    border: 1px solid rgba(0, 0, 0, 0.08);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--thin-color);
    // padding: 0 24px;
}
.chat-detail-box {
    height: calc(100% - 50px);
    padding: 12px;
}
// scroll-bar custom
.chat-detail-box::-webkit-scrollbar {
    width: 20px;
}
.chat-detail-box::-webkit-scrollbar-track {
    background: transparent;
}
.chat-detail-box::-webkit-scrollbar-thumb {
    background: lightgray;
    background-clip: padding-box;
    border: 5px solid transparent;
    border-radius: 10px;
}

.chat ul {
    width: 100%;
    padding: 0px;
    margin: 5px 0px;
    list-style: none;
}
.chat ul li {
    width: 100%;
}
.chat ul li .another-user-chat {
    text-align: left;
}
.chat ul li .chat-bubble-item {
    display: inline-block;
    word-break: break-all;
    max-width: 75%;
    text-align: left;
}
.self-user-chat {
    text-align: right;
}

.chat ul {
    width: 100%;
    padding: 0px;
    list-style: none;
}
.chat ul li {
    width: 100%;
}
.chat ul li .another-user-chat {
    text-align: left;
}
.chat ul li .chat-bubble-item {
    display: inline-block;
    word-break: break-all;
    max-width: 75%;
    text-align: left;
}
.self-user-chat {
    text-align: right;
}
.chat-input-group {
    // position: absolute;
    // left: 0px;
    // bottom: 0px;
}
.chat-input-group input:focus {
    border: 1px solid var(--primary-color);
    outline: none;
    box-shadow: 0 0 0px;
}
</style>
