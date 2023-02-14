<template>
    <div>
        <title-text title="토니버니란?" />
        <div>
            토니버니는 통역, 번역 중계 플랫폼으로
            <br />
            <span class="primaryColor">도움이 필요한 사람을 위한 통역 및 번역 서비스를 중계</span>하고
            있습니다.

            <br />
            <br />
            도움이 필요할 땐 언제든 당근을 흔들어주세요!
            <br/>
            언어를 잇는 공간, 당신을 잇는 공간
            <br/>
            Let's TonnyBunny!
            <loading-item class="carrotWidth" />
        </div>
    </div>

</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import LoadingItem from "@/components/common/LoadingItem.vue";
export default {
    components: { TitleText, LoadingItem },
    name: "IntroPage",
};
</script>

<style lang="scss" scoped>
.primaryColor {
    color: var(--primary-color);
}

.carrotWidth {
    width: 50%;
}
</style>
