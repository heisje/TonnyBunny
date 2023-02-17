<template lang="">
    <div class="chat-item d-flex px-3">
        <div class="profile-image-wrap">
            <!-- <img src="@/assets/noProfile.png" /> -->
            <user-profile-img :profileImagePath="profileImagePath" width="40" />
        </div>
        <!-- <div><img :src="profileImagePath" /></div> -->
        <div class="chat-wrap pl-4 d-flex flex-column justify-content-center">
            <div class="fw-bold m-1">{{ nickName }}</div>
            <div class="recent-message m-1">{{ recentMessage }}</div>
        </div>
        <div class="not-read-count ms-auto my-auto">
            {{ notReadCount < 100 ? notReadCount : "99+" }}
        </div>
    </div>
</template>
<script>
import UserProfileImg from "../common/UserProfileImg.vue";
// const fs = require("fs");
export default {
    components: { UserProfileImg },

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
    height: 80px;
    // max-width: 70vw;
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
.chat-wrap {
    max-width: 70%;
    height: inherit;
    overflow: hidden;
}
.chat-wrap div:nth-child(1) {
    font-size: 1.25em;
    // 긴 닉네임 뒤에 생략
    overflow: hidden;
    text-overflow: ellipsis;
}
.chat-wrap div:nth-child(2) {
    color: var(--sub-color);
    // 긴 채팅 뒤에 생략
    overflow: hidden;
    text-overflow: ellipsis;
}
.not-read-count {
    --circle-diameter: 2.5rem;
    width: var(--circle-diameter);
    height: var(--circle-diameter);
    line-height: var(--circle-diameter);
    text-align: center;
    font-size: 1.1rem;
    color: white;

    // circle
    border-radius: 50%;
    background: var(--carrot-color);
}
</style>
