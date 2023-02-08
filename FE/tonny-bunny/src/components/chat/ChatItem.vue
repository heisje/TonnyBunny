<template lang="">
    <div class="border p-3 m-2">
        <div><img src="@/assets/noProfile.png" /></div>
        <!-- <div><img :src="profileImagePath" /></div> -->
        <div>닉네임 {{ nickName }}</div>
        <div>채팅내용 {{ recentMessage }}</div>
        <div>안 읽은 개수 {{ notReadCount }}</div>
    </div>
</template>
<script>
// const fs = require("fs");
export default {
    props: {
        imagePath: {
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
<style lang=""></style>
