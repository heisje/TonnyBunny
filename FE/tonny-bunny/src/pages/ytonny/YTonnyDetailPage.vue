<template>
    <v-container class="yTonnyDetailContainer row" v-scroll="onScroll">
        <!-- <title-banner title="예약통역 공고" text=""></title-banner> -->
        <div class="yTonnyDetailWrap col-lg-6 col-12" ref="yTonnyDetail">
            <!-- yTonny Detail View -->
            <v-lazy
                class="yTonnyDetail"
                v-model="isActive"
                :options="{
                    threshold: 0.5,
                }"
                transition="fade-transition">
                <!-- yTonny 공고 정보 라인 -->
                <div class="infos">
                    <div class="tag">
                        <div>
                            <square-tag sub :text="yTonnyDetail.taskCode" class="me-2"></square-tag>
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
                        <div class="label">{{ createdAt }}</div>
                    </div>

                    <div class="metas mt-3 mb-5">
                        <div class="writers">
                            <a>
                                <!-- <img
                                    :src="yTonnyDetail.client.profileImagePath"
                                    width="40"
                                    height="40"
                                    class="me-3" /> -->

                                <img
                                    src="@/assets/noProfile.png"
                                    width="40"
                                    height="40"
                                    class="me-3" />
                            </a>

                            <a>
                                <div>{{ yTonnyDetail.client.nickName }}</div>
                            </a>
                        </div>
                        <div class="edits" v-if="yTonnyDetail.isCreator">
                            <div @click.prevent="toggleEditOpen" v-click-outside="onClickOutside">
                                <span class="material-symbols-outlined fs-4"> more_vert </span>
                            </div>
                            <div class="editPopOverWrap">
                                <!-- <Transition class="editPopOver" name="fade" v-show="isEditOpen">
								<div>
									<div @click="updateFormOpen">예약 수정</div>
									<div @click="removeYTonny">예약 삭제</div>
									<div @click="applyListOpen">가격 제안 헬퍼 보기</div>
								</div>
							</Transition> -->
                                <div class="editPopOver" v-show="isEditOpen">
                                    <div @click="updateFormOpen">예약 수정</div>
                                    <div @click="removeYTonny">예약 삭제</div>
                                    <div @click="applyListOpen">가격 제안 헬퍼 보기</div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="contents">
                        <v-table>
                            <thead>
                                <tr class="">
                                    <th class="col-4 fw-bold">제목</th>
                                    <th class="col-8 fw-bold">내용</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>언어</td>
                                    <td class="d-flex flex-row align-items-center">
                                        <square-tag
                                            :text="yTonnyDetail.startLangCode"
                                            sub
                                            class="me-2"></square-tag>
                                        <div class="me-2">
                                            <span class="material-symbols-outlined">
                                                compare_arrows
                                            </span>
                                        </div>
                                        <square-tag
                                            :text="yTonnyDetail.endLangCode"
                                            sub></square-tag>
                                    </td>
                                </tr>

                                <tr>
                                    <td>예약 날짜</td>
                                    <td>{{ startDate }}</td>
                                </tr>

                                <tr>
                                    <td>예약 시간</td>
                                    <td>{{ startTime }}</td>
                                </tr>

                                <tr>
                                    <td>예상 소요 시간</td>
                                    <td>{{ estimateTime }}</td>
                                </tr>

                                <tr>
                                    <td>지불 캐럿</td>
                                    <td>
                                        {{ yTonnyDetail.estimatePrice }}
                                        <span class="label">CRT</span>
                                    </td>
                                </tr>

                                <tr>
                                    <td>상황 카테고리</td>
                                    <td>
                                        <square-tag
                                            :text="yTonnyDetail.tonnySituCode"
                                            sub></square-tag>
                                    </td>
                                </tr>

                                <tr>
                                    <td>상황 설명</td>
                                    <td class="pt-3">{{ yTonnyDetail.content }}</td>
                                </tr>
                            </tbody>
                        </v-table>
                    </div>
                </div>
            </v-lazy>
        </div>

        <div class="col-lg-6 col-12">
            <!-- yTonny 공고 신청 버튼 라인 -->
            <div class="applys">
                <h1 class="mb-4">
                    <i class="fa-solid fa-carrot fs-4 ms-1 me-1"></i>
                    가격 제안하기
                </h1>
                <v-lazy
                    v-model="isActive"
                    :options="{ threshold: 0.5 }"
                    transition="fade-transition">
                    <div v-if="isApply">
                        <div class="d-flex align-items-center mb-3">
                            <input
                                type="text"
                                placeholder="제안할 캐럿을 입력해주세요. ex) 1000"
                                v-model="totalPrice" />
                        </div>
                        <medium-btn
                            class="w-100"
                            color="primary"
                            font="white"
                            text="헬퍼 신청하기"
                            @click.prevent="insertYTonnyApply"></medium-btn>
                        <!-- <large-btn text="헬퍼 신청하기" class="d-lg-none"></large-btn> -->
                    </div>
                    <div v-else>
                        <medium-btn text="헬퍼 신청 취소하기"></medium-btn>
                    </div>
                </v-lazy>
            </div>

            <!-- yTonny Applu List View -->
            <div class="yTonnyApplyList">
                <div class="mb-4 d-flex align-items-center justify-content-between">
                    <h1>가격을 제안한 헬퍼들</h1>
                    <div class="label">더보기</div>
                </div>

                <hr />

                <v-lazy
                    v-if="yTonnyApplyList.length > 0"
                    class="lists"
                    v-model="isActive"
                    :options="{ threshold: 0.5 }"
                    transition="fade-transition">
                    <!-- <div
                        v-for="(apply, index) in yTonnyApplyList"
                        :key="index"
                        ref="yTonnyApplyList">
                        <helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card>
                    </div> -->
                    <div ref="yTonnyApplyList">
                        <helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card
                        ><helper-card rightBtnText="상담하기" @clickBtn2="startChat"></helper-card>
                    </div>
                </v-lazy>
                <div v-else>가격을 제안한 헬퍼가 없습니다.</div>
            </div>
        </div>
    </v-container>
</template>

<script>
import { mapGetters } from "vuex";

import utils from "@/common/utils";
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
            windowHeight: 0,
            yTonnyDetailElement: null,
            yTonnyApplyListElement: null,

            isHelper: true,
            isApply: true,
            isEditOpen: false,
            isActive: true,

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

        createdAt() {
            return this.yTonnyDetail.createdAt.split("T").join(" ").substr(0, 16);
        },
        startDate() {
            let date = utils.dateSplit(this.yTonnyDetail.startDateTime);
            return date.year + "년 " + date.month + "월 " + date.day + "일";
        },
        startTime() {
            let time = utils.dateTimeSplit(this.yTonnyDetail.startDateTime);
            return time.hour + "시 " + time.minute + "분";
        },
        estimateTime() {
            let time = utils.timeSplit(this.yTonnyDetail.estimateTime);
            return "대략 " + time.hour + "시간 " + time.minute + "분";
        },
    },

    methods: {
        onScroll() {
            // FIXME: 여기서 500이 아니라 카드의 height 로
            if (window.innerWidth >= 992) {
                let yTonnyMarginTop = this.yTonnyDetailElement.style.marginTop.replace("px", "");

                if (yTonnyMarginTop == "") yTonnyMarginTop = 0;
                else yTonnyMarginTop = parseInt(yTonnyMarginTop);

                if (500 <= window.scrollY && window.scrollY < this.windowHeight)
                    this.yTonnyDetailElement.style.marginTop = window.scrollY + "px";
                else if (500 > window.scrollY) this.yTonnyDetailElement.style.marginTop = 0;
            }
        },

        startChat() {
            console.log("start chat");
            this.$router.push({ name: "ChatDetailPage" });
        },

        toggleEditOpen() {
            this.isEditOpen = !this.isEditOpen;
        },

        onClickOutside() {
            this.isEditOpen = false;
        },

        insertYTonnyApply() {
            let payload = {
                helperSeq: 1,
                ytonnySeq: this.yTonnySeq,
                totalPrice: this.totalPrice,
            };

            this.$store.dispatch("insertYTonnyApply", payload);
        },

        updateFormOpen() {
            this.$router.push({ name: "YTonnyUpdatePage" });
        },

        removeYTonny() {
            console.log("remove ytonny");
        },
        applyListOpen() {},
    },

    async created() {
        console.log("userInfo: ", this.userInfo, "token: ", this.userInfo.seq);

        // detail 정보 가져오기
        await this.$store.commit("SET_Y_TONNY_SEQ", this.$route.params.id);
        console.log("안뇽뇽: ", this.userInfo.seq);

        let payload = { yTonnySeq: this.yTonnySeq, userSeq: this.userInfo.seq };
        await this.$store.dispatch("getYTonnyDetail", payload);
        await this.$store.dispatch("getYTonnyApplyList", this.yTonnySeq);

        window.scrollTo(0, 0);
    },

    mounted() {
        this.yTonnyDetailElement = this.$refs.yTonnyDetail;
        this.yTonnyApplyListElement = this.$refs.yTonnyApplyList;
        this.windowHeight =
            parseInt(
                window.getComputedStyle(this.yTonnyApplyListElement).height.replace("px", "")
            ) - 450;
    },
};
</script>

<style lang="scss" scoped>
.material-symbols-outlined {
    font-variation-settings: "FILL" 0, "wght" 300;
}

.yTonnyDetailContainer {
    padding-top: 54px;
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: center;

    .applys {
        // margin-top: 40px;
        // margin-bottom: 80px;

        // padding: 100px;
        padding: 32px 24px;
        border: 1px solid rgba(0, 0, 0, 0.08);
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
        background-color: var(--thin-color);
        border-radius: 6px;
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

.yTonnyDetailWrap {
}
.yTonnyDetail {
    cursor: default;
    padding: 32px 24px;

    // box-shadow: 1px 1px 1px black;
    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;

    .infos {
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
                cursor: pointer;
            }
            .edits {
                // display: flex;
                // align-items: center;
                cursor: pointer;
                // position: absolute;

                .editPopOverWrap {
                    position: relative;
                    right: 120px;

                    .editPopOver {
                        position: absolute;
                        // left: 100px;
                        // right: 535px;
                        // right: 100px;
                        width: 130px;
                        padding: 10px;

                        background-color: #fff;
                        border: 1px solid rgba(0, 0, 0, 0.2);
                        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
                        z-index: 99;

                        div:nth-child(1) {
                            margin-top: 0;
                        }
                        > div {
                            // border-bottom: 1px solid var(--light-color);
                            margin-top: 4px;
                            // line-height: 20px;
                            // margin-bottom: 3px;

                            &:hover {
                                text-decoration: underline;
                            }
                        }
                    }
                }
            }
        }

        .contents {
            padding-right: 16px;

            table {
                th,
                td,
                tr {
                    font-size: 1rem;
                }
            }

            .langs {
                display: flex;
                flex-direction: column;
                // justify-content: center;
                // align-items: center;
            }
        }
    }
}

hr {
    color: var(--sub-color);
    // margin: 32px 0;
    // padding: 32px 0;
}

.fade-enter-active,
.fade-leave-active {
    transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
    opacity: 0;
}

.slide-up {
    transition: all 0.25s;
}
.slide-up-enter-active {
    transition: all 0.25s ease;
}
.slide-up-leave-active {
    transition: all 0.25s cubic-bezier(1, 0.5, 0.8, 1);
}
.slide-up-enter,
.slide-up-leave-active {
    opacity: 0;
    transform: translateY(100%);
}

@media (min-width: 1264px) {
    .yTonnyDetailWrap {
        transition: all 0.13s;
    }

    .yTonnyDetail {
        margin-right: 24px;
    }
}
</style>
