<template>
    <title-text type="h1" title="즐겨찾기" class="text-center" />

    <div class="container w-75">
        <div v-for="i in 10" :key="i">
            <UserProfileItem btnText="언팔로우" helperInfo="" />
        </div>
    </div>
</template>

<script>
import UserProfileItem from "@/components/mypage/UserProfileItem.vue";
import TitleText from "@/components/common/TitleText.vue";
export default {
    name: "FavoriteListPage",

    components: {
        UserProfileItem,
        TitleText,
    },
};
</script>

<style></style>
