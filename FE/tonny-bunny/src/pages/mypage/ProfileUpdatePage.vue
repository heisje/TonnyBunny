<template>
	<h1>마이페이지 - 개인 프로필 수정</h1>
	헬퍼 프로필 번경
	
	언어선택
	셀렉트박스 <button>small버튼 - 추가</button>

	자격증 추가
	인풋 (검색으로 바꿀 수도 있음. DB에 따라) <button>small버튼 - 추가</button>

	한 줄 소개
	<input type="text">

	사진 첨부
	(갯수 제한 두는 게 좋을듯)
	+ 그려진 사진 누르면 사진 추가 모달 뜸

	본인 소개
	<textarea name="" id="" cols="30" rows="10">소개입력</textarea>

	약관동의 체크박스 / <button>자세히보기</button>

	<button>large버튼 - 수정완료</button>
</template>

<script>
	export default {
		name: "ProfileUpdatePage",
	};
</script>

<style></style>
