<template>
    <div>
        <h1>채팅 - 채팅 내역 조회 페이지</h1>

        <FirstMessage />
        <SecondMessage />

        <div>
            <div>파일 업로드 버튼</div>
            메시지 작성 폼
            <button>메시지 날리기 버튼</button>
        </div>
    </div>
</template>

<script>
import FirstMessage from "@/components/chat/FirstMessage.vue";
import SecondMessage from "@/components/chat/SecondMessage.vue";

export default {
    components: {
        FirstMessage,
        SecondMessage,
    },
};
</script>

<style></style>
