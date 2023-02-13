<template lang="">
    <div class="chat-item p-3 d-flex flex-row">
        <div class="profile-image-wrap"><img src="@/assets/noProfile.png" /></div>
        <!-- <div><img :src="profileImagePath" /></div> -->
        <div class="pl-4 d-flex flex-column">
            <div class="fw-bold fs-5 m-2">{{ nickName }}</div>
            <div class="fs-6 m-2">{{ recentMessage }}</div>
        </div>
        <div class="not-read-count ms-auto my-auto">
            {{ notReadCount < 5 ? notReadCount : "99+" }}
        </div>
    </div>
</template>
<script>
// const fs = require("fs");
export default {
    props: {
        profileImagePath: {
            type: String,
            default: "@/assets/noProfile.png",
        },
        nickName: {
            type: String,
        },
        recentMessage: {
            type: String,
            description: "채팅방에 보내진 최신 메세지",
        },
        notReadCount: {
            type: Number,
            description: "안 읽은 메세지 개수",
        },
    },
    computed: {
        // profileImagePath() {
        //     return this.imagePath === "" ||
        //         this.imagePath === null ||
        //         !fs.existsSync(this.imagePath)
        //         ? require("@/assets/noProfile.png")
        //         : require(this.imagePath);
        // },
    },
};
</script>
<style lang="scss" scoped>
.chat-item {
    // border-top: 1px solid lightgray;
    border-bottom: 1px solid lightgray;
}
.profile-image-wrap {
    position: relative;
    width: 60px;
}
.profile-image-wrap img {
    position: absolute;
    width: inherit;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}
.not-read-count {
    --circle-diameter: 50px;
    width: var(--circle-diameter);
    height: var(--circle-diameter);
    line-height: var(--circle-diameter);
    text-align: center;
    font-size: 1.3rem;
    color: white;

    // circle
    border-radius: 50%;
    background: var(--carrot-color);
}
</style>
