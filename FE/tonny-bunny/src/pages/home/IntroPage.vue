<template>
    <div>
        <title-text title="토니버니란?" />
        <div>
            토니버니는 통역, 번역 중계 플랫폼으로
            <br />
            <span class="primaryColor">급히 통역이 필요한 사람을 위한 통역 서비스를 중계</span>하고
            있습니다.
            <br />
            그리고 통역 예약 서비스와 번역서비스도 하고 있습니다.
            <br />
            <br />
            많은 이용 부탁드립니다!
            <loading-item class="carrotWidth" />
        </div>
    </div>
    <!-- 
		아이디어가 없음
		나중에 컨셉 정리해서
		설명 페이지 만듦
	-->
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
