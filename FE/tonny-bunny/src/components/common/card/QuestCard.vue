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
                        <square-tag sub :text="categoryToStr"></square-tag>
                        <square-tag sub :text="language"></square-tag>
                    </div>
                    <div class="process">
                        <square-tag success></square-tag>
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
                        <h4>{{ helperNickname }}</h4>
                    </div>

                    <div>
                        <span class="material-symbols-outlined arrow"> sync_alt </span>
                    </div>

                    <div class="profile">
                        <h4>{{ clientNickname }}</h4>
                        <img
                            class="profileImg"
                            src="@/assets/noProfile_white.png"
                            width="40"
                            height="40" />
                    </div>
                </div>

                <!-- 4. 상황 설명 라인 -->
                <div class="questInfo">
                    <div class="infoDesc mb-4">
                        <h3 class="title">바이어 미팅 건으로 급하게 통역을 요청합니다.</h3>
                        <div class="sub">
                            자동차 관련 회사입니다. 생활 영어 능숙하신 분 찾습니다.
                        </div>
                    </div>

                    <div class="infoDate">
                        <div v-if="category == 'jtonny'" class="jtonnyDate">
                            <div class="date">
                                <h4>날짜</h4>
                                <div>상세날짜</div>
                            </div>
                            <div class="time">
                                <h4>시간</h4>
                                <div>상세시간</div>
                            </div>
                        </div>

                        <div v-else-if="category == 'ytonny'" class="ytonnyDate">
                            <div class="date">
                                <h4>마감날짜</h4>
                                <div>상세날짜</div>
                            </div>
                            <div class="time">
                                <h4>마감시간</h4>
                                <div>상세시간</div>
                            </div>
                        </div>
                    </div>

                    <div class="infoBottom">
                        <div class="infoFare">
                            <h4>예상요금</h4>
                            <div>1000 <span>캐럿</span></div>
                        </div>
                        <div class="infoCategory">
                            <h4>상황 카테고리</h4>
                            <div style="width: 100%">
                                <square-tag sub text="카테고리"></square-tag>
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

export default {
    name: "QuestCard",

    components: {
        SquareTag,
        XSmallBtn,
    },

    props: {
        category: {
            type: String,
            default: "jtonny",
            description:
                "즉시통역 / 예약통역 / 번역의뢰 / 번역상담 [jtonny / ytonny / nbunny / pbunny]",
        },

        process: {
            type: String,
            default: "success",
            description: "success:모집중, info:진행중, white:완료됨",
        },

        language: {
            type: String,
            default: "영어 ↔ 한국어",
            description: "통번역 카테고리 [즉시통역, 통역예약 etc..]",
        },

        processColor: {
            type: String,
            default: "success",
        },
        processText: {
            type: String,
            default: "success",
        },

        helperNickname: {
            type: String,
            default: "헬퍼명",
        },
        clientNickname: {
            type: String,
            default: "고객명",
        },

        rightBtnText: {
            type: String,
            default: "수락하기",
            description: "오른쪽 버튼 텍스트 [수락하기, 상담하기 etc]",
        },
    },

    computed: {
        categoryToStr() {
            if (this.category == "jtonny") return "즉시통역";
            else if (this.category == "ytonny") return "예약통역";
            else if (this.category == "nbunny") return "번역의뢰";
            else return "번역상담";
        },
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
