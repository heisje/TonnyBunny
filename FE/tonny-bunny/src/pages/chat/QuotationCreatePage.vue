<template>
    <div>
        <title-banner title="🐰 견적서 작성" text="견적서를 통해 계약해보세요" />

        <quotation-form />
    </div>
</template>

<script>
import QuotationForm from "@/components/chat/QuotationForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
export default {
    components: { QuotationForm, TitleBanner },
};
</script>

<style></style>
