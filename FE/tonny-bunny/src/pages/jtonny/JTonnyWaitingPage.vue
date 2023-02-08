<template>
    <div class="waitingContainer container">
        <!-- <title-banner
            title="즉시통역 서비스 연결"
            text="통역을 도와줄 헬퍼를 찾아볼까요?"></title-banner> -->
        <div class="waitingWrap">
            <div v-if="jtonnyApplyList.length == 0">
                <div class="waigingContent">
                    <div
                        class="customForm col-md-6 col-12 d-flex flex-column justify-content-center">
                        <div class="jtonnyRequestWrap">
                            <title-text title="신청한 즉시통역 카드"></title-text>
                            {{ jtonnyRequest }}
                            <div class="jtonnyRequestContent">
                                <div></div>
                            </div>

                            <div class="yTonnyDetailWrap col-md-6 col-12" ref="yTonnyDetail">
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
                                                <square-tag
                                                    sub
                                                    :text="yTonnyDetail.taskCode"
                                                    class="me-2"></square-tag>
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
                                                <div
                                                    @click.prevent="toggleEditOpen"
                                                    v-click-outside="onClickOutside">
                                                    <span class="material-symbols-outlined fs-4">
                                                        more_vert
                                                    </span>
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
                                                        <div
                                                            @click="
                                                                this.$store.commit(
                                                                    'TOGGLE_ALARM_MODAL'
                                                                )
                                                            ">
                                                            예약 삭제
                                                        </div>
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
                                                        <td
                                                            class="d-flex flex-row align-items-center">
                                                            <square-tag
                                                                :text="yTonnyDetail.startLangCode"
                                                                sub
                                                                class="me-2"></square-tag>
                                                            <div class="me-2">
                                                                <span
                                                                    class="material-symbols-outlined">
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
                                                        <td class="pt-3">
                                                            {{ yTonnyDetail.content }}
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </v-table>
                                        </div>
                                    </div>
                                </v-lazy>
                            </div>
                        </div>
                        <title-text
                            title="즉시통역 가능한 헬퍼를 찾아볼까요?"
                            text="헬퍼의 프로필을 자세히 보고 통역을 부탁한 헬퍼를 수락해주세요" />
                        <JTonnyLoading></JTonnyLoading>
                        <medium-btn
                            text="신청 취소하기"
                            color="light"
                            font="main"
                            class="w-100"></medium-btn>
                    </div>

                    <div class="customForm col-md-6 col-12">
                        <title-text title="즉시통역을 제안한 헬퍼들"></title-text>
                        <hr />
                        <div v-for="i in 2" :key="i">
                            <helper-card />
                        </div>
                        <div v-for="(apply, index) in jtonnyApplyList" :key="index">
                            {{ apply }}
                            <helper-card />
                        </div>
                    </div>
                </div>
            </div>
            <div v-else>
                <JTonnyLoading></JTonnyLoading>
                <medium-btn
                    text="신청 취소하기"
                    color="light"
                    font="main"
                    class="w-100"></medium-btn>
            </div>
            <small-btn color="light" font="live" text="찾으면" @click.prevent="찾아짐()" />
            <small-btn color="light" font="live" text="수락하기누름" @click.prevent="openModal" />

            <AlarmModal
                v-show="isOpen1"
                :isOpen="isOpen1"
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

import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import QuestCard from "@/components/common/card/QuestCard.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

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
        // LargeBtn,
        AlarmModal,
        // TitleBanner,
        MediumBtn,
    },

    data() {
        return {
            isFind: false,
            isOpen1: false,
            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyList: {},
            jtonnyApplyList: [],
        };
    },

    computed: {
        ...mapGetters({
            jtonnyRequest: "getJtonnyRequest",
            // jtonnyList: "getJtonnyList",
        }),
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
        console.log("hi!!!!!!!!!!!!!!!", this.jtonnyApplyList.length);
        let clientSeq = this.jtonnyRequest.client.seq;
        const serverURL = "http://localhost:8080/api/stomp";
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
                    this.jtonnyList[request.helper.seq] = request;
                });
                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}/cancel`, (res) => {
                    console.log("즉시통역 요청이 취소되었습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    delete this.jtonnyList[request.helper.seq];
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

.waigingContent {
    display: flex;
    justify-content: space-evenly;
    // justify-content: space-around;
    // justify-content: center;
    // align-items: center;
}

.jtonnyRequestContent {
    cursor: default;
    padding: 32px 24px;

    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;
}
</style>
