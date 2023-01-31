<template lang="">
    <div class="loadingContainer" v-show="isLoading">
        <div class="loadingWarp">
            <div class="loadingContent">
                <lottie-player
                    src="https://lottie.host/b162872c-29b1-4195-a818-a20ea5a46343/AHLhXNb0eX.json"
                    background="transparent"
                    speed="1"
                    style="width: 100px; height: 100px"
                    loop
                    autoplay></lottie-player>
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
    name: "AppLoading",

    computed: {
        ...mapGetters({ isLoading: "getIsLoading" }),
    },
};
</script>
<style lang="scss" scoped>
.loadingContainer {
    position: fixed;
    width: 100vw;
    height: 100vh;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #00000033;
    z-index: 200;
}
.loadingWarp {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    .loadingContent {
    }
}
</style>
