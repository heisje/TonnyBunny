<template>
    <div>
        <title-banner title="채팅함" text="채팅을 확인해보세요"></title-banner>
        <div style="margin-top: 100px"></div>
        <div class="d-flex justify-content-center">
            <chat-list class="chat-list-view customForm" />
        </div>
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
