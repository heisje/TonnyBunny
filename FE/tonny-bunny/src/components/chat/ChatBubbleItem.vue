<template lang="">
    <div class="chat">
        <div :class="other ? 'other-chat' : 'self-chat'">
            <div v-if="other === true">
                <div><img class="img-thumbnail" :src="profileImageLink" /></div>
                <div>{{ name }}</div>
            </div>
            <h2>{{ text }}</h2>
            <div>{{ time }}</div>
        </div>
    </div>
</template>
<script>
export default {
    name: "ChatBubbleItem",
    props: {
        other: {
            type: Boolean,
            default: false,
            description: "true: 상대방, false: 나",
        },
        profileImageLink: {
            type: String,
            default: "@/assets/noProfile.png",
            description: "이미지",
        },
        name: {
            type: String,
            default: "이름",
            description: "이름",
        },
        text: {
            type: String,
            default: "내용입니다",
            description: "내용",
        },
        time: {
            type: String,
            default: "00:00",
            description: "시간",
        },
        messageType: {
            type: String,
            default: "text",
            description: "'text': 일반 텍스트 메세지, 'route': 다른 페이지로 이동하는 메세지",
        },
        routeInfo: {
            type: Object,
            defalult: {
                pageName: "",
                pageSeq: 0,
            },
            description:
                "이동할 페이지 이름. messageType='url'이어야 한다.\
                (예시) 번역 공고 페이지로 이동하고 싶다면 {pageName: 'NBunnyDetailPage', pageSeq: (조회 API 요청할 번역 공고 Seq)}",
        },
    },
};
</script>
<style lang="scss" scoped>
.chat {
    border: 0px solid gray;
}
.other-chat {
}
.self-chat {
    text-align: end;
}
</style>
