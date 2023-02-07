<template>
    <div class="cardContainer">
        <div class="cardWrap">
            <div class="questCard">
                <!-- 1. x 버튼 라인 -->
                <!-- <div class="closeBtn"> -->
                <!-- <span class="material-symbols-outlined"> close </span> -->
                <!-- </div> -->

                <!-- 2. tag 라인 -->
                <div class="questTag">
                    <div class="tags">
                        <square-tag sub :text="allCode[questDetail?.taskCode]"></square-tag>
                        <square-tag
                            sub
                            :text="
                                allCode[questDetail?.startLangCode] +
                                ' - ' +
                                allCode[questDetail?.endLangCode]
                            "></square-tag>
                    </div>
                    <div class="process">
                        <square-tag :text="allCode[questDetail?.taskStateCode]" sub></square-tag>
                    </div>
                </div>

                <!-- 3. client - helper 라인 -->
                <div class="questProfile mt-3">
                    <div class="profile">
                        <img
                            class="profileImg"
                            src="@/assets/noProfile_white.png"
                            width="50"
                            height="50" />
                        <div class="userType">
                            <span class="label">고객</span>
                            <h4 class="overflow" v-if="questDetail?.client?.nickName">
                                {{ questDetail?.client?.nickName }}
                            </h4>
                            <h4 class="overflow" v-else>미지정</h4>
                        </div>
                    </div>

                    <div>
                        <span class="material-symbols-outlined arrow"> sync_alt </span>
                    </div>

                    <div class="profile">
                        <div class="userType">
                            <span class="label">헬퍼</span>
                            <h4 class="overflow" v-if="questDetail?.helper?.nickName">
                                {{ questDetail?.helper?.nickName }}
                            </h4>
                            <h4 class="overflow" v-else>미지정</h4>
                        </div>
                        <img
                            class="profileImg"
                            src="@/assets/noProfile_white.png"
                            width="50"
                            height="50" />
                    </div>
                </div>

                <!-- 4. 상황 설명 라인 -->
                <div class="questInfo">
                    <div class="infoDesc mb-4">
                        <h3 class="title">{{ questDetail?.title }}</h3>
                        <div class="sub">{{ questDetail?.content }}</div>
                    </div>

                    <div class="infoDateWrap">
                        <div v-if="allCode[questDetail?.taskCode] == '즉시통역'" class="infoDate">
                            <div class="date">
                                <h4>날짜</h4>
                                <div>{{ questDetail?.startDate }}</div>
                            </div>
                            <div class="time">
                                <h4>시간</h4>
                                <div>{{ questDetail?.estimateTime }}</div>
                            </div>
                        </div>
                        <div
                            v-else-if="allCode[questDetail?.taskCode] == '예약통역'"
                            class="infoDate">
                            <div class="date">
                                <h4>날짜</h4>
                                <div>{{ questDetail?.estimateDate.substr(0, 10) }}</div>
                            </div>
                            <div class="time">
                                <h4>시간</h4>
                                <div>
                                    {{ questDetail?.estimateStartTime
                                    }}{{ questDetail?.estimateTime }}
                                </div>
                            </div>
                        </div>
                        <div v-else-if="allCode[questDetail?.taskCode] == '번역'" class="infoDate">
                            <div class="date">
                                <h4>마감기한</h4>
                                <div>{{ questDetail?.startDate.substr(0, 10) }}</div>
                            </div>
                            <div class="time">
                                <h4>마감시간</h4>
                                <div>{{ questDetail?.endDate.substr(0, 10) }}</div>
                            </div>
                        </div>
                    </div>

                    <div class="infoBottom">
                        <div class="infoFare" v-if="allCode[questDetail?.taskCode] == '예약통역'">
                            <h4>예상 금액</h4>
                            <div v-if="questDetail?.estimatePrice == 0">
                                <div>미설정<span>캐럿</span></div>
                            </div>
                            <div v-else>
                                <div>{{ questDetail?.estimatePrice }} <span>캐럿</span></div>
                            </div>
                        </div>
                        <div class="infoCategory">
                            <h4>상황 카테고리</h4>
                            <div style="width: 100%">
                                <square-tag
                                    sub
                                    :text="allCode[questDetail?.bunnySituCode]"></square-tag>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 5. 하단 버튼 라인 -->
                <div class="questBtns mt-2">
                    <XSmallBtn text="자세히보기" @click="clickBtn1" />
                    <XSmallBtn :text="rightBtnText" color="carrot" @click="clickBtn2" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SquareTag from "../tag/SquareTag.vue";
import XSmallBtn from "../button/XSmallBtn.vue";
import { mapGetters } from "vuex";
// import { getKeyByValue } from "@/common/utils";
export default {
    name: "QuestCard",

    components: {
        SquareTag,
        XSmallBtn,
    },

    props: ["questDetail"],

    computed: {
        ...mapGetters({ allCode: "getAllCode" }),
    },

    methods: {
        clickBtn1(e) {
            e.preventDefault();
            this.$emit("clickBtn1");
        },

        clickBtn2(e) {
            e.preventDefault();
            this.$emit("clickBtn2");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/card.scss";
</style>
