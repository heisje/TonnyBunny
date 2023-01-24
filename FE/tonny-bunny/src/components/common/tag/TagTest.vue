<template>
    <div>
        <h1>Bedge Test</h1>

        <square-tag></square-tag>
        <square-tag color="info" text="진행중"></square-tag>
        <square-tag color="white" text="완료됨"></square-tag>
    </div>
</template>

<script>
import SquareTag from "./SquareTag.vue";

export default {
    name: "TagTest",

    components: {
        SquareTag,
    },
};
</script>

<style></style>
