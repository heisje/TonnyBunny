<template>
	<h1>홈 - 마이 페이지</h1>

	<div>
		유저카드
		<ClientCard />
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
		<MyPageBtn />
		즐겨찾기
		<MyPageBtn />
		일정
		<MyPageBtn />
	</div>

	<div>
		이용내역
		<MyPageBtn />
		즐겨찾기
		<MyPageBtn />
		일정
		<MyPageBtn />
	</div>

	<div>
		차단목록
		<MyPageListItem />
	</div>
	<div>
		푸시알림
		<MyPageListItem />
	</div>
	<div>
		고객센터
		<MyPageListItem />
	</div>
	<div>
		헬퍼 인증 및 변경
		<MyPageListItem />
	</div>
	<div>
		로그아웃
		<MyPageListItem />
	</div>
</template>

<script>
import ClientCard from "@/components/common/card/ClientCard.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import MyPageBtn from "@/components/home/MyPageBtn.vue";
import MyPageListItem from "@/components/home/MyPageListItem.vue";

export default {
	name: "MyPage",

	components: {
		ClientCard,
		HelperCard,
		MyPageBtn,
		MyPageListItem
	}
};
</script>

<style></style>
