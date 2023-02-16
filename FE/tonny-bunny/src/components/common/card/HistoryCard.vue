<template>
    <div class="cardContainer questCardContainer">
        <div class="cardWrap">
            <div class="questCard">
                <!-- 1. x 버튼 라인 -->
                <!-- <div class="closeBtn"> -->
                <!-- <span class="material-symbols-outlined"> close </span> -->
                <!-- </div> -->

                <!-- 2. tag 라인 -->
                <div>
                    <square-tag
                        v-if="allCode[historyDetail?.taskStateCode] == '모집중'"
                        :text="allCode[historyDetail?.taskStateCode]"
                        success
                    ></square-tag>
                    <square-tag
                        v-if="allCode[historyDetail?.taskStateCode] == '진행중'"
                        :text="allCode[historyDetail?.taskStateCode]"
                        info
                    ></square-tag>
                    <square-tag
                        v-if="allCode[historyDetail?.taskStateCode] == '완료됨'"
                        :text="allCode[historyDetail?.taskStateCode]"
                        white
                    ></square-tag>

                    <br />
                    <br />
                </div>
                <div class="questTag">
                    <div class="tags">
                        <square-tag sub :text="allCode[historyDetail?.taskCode]"></square-tag>
                        <square-tag
                            sub
                            :text="
                                allCode[historyDetail?.startLangCode] +
                                ' - ' +
                                allCode[historyDetail?.endLangCode]
                            "
                        ></square-tag>
                    </div>
                    <div>
                        <square-tag
                            sub
                            :text="
                                '작성시간 : ' +
                                historyDetail?.updatedAt.substr(0, 10) +
                                ' ' +
                                historyDetail?.updatedAt.substr(11, 12)
                            "
                        ></square-tag>
                        <!-- {{ historyDetail?.updatedAt.substr(0, 10) }}
                        {{ historyDetail?.updatedAt.substr(11, 12) }} -->
                    </div>
                </div>

                <!-- 3. client - helper 라인 -->
                <div class="questProfile mt-3">
                    <div class="profile">
                        <UserProfileImg
                            class="profileImg"
                            :profileImagePath="historyDetail?.client?.profileImagePath"
                        />
                        <div class="userType">
                            <span class="label">고객</span>
                            <h4 class="overflow" v-if="historyDetail?.client?.nickName">
                                {{ historyDetail?.client?.nickName }}
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
                            <h4 class="overflow" v-if="historyDetail?.helper?.nickName">
                                {{ historyDetail?.helper?.nickName }}
                            </h4>
                            <h4 class="overflow" v-else>미지정</h4>
                        </div>
                        <UserProfileImg
                            class="profileImg"
                            :profileImagePath="historyDetail?.helper?.profileImagePath"
                        />
                    </div>
                </div>

                <!-- 4. 상황 설명 라인 -->
                <div class="questInfo">
                    <div class="infoDesc mb-4" v-if="historyDetail?.title">
                        <div class="date">
                            <h4>제목</h4>
                            <div>{{ historyDetail?.title }}</div>
                        </div>
                    </div>

                    <div class="infoDateWrap">
                        <div v-if="allCode[historyDetail?.taskCode] == '즉시통역'" class="infoDate">
                            <div class="date">
                                <h4>날짜</h4>
                                <div>{{ historyDetail?.startDateTime.substr(0, 10) }}</div>
                            </div>
                        </div>
                        <div
                            v-else-if="allCode[historyDetail?.taskCode] == '예약통역'"
                            class="infoDate"
                        >
                            <div class="date">
                                <h4>날짜</h4>
                                <div>{{ historyDetail?.startDateTime.substr(0, 10) }}</div>
                            </div>
                        </div>
                        <div
                            v-else-if="allCode[historyDetail?.taskCode] == '번역'"
                            class="infoDate"
                        >
                            <div class="date">
                                <h4>시작날짜</h4>
                                <div>{{ historyDetail?.startDateTime.substr(0, 10) }}</div>
                            </div>
                            <div class="date">
                                <h4>종료날짜</h4>
                                <div>{{ historyDetail?.endDateTime.substr(0, 10) }}</div>
                            </div>
                        </div>
                    </div>

                    <div class="infoDateWrap">
                        <div class="infoDate" v-if="allCode[historyDetail?.taskCode] == '즉시통역'">
                            <div class="time">
                                <h4>라이브 시간</h4>
                                <div>{{ historyDetail?.totalTime }} 초</div>
                            </div>
                            <div class="date">
                                <h4>결제 단가</h4>
                                <div>{{ historyDetail?.unitPrice }} CRT</div>
                            </div>
                        </div>
                        <div class="infoDate" v-if="allCode[historyDetail?.taskCode] == '예약통역'">
                            <div class="time">
                                <h4>라이브 시간</h4>
                                <div>{{ historyDetail?.totalTime }} 초</div>
                            </div>
                            <div class="date">
                                <h4>결제 단가</h4>
                                <div>{{ historyDetail?.unitPrice }} CRT</div>
                            </div>
                        </div>
                        <div class="infoDate" v-if="allCode[historyDetail?.taskCode] == '번역'">
                            <div class="date">
                                <h4>결제 단가</h4>
                                <div>{{ historyDetail?.totalPrice }} CRT</div>
                            </div>
                        </div>
                    </div>
                    <div
                        class="infoCategory"
                        v-show="
                            historyDetail?.tonnySituCode != `` &&
                            historyDetail?.tonnySituCode != null
                        "
                    >
                        <h4>상황 카테고리</h4>
                        <div style="width: 100%">
                            <div v-if="allCode[historyDetail?.taskCode] == '즉시통역'">
                                <square-tag
                                    sub
                                    :text="allCode[historyDetail?.tonnySituCode]"
                                ></square-tag>
                            </div>
                            <div v-if="allCode[historyDetail?.taskCode] == '예약통역'">
                                <square-tag
                                    sub
                                    :text="allCode[historyDetail?.tonnySituCode]"
                                ></square-tag>
                            </div>
                            <div v-if="allCode[historyDetail?.taskCode] == '번역'">
                                <square-tag
                                    sub
                                    :text="allCode[historyDetail?.bunnySituCode]"
                                ></square-tag>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 5. 하단 버튼 라인 -->
                <!-- <div class="questBtns mt-2">
                    <div>
                        <XSmallBtn
                            text="자세히보기"
                            @click="goHistoryDetail"
                            color="outline"
                            font="active"
                        />
                    </div>
                </div> -->
            </div>
        </div>
    </div>
</template>

<script>
import SquareTag from "../tag/SquareTag.vue";
// import XSmallBtn from "../button/XSmallBtn.vue";
import { mapGetters } from "vuex";
import UserProfileImg from "../UserProfileImg.vue";
// import { getKeyByValue } from "@/common/utils";
export default {
    name: "HistoryCard",

    components: {
        SquareTag,
        // XSmallBtn,
        UserProfileImg,
    },

    props: {
        historyDetail: {
            typeof: Array,
        },
    },

    computed: {
        ...mapGetters({ allCode: "getAllCode" }),
    },

    methods: {
        goHistoryDetail(e) {
            e.preventDefault();
            // this.$emit("clickBtn1");

            // if (this.allCode[this.historyDetail?.taskCode] == "즉시통역") {
            //     this.$router.push({
            //         name: "NBunnyDetailPage",
            //         params: { id: this.historyDetail?.seq },
            //     });
            // } else if (this.allCode[this.historyDetail?.taskCode] == "예약통역") {
            //     this.$router.push({
            //         name: "YTonnyDetailPage",
            //         params: { id: this.historyDetail?.seq },
            //     });
            // } else if (this.allCode[this.historyDetail?.taskCode] == "번역") {
            //     this.$router.push({
            //         name: "NBunnyDetailPage",
            //         params: { id: this.historyDetail?.seq },
            //     });
            // }
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
