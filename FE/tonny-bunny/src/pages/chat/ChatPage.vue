<template>
    <div>
        <h1>채팅 - 채팅 목록</h1>
        <chat-list />
    </div>
</template>

<script>
import ChatList from "@/components/chat/ChatList.vue";
export default {
    components: { ChatList },
    name: "ChatPage",
};
</script>

<style></style>
