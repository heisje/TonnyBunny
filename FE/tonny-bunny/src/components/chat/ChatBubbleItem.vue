<template lang="">
    <div class="chat">
        <div
            :class="{
                'other-chat': other,
                'self-chat': !other,
                'message-type-url': messageType == 'url',
                'message-type-text': messageType == 'text',
            }">
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
            description: "messageType에 따라 css를 변경합니다.",
        },
    },
};
</script>
<style lang="scss" scoped>
.chat {
    border: 1px solid lightgray;
    margin: 10px;
    // padding: 10px;
}
.other-chat {
}
.self-chat {
    text-align: end;
}
.message-type-text {
}
.message-type-url {
    background: lightgrey;
}
</style>
