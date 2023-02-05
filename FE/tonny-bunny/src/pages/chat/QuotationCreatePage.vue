<template>
	<div>
		<title-banner title="견적서 작성 페이지" text="견적서를 통해 계약해보세요" />
		<y-tonny-quotation-form></y-tonny-quotation-form>
		<!-- <quotation-form /> -->
	</div>
</template>

<script>
// import QuotationForm from "@/components/chat/QuotationForm.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import YTonnyQuotationForm from "@/components/chat/YTonnyQuotationForm.vue";

export default {
	components: {
		// QuotationForm,
		TitleBanner,
		YTonnyQuotationForm
	}
};
</script>

<style></style>
