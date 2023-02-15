<template>
    <div>
        <title-banner title="채팅함" text=""></title-banner>
        <chat-list class="chat-list-view" />
    </div>
</template>

<script>
import ChatList from "@/components/chat/ChatList.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
export default {
    components: { ChatList, TitleBanner },
    name: "ChatPage",
};
</script>

<style lang="scss" scoped>
.chat-list-view {
    min-height: 70vh;
}
</style>
