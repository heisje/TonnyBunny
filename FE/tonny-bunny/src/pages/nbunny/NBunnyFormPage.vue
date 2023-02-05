<template>
    <div>
        <title-banner title="🐰 번역 맡기기" text="헬퍼에게 번역을 맡길 수 있습니다" />
        <NBunnyClientForm @toggleSubmit="openModal"></NBunnyClientForm>
        <AlarmModal
            title="성공"
            type="success"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @clickBtn2="clickBtn2">
            <template #content>
                글이 성공적으로 작성되었습니다!
                <br />
                제안 온 헬퍼에게 상담신청을 눌러 상담해보세요
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import NBunnyClientForm from "@/components/nbunny/NBunnyClientForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
export default {
    name: "NBunnyFormPage",
    data() {
        return {};
    },
    components: { NBunnyClientForm, TitleBanner, AlarmModal },
    methods: {
        openModal(e) {
            // this.$store.commit("TOGGLE_ALARM_MODAL");
            e.preventDefault();
        },

        clickBtn2() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
            const bunnySeq = this.$store.state.bunny.bunnySeq;
            console.log(bunnySeq);
            this.$router.push({ name: "NBunnyDetailPage", params: { id: bunnySeq } });
        },
    },
};
</script>

<style></style>
