<template>
	<div>마이페이지 - 회원정보수정 수정</div>
	<img src="" alt="프로필사진">
	프로필수정버튼
	<!-- <ProfileUpdateBtn> -->
	<div>
		프로필사진선택모달 
		<ProfileImageModal/>
	</div>
	<div>
		<label for="nickname">닉네임</label>
		<input type="text" id="nickname">
	</div>
		<div>
		<label for="password1">현재 비밀번호</label>
		<input type="password" id="password1">
	</div>
		<div>
		<label for="password2">새 비밀번호</label>
		<input type="password" id="password2">
	</div>
		<div>
		<label for="password3">새 비밀번호 확인</label>
		<input type="password" id="password3">
	</div>
	<button class="largeBtn">완료</button>

</template>

<script>
import ProfileImageModal from "@/components/mypage/ProfileImageModal.vue"
	export default {
		name: "InfoUpdatePage",

		components: {
			ProfileImageModal
		},
	};
</script>

<style></style>
