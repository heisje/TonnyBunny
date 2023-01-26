<template>
    <div>
        <title-banner title="🐰 통역예약" text="헬퍼에게 통역 서비스를 예약합니다." />

        <YTonnyClientForm></YTonnyClientForm>

        <div>성공모달</div>
    </div>
</template>

<script>
import YTonnyClientForm from "@/components/ytonny/YTonnyClientForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
export default {
    components: { YTonnyClientForm, TitleBanner },
};
</script>

<style></style>
