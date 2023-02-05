<template lang="">
    <div>
        <LoadingItem />
        <!-- <Vue3Lottie
            :animationLink="`https://lottie.host/b162872c-29b1-4195-a818-a20ea5a46343/AHLhXNb0eX.json`"
            background="transparent"
            style="width: 300px; height: 300px"
            loop
            autoplay>
        </Vue3Lottie> -->
        <div>당근을 흔드는 중...</div>
        <div>당근을 흔드는 중...</div>
    </div>
</template>
<script>
import LoadingItem from "../common/LoadingItem.vue";

export default {
    components: { LoadingItem },
    data() {
        return {};
    },
};
</script>
<style lang=""></style>
