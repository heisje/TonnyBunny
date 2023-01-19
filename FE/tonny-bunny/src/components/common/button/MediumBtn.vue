<template>
    <div class="mediumBtn">
        <div>{{ text }}</div>
    </div>
</template>

<script>
export default {
    props: {
        text: {
            type: String,
            default: "중간버튼",
            description: "button tag",
        },
    },
};
</script>

<style lang="scss" scoped>
.mediumBtn {
    display: flex;
    align-items: center;
    justify-content: center;

    color: white;
    font-size: 0.8rem;
    font-weight: 600;
    width: 88px;
    height: 32px;
    background: linear-gradient(126.73deg, #ff8a65 15.12%, #ff7765 86.23%);
    border-radius: 4px;
}
</style>
