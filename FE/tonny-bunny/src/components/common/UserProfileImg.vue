<template lang="">
    <div class="userProfileImgContainer">
        <!-- <img class="userProfileImg" :src="require('@/assets/newLogo3.png')" alt="" /> -->
        <img
            :src="`/images/${profileImagePath}`"
            onerror="this.onerror=null; this.src='/images/noProfile_white.png';" />
    </div>
</template>
<script>
export default {
    props: {
        profileImagePath: {
            type: String,
        },
    },
};
</script>
<style lang="scss">
.userProfileImgContainer {
    width: 100%;
    aspect-ratio: 1/1;

    .userProfileImg {
        display: block; /*img default display - inline-block*/
        width: 100%;
        height: 100%;
        object-fit: cover;
    }
}
</style>
