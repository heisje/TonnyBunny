<template>
    <div>
        <h1>채팅 - 채팅 내역 조회 페이지</h1>
        <div v-for="chatData in chatDatas" :key="chatData">
            <chat-bubble-item other />
        </div>

        <div>
            <form action="">
                <button>파일 업로드 버튼</button>
                <input v-model="insertMessageValue" type="text" />
                {{ insertMessageValue }}
                <button>메시지 날리기 버튼</button>
            </form>
        </div>
    </div>
</template>

<script>
import ChatBubbleItem from "@/components/chat/ChatBubbleItem.vue";
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
        };
    },
};
</script>

<style></style>
