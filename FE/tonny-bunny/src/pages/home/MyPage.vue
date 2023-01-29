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
		<title-text title="헬퍼프로필" type="h2" />
		<helper-card
			nickName="아스파라거스"
			oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
			fareText="2000"
			starText="4.0"
			countText="100"
			likeText="10"
			isLikeEmpty
			rightBtnText="상담하기"></helper-card>
		<!-- 
			자세히보기 XSmallBtn
			수정하기 XSmallBtn 
		-->
	</div>

	<div>
		<router-link :to="{ name: 'HistoryPage' }">이용내역<MyPageBtn /></router-link>
		<router-link :to="{ name: 'FavoriteListPage' }">즐겨찾기<MyPageBtn /></router-link>
		<router-link :to="{ name: 'SchedulePage' }">일정<MyPageBtn /></router-link>
	</div>

	<div>
		<router-link :to="{ name: 'BlockListPage' }">차단목록<MyPageListItem /></router-link>
	</div>
	<div>
		<router-link :to="{ name: 'PushAlarmPage' }">푸시알림<MyPageListItem /></router-link>
	</div>
	<div>
		<router-link :to="{ name: 'NoticePage' }">고객센터<MyPageListItem /></router-link>
	</div>
	<div>
		<router-link :to="{ name: 'HelperChangePage' }"
			>헬퍼 인증 및 변경<MyPageListItem
		/></router-link>
	</div>
	<medium-btn text="로그아웃" @click="openModal"></medium-btn>
	<AlarmModal
		v-show="isOpen"
		:isOpen="isOpen"
		title="완료"
		type="success"
		btnText2="예"
		btnColor1="main"
		btnColor2="carrot"
		btnFontColor1="white"
		btnFontColor2="white"
		@close-modal="closeModal"
		:to="{ name: 'HomePage' }">
		<template #content> 로그아웃이 완료되었습니다 </template>
	</AlarmModal>
</template>

<script>
import ClientCard from "@/components/common/card/ClientCard.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import MyPageBtn from "@/components/home/MyPageBtn.vue";
import MyPageListItem from "@/components/home/MyPageListItem.vue";
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
	name: "MyPage",
	data() {
		return {
			isHelper: true,
			isOpen: false
		};
	},
	components: {
		ClientCard,
		HelperCard,
		MyPageBtn,
		MyPageListItem,
		TitleText,
		AlarmModal,
		MediumBtn
	},
	methods: {
		openModal(e) {
			e.preventDefault();
			this.isOpen = true;
		},

		closeModal() {
			this.isOpen = false;
		}
	}
};
</script>

<style></style>
