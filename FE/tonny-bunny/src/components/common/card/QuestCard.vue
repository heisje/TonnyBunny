<template>
    <div class="cardContainer questCardContainer">
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
                        <square-tag
                            v-if="allCode[questDetail?.taskStateCode] == '모집중'"
                            :text="allCode[questDetail?.taskStateCode]"
                            success></square-tag>
                        <square-tag
                            v-if="allCode[questDetail?.taskStateCode] == '진행중'"
                            :text="allCode[questDetail?.taskStateCode]"
                            info></square-tag>
                        <square-tag
                            v-if="allCode[questDetail?.taskStateCode] == '완료됨'"
                            :text="allCode[questDetail?.taskStateCode]"
                            white></square-tag>
                    </div>
                </div>

                <!-- 3. client - helper 라인 -->
                <div class="questProfile mt-3">
                    <div class="profile">
                        <UserProfileImg
                            class="profileImg"
                            :profileImagePath="questDetail?.client?.profileImagePath" />
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
                        <UserProfileImg
                            class="profileImg"
                            :profileImagePath="questDetail?.helper?.profileImagePath" />
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
                                <div>{{ questDetail?.startDateTime }}</div>
                            </div>
                            <div class="time">
                                <h4>예약시간</h4>
                                <div>
                                    {{ questDetail?.estimateTime }}
                                </div>
                            </div>
                        </div>
                        <div v-else-if="allCode[questDetail?.taskCode] == '번역'" class="infoDate">
                            <div class="date">
                                <h4>마감기한</h4>
                                <div>{{ questDetail?.startDateTime }}</div>
                            </div>
                            <div class="time">
                                <h4>마감시간</h4>
                                <div>{{ questDetail?.endDateTime }}</div>
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
                                <div v-if="allCode[questDetail?.taskCode] == '즉시통역'">
                                    <square-tag
                                        sub
                                        :text="allCode[questDetail?.tonnySituCode]"></square-tag>
                                </div>
                                <div v-if="allCode[questDetail?.taskCode] == '예약통역'">
                                    <square-tag
                                        sub
                                        :text="allCode[questDetail?.tonnySituCode]"></square-tag>
                                </div>
                                <div v-if="allCode[questDetail?.taskCode] == '번역'">
                                    <square-tag
                                        sub
                                        :text="allCode[questDetail?.bunnySituCode]"></square-tag>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 5. 하단 버튼 라인 -->
                <div class="questBtns mt-2">
                    <div v-if="!(allCode[questDetail?.taskCode] == '즉시통역')">
                        <XSmallBtn
                            text="자세히보기"
                            @click="clickBtn1"
                            color="outline"
                            font="active" />
                    </div>
                    <div v-if="allCode[questDetail?.taskStateCode] == '모집중'">
                        <XSmallBtn :text="rightBtnText" color="carrot" @click="clickBtn2" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SquareTag from "../tag/SquareTag.vue";
import XSmallBtn from "../button/XSmallBtn.vue";
import { mapGetters } from "vuex";
import UserProfileImg from "../UserProfileImg.vue";
// import { getKeyByValue } from "@/common/utils";
export default {
    name: "QuestCard",

    components: {
        SquareTag,
        XSmallBtn,
        UserProfileImg,
    },

    props: {
        questDetail: {
            typeof: Array,
        },

        rightBtnText: {
            type: String,
            default: "수락하기",
            description: "오른쪽 버튼 텍스트 [수락하기, 상담하기 etc]",
        },
    },

    computed: {
        ...mapGetters({ allCode: "getAllCode" }),
    },

    methods: {
        clickBtn1(e) {
            e.preventDefault();
            this.$emit("clickBtn1");

            if (this.allCode[this.questDetail?.taskCode] == "즉시통역") {
                this.$router.push({
                    name: "NBunnyDetailPage",
                    params: { id: this.questDetail?.seq },
                });
            } else if (this.allCode[this.questDetail?.taskCode] == "예약통역") {
                this.$router.push({
                    name: "YTonnyDetailPage",
                    params: { id: this.questDetail?.seq },
                });
            } else if (this.allCode[this.questDetail?.taskCode] == "번역") {
                this.$router.push({
                    name: "NBunnyDetailPage",
                    params: { id: this.questDetail?.seq },
                });
            }
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
