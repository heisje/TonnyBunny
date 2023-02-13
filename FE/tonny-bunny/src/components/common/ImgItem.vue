<template lang="">
    <div class="imgItemContainer" :style="`width:${width}px ; height:${width}px`">
        <img
            v-if="imagePath"
            class="imgItem"
            :src="`/images/${imagePath}`"
            onerror="this.onerror=null; this.src='/images/noBoardImg.png';" />
        <img v-else class="imgItem" src="@/assets/noBoardImg.png" />
        <!-- <img :src="`/images/${imagePath}`"
            onerror="this.onerror=null; this.src='/images/noBoardImg.png';" /> -->
    </div>
</template>
<script>
export default {
    props: {
        imagePath: {
            type: String,
        },

        width: {
            type: Number,
        },
    },
};
</script>
<style lang="scss" scoped>
.imgItemContainer {
    aspect-ratio: 1/1;
    cursor: pointer;

    .imgItem {
        display: block;
        width: 100%;
        height: 100%;
        object-fit: cover;
        border-radius: 10%;
    }
}
</style>
