<template lang="">
    <div>
        <div>
            <square-tag :text="allCode[questDetail?.taskCode]" sub></square-tag>
            <square-tag :text="allCode[questDetail?.taskStateCode]" sub></square-tag>
        </div>
        <div>
            <TitleText :title="questDetail?.title" type="h1-small" top="0" />
        </div>
        <div>
            <div>
                <TitleText title="번역서비스 요청자" type="h2" />
                <img src="" alt="" />
                <span>{{ questDetail?.clientSeq }}</span>
            </div>
            <div>
                <TitleText title="번역서비스 제공자" type="h2" />
                <img src="" alt="" />
                <span>{{ questDetail?.helperSeq }}</span>
            </div>
        </div>

        <div>
            <TitleText title="번역언어" type="h2" />
            <square-tag
                :text="
                    allCode[questDetail?.startLangCode] + ' - ' + allCode[questDetail?.endLangCode]
                "
                sub></square-tag>
        </div>

        <div v-if="allCode[questDetail?.taskCode] == '즉시통역'">
            <div>
                <TitleText title="금액" type="h2" />
                <h3>{{ questDetail?.startDate }}캐럿</h3>
            </div>
            <div>
                <TitleText title="카테고리" type="h2" />
                <square-tag :text="tonnySituCode" sub></square-tag>
            </div>
        </div>

        <div v-if="allCode[questDetail?.taskCode] == '예약통역'">
            <div>
                <TitleText title="금액" type="h2" />
                <h3>{{ questDetail?.startDate }}캐럿</h3>
            </div>
            <div>
                <TitleText title="카테고리" type="h2" />
                <square-tag :text="tonnySituCode" sub></square-tag>
            </div>
        </div>

        <div v-if="allCode[questDetail?.taskCode] == '번역'">
            <div>
                <TitleText title="금액" type="h2" />
                <h3>{{ questDetail?.startDate }}캐럿</h3>
            </div>
            <div>
                <TitleText title="카테고리" type="h2" />
                <square-tag :text="bunnySituCode" sub></square-tag>
            </div>
            <div>
                <TitleText title="마감기한" type="h2" />
                <h3>{{ questDetail?.startDate }}</h3>
            </div>
            <div>
                <TitleText title="마감기한" type="h2" />
                <h3>{{ questDetail?.endDate }}</h3>
            </div>
        </div>

        <div>
            <TitleText title="내용" type="h2" />
            <p>{{ questDetail?.content }}</p>
        </div>

        <div>
            <TitleText title="사진" type="h2" />
            <div style="width: 100px; height: 100px; background-color: #999999"></div>
        </div>
    </div>
</template>
<script>
import TitleText from "./TitleText.vue";
import SquareTag from "./tag/SquareTag.vue";
import { mapGetters } from "vuex";

export default {
    name: "QuestDetail",
    components: {
        TitleText,
        SquareTag,
    },
    data() {
        return {
            isHelper: false,
        };
    },
    props: ["questDetail"],

    computed: {
        ...mapGetters({ allCode: "getAllCode" }),
    },
};
</script>
<style lang=""></style>
