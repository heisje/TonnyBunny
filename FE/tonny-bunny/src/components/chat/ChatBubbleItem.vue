<template lang="">
    <div class="chat-box">
        <div v-if="other == true" class="chat-user-profile">
            <img src="@/assets/noProfile.png" />
        </div>
        <div class="chat-message-wrap">
            <div v-show="other == true">{{ name }}</div>
            <div
                class="chat-message"
                :class="{
                    'message-type-url': messageType == 'url',
                    'message-type-text': messageType == 'text',
                }">
                <div>
                    <div class="text">{{ text }}</div>
                    <div class="time" :class="other ? 'time-other' : 'time-self'">{{ time }}</div>
                </div>
            </div>
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
.chat-box {
    max-width: 75%;
}
.chat-message-wrap {
    padding-left: 60px;
}
.chat-user-profile img {
    width: 50px;
    float: left;
}
.chat-message {
    border: 1px solid lightgray;
    // margin: 10px;
    display: inline-block;
    padding: 10px;
    position: relative;

    border-radius: 15px;
    // width: 40%;
    // max-width: 75%;
}
.text {
}
.time {
    position: absolute;
    bottom: 0px;
}
.time-other {
    right: -72px;
}
.time-self {
    left: -72px;
}
.message-type-text {
}
.message-type-url {
    background: lightgrey;
}
</style>
