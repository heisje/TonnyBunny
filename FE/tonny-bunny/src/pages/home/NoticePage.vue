<template>
	<h1>홈 - 고객센터 메인</h1>
	<div>
		공지사항
		<MyPageListItem />
	</div>
	<div>
		자주 묻는 질문
		<MyPageListItem />
	</div>
	<div>
		이메일 문의
		<MyPageListItem />
	</div>
	<div>
		고객센터 전화
		<MyPageListItem />
		<textarea name="" id="" cols="30" rows="10"></textarea>
	</div>
	<div>
		이용약관
		<MyPageListItem />
	</div>
	<div>
		회원탈퇴
		<MyPageListItem />
	</div>
</template>

<script>
import MyPageListItem from "@/components/home/MyPageListItem.vue";

export default {
	name: "NoticePage",
	components: {
		MyPageListItem
	}
};
</script>

<style></style>
