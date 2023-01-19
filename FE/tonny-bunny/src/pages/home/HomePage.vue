<template>
	<h1>홈 - 메인</h1>
	<v-alert type="success">토니버니만의 서비스를 이용해보세요!</v-alert>

	<div>
		통역
		<ServiceBtn/>
		번역
		<ServiceBtn/>
	</div>

	<div v-if="isHelper === true">
		헬퍼페이지
		<ServiceBtn/>
	</div>
	<div v-if="isHelper === true">
		나의 의뢰
		<QuestList/>
	</div>
	<div> 
		오늘 일정
		<ScheduleList/>
	</div>
	<div>
		커뮤니티
		<BoardList/>
	</div>
	<div>
		즉시대기
		<QuickBtn/>
		헬퍼페이지
		<QuickBtn/>
	</div>

</template>

<script>
/* eslint-disable */
import ButtonTest from "@/components/common/button/ButtonTest.vue";

export default {
    components: {
        ButtonTest,
    },
};
</script>

<style></style>
