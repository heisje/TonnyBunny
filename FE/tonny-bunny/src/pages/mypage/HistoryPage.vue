<template>
    <h1>마이페이지 - 이용내역</h1>
    <div v-if="isHelper === true">
        <SwitchTaps />
    </div>
    필터선택
    <select name="" id="">
        전체
    </select>
    <option value="">신청중</option>
    <option value="">진행중</option>
    <option value="">완료됨</option>

    <button>필터버튼</button>
</template>

<script>
import SwitchTaps from "@/components/mypage/SwitchTaps.vue";

export default {
    name: "HelperChangePage",

    components: {
        SwitchTaps,
    },
};
</script>

<style></style>
