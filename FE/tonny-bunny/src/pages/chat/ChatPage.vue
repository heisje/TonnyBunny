<template>
    <div>
        <h1>채팅 - 채팅 목록</h1>

        <div>
            <ChatListItem />
        </div>
    </div>
</template>

<script>
import ChatListItem from "@/components/chat/ChatListItem.vue";

export default {
    name: "ChatListPage",
    components: {
        ChatListItem,
    },
};
</script>

<style></style>
