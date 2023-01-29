<template>
    <div>
        <img src="@/assets/noProfile.png" alt="프로필이미지" />
        <p>통역 요금</p>
        <h2>600캐럿/5분</h2>
        <br />
        <p>지난시간</p>
        <h4>10분 00초</h4>
        <br />
        <p>비용</p>
        <h1>600캐럿</h1>
        <br />
        <div>
            <button>스피커</button>
            <button>마이크</button>
            <button>비디오</button>
            <button>신고하기</button>
            <div v-if="isHelper">
                <button @click.prevent="endEmit">종료요청</button>
            </div>
            <div v-else>
                <button @click.prevent="endEmit">종료하기</button>
            </div>
        </div>
        <button>모달닫기</button>
    </div>
</template>

<script>
export default {
    name: "TonnyInfo",
    data() {
        return {
            isOpen: false,
            isHelper: false,
        };
    },
    components: {},
    methods: {
        endEmit() {
            this.$emit("toggle", true);
        },
    },
};
</script>

<style></style>
