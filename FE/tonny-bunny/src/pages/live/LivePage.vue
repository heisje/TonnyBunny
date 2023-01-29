<template>
    <h1>라이브 - on air 페이지</h1>
    <div>
        <TonnyInfo @toggle="(e) => (isOpen = true)" />
    </div>
    <div>
        채팅모달
        <ChatModal />
    </div>

    <large-btn color="carrot" text="채팅하기" />
    <div v-if="isHelper">
        <AlarmModal
            v-show="isOpen"
            :isOpen="isOpen"
            title="종료 요청"
            type="danger"
            btnText1="확인"
            btnText2="취소인데 일단 이게 확인"
            btnColor1="carrot"
            btnColor2="light"
            btnFontColor1="white"
            btnFontColor2="main"
            @close-modal="closeModal"
            :to="{ name: 'LiveClosePage' }">
            <template #content>
                동시통역 종료를 신청하시겠습니까?<br />고객이 동의하면 종료됩니다.
            </template>
        </AlarmModal>
    </div>
    <div v-else>
        <AlarmModal
            v-show="isOpen"
            :isOpen="isOpen"
            title="통역 종료"
            type="danger"
            btnText1="확인"
            btnText2="취소인데 일단 이게 확인"
            btnColor1="carrot"
            btnColor2="light"
            btnFontColor1="white"
            btnFontColor2="main"
            @close-modal="closeModal"
            :to="{ name: 'LiveClosePage' }">
            <template #content>
                동시통역을 종료하시겠습니까?<br />이용시간: 10분 / 비용: 500캐럿
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import TonnyInfo from "@/components/live/TonnyInfo.vue";
import ChatModal from "@/components/live/ChatModal.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    name: "LivePage",
    data() {
        return {
            isOpen: false,
            isHelper: true,
        };
    },
    components: {
        TonnyInfo,
        ChatModal,
        LargeBtn,
        AlarmModal,
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
</script>

<style></style>
