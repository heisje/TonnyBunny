<template>
	<h1>마이페이지 - 개인 프로필</h1>
	프로필 카드 (본인일 시 정보수정 버튼 보임)
	
	어학능력
	x-small 버튼으로 나열

	자격증 (컴포넌트로 보여주는 게 나을듯)
	<CertifyItem/>
	헬퍼 소개서
	등록된 이미지 Card 형태로 보여줌
	
	본인설명 두 줄

	리뷰내역
	<ReviewItem/>

	(남이볼 때)
	<button>large버튼 - 상담 요청?</button>

</template>

<script>
import CertifyItem from "@/components/mypage/CertifyItem.vue"
import ReviewItem from "@/components/mypage/ReviewItem.vue"
	export default {
		name: "ProfilePage",
		components:{
			CertifyItem,
			ReviewItem,
		}
	};
</script>

<style></style>
