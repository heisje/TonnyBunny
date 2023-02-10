<template>
    <!-- <v-container class="yTonnyDetailContainer row" v-scroll="onScroll"> -->
    {{ yTonnyDetail }}
    <v-container class="yTonnyDetailContainer row">
        <!-- <title-banner title="예약통역 공고" text=""></title-banner> -->
        <div class="yTonnyDetailWrap customForm col-md-6 col-12" ref="yTonnyDetail">
            <!-- yTonny Detail View -->
            <v-lazy
                class="yTonnyDetail"
                v-model="isActive"
                :options="{
                    threshold: 0.5,
                }"
                transition="fade-transition"
            >
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
                            bottom="10"
                        ></title-text>
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
                                    class="me-3"
                                />
                            </a>

                            <a>
                                <div>{{ yTonnyDetail.client.nickName }}</div>
                            </a>
                        </div>
                        <div class="edits" v-show="isCreator">
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
                                    <div @click="openDeleteModal">예약 삭제</div>
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
                                            class="me-2"
                                        ></square-tag>
                                        <div class="me-2">
                                            <span class="material-symbols-outlined">
                                                compare_arrows
                                            </span>
                                        </div>
                                        <square-tag
                                            :text="yTonnyDetail.endLangCode"
                                            sub
                                        ></square-tag>
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
                                            sub
                                        ></square-tag>
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
            <div v-show="yTonnyDetail.helper">
                <div v-show="isCreator">
                    <div class="goLiveBtn">
                        <medium-btn
                            class="w-100"
                            color="carrot"
                            font="white"
                            text="라이브로 이동하기"
                            @click.prevent="openLiveModalByClient"
                        ></medium-btn>
                    </div>
                </div>
                <div v-show="isManager">
                    <div class="goLiveBtn">
                        <medium-btn
                            class="w-100"
                            color="carrot"
                            font="white"
                            text="라이브로 이동하기"
                            @click.prevent="openLiveModalByHelper"
                        ></medium-btn>
                    </div>
                </div>
            </div>
        </div>

        <div class="customForm col-md-6 col-12">
            <!-- yTonny 공고 신청 버튼 라인 -->
            <div class="applys" v-show="isHelper">
                <h1 class="mb-4">
                    <i class="fa-solid fa-carrot fs-4 ms-1 me-1"></i>
                    가격 제안하기
                </h1>
                <v-lazy
                    v-model="isActive"
                    :options="{ threshold: 0.5 }"
                    transition="fade-transition"
                >
                    <div>
                        <div class="d-flex align-items-center mb-3">
                            <input
                                type="text"
                                placeholder="제안할 캐럿을 입력해주세요. ex) 1000"
                                v-model="unitPrice"
                                @keydown.enter="insertYTonnyApply"
                            />
                        </div>
                        <medium-btn
                            class="w-100"
                            color="outline"
                            font="active"
                            text="헬퍼 신청하기"
                            @click.prevent="insertYTonnyApply"
                        ></medium-btn>
                        <!-- <large-btn text="헬퍼 신청하기" class="d-lg-none"></large-btn> -->
                    </div>
                </v-lazy>
            </div>

            <!-- yTonny Applu List View -->
            <div class="yTonnyApplyList mt-0">
                <div class="d-flex align-items-center justify-content-between">
                    <h1>가격을 제안한 헬퍼들</h1>
                    <!-- <div class="label">더보기</div> -->
                </div>

                <hr />

                <v-lazy
                    v-model="isActive"
                    :options="{ threshold: 0.5 }"
                    transition="fade-transition"
                    ref="yTonnyApplyListRef"
                >
                    <div v-if="yTonnyApplyList.length > 0">
                        <transition-group name="slide-up">
                            <div
                                v-for="(apply, index) in yTonnyApplyList"
                                :key="index"
                                class="w-100 row"
                            >
                                <!-- {{ apply }} -->

                                <div class="d-flex flex-row align-items-center apply">
                                    <div
                                        class="col-2 d-flex flex-column align-items-center justify-content-center"
                                    >
                                        <img src="@/assets/noProfile.png" width="50" height="50" />

                                        <!-- <img :src="apply.helper.profileImagePath" /> -->
                                        <!-- <div>{{ apply.helper.nickName }}</div> -->
                                        <!-- <div>{{ apply.helper.helperInfo.oneLineIntroduction }}</div> -->
                                    </div>
                                    <div class="col-10 helperInfo">
                                        <div class="d-flex flex-row">
                                            <div class="likeBtn" @click="toggleLikeBtn">
                                                <span
                                                    v-if="isLikeEmpty"
                                                    class="material-symbols-outlined likeIcon empty"
                                                >
                                                    favorite
                                                </span>
                                                <span
                                                    v-else
                                                    class="material-symbols-outlined likeIcon"
                                                >
                                                    favorite
                                                </span>
                                            </div>
                                            <div>{{ apply.helper.nickName }}</div>
                                        </div>
                                        <div>{{ apply.helper.helperInfo.oneLineIntroduction }}</div>
                                        <div class="d-flex">
                                            <div>
                                                &nbsp;평점 {{ apply.helper.helperInfo.totalScore }}
                                            </div>
                                            <div>
                                                &nbsp;리뷰 {{ apply.helper.helperInfo.reviewCount }}
                                            </div>
                                            <div>&nbsp;캐럿 {{ apply.unitPrice }}</div>
                                        </div>

                                        <!-- <div>{{ apply.unitPrice }}</div> -->
                                    </div>

                                    <div
                                        v-if="apply.helper.seq == userInfo.seq"
                                        class="closeBtn col-1"
                                        @click="removeYTonnyApply(apply.ytonnySeq, apply.seq)"
                                    >
                                        <span class="material-symbols-outlined"> close </span>
                                    </div>
                                    <div
                                        v-else-if="isCreator"
                                        class="checkBtn col-1"
                                        @click="
                                            acceptYTonnyApply(
                                                apply.seq,
                                                apply.helper.seq,
                                                apply.unitPrice
                                            )
                                        "
                                    >
                                        <span class="material-symbols-outlined"> done </span>
                                    </div>
                                </div>
                            </div>
                        </transition-group>
                        <v-pagination
                            v-model="currentPage"
                            :length="yTonnyListTotalCount"
                            rounded="circle"
                            :total-visible="5"
                            class="mt-5 me-5"
                            prev-icon="mdi-menu-left"
                            next-icon="mdi-menu-right"
                            @click="nextPage"
                        ></v-pagination>
                    </div>
                    <div v-else class="mt-5">가격을 제안한 헬퍼가 없습니다.</div>
                </v-lazy>
            </div>
        </div>

        <alarm-modal
            v-show="modalName == 'delete'"
            type="danger"
            btnText1="확인"
            btnText2="취소"
            @clickBtn1="removeYTonny(yTonnyDetail.seq)"
            @clickBtn2="this.$store.commit('CLOSE_ALARM_MODAL')"
            btnColor1="outline"
            btnFontColor1="main"
            btnColor2="primary"
            btnFontColor2="white"
        >
            <template #content>
                통역 예약을 삭제하시겠습니까? <br />
                삭제한 후에는 다시 되돌릴 수가 없습니다.
            </template>
        </alarm-modal>

        <alarm-modal
            v-show="modalName == 'goLiveModalByClient'"
            type="danger"
            btnText1="확인"
            btnText2="취소"
            @clickBtn1="startLiveByClient"
            @clickBtn2="this.$store.commit('CLOSE_ALARM_MODAL')"
            btnColor1="outline"
            btnFontColor1="main"
            btnColor2="primary"
            btnFontColor2="white"
        >
            <template #content>
                통역 예약 라이브를 시작하시겠습니까? <br /><br />
                시작한 후에는 재시작 할 수 없습니다. <br />
                담당자와 시간 조율을 한 후 시작해주세요!
            </template>
        </alarm-modal>

        <alarm-modal
            v-show="modalName == 'goLiveModalByHelper'"
            type="danger"
            btnText1="확인"
            btnText2="취소"
            @clickBtn1="goLiveByHelper"
            @clickBtn2="this.$store.commit('CLOSE_ALARM_MODAL')"
            btnColor1="outline"
            btnFontColor1="main"
            btnColor2="primary"
            btnFontColor2="white"
        >
            <template #content>
                통역 예약 라이브에 입장하시겠습니까? <br /><br />
                고객과 시간 조율을 한 후 시작해주세요!
            </template>
        </alarm-modal>
    </v-container>
</template>

<script>
import { mapGetters } from "vuex";

import utils from "@/common/utils";

import TitleText from "@/components/common/TitleText.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    name: "YTonnyDetailPage",

    components: {
        TitleText,
        SquareTag,
        MediumBtn,
        AlarmModal,
    },

    data() {
        return {
            windowHeight: 0,
            yTonnyDetailElement: null,
            yTonnyApplyListElement: null,
            currentPage: 1,

            isCreator: false,
            isManager: false,
            isEditOpen: false,
            isActive: true,
            isLikeEmpty: true,

            unitPrice: "",

            modalName: "",
        };
    },

    computed: {
        ...mapGetters({
            yTonnySeq: "getYTonnySeq",
            yTonnyDetail: "getYTonnyDetail",
            yTonnyApplyList: "getYTonnyApplyList",
            yTonnyListTotalCount: "getYTonnyApplyListTotalCount",
            userInfo: "getUserInfo",
            isHelper: "getIsHelper",
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
        // onScroll() {
        //     // FIXME: 여기서 500이 아니라 카드의 height 로
        //     if (window.innerWidth >= 992) {
        //         let yTonnyMarginTop = this.yTonnyDetailElement.style.marginTop.replace("px", "");

        //         if (yTonnyMarginTop == "") yTonnyMarginTop = 0;
        //         else yTonnyMarginTop = parseInt(yTonnyMarginTop);

        //         // if (500 <= window.scrollY && window.scrollY < this.windowHeight)
        //         if (500 <= window.scrollY && window.scrollY < 1000)
        //             this.yTonnyDetailElement.style.marginTop = window.scrollY + "px";
        //         else if (500 > window.scrollY) this.yTonnyDetailElement.style.marginTop = 0;
        //     }
        // },
        openDeleteModal() {
            this.modalName = `delete`;
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        openLiveModalByClient() {
            this.modalName = `goLiveModalByClient`;
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        openLiveModalByHelper() {
            this.modalName = `goLiveModalByHelper`;
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        async startLiveByClient() {
            // 히스토리 생성 -----------------------------------------
            const payload = {
                yTonnySeq: this.yTonnyDetail.seq,
            };
            console.log(payload);
            await this.$store.dispatch("startYTonnyLive", payload);
            this.$store.commit("TOGGLE_ALARM_MODAL");
            this.$router.push({ name: "LivePage" });

            // 히스토리 생성 끝 --------------------------------------
        },

        goLiveByHelper() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
            this.$router.push({ name: "LivePage" });
        },

        async nextPage() {
            await this.getYTonnyApplyList();
            // window.scrollTo(0, 0);
        },

        async getYTonnyApplyList() {
            let size = 4;
            if (!this.isHelper) size = 6;

            this.$store.commit("SET_Y_TONNY_APPLY_SIZE", size);

            await this.$store.dispatch("getYTonnyApplyList", {
                page: this.currentPage,
                size: size,
                yTonnySeq: this.yTonnySeq,
            });
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

        async insertYTonnyApply() {
            let payload = {
                helperSeq: this.userInfo.seq,
                ytonnySeq: this.yTonnySeq,
                unitPrice: this.unitPrice,
            };

            await this.$store.dispatch("insertYTonnyApply", payload);
            await this.getYTonnyApplyList();
            this.unitPrice = "";
        },

        updateFormOpen() {
            this.$router.push({ name: "YTonnyUpdatePage", params: { id: this.yTonnySeq } });
        },

        async removeYTonny(yTonnySeq) {
            await this.$store.dispatch("removeYTonny", yTonnySeq);
            this.$store.commit("CLOSE_ALARM_MODAL");
            this.$router.replace({ name: "HomePage" });
        },

        async removeYTonnyApply(yTonnySeq, yTonnyApplySeq) {
            let payload = { yTonnySeq, yTonnyApplySeq };
            await this.$store.dispatch("removeYTonnyApply", payload);
            await this.getYTonnyApplyList();
        },

        checkIsCreator() {
            let yTonnyCreatorSeq = this.yTonnyDetail.client.seq;

            if (yTonnyCreatorSeq == this.userInfo.seq) {
                this.isCreator = true;
            }
        },

        checkIsManager() {
            let yTonnyManageSeq = this.yTonnyDetail.helper?.seq;

            if (yTonnyManageSeq == this.userInfo.seq) {
                this.isManager = true;
            }
        },

        async acceptYTonnyApply(yTonnyApplySeq, helperSeq, unitPrice) {
            let yTonnySeq = this.yTonnySeq;
            let payload = { yTonnySeq, yTonnyApplySeq, helperSeq, unitPrice };
            await this.$store.dispatch("acceptYTonnyApply", payload);
            console.log("accept", this.yTonnySeq, yTonnyApplySeq, helperSeq);
            // await this.getYTonnyApplyList();
        },
    },

    async created() {
        window.scrollTo(0, 0);

        console.log("userInfo: ", this.userInfo, "token: ", this.userInfo.seq);

        // detail 정보 가져오기
        await this.$store.commit("SET_Y_TONNY_SEQ", this.$route.params.id);
        console.log("routes params: ", this.$route.params.id);

        // let payload = { yTonnySeq: this.yTonnySeq, userSeq: this.userInfo.seq };
        await this.$store.dispatch("getYTonnyDetail", this.yTonnySeq);
        this.checkIsCreator();
        this.checkIsManager();

        window.scrollTo(0, 0);
        await this.$store.dispatch("getYTonnyApplyListTotalCount", this.yTonnySeq);
        await this.getYTonnyApplyList();
    },

    mounted() {
        this.yTonnyDetailElement = this.$refs.yTonnyDetail;
        // this.yTonnyApplyListElement = this.$refs.yTonnyApplyListRef;
        // console.log(this.yTonnyApplyListElement);
        // this.windowHeight =
        //     parseInt(
        //         window.getComputedStyle(this.yTonnyApplyListElement).height.replace("px", "")
        //     ) - 450;
    },

    watch: {
        // yTonnyApplyList: function () {
        // this.$store.dispatch("getYTonnyApplyList", this.yTonnySeq);
        // console.log("hihi");
        // }
    },
};
</script>

<style lang="scss" scoped>
.material-symbols-outlined {
    font-variation-settings: "FILL" 0, "wght" 300;
}

.yTonnyDetailContainer {
    // width: calc(100% - 200px);
    // margin: 0 auto;

    padding-top: 54px;
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: center;

    .applys {
        // margin-top: 40px;
        margin-bottom: 40px;

        // padding: 100px;
        padding: 32px 24px;
        border: 1px solid rgba(0, 0, 0, 0.08);
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
        background-color: var(--thin-color);
        border-radius: 6px;
    }

    .yTonnyApplyList {
        margin-top: 50px;
        cursor: default;
        // margin-bottom: 80px;

        // .lists {
        // 	display: flex;
        // 	flex-direction: column;
        // 	justify-content: center;
        // 	align-items: center;
        // }

        .apply {
            // background-color: var(--thin-color);
            // margin: 10px 12px;
            // padding: 0 6px 0 0;
            // margin: 10px 0;
            padding: 12px 0;
            // padding-bottom: 20px;

            border-bottom: 1px solid var(--thin-color);
            // width: 100%;
            // align-items: center;
        }

        .helperInfo {
            display: flex;
            flex-direction: column;
        }
    }
}

hr {
    margin-top: 12px;
    margin-bottom: 0;
}

.yTonnyDetailWrap {
    margin-right: 30px;
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

.likeIcon {
    color: var(--danger-color);
    font-size: 1.25rem;
    font-variation-settings: "FILL" 1, "wght" 400, "GRAD" 0, "opsz" 10;
    margin-right: 4px;
    // cursor: pointer;
    transition: all 0.13s;

    .likeText {
        color: var(--sub-color);
        // cursor: pointer;
        transition: all 0.13s;
    }

    &:hover {
        .likeIcon {
            color: #fc5148;
            font-variation-settings: "FILL" 1;
        }

        .likeText {
            color: var(--main-color);
        }
    }
}
.empty {
    // color: var(--sub-color);
    font-variation-settings: "FILL" 0;
}

.closeBtn {
    position: relative;
    right: 12px;
    cursor: pointer;

    span {
        font-variation-settings: "wght" 300;
        transition: all 0.13s;
        color: var(--sub-color);
    }

    &:hover {
        span {
            color: var(--danger-color);
        }
    }
}

.checkBtn {
    position: relative;
    right: 12px;
    cursor: pointer;

    span {
        font-variation-settings: "wght" 300;
        transition: all 0.13s;
        color: var(--sub-color);
    }

    &:hover {
        span {
            color: var(--success-color);
        }
    }
}

// .closeBtn {
// 	position: absolute;
// 	// top: 0;

// 	left: 10px;
// 	z-index: 99;
// 	// float: right;
// 	// top: -6px;
// 	// right: -6px;

// 	font-variation-settings: "wght" 300;

// 	cursor: pointer;

// 	span {
// 		transition: all 0.13s;
// 		color: var(--sub-color);
// 	}

// 	&:hover {
// 		// opacity: 0.8;

// 		span {
// 			color: var(--main-color);
// 		}
// 	}
// }

nav {
    :nth-child(1) {
        padding: 0;
    }
}

@media (min-width: 1264px) {
    .yTonnyDetailWrap {
        transition: all 0.13s;
    }

    .yTonnyDetail {
        margin-right: 24px;
    }
    .goLiveBtn {
        margin-right: 24px;
    }
}
</style>
