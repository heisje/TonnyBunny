<template>
    <!-- 통역 결과시간과 포인트를 알려주는 아이템 -->

    <result-loading-item />

    <!-- <div v-if="isHelper">
        <img src="@/assets/noProfile.png" alt="" />
        <div>고객 닉네임님이</div>
        <h1>8분 600캐럿을 지불하였습니다.</h1>
    </div>
    <div v-else>
        <img src="@/assets/noProfile.png" alt="" />
        <div>헬퍼 닉네임님의 통역이 종료되었습니다.</div>
        <h1>8분 600캐럿</h1>
    </div> -->
</template>

<script>
import ResultLoadingItem from "../common/resultLoadingItem.vue";
export default {
    name: "TonnyResultItem",
    data() {
        return {
            isHelper: false,
        };
    },
    components: { ResultLoadingItem },
};
</script>

<style></style>
