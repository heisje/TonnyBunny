<template lang="">
    <div class="loadingContainer" v-show="isLoading">
        <div class="loadingWarp">
            <div class="loadingContent">
                <LoadingItem />
                <!-- <Vue3Lottie
                    :animationLink="`https://lottie.host/b162872c-29b1-4195-a818-a20ea5a46343/AHLhXNb0eX.json`"
                    background="transparent"
                    style="width: 300px; height: 300px"
                    loop
                    autoplay>
                </Vue3Lottie> -->
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import LoadingItem from "@/components/common/LoadingItem.vue";

export default {
    name: "AppLoading",

    components: { LoadingItem },

    data() {
        return {};
    },
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
