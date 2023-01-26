<template>
    <div>
        <title-banner title="🐰 번역 맡기기" text="헬퍼에게 번역을 맡길 수 있습니다" />
        <NBunnyClientForm></NBunnyClientForm>
        <div>상단정보</div>
        <div>폼</div>
    </div>
</template>

<script>
import NBunnyClientForm from "@/components/nbunny/NBunnyClientForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
export default {
    components: { NBunnyClientForm, TitleBanner },
};
</script>

<style></style>
