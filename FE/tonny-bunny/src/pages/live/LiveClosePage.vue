<template>
    <title-text title="동시통역 종료" text="통역에 대한 리뷰를 남겨주세요" />

    <div>
        <TonnyResult />
    </div>

    <div v-if="isHelper == true">
        <button class="mediumBtn">이용내역확인</button>
    </div>
    <div v-else>
        <router-link :to="{ name: 'ReviewPage' }">
            <button class="mediumBtn">리뷰쓰기</button>
        </router-link>

        <router-link :to="{ name: 'HomePage' }">
            <button>다음에 작성하기</button>
        </router-link>
    </div>
</template>

<script>
import TonnyResult from "@/components/live/TonnyResult.vue";
import TitleText from "@/components/common/TitleText.vue";

export default {
    name: "LiveClosePage",

    components: {
        TonnyResult,
        TitleText,
    },
};
</script>

<style></style>
