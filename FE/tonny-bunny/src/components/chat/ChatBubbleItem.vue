<template lang="">
    <div class="m-3 p-3 border">
        <div v-if="other === true">
            <div>
                <div><img class="img-thumbnail" src="@/assets/noProfile.png" /></div>
                <div>{{ name }}</div>
            </div>
            <h2>{{ text }}</h2>
            <div>{{ time }}</div>
        </div>
        <div v-else class="text-end">
            <h2>{{ text }}</h2>
            <div>{{ time }}</div>
        </div>
    </div>
</template>
<script>
export default {
    name: "ChatBubbleItem",
    props: {
        chatData: {
            type: Object,
            defaultObject: {
                name: "a",
                text: "a",
                time: "10:00",
            },
            description: "상대방",
        },
        other: {
            type: Boolean,
            default: false,
            description: "상대방",
        },
        imglink: {
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
    },
};
</script>
<style lang=""></style>
