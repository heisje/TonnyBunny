<template>
    <h1>ModeSwitchTap</h1>
    <div class="form-check">
        <input
            class="form-check-input"
            type="radio"
            name="flexRadioDefault"
            id="flexRadioDefault1" />
        <label class="form-check-label" for="flexRadioDefault1"> 고객 </label>
    </div>
    <div class="form-check">
        <input
            class="form-check-input"
            type="radio"
            name="flexRadioDefault"
            id="flexRadioDefault2"
            checked />
        <label class="form-check-label" for="flexRadioDefault2"> 헬퍼 </label>
    </div>
    <!-- 고객 / 헬퍼 상태 변경탭 -->
</template>

<script>
export default {
    name: "SwitchTaps",
};
</script>

<style></style>
