<template>
	<h1>고객센터 - 공지사항</h1>
	
	공지사항 및 FAQ 공통 게시물 형태
	<NoticePostItem/>
</template>

<script>
import NoticePostItem from "@/components/notice/NoticePostItem.vue"
	export default {
		name: "NoticePage",
		components:{
			NoticePostItem,
		}
	};
</script>

<style></style>
