<template lang="">
    <div id="agreeInput">
        <div class="form-check">
            <input
                style="border: 2px solid var(--sub-color); width: 20px; height: 20px"
                @click="changeValue()"
                class="form-check-input"
                type="checkbox"
                v-model="value"
                id="flexCheckDefault" />
            <h3 style="margin-left: 4px">[필수] 약관 모두 동의</h3>
            <span class="label detail">자세히보기</span>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            value: false,
        };
    },
    methods: {
        changeValue() {
            this.$emit("toggle", !this.value);
        },
    },
};
</script>
<style lang="scss" scoped>
#agreeInput {
    display: block;
    margin-top: 36px;
    margin-bottom: 12px;
}
.form-check {
    display: flex;
    align-items: center;
    justify-content: left;
}
.detail {
    margin-left: 12px;
    text-decoration: underline;
    cursor: pointer;
}
</style>
