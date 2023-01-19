<template>
    <div>
        <h1>커뮤니티 - 게시글 상세 조회</h1>

        <div>메인 텍스트 배너</div>

        <div>수정 버튼 라인 (점 3개)</div>
        <div>제목 라인</div>
        <div>날짜 라인</div>
        <div>작성자, 카테고리 라인</div>
        <div>사진 라인</div>
        <div>내용 라인</div>

        <div>
            댓글 컴포넌트 CommentList

            <div>작성자 폼</div>
            <div>댓글 메타 정보 라인</div>
            <CommentListItem />
        </div>
    </div>
</template>

<script>
import CommentListItem from "@/components/board/CommentListItem.vue";

export default {
    components: {
        CommentListItem,
    },
};
</script>

<style></style>
