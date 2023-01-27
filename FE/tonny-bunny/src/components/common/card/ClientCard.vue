<template>
    <div>client card</div>
    <img src="@/assets/noProfile.png" alt="" />
    <div>username</div>
    <div>
        <router-link :to="{ name: 'InfoPage' }">정보수정</router-link>
    </div>
    <div>
        <h4>보유캐럿</h4>
        <div>120개</div>
        <div>
            <router-link :to="{ name: 'CarrotPage' }">포인트관리</router-link>
        </div>
    </div>
    <!-- 유저카드 구성요소
		프로필이미지아이템
		정보수정버튼
		포인트아이템 -->
</template>

<script>
export default {
    name: "ClientCard",
};
</script>

<style></style>
