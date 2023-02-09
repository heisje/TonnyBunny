<template>
    <title-text title="동시통역 종료" text="통역에 대한 리뷰를 남겨주세요" />

    <div>
        <TonnyResult />
    </div>

    <div>
        {{ getStartResData }}
    </div>
    <div>
        <br />
    </div>
    <div>
        {{ getCompleteData }}
    </div>
    <!-- <div v-if="isHelper == true">
        <button class="mediumBtn">이용내역확인</button>
    </div>
    <div v-else>
        <router-link :to="{ name: 'ReviewPage' }">
            <small-btn color="carrot" text="리뷰쓰기" />
        </router-link>

        <router-link :to="{ name: 'HomePage' }">
            <small-btn color="white" font="sub" text="다음에 작성하기" />
        </router-link>
    </div> -->
</template>

<script>
import TonnyResult from "@/components/live/TonnyResult.vue";
import TitleText from "@/components/common/TitleText.vue";
// import SmallBtn from "@/components/common/button/SmallBtn.vue";
import { mapGetters } from "vuex";

export default {
    name: "LiveClosePage",

    components: {
        TonnyResult,
        TitleText,
        // SmallBtn,
    },

    computed: {
        ...mapGetters({ getStartResData: "getStartResData" }),
        ...mapGetters({ getCompleteData: "getCompleteData" }),
    },

    data() {
        return {};
    },
};
</script>

<style></style>
