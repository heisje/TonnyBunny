<template>
    <div>
        <quest-detail />

        <div>내 신청 정보</div>
        <div>수락버튼</div>
        <div>수락성공모달</div>
        <div>거절버튼</div>
        <div>수락거절모달</div>

        <div v-if="isHelper">
            <large-btn text="가격 제안 취소하기" />
            <router-link :to="{ name: 'YTonnyMatchingPage' }">
                <large-btn text="의뢰신청하기" />
            </router-link>
        </div>
        <div v-else>
            <TitleText title="가격을 제안한 헬퍼들" />
            <button>더보기</button>
            <div v-for="i in 5" :key="i">
                <HelperCard rightBtnText="상담하기" @clickRightBtn="clickRightBtn" />
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import QuestDetail from "@/components/common/QuestDetail.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
export default {
    name: "YTonnyDetailPage",
    data() {
        return {
            isHelper: true,
            isOpen: false,
        };
    },
    components: { TitleText, HelperCard, QuestDetail, LargeBtn },
    methods: {
        clickRightBtn() {
            this.$router.push({ name: "ChatDetailPage" });
        },
        openModal(e) {
            e.preventDefault();
            this.isOpen = true;
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};
</script>

<style></style>
