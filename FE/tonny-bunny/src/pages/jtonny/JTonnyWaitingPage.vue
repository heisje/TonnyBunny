<template>
    <div>
        <div v-if="!isFind">
            <title-text title="🐰통역을 도와줄 헬퍼를 찾는 중입니다.." />
            <large-btn color="light" font="live" text="찾기 취소" style="width: 100%" />
            <JTonnyLoading></JTonnyLoading>
        </div>
        <div v-else>
            <title-text
                title="🐰통역 가능한 헬퍼가 도착하고 있습니다..."
                text="헬퍼의 프로필을 자세히 보고 통역을 부탁한 헬퍼를 수락해주세요" />
            <large-btn color="light" font="live" text="찾기 취소" style="width: 100%" />
            <JTonnyLoading></JTonnyLoading>
            <div v-for="i in 2" :key="i">
                <helper-card />
            </div>
        </div>
        <small-btn color="light" font="live" text="찾으면" @click.prevent="찾아짐()" />
        <small-btn color="light" font="live" text="수락하기누름" @click.prevent="openModal" />

        <AlarmModal
            v-show="isOpen1"
            :isOpen="isOpen1"
            title="경고"
            type="danger"
            btnText1="취소"
            btnText2="진행"
            btnColor1="light"
            btnColor2="carrot"
            btnFontColor1="sub"
            btnFontColor2="white"
            @close-modal="closeModal"
            :link="{ name: 'LivePage' }">
            <template #content>
                수락하시면 바로 해당 헬퍼와 동시 통역이 진행됩니다. 진행하시겠습니까?
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    data() {
        return {
            isFind: false,
            isOpen1: false,
        };
    },
    components: {
        JTonnyLoading,
        TitleText,
        SmallBtn,
        HelperCard,
        LargeBtn,
        AlarmModal,
    },
    methods: {
        찾아짐() {
            this.isFind = true;
        },
        openModal(e) {
            e.preventDefault();
            this.isOpen1 = true;
        },

        closeModal() {
            this.isOpen1 = false;
        },
    },
};
</script>

<style></style>
