<template>
	<h1>고객센터 - 자주묻는질문</h1>
	
	공지사항 및 FAQ 공통 게시물 형태
	<NoticePostItem/>
</template>

<script>
import NoticePostItem from "@/components/notice/NoticePostItem.vue"
	export default {
		name: "FAQPage",
		components:{
			NoticePostItem,
		}
	};
</script>

<style></style>
