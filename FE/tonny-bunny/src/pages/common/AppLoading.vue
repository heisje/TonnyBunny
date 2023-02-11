<template lang="">
    <div></div>
    <!-- <div class="loadingContainer" v-show="isLoading">
        <div class="loadingWarp">
            <div class="loadingContent">
                <LoadingItem />

            </div>
        </div>
    </div> -->
</template>
<script>
import { mapGetters } from "vuex";
// import LoadingItem from "@/components/common/LoadingItem.vue";

export default {
    name: "AppLoading",

    // components: { LoadingItem },

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
