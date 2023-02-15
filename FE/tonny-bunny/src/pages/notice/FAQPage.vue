<template>
    <title-banner title="FAQ" text="🐰자주 묻는 질문입니다." />
    <div class="container w-50">
        <NoticePostItem
            title="질문 1. 헬퍼로 전환하려면 어떻게 해야하나요?"
            content="마이페이지 - 헬퍼전환 탭에서 손쉽게 헬퍼 등록을 하실 수 있습니다."
        />
        <NoticePostItem
            title="질문 2. 결제는 어떻게 이루어지나요?"
            content="결제는 등록한 결제 수단을 통해 이루어집니다. 자동 결제 서비스가 추후 추가될 예정입니다."
        />
        <NoticePostItem
            title="질문 3. 잘해오조 화이팅입니다."
            content="노력한 만큼 좋은 결과로 돌려받으면 좋겠습니다."
        />
        <div style="margin-top: 8px">
            <router-link :to="{ name: 'HomePage' }">
                <smallBtn
                    color="outline"
                    font="main"
                    style="width: 100%"
                    text="홈으로 돌아가기"
                ></smallBtn>
            </router-link>
        </div>
    </div>
</template>

<script>
import NoticePostItem from "@/components/notice/NoticePostItem";
import TitleBanner from "@/components/common/TitleBanner.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
export default {
    name: "FAQPage",
    components: {
        NoticePostItem,
        TitleBanner,
        smallBtn,
    },
};
</script>

<style></style>
