<template>
    <div>
        <title-banner
            title="즉시통역 서비스 신청"
            text="실시간으로 헬퍼에게 통역 서비스를 신청합니다" />

        <JTonnyClientForm />
    </div>
</template>

<script>
import JTonnyClientForm from "@/components/jtonny/JTonnyClientForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";

export default {
    components: {
        JTonnyClientForm,
        TitleBanner,
    },
};
</script>

<style></style>
