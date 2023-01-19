<template>
	<h1>홈 - 헬퍼 랜딩 메인</h1>
	<div>
		헬퍼 카드
		<HelperCard />
	</div>
	<div>
		통역
		<MainBtn />
		번역
		<MainBtn />
		헬퍼
		<MainBtn />
	</div>
	<div>
		나의 의뢰
		<QuestList />
	</div>
	<div>
		오늘 일정
		<ScheduleList />
	</div>
</template>

<script>
import MainBtn from "@/components/home/MainBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import QuestList from "@/components/home/QuestList.vue";
import ScheduleList from "@/components/home/ScheduleList.vue";

export default {
	name: "HelperHomePage",

	components: {
		MainBtn,
		HelperCard,
		QuestList,
		ScheduleList
	}
};
</script>

<style></style>
