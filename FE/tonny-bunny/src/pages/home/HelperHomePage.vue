<template>
	<h1>홈 - 헬퍼 랜딩 메인</h1>
	<div>
		헬퍼 카드
		<HelperCard/>
	</div>
	<div>
		통역
		<ServiceBtn/>
		번역
		<ServiceBtn/>
		헬퍼
		<ServiceBtn/>
	</div>
	<div>
		나의 의뢰
		<QuestList/>
	</div> 
	<div>
		오늘 일정
		<ScheduleList/>
	</div>
</template>

<script>
import ServiceBtn from "@/components/home/ServiceBtn.vue"
import HelperCard from "@/components/home/HelperCard.vue"
import QuestList from "@/components/home/QuestList.vue"
import ScheduleList from "@/components/home/ScheduleList.vue"
	export default {
		name: "HelperHomePage",

		components: {
			ServiceBtn,
			HelperCard,
			QuestList,
			ScheduleList,
		},
	};
</script>

<style></style>
