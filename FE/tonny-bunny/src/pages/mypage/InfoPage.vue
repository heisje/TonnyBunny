<template>
    <div>마이페이지 - 회원정보수정 메인</div>
    <!-- 메인은 없고 업데이트 페이지만 사용할듯 -->
    <h1>회원정보를 수정하시려면</h1>
    <br />
    <h1>비밀번호를 입력해주세요</h1>
    <div>
        <title-text title="현재 비밀번호" type="h2" />
        <input type="text" placeholder="비밀번호입력" />
        <router-link :to="{ name: 'InfoUpdatePage' }">수정하기</router-link>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
export default {
    components: { TitleText },
    name: "InfoPage",
};
</script>

<style></style>
