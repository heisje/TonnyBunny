<template>
    <h1>UserCard</h1>
    <!-- 메인페이지에 표시되는 유저 프로필 -->
    유저프로필
    유저 네임
    <button>정보 수정</button>
    캐럿관리
    <PointManagementItem/>
</template>

<script>
import PointManagementItem from "@/components/mypage/PointManagementItem.vue"
export default {
		name: "UserCard",

		components: {
            PointManagementItem,
        },
}
</script>

<style>

</style>