<template>
    <div class="mainBtnWrap">
        <div class="mainBtn">
            <v-slot name="icon">아이콘</v-slot>
            <span class="btnText">버튼</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "MainBtn",

    components: {},

    props: {},
};
</script>

<style lang="scss" scoped>
.mainBtnWrap {
    display: inline-block;
}
.mainBtn {
    width: 200px;
    height: 200px;

    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    background-color: var(--light-color);
    box-shadow: 1px 1px 2px 1px rgba(0, 0, 0, 0.05);
    border-radius: 100%;
    .btnText {
        font-size: 2rem;
        font-weight: 600;
    }
}
</style>
