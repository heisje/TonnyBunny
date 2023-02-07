<template>
    <video
        autoplay
        style="
            -moz-transform: scale(-1, 1);
            -webkit-transform: scale(-1, 1);
            -o-transform: scale(-1, 1);
            transform: scale(-1, 1);
            filter: FlipH;
        "
    />
</template>

<script>
export default {
    name: "OvVideo",

    props: {
        streamManager: Object,
    },

    mounted() {
        this.streamManager.addVideoElement(this.$el);
    },
};
</script>
