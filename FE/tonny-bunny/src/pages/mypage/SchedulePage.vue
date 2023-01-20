<template>
	<h1>마이페이지 - 일정 관리</h1>
	Calender 컴포넌트 어디서 들고오기

	일정알려주는 모달
	<CalenderModal/>

	일정추가모달
	<CalenderUpdateModal/>

</template>

<script>
import CalenderModal from "@/components/mypage/CalenderModal.vue"
import CalenderUpdateModal from "@/components/mypage/CalenderUpdateModal.vue"
	export default {
		name: "SchedulePage",
		components:{
			CalenderModal,
			CalenderUpdateModal,
		}
	};
</script>

<style></style>
