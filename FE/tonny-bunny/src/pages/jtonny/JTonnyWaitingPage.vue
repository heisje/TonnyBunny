<template>
    <div class="waitingContainer container">
        <!-- <title-banner
            title="즉시통역 서비스 연결"
            text="통역을 도와줄 헬퍼를 찾아볼까요?"></title-banner> -->
        <div class="waitingWrap">
            <div class="waitingContent row">
                <div
                    class="jtonnyRequestWrap customForm col-md-6 col-12 d-flex flex-column justify-content-center">
                    <title-text
                        title="즉시통역 신청 카드"
                        text="고객님이 신청한 즉시통역 내역이에요"></title-text>
                    <hr />
                    <div class="jtonnyRequestContent">
                        <!-- {{ jtonnyRequest }} -->

                        <div class="questProfile mt-3">
                            <div class="profile ms-3">
                                <img
                                    class="profileImg"
                                    src="@/assets/noProfile_white.png"
                                    width="70"
                                    height="70" />
                                <div class="userType ms-2">
                                    <span class="label">고객</span>
                                    <h4 class="" v-if="jtonnyRequest?.client?.nickName">
                                        {{ jtonnyRequest?.client?.nickName }}
                                    </h4>
                                    <h4 class="" v-else>미지정</h4>
                                </div>
                            </div>

                            <div>
                                <span class="material-symbols-outlined arrow"> sync_alt </span>
                            </div>

                            <div class="profile me-3">
                                <div class="userType me-2">
                                    <span class="label text-end">헬퍼</span>
                                    <h4 class="text-end" v-if="jtonnyRequest?.helper?.nickName">
                                        {{ jtonnyRequest?.helper?.nickName }}
                                    </h4>
                                    <h4 class="" v-else>미지정</h4>
                                </div>
                                <img
                                    class="profileImg"
                                    src="@/assets/noProfile_white.png"
                                    width="70"
                                    height="70" />
                            </div>
                        </div>

                        <v-lazy
                            v-model="jtonnyRequest"
                            :options="{ threshold: 0.5 }"
                            transition="fade-transition">
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
                                                :text="jtonnyRequest.startLangCode"
                                                sub
                                                class="me-2"></square-tag>
                                            <div class="me-2">
                                                <span class="material-symbols-outlined">
                                                    compare_arrows
                                                </span>
                                            </div>
                                            <square-tag
                                                :text="jtonnyRequest.endLangCode"
                                                sub></square-tag>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>예상 소요 시간</td>
                                        <td>{{ estimateTime }}</td>
                                    </tr>

                                    <tr>
                                        <td>지불 캐럿</td>
                                        <td>
                                            {{ jtonnyRequest.unitPrice }}
                                            <span class="label">CRT</span>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>상황 카테고리</td>
                                        <td>
                                            <square-tag :text="jtonnyRequest.tonnySituCode" sub>
                                            </square-tag>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>상황 설명</td>
                                        <td class="pt-3">{{ jtonnyRequest.content }}</td>
                                    </tr>
                                </tbody>
                            </v-table>
                        </v-lazy>
                    </div>
                    <medium-btn
                        text="신청 취소하기"
                        color="primary"
                        font="white"
                        class="w-100"></medium-btn>
                </div>
                <div class="customForm col-md-6 col-12 jTonnyApplyList">
                    <title-text
                        title="즉시통역을 제안한 헬퍼들"
                        text="어떤 헬퍼와 함께 즉시통역을 시작해볼까요?"></title-text>
                    <hr />

                    <div
                        class="d-flex flex-row align-items-center justify-content-center row loadingHelper">
                        <!-- <Vue3Lottie
                            :animationLink="`https://lottie.host/b162872c-29b1-4195-a818-a20ea5a46343/AHLhXNb0eX.json`"
                            background="transparent"
                            style="display: inline-block"
                            class="col-2"
                            width="70px"
                            loop
                            autoplay>
                        </Vue3Lottie> -->

                        <div class="col-10 d-flex align-items-center justify-content-center">
                            <div class="me-4">헬퍼를 찾는 중</div>
                            <div class="text-center">
                                <div
                                    class="spinner-border"
                                    role="status"
                                    style="color: var(--success-color)">
                                    <span class="visually-hidden">Loading...</span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <v-lazy
                        v-model="jtonnyApplyList"
                        :options="{ threshold: 0.5 }"
                        transition="fade-transition">
                        <div v-if="jtonnyApplyListLength > 0">
                            <transition-group name="slide-up">
                                <div
                                    v-for="(apply, index) in jtonnyApplyList"
                                    :key="index"
                                    class="row">
                                    <!-- {{ apply }} -->

                                    <div class="d-flex flex-row align-items-center apply">
                                        <div
                                            class="col-2 d-flex flex-column align-items-center justify-content-center">
                                            <img
                                                src="@/assets/noProfile.png"
                                                width="50"
                                                height="50" />

                                            <!-- <img :src="apply.helper.profileImagePath" /> -->
                                            <!-- <div>{{ apply.helper.nickName }}</div> -->
                                            <!-- <div>{{ apply.helper.helperInfo.oneLineIntroduction }}</div> -->
                                        </div>
                                        <div class="col-9 helperInfo">
                                            <div class="d-flex flex-row">
                                                <div>{{ apply.helper.nickName }}</div>
                                            </div>
                                            <div>한 줄 소개</div>
                                            <div class="d-flex">
                                                <div>평점(리뷰카운터)</div>
                                                <div>{{ apply.unitPrice }}</div>
                                            </div>

                                            <!-- <div>{{ apply.helper.helperInfo.avgScore }}</div> -->
                                            <!-- <div>{{ apply.helper.helperInfo.reviewCount }}</div> -->
                                            <!-- <div>{{ apply.totalPrice }}</div> -->
                                        </div>

                                        <div class="closeBtn col-1" @click="toggleLikeBtn">
                                            <span
                                                v-if="isLikeEmpty"
                                                class="material-symbols-outlined likeIcon empty">
                                                favorite
                                            </span>
                                            <span v-else class="material-symbols-outlined likeIcon">
                                                favorite
                                            </span>
                                        </div>
                                    </div>

                                    <div class="d-flex btns row ms-0 me-0 ps-0 pe-0">
                                        <div class="col-6 accept">거절</div>
                                        <div class="col-6 reject">수락</div>
                                    </div>
                                </div>
                            </transition-group>
                        </div>
                        <div v-else class="mt-5 text-center">가격을 제안한 헬퍼가 없습니다.</div>
                    </v-lazy>
                </div>
            </div>
            <!-- <div v-else>
                <JTonnyLoading></JTonnyLoading>
                <medium-btn
                    text="신청 취소하기"
                    color="thin"
                    font="main"
                    class="w-100"></medium-btn>
            </div> -->
            <!-- <small-btn color="light" font="live" text="찾으면" @click.prevent="찾아짐()" /> -->
            <!-- <small-btn color="light" font="live" text="수락하기누름" @click.prevent="openModal" /> -->

            <AlarmModal
                title="경고"
                type="danger"
                btnText1="취소"
                btnText2="진행"
                btnColor1="light"
                btnColor2="carrot"
                btnFontColor1="sub"
                btnFontColor2="white"
                @close-modal="closeModal"
                :link="{ name: 'LivePage' }">
                <template #content>
                    수락하시면 바로 해당 헬퍼와 동시 통역이 진행됩니다. 진행하시겠습니까?
                </template>
            </AlarmModal>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import { mapGetters } from "vuex";

import LoadingItem from "@/components/common/LoadingItem.vue";
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import QuestCard from "@/components/common/card/QuestCard.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
// import TitleBanner from "@/components/common/TitleBanner.vue";

export default {
    name: "JTonnyWaitingPage",

    components: {
        JTonnyLoading,
        TitleText,
        SmallBtn,
        HelperCard,
        QuestCard,
        LoadingItem,
        // LargeBtn,
        AlarmModal,
        // TitleBanner,
        MediumBtn,
        SquareTag,
    },

    data() {
        return {
            isFind: false,
            isOpen1: false,
            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyApplyList: {},
        };
    },

    computed: {
        ...mapGetters({
            jtonnyRequest: "getJtonnyRequest",
        }),

        estimateTime() {
            return (
                "대략 " +
                this.jtonnyRequest.estimateHour +
                "시간 " +
                this.jtonnyRequest.estimateMinute +
                "분"
            );
        },

        jtonnyApplyListLength() {
            return Object.keys(this.jtonnyApplyList).length;
        },
    },

    methods: {
        찾아짐() {
            this.isFind = true;
        },
        openModal(e) {
            e.preventDefault();
            this.isOpen1 = true;
        },

        closeModal() {
            this.isOpen1 = false;
        },
        accept() {
            this.stompClient.send(`/pub/jtonny/accept`, JSON.stringify(this.jtonnyRequest), {});
        },
        reject() {
            this.stompClient.send(`/pub/jtonny/reject`, JSON.stringify(this.jtonnyRequest), {});
        },
    },

    mounted() {
        const serverURL = "http://localhost:8080/api/stomp";

        let clientSeq = this.jtonnyRequest.client.seq;
        let socket = new SockJS(serverURL);
        this.stompClient = Stomp.over(socket);
        console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

        this.stompClient.connect(
            {},
            () => {
                // 소켓 연결 성공
                this.connected = true;
                console.log("소켓 연결 성공");

                // 본인 seq 를 구독합니다.
                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}`, (res) => {
                    console.log("즉시통역 요청이 도착했습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    // this.jtonnyApplyList.push(request);
                    this.jtonnyApplyList[request.helper.seq] = request;
                });

                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}/cancel`, (res) => {
                    console.log("즉시통역 요청이 취소되었습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    console.log("이건 뭐고:", request);
                    delete this.jtonnyApplyList[request.helper.seq];
                });
                this.stompClient.send(
                    `/pub/jtonny/request`,
                    JSON.stringify(this.jtonnyRequest),
                    {}
                );
            },
            (error) => {
                // 소켓 연결 실패
                console.log("소켓 연결 실패", error);
                this.connected = false;
            }
        );
    },
};
</script>

<style lang="scss" scoped>
.waitingWrap {
    margin-top: 32px;
    margin-bottom: 120px;
}

.waitingContent {
    display: flex;
    justify-content: space-evenly;
    align-items: flex-start;
    // justify-content: space-around;
    // justify-content: center;
    // align-items: center;
}

.jtonnyCarrot {
    position: relative;
    top: -50px;
    right: 100px;
}

.jtonnyRequestContent {
    cursor: default;
    padding: 24px 24px;

    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;

    .questProfile {
        display: flex;
        justify-content: space-between;

        align-items: center;
        width: 100%;
        height: 100px;

        background-color: var(--thin-color);
        border-radius: 8px;
        padding: 8px 8px;
        margin: 8px 0;

        .profile {
            display: flex;
            align-items: center;
            max-width: 140px;

            img {
                // width: 32px;
                // height: 32px;
                border-radius: 100%;
                box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
            }
        }

        .arrow {
            color: var(--sub-color);
        }

        .userType {
            display: flex;
            flex-direction: column;
            width: 100%;
            .overflow {
                width: 80px;
                overflow: hidden; // 을 사용해 영역을 감출 것
                text-overflow: ellipsis; // 로 ... 을 만들기
                white-space: nowrap;
            }
        }

        div:nth-child(1) {
            img {
                margin-right: 8px;
            }
        }
        div:nth-child(3) {
            img {
                margin-left: 8px;
            }
        }
    }
}

.jTonnyApplyList {
    cursor: default;

    .apply {
        margin-top: 24px;
        padding: 12px 0;
        // border-bottom: 1px solid var(--thin-color);
        border: 1px solid rgba(0, 0, 0, 0.13);
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
        border-radius: 6px;
    }

    .helperInfo {
        display: flex;
        flex-direction: column;
    }

    .btns {
        cursor: pointer;

        div {
            border-radius: 2px;
            border: 1px solid var(--light-color);
            padding: 2px;
            text-align: center;
            font-weight: 500;
        }

        div:nth-child(1) {
            background-color: var(--light-color);
            color: var(--main-color);
        }

        div:nth-child(2) {
            background-color: var(--primary-color);
            color: white;
        }
    }
}

.loadingHelper {
    background-color: var(--thin-color);
    padding: 10px;
    border-radius: 6px;
}

.closeBtn {
    position: relative;
    // right: 24px;
    z-index: 99;
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
</style>
