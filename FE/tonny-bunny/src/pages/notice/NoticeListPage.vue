<template>
    <TitleText title="공지사항" center text="토니버니 이용을 위한 공지사항입니다." type="h1" ></TitleText>
    <div class="container w-50">
        <NoticePostItem title="공지사항 1. 토니버니 서비스가 출시되었습니다." content="여러분들의 성원에 보답하는 서비스를 제공하도록 노력하겠습니다."/>
        <NoticePostItem title="공지사항 2. 신고 누적 처리 결과입니다." content="닉네임 baebug 님이 불법 프로그램 사용으로 서비스 정지처리 되었습니다.."/>
        <NoticePostItem title="공지사항 3. 잘해오조 화이팅입니다." content="노력한 만큼 좋은 결과로 돌려받으면 좋겠습니다."/>
        <div style="margin-top: 8px">
            <router-link :to="{ name: 'HomePage' }">
                <smallBtn
                    color="outline"
                    font="main"
                    style="width: 100%"
                    text="홈으로 돌아가기"></smallBtn>
            </router-link>
        </div>
    </div>

</template>

<script>
import NoticePostItem from "@/components/notice/NoticePostItem"
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
export default {
    name: "NoticePage",
    components: {
        NoticePostItem,
        TitleText,
        smallBtn,
    },
};
</script>

<style></style>
