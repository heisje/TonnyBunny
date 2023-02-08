<template lang="">
    <div class="loadingContainer container">
        <div class="loadingWrap">
            <div class="loadingContent">
                <LoadingItem class="loadingCarrot" />
                <div class="loadingText">헬퍼를 찾기위해<br />당근을 흔들고 있어요!</div>
            </div>

            <div class="subText">당신의 귀여운 통역번역가, TonnyBunny!</div>
        </div>
    </div>
</template>
<script>
import LoadingItem from "../common/LoadingItem.vue";

export default {
    name: "JTonnyLoading",

    components: { LoadingItem },

    data() {
        return {};
    },
};
</script>
<style lang="scss" scoped>
.loadingContainer {
}

.loadingWrap {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .subText {
        margin: 32px 0;
        color: var(--sub-color);
    }
}

.loadingContent {
    display: flex;
    width: 400px;
    height: 400px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 100%;
    border: 8px solid var(--primary-color);

    .loadingCarrot {
        width: 150px;
        margin-bottom: 20px;
        // width: 400px;
        // // margin: 40px;
        // // padding: 200px;

        // &.loadingCarrot {
        //     // background-color: red;
        //     width: 200px;
        //     padding: 100px;
        // }
    }
    .loadingText {
        position: relative;
        top: -75px;
        text-align: center;
        font-size: 1.1rem;
        letter-spacing: 0px;
        line-height: 30px;
        font-weight: 500;
        color: var(--main-color);
    }
}
</style>
