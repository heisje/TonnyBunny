<template>
    <!-- 통역 결과시간과 포인트를 알려주는 아이템 -->

    <lottie-player
        src="https://lottie.host/a5716bf6-cf0e-4f59-9127-f1660f329507/zSnVNdBJBp.json"
        background="transparent"
        speed="1"
        style="width: 300px; height: 300px"
        loop
        autoplay></lottie-player>

    <div v-if="isHelper">
        <img src="@/assets/noProfile.png" alt="" />
        <div>고객 닉네임님이</div>
        <h1>8분 600캐럿을 지불하였습니다.</h1>
    </div>
    <div v-else>
        <img src="@/assets/noProfile.png" alt="" />
        <div>헬퍼 닉네임님의 통역이 종료되었습니다.</div>
        <h1>8분 600캐럿</h1>
    </div>
</template>

<script>
export default {
    name: "TonnyResultItem",
    data() {
        return {
            isHelper: false,
        };
    },
    components: {},
};
</script>

<style></style>
