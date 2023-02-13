<template lang="">
    <div class="userProfileImgContainer">
        <!-- <img class="userProfileImg" :src="require('@/assets/mint.png')" alt="" /> -->

        <img
            class="userProfileImg"
            :src="`/images/${profileImagePath}`"
            onerror="this.onerror=null; this.src='/images/noProfile.png';" />
    </div>
</template>
<script>
export default {
    props: {
        profileImagePath: {
            type: String,
        },
    },

    computed: {
        // originUrl() {
        //     return window.location.protocol + window.location.hostname;
        // },
    },
};
</script>
<style lang="scss" scoped>
.userProfileImgContainer {
    width: 100%;
    aspect-ratio: 1/1;

    .userProfileImg {
        display: block; /*img default display - inline-block*/
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 50%;
    }
}
</style>
