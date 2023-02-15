<template>
	<div>
		<TitleText title="찾으시는 페이지가 존재하지 않습니다." center text="경로를 다시 한번 확인해주세요."></TitleText>
		<loading-item class="carrotWidth w-50" />
		<div style="margin-top: 8px">
                <router-link :to="{ name: 'HomePage' }">
                    <smallBtn
                        color="outline"
                        font="main"
                        style="width: 100%"
                        text="홈으로 돌아가기"></smallBtn>
                </router-link>
            </div>
	</div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import LoadingItem from "@/components/common/LoadingItem.vue";
export default {
	components:{
		TitleText,
		smallBtn,
		LoadingItem,

	}
};
</script>

<style></style>
