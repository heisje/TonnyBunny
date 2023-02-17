<template>
    <div class="waitingContainer container">
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
                                <user-profile-img
                                    class="profileImg"
                                    :profileImagePath="jtonnyRequest?.client?.profileImagePath"
                                    width="70" />
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

                                <user-profile-img
                                    class="profileImg"
                                    :profileImagePath="jtonnyRequest?.helper?.profileImagePath"
                                    width="70" />
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
                                                :text="getStartLangCode"
                                                sub
                                                class="me-2"></square-tag>
                                            <div class="me-2">
                                                <span class="material-symbols-outlined">
                                                    compare_arrows
                                                </span>
                                            </div>
                                            <square-tag :text="getEndLangCode" sub></square-tag>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>예상 소요 시간</td>
                                        <td>{{ estimateTime }}</td>
                                    </tr>

                                    <!-- <tr> -->
                                    <!-- <td>지불 캐럿</td> -->
                                    <!-- <td> -->
                                    <!-- {{ jtonnyRequest.unitPrice }} -->
                                    <!-- <span class="label">캐럿</span> -->
                                    <!-- </td> -->
                                    <!-- </tr> -->

                                    <tr>
                                        <td>상황 카테고리</td>
                                        <td>
                                            <div
                                                v-if="jtonnyRequest.tonnySituCode == ''"
                                                style="color: var(--sub-color)">
                                                상황 카테고리가 없습니다.
                                            </div>
                                            <square-tag v-else :text="getTonnySituCode" sub>
                                            </square-tag>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>상황 설명</td>
                                        <td class="">
                                            <div
                                                v-if="jtonnyRequest.content == ''"
                                                style="color: var(--sub-color)">
                                                상황 설명이 없습니다.
                                            </div>
                                            <div v-else>{{ jtonnyRequest.content }}</div>
                                        </td>
                                    </tr>
                                </tbody>
                            </v-table>
                        </v-lazy>
                    </div>
                    <medium-btn
                        text="신청 취소하기"
                        color="light"
                        font="active"
                        @click="cancelRequest"
                        class="w-100 cancleBtn"></medium-btn>
                </div>
                <div class="customForm col-md-6 col-12 jTonnyApplyList">
                    <title-text
                        title="즉시통역을 제안한 헬퍼들"
                        text="어떤 헬퍼와 함께 즉시통역을 시작해볼까요?"></title-text>
                    <hr />

                    <v-lazy
                        v-model="jtonnyApplyList"
                        :options="{ threshold: 0.5 }"
                        transition="fade-transition">
                        <div v-if="jtonnyApplyListLength > 0" class="mb-5">
                            <transition-group name="slide-up">
                                <div
                                    v-for="(apply, index) in jtonnyApplyList"
                                    :key="index"
                                    class="row px-3">
                                    <div class="d-flex flex-row align-items-center apply">
                                        <div
                                            class="col-2 d-flex flex-column align-items-center justify-content-center">
                                            <user-profile-img
                                                :profileImagePath="apply?.helper?.profileImagePath"
                                                width="50" />
                                        </div>
                                        <div class="col-9 helperInfo">
                                            <div class="d-flex flex-row align-items-center ms-1">
                                                <!-- <div
													class="closeBtn me-1 d-flex flex-row align-items-center"
													@click="toggleLikeBtn"></div> -->
                                                <h3>{{ apply.helper.nickName }}</h3>
                                            </div>
                                            <div
                                                class="mt-2 text-truncate ms-1"
                                                style="color: var(--sub-color)">
                                                {{ apply.helper.helperInfo.oneLineIntroduction }}
                                            </div>
                                            <div class="d-flex infos mt-3">
                                                <div>
                                                    <h4>평점</h4>
                                                    {{ apply.helper.helperInfo.avgScore }}
                                                    <span class="">점</span>
                                                </div>
                                                <div>
                                                    <h4>받은리뷰</h4>
                                                    {{ apply.helper.helperInfo.reviewCount }}
                                                    <span class="">건</span>
                                                </div>
                                                <div>
                                                    <h4>헬퍼단가</h4>
                                                    {{ apply.helper.helperInfo.unitPrice }}
                                                    <span class="label">캐럿</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="d-flex btns row ms-0 me-0 ps-0 pe-0 mb-3">
                                        <div class="col-6 reject" @click="reject(apply.helper)">
                                            거절
                                        </div>
                                        <div class="col-6 accept" @click="accept(apply.helper)">
                                            수락
                                        </div>
                                    </div>
                                </div>
                            </transition-group>
                        </div>
                    </v-lazy>

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

                        <div class="mt-3 col-10 d-flex align-items-center justify-content-center">
                            <div class="me-3">헬퍼를 찾는 중</div>
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
                </div>
            </div>

            <AlarmModal
                title="성공"
                type="success"
                btnText2="확인"
                btnColor1="outline"
                btnColor2="carrot"
                btnFontColor1="main"
                btnFontColor2="white"
                @clickBtn2="onAir">
                <template #content>
                    수락하시면 바로 즉시통역이 진행됩니다.<br />진행하시겠습니까?
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
import http from "@/common/axios";
import UserProfileImg from "@/components/common/UserProfileImg.vue";

export default {
    name: "JTonnyWaitingPage",

    data() {
        return {
            // 수락할 시
            sessionName: "",
            isLikeEmpty: false,
            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyApplyList: {},
        };
    },

    components: {
        JTonnyLoading,
        TitleText,
        SmallBtn,
        HelperCard,
        QuestCard,
        LoadingItem,
        AlarmModal,
        MediumBtn,
        SquareTag,
        UserProfileImg,
    },

    computed: {
        ...mapGetters({
            jtonnyRequest: "getJtonnyRequest",
            userInfo: "getUserInfo",
            langCode: "getLangCode",
            tonnySituCode: "getTonnySituCode",
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

        getStartLangCode() {
            let code;
            this.langCode.forEach((e) => {
                if (e.value == this.jtonnyRequest.startLangCode) code = e.name;
            });
            return code;
        },
        getEndLangCode() {
            let code;
            this.langCode.forEach((e) => {
                if (e.value == this.jtonnyRequest.endLangCode) code = e.name;
            });
            return code;
        },
        getTonnySituCode() {
            let code;
            this.tonnySituCode.forEach((e) => {
                if (e.value == this.jtonnyRequest.tonnySituCode) code = e.name;
            });
            return code;
        },
    },

    methods: {
        onAir() {
            this.$store.commit("CLOSE_ALARM_MODAL");
            this.$router.push({ name: "OnAirPage" });
        },

        accept(helper) {
            // let jtonny = this.jtonnyApplyList[helperSeq];

            this.jtonnyRequest.helper = helper;
            this.jtonnyRequest.unitPrice = helper.helperInfo.unitPrice; // unitPrice 입력 받으면 여기다가 넣으면 됨
            this.stompClient.send(`/pub/jtonny/accept`, JSON.stringify(this.jtonnyRequest), {});
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
        async reject(helper) {
            this.jtonnyRequest.helper = helper;
            this.stompClient.send(`/pub/jtonny/reject`, JSON.stringify(this.jtonnyRequest), {});
            delete this.jtonnyApplyList[this.jtonnyRequest.helper.seq];

            let alert = {
                userSeq: this.userInfo.seq,
                clientSeq: this.userInfo.seq,
                helperSeq: helper.seq,
                taskCode: this.jtonnyRequest.taskCode,
                content: "즉시 통역 헬퍼 신청을 거절했습니다.",
                clientNickname: this.userInfo.nickName,
                helperNickname: helper.nickName,
            };

            await this.$store.dispatch("rejectAlert", alert);
        },
        cancelRequest() {
            this.stompClient.send(
                `/pub/jtonny/request/cancel`,
                JSON.stringify(this.jtonnyRequest),
                {}
            );

            this.$router.replace({ name: "TonnyPage" });
        },
    },
    mounted() {
        const serverURL = http.getUri() + "/stomp";

        let clientSeq = this.jtonnyRequest.client.seq;
        let socket = new SockJS(serverURL);
        this.stompClient = Stomp.over(socket);
        this.stompClient.hasDebug = false;
        // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

        this.stompClient.connect(
            {},
            () => {
                // 소켓 연결 성공
                this.connected = true;
                // console.log("소켓 연결 성공");

                // 본인 seq 를 구독합니다.
                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}`, (res) => {
                    // console.log("즉시통역 요청이 도착했습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    this.jtonnyApplyList[request.helper.seq] = request;
                });

                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}/cancel`, (res) => {
                    // console.log("즉시통역 요청이 취소되었습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    delete this.jtonnyApplyList[request.helper.seq];
                });

                this.stompClient.subscribe(`/sub/jtonny/accept/${clientSeq}`, async (res) => {
                    // console.log("즉시통역 매칭 완료. 오픈비두 이동(고객)", res.body);

                    const data = JSON.parse(res.body);
                    await this.$store.commit("SET_START_RES_DATA", data);

                    // 히스토리 생성 -----------------------------------------

                    let res2 = await http.post(`/live/jtonny/start`, data);

                    try {
                        // service logic
                        switch (res2.data.resultCode) {
                            case "SUCCESS":
                                this.$store.commit("SET_HISTORY_SEQ", res2.data.data);
                                this.$store.state.live.historySeq = res2.data.data;
                                break;
                            case "FAIL":
                                break;
                        }
                    } catch (err) {
                        console.error(err);

                        // exception
                        if (err.response.status == 403) {
                            alert("로그인 하세요");
                        }
                    }

                    // 히스토리 생성 끝 --------------------------------------

                    // this.$router.push({ name: "LivePage", params: { sessionName: res.body.uuid } });

                    /*
                        let jtonny = JSON.parse(res.body);

                        오픈비두 이동 router.PUSH
                        param? query? 는 jtonny
                    */
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

    created() {
        window.scrollTo(0, 0);
    },
    beforeUnmount() {
        this.stompClient.disconnect();
    },
};
</script>

<style lang="scss" scoped>
hr {
    // margin-bottom: 0;
}

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
    margin-bottom: 40px;

    .apply {
        // margin-top: 12px;
        padding: 12px 0;
        // border-bottom: 1px solid var(--thin-color);
        border: 1px solid rgba(0, 0, 0, 0.13);
        box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
        border-radius: 6px 6px 0 0;
    }

    .helperInfo {
        display: flex;
        flex-direction: column;
        padding-bottom: 8px;

        .infos {
            background-color: var(--thin-color);
            padding: 4px;
            border-radius: 6px;
            display: flex;
            justify-content: space-between;
            margin-top: 8px;

            div {
                padding: 2px 8px;
            }
        }
    }

    .btns {
        cursor: pointer;

        div {
            border: 1px solid var(--light-color);
            padding: 4px;
            text-align: center;
            font-weight: 600;
            transition: all 0.13s;
            font-size: 0.9rem;
            color: var(--active-color);

            &:hover {
                opacity: 0.9;
            }
        }

        div:nth-child(1) {
            background-color: var(--light-color);
            border-radius: 0 0 0 8px;
        }

        div:nth-child(2) {
            background-color: var(--success-color);
            border-radius: 0 0 8px 0;
        }
    }
}

.loadingHelper {
    // background-color: var(--thin-color);
    padding: 10px;
    border-radius: 6px;
}

.likeIcon {
    font-size: 1.3rem;
    margin-top: 4px;
}
.closeBtn {
    position: relative;
    // right: 24px;
    z-index: 99;
    cursor: pointer;
    transition: all 0.13s;

    span {
        font-variation-settings: "wght" 300;
        transition: all 0.13s;
        color: var(--sub-color);
    }

    &:hover {
        span {
            color: var(--danger-color);
            font-variation-settings: "FILL" 1;
        }
    }
}
</style>
