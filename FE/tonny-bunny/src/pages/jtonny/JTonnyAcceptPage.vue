<template>
    <div>
        <div v-if="isFind">
            <title-text title="즉시 통역 찾기" text="지금 당장 번역이 필요한 고객을 찾습니다" />
            <JTonnyLoading />
        </div>
        <div v-else>
            <title-text
                title="즉시 통역 요청이 왔어요!"
                text="통역 요청글을 자세히 보고 가능한 통역을 신청하세요!" />
            <JTonnyLoading />
        </div>
        <router-link :to="{ name: 'JTonnyMatchingPage' }"><button>다음페이지</button></router-link>
        <large-btn text="대기 취소" @click.prevent="openModal" />

        <div v-for="i in 5" :key="i">
            <helper-card />
        </div>

        <AlarmModal
            v-show="isOpen"
            :isOpen="isOpen"
            title="확인"
            type="success"
            btnText2="다음"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal"
            :link="{ name: 'HomePage' }">
            <template #content> 대기가 취소되었습니다<br />또 이용해주세요! </template>
        </AlarmModal>
    </div>
</template>

<script>
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    components: {
        JTonnyLoading,
        TitleText,
        LargeBtn,
        HelperCard,
        AlarmModal,
    },
    data() {
        return {
            isFind: false,
            isOpen: false,
        };
    },
    methods: {
        openModal(e) {
            e.preventDefault();
            this.isOpen = true;
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};

// import JTonnyLoading from '@/components/jtonny/JTonnyLoading.vue';
// export default {
//   components: { JTonnyLoading },};
</script>

<style></style>
