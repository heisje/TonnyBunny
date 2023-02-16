<template lang="">
    <div class="userProfileImgContainer" :style="`width:${width}px`">
        <!-- <img class="userProfileImg" :src="require('@/assets/mint.png')" alt="" /> -->

        <img
            @click="imgClick"
            class="userProfileImg"
            :src="`/images${profileImagePath}`"
            onerror="this.onerror=null; this.src='/images/noProfile.png';" />
        <!-- <img class="userProfileImg" src="@/assets/noProfile.png" /> -->
    </div>
</template>
<script>
export default {
    props: {
        userInfo: {
            type: Object,
        },

        profileImagePath: {
            type: String,
        },

        width: {
            type: Number,
            default: 32,
        },
    },

    methods: {
        imgClick() {
            if (this.userInfo?.helperInfo) {
                this.$store.dispatch("getHelperModalInfo", this.userInfo.seq);
                // this.$store.commit("TOGGLE_HELPER_MODAL", this.userInfo);
            }
        },
    },
};
</script>
<style lang="scss" scoped>
.userProfileImgContainer {
    aspect-ratio: 1/1;
    cursor: pointer;

    .userProfileImg {
        display: block; /*img default display - inline-block*/
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 50%;
    }
}
</style>
