<template>
    <h1>홈 - 마이 페이지</h1>

    <div>
        유저카드
        <UserCard />
        <!-- 유저카드 구성요소
		프로필이미지아이템
		정보수정버튼
		포인트아이템 -->
    </div>

    <div v-if="isHelper === true">
        헬퍼카드
        <HelperCard />
        <!-- 
			자세히보기 XSmallBtn
			수정하기 XSmallBtn 
		-->
    </div>

    <div>
        이용내역
        <LinkBtn />
        즐겨찾기
        <LinkBtn />
        일정
        <LinkBtn />
    </div>

    <div>
        이용내역
        <LinkBtn />
        즐겨찾기
        <LinkBtn />
        일정
        <LinkBtn />
    </div>

    <div>
        차단목록
        <MenuItem />
    </div>
    <div>
        푸시알림
        <MenuItem />
    </div>
    <div>
        고객센터
        <MenuItem />
    </div>
    <div>
        헬퍼 인증 및 변경
        <MenuItem />
    </div>
    <div>
        로그아웃
        <MenuItem />
    </div>
</template>

<script>
import UserCard from "@/components/home/UserCard.vue";
import HelperCard from "@/components/home/HelperCard.vue";
import LinkBtn from "@/components/home/LinkBtn.vue";
import MenuItem from "@/components/home/MenuItem.vue";

export default {
    name: "MyPage",

    components: {
        UserCard,
        HelperCard,
        LinkBtn,
        MenuItem,
    },
};
</script>

<style></style>
