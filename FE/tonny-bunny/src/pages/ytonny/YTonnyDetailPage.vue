<template>
    <!-- <title-banner title="예약통역 공고" text=""></title-banner> -->
    <div class="yTonnyDetailWrap">
        <!-- yTonny Detail View -->
        <div class="yTonnyDetail customForm">
            <!-- yTonny 공고 정보 라인 -->
            <div class="infos">
                <div class="tag">
                    <div>
                        <square-tag sub :text="yTonnyDetail.taskCode" class="me-2"></square-tag>
                        <square-tag sub :text="yTonnyDetail.taskStateCode"></square-tag>
                    </div>
                    <div>
                        <square-tag success></square-tag>
                    </div>
                </div>

                <div class="titles mt-4">
                    <title-text
                        type="h1"
                        :title="yTonnyDetail.title"
                        top="10"
                        bottom="10"></title-text>
                </div>

                <div class="metas mb-5">
                    <div class="writers">
                        <img
                            :src="yTonnyDetail.client.profileImagePath"
                            width="40"
                            height="40"
                            class="me-3" />
                        <div>{{ yTonnyDetail.client.nickName }}</div>
                    </div>
                    <div class="edits">
                        <div>{{ yTonnyDetail.createdAt }}</div>
                        <span class="material-symbols-outlined fs-4"> more_vert </span>
                    </div>
                </div>

                <div class="langs mb-5">
                    <h3 class="mb-2">통역 언어</h3>
                    <div class="d-flex flex-row align-items-center">
                        <square-tag
                            :text="yTonnyDetail.startLangCode"
                            sub
                            class="me-2"></square-tag>
                        <div class="me-2">
                            <span class="material-symbols-outlined"> compare_arrows </span>
                        </div>
                        <square-tag :text="yTonnyDetail.endLangCode" sub></square-tag>
                    </div>
                </div>

                <div class="dates mb-5">
                    <h3 class="mb-2">통역 예약 시간</h3>
                    <div>{{ yTonnyDetail.startDateTime }}</div>
                    <div>{{ yTonnyDetail.estimateTime }}</div>
                </div>

                <div class="prices mb-5">
                    <h3 class="mb-2">지불 금액</h3>
                    <div>{{ yTonnyDetail.estimatePrice }}</div>
                </div>

                <div class="categorys mb-5 d-flex flex-column">
                    <h3 class="mb-2">상황 카테고리</h3>
                    <div>
                        <square-tag :text="yTonnyDetail.tonnySituCode" sub></square-tag>
                    </div>
                </div>

                <div class="contents mb-5">
                    <h3 class="mb-2">상황 설명</h3>
                    <div>{{ yTonnyDetail.content }}</div>
                </div>
            </div>

            <hr />

            <!-- yTonny 공고 신청 버튼 라인 -->
            <div class="applys" v-show="isHelper">
                <h1 class="mb-4">가격 제안하기</h1>
                <div v-if="isApply">
                    <div class="d-flex align-items-center mb-3">
                        <input
                            class="me-2"
                            type="text"
                            placeholder="제안할 가격을 입력해주세요. ex) 1000"
                            v-model="totalPrice" />
                        <h2>CRT</h2>
                    </div>
                    <medium-btn
                        class="w-100"
                        text="통역 헬퍼 신청하기"
                        @click="insertYTonnyApply"></medium-btn>
                    <!-- <large-btn text="헬퍼 신청하기" class="d-lg-none"></large-btn> -->
                </div>
                <div v-else>
                    <medium-btn text="헬퍼 신청 취소하기"></medium-btn>
                </div>
            </div>

            <hr />

            <!-- yTonny Applu List View -->
            <div class="yTonnyApplyList">
                <div class="mb-4 d-flex align-items-center justify-content-between">
                    <h1>가격을 제안한 헬퍼들</h1>
                    <div class="label">더보기</div>
                </div>

                <div v-if="yTonnyApplyList.length > 0" class="lists">
                    <div v-for="(apply, index) in yTonnyApplyList" :key="index">
                        <!-- {{ apply }} -->
                        <helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card>
                    </div>
                </div>
                <div v-else>가격을 제안한 헬퍼가 없습니다.</div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
// import TitleBanner from "@/components/common/TitleBanner.vue";
// import LargeBtn from "@/components/common/button/LargeBtn.vue";

export default {
    name: "YTonnyDetailPage",

    components: {
        TitleText,
        HelperCard,
        SquareTag,
        MediumBtn,
        // TitleBanner,
        // LargeBtn
    },

    data() {
        return {
            isHelper: true,
            isApply: true,

            totalPrice: "",
        };
    },

    computed: {
        ...mapGetters({
            yTonnySeq: "getYTonnySeq",
            yTonnyDetail: "getYTonnyDetail",
            yTonnyApplyList: "getYTonnyApplyList",
            userInfo: "getUserInfo",
        }),
    },

    methods: {
        startChat() {
            console.log("start chat");
            this.$router.push({ name: "ChatDetailPage" });
        },

        insertYTonnyApply(e) {
            e.preventDefault();

            let payload = {
                helperSeq: 1,
                ytonnySeq: this.yTonnySeq,
                totalPrice: this.totalPrice,
            };

            this.$store.dispatch("insertYTonnyApply", payload);
        },
    },

    async created() {
        console.log("userInfo: ", this.userInfo, "token: ", this.userInfo.seq);

        // detail 정보 가져오기
        await this.$store.commit("SET_Y_TONNY_SEQ", this.$route.params.id);
        console.log("안뇽뇽: ", this.userInfo.seq);

        let payload = { yTonnySeq: this.yTonnySeq, userSeq: this.userInfo.seq };
        await this.$store.dispatch("getYTonnyDetail", payload);
        await this.$store.dispatch("getYTonnyApplyList", this.yTonnySeq);
    },
};
</script>

<style lang="scss" scoped>
.yTonnyDetailWrap {
    padding-top: 54px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.yTonnyDetail {
    .infos {
        margin-bottom: 100px;
        .tag {
            display: flex;
            justify-content: space-between;
        }

        .titles {
            word-break: break-all;
        }

        .metas {
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 54px;

            .writers {
                display: flex;
                align-items: center;
            }
            .edits {
                display: flex;
                align-items: center;
            }
        }

        .langs {
            display: flex;
            flex-direction: column;
            // justify-content: center;
            // align-items: center;
        }
    }

    .applys {
        margin-top: 40px;
        margin-bottom: 80px;
    }

    .yTonnyApplyList {
        margin-top: 40px;
        margin-bottom: 80px;

        .lists {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    }
}

hr {
    color: var(--sub-color);
    // margin: 32px 0;
    // padding: 32px 0;
}
</style>
