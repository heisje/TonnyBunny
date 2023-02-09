<template lang="">
    <div class="helperProfileContainer">
        <div class="helperProfileWrap">
            <div>
                <TitleText title="헬퍼프로필" type="h2" />
                <helper-Card disable :userInfo="userInfo" />

                <TitleText title="어학능력" type="h2" />
                <div>
                    <div
                        v-for="possibleLanguageItem in userInfo?.helperInfo?.possibleLanguageList"
                        :key="possibleLanguageItem">
                        <CircleTag :text="possibleLanguageItem?.name" />
                    </div>
                </div>

                <TitleText title="자격증" type="h2" />
                <div
                    v-for="certificateItem in userInfo?.helperInfo?.certificateList"
                    :key="certificateItem">
                    <div>
                        <SquareTag sub :text="allCode[certificateItem?.langCode]" />
                        {{ certificateItem?.certName }}
                        {{ certificateItem?.content }}
                    </div>
                </div>

                <TitleText title="헬퍼 소개" type="h2" />
                <div>
                    <div>
                        {{ userInfo?.helperInfo?.introduction }}
                    </div>
                </div>

                <TitleText title="리뷰" type="h2" />
                {{ userReview }}

                <LargeBtn text="수정하기" />
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import TitleText from "./TitleText.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import CircleTag from "@/components/common/tag/CircleTag.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";

export default {
    components: {
        HelperCard,
        TitleText,
        CircleTag,
        LargeBtn,
        SquareTag,
    },
    props: {
        userInfo: {
            type: Object,
        },
    },

    computed: {
        ...mapGetters({ allCode: "getAllCode" }),
        ...mapGetters({ userReview: "getUserReview" }),
    },

    mounted() {
        this.$store.dispatch("getUserReview", this.userInfo.seq);
    },
};
</script>
<style lang="scss" scoped>
.helperProfileContainer {
    .helperProfileWrap {
    }
}
</style>
