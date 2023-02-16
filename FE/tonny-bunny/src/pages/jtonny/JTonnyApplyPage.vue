<template>
    <div class="acceptContainer container">
        <div class="acceptWrap">
            <div class="acceptContent row">
                <div class="customForm col-md-6 col-12 d-flex flex-column justify-content-center">
                    <title-text
                        title="즉시통역 신청이 들어왔어요!"
                        text="고객들이 신청한 즉시통역 내역이에요"
                    />
                    <hr />
                    <v-alert
                        v-show="isAlertOpen"
                        closable
                        icon="mdi-alert"
                        text="즉시통역 신청은 3개까지만 가능합니다."
                        type="error"
                        variant="tonal"
                        class="alert"
                    ></v-alert>
                    <v-lazy
                        v-model="jtonnyQuestList"
                        :options="{ threshold: 0.5 }"
                        transition="fade-transition"
                    >
                        <div v-if="jtonnyQuestListLength > 0" class="mb-5">
                            <transition-group name="slide-up">
                                <div v-for="(quest, index) in jtonnyQuestList" :key="index">
                                    <!-- {{ quest }} -->

                                    <div class="quest">
                                        <div class="questProfileWrap">
                                            <div class="questProfile mt-3">
                                                <div class="profile ms-3 me-4">
                                                    <user-profile-img
                                                        class="profileImg"
                                                        :profileImagePath="
                                                            quest?.client?.profileImagePath
                                                        "
                                                        width="70"
                                                    />

                                                    <!-- <img
                                                        class="profileImg"
                                                        src="@/assets/noProfile_white.png"
                                                        width="70"
                                                        height="70" /> -->
                                                    <div class="userType ms-2">
                                                        <span class="label">고객</span>
                                                        <h4 class="" v-if="quest?.client?.nickName">
                                                            {{ quest?.client?.nickName }}
                                                        </h4>
                                                        <h4 class="" v-else>미지정</h4>
                                                    </div>
                                                </div>

                                                <div class="d-flex flex-column">
                                                    <div class="d-flex flex-row align-items-center">
                                                        <square-tag
                                                            :text="allCode[quest?.startLangCode]"
                                                            sub
                                                            class="me-2"
                                                        ></square-tag>
                                                        <div class="me-2">
                                                            <span class="material-symbols-outlined">
                                                                compare_arrows
                                                            </span>
                                                        </div>
                                                        <square-tag
                                                            :text="allCode[quest?.endLangCode]"
                                                            sub
                                                        ></square-tag>
                                                    </div>
                                                    <div>
                                                        <h3>소요시간</h3>
                                                        {{ quest.estimateTime }}
                                                        <!-- <span class="">건</span> -->
                                                    </div>
                                                    <div v-if="quest.tonnySituCode != ''">
                                                        <h3>카테고리</h3>
                                                        {{ allCode[quest?.tonnySituCode] }}
                                                        <!-- <span class="label"></span> -->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <medium-btn
                                            text="더보기"
                                            color="outline"
                                            font="active"
                                            class="w-100"
                                            @click="toggleMoreOpen(quest)"
                                        ></medium-btn>
                                        <v-table v-show="quest.isMoreOpen">
                                            <thead>
                                                <tr class="">
                                                    <th class="col-4 fw-bold">제목</th>
                                                    <th class="col-8 fw-bold">내용</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- <tr>
                                                    <td>언어</td>
                                                    <td class="d-flex flex-row align-items-center">
                                                        <square-tag
                                                            :text="allCode[quest?.startLangCode]"
                                                            sub
                                                            class="me-2"></square-tag>
                                                        <div class="me-2">
                                                            <span class="material-symbols-outlined">
                                                                compare_arrows
                                                            </span>
                                                        </div>
                                                        <square-tag
                                                            :text="allCode[quest?.endLangCode]"
                                                            sub></square-tag>
                                                    </td>
                                                </tr> -->

                                                <tr>
                                                    <td>예상 소요 시간</td>
                                                    <td>
                                                        {{
                                                            "대략 " +
                                                            quest.estimateTime.split(":")[0] +
                                                            "시간 " +
                                                            quest.estimateTime.split(":")[1] +
                                                            "분"
                                                        }}
                                                    </td>
                                                </tr>

                                                <!-- <tr> -->
                                                <!-- <td>지불 캐럿</td> -->
                                                <!-- <td> -->
                                                <!-- {{ quest.unitPrice }} -->
                                                <!-- <span class="label">CRT</span> -->
                                                <!-- </td> -->
                                                <!-- </tr> -->

                                                <tr>
                                                    <td>상황 카테고리</td>
                                                    <td>
                                                        <div
                                                            v-if="quest.tonnySituCode == ''"
                                                            class="fst-italic"
                                                            style="color: var(--sub-color)"
                                                        >
                                                            상황 카테고리가 없습니다.
                                                        </div>
                                                        <square-tag
                                                            v-else
                                                            :text="allCode[quest?.tonnySituCode]"
                                                            sub
                                                        ></square-tag>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td>상황 설명</td>

                                                    <td>
                                                        <div
                                                            v-if="quest.content == ''"
                                                            class="fst-italic"
                                                            style="color: var(--sub-color)"
                                                        >
                                                            상황 설명이 없습니다.
                                                        </div>
                                                        <div v-else>{{ quest.content }}</div>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </v-table>
                                    </div>
                                    <div class="d-flex btns row ms-0 me-0 ps-0 pe-0">
                                        <div
                                            class="col-6 reject"
                                            @click="cancelApply(quest.client.seq)"
                                        >
                                            거절
                                        </div>
                                        <div class="col-6 accept" @click="apply(quest.client.seq)">
                                            신청
                                        </div>
                                    </div>
                                </div>
                            </transition-group>
                        </div>
                        <div v-else>들어온 즉시통역 신청 카드가 없습니다.</div>
                    </v-lazy>
                </div>
                <div class="customForm col-md-6 col-12">
                    <div>
                        <title-text
                            title="신청한 즉시통역 목록"
                            text="현재 신청한 즉시통역 내역이에요"
                        ></title-text>
                        <hr />

                        <transition-group name="slide-up">
                            <div
                                v-for="(apply, index) in Object.values(jtonnyApplyQuestList)"
                                :key="index"
                            >
                                <div v-if="index < 3" class="row px-3">
                                    <!-- {{ apply }} -->

                                    <div class="d-flex flex-row align-items-center quest py-3 px-2">
                                        <div
                                            class="col-2 d-flex flex-column align-items-center justify-content-center"
                                        >
                                            <user-profile-img
                                                :profileImagePath="apply?.client?.profileImagePath"
                                                width="50"
                                            />

                                            <!-- <img :src="apply.helper.profileImagePath" /> -->
                                            <!-- <div>{{ apply.helper.nickName }}</div> -->
                                            <!-- <div>{{ apply.helper.helperInfo.oneLineIntroduction }}</div> -->
                                        </div>
                                        <div class="col-9 helperInfo">
                                            <div class="d-flex flex-row">
                                                <div>{{ apply.client.nickName }}</div>
                                            </div>
                                            <div class="d-flex flex-row align-items-center">
                                                <square-tag
                                                    :text="allCode[apply?.startLangCode]"
                                                    sub
                                                    class="me-2"
                                                ></square-tag>
                                                <div class="me-2">
                                                    <span class="material-symbols-outlined">
                                                        compare_arrows
                                                    </span>
                                                </div>
                                                <square-tag
                                                    :text="allCode[apply?.endLangCode]"
                                                    sub
                                                ></square-tag>
                                            </div>
                                            <div>
                                                <h3>소요시간</h3>
                                                {{ apply.estimateTime }}
                                                <!-- <span class="">건</span> -->
                                            </div>
                                            <div>
                                                <h3>카테고리</h3>
                                                {{ allCode[apply?.tonnySituCode] }}
                                                <!-- <span class="label"></span> -->
                                            </div>
                                            <!-- <div class="d-flex">
                                                <div>{{ apply.startLangCode }}</div>
                                                <div>{{ apply.endLangCode }}</div>
                                            </div>
                                            <div class="d-flex">
                                                <div>{{ apply.estimateTime }}</div>
                                                <div>{{ apply.tonnySituCode }}</div>
                                                <div>{{ apply.unitPrice }}</div>
                                            </div> -->

                                            <!-- <div>{{ apply.helper.helperInfo.avgScore }}</div> -->
                                            <!-- <div>{{ apply.helper.helperInfo.reviewCount }}</div> -->
                                            <!-- <div>{{ apply.totalPrice }}</div> -->
                                        </div>

                                        <div
                                            class="closeBtn col-1"
                                            @click="cancelApply(apply.client.seq)"
                                        >
                                            <span class="material-symbols-outlined"> close </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </transition-group>
                    </div>
                </div>
            </div>

            <AlarmModal
                v-show="modalName == `reject`"
                title="거절"
                type="danger"
                btnText2="다음"
                btnColor2="carrot"
                btnFontColor1="white"
                btnFontColor2="white"
                @clickBtn2="closeModal"
            >
                <template #content> 제안이 거절되었습니다<br />다음에 이용해주세요! </template>
            </AlarmModal>

            <AlarmModal
                v-show="modalName == `accept`"
                title="수락"
                type="success"
                btnText2="확인"
                btnColor1="main"
                btnColor2="carrot"
                btnFontColor1="main"
                btnFontColor2="white"
                @clickBtn2="onAir"
            >
                <template #content>
                    제안이 수락되었습니다<br />확인을 누르시면 즉시통역이 시작됩니다!
                </template>
            </AlarmModal>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import http from "@/common/axios";

import SquareTag from "@/components/common/tag/SquareTag.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import UserProfileImg from "@/components/common/UserProfileImg.vue";

export default {
    components: {
        TitleText,
        AlarmModal,
        SquareTag,
        MediumBtn,
        UserProfileImg,
    },

    data() {
        return {
            isMoreOpen: false,
            isAlertOpen: false,
            applyCount: 0,

            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyQuestList: {},
            jtonnyApplyQuestList: {}, // 현재 신청한 목록
            jtonnyRequest: {},
            subs: [], // 페이지 이탈할 때 unsubscribe(),

            modalName: "", // 모달 선택
        };
    },

    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
            yTonnyList: "getYTonnyList",
            allCode: "getAllCode",
        }),

        jtonnyQuestListLength() {
            return Object.keys(this.jtonnyQuestList).length;
        },
    },

    methods: {
        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        onAir() {
            this.$router.push({ name: "OnAirPage" });
            this.$store.commit("CLOSE_ALARM_MODAL");
        },

        toggleMoreOpen(quest) {
            quest.isMoreOpen = !quest.isMoreOpen;
        },

        async apply(seq) {
            // this.jtonnyRequest 에 단가, this.userInfo.seq 넣기
            let jtonnyApply = this.jtonnyQuestList[seq];
            console.log("jtonnyApply!!!!!!!!!!!!!!", jtonnyApply);

            jtonnyApply.helper = {
                seq: this.userInfo.seq,
                nickName: this.userInfo.nickName,
                userCode: this.userInfo.userCode,
                helperInfo: this.userInfo.helperInfo,
            };

            // jtonnyApply.unitPrice = this.userInfo.helperInf.unitPrice;
            this.jtonnyApplyQuestList[seq] = jtonnyApply;
            this.applyCount++;

            if (this.applyCount > 3) {
                this.applyCount = true;
                this.applyCount = 3;
            }

            this.stompClient.send("/pub/jtonny/apply", JSON.stringify(jtonnyApply), {});

            // let jtonnyDetail = this.jtonnyRequest[seq];
            // console.log("!!!!!jtonnyDetail", jtonnyDetail);

            let alert = {
                userSeq: this.userInfo.seq,
                clientSeq: jtonnyApply.client.seq,
                helperSeq: this.userInfo.seq,
                taskCode: jtonnyApply.taskCode,
                content: "즉시 통역 헬퍼 신청에 성공했습니다.",
                clientNickname: jtonnyApply.client.nickName,
                helperNickname: this.userInfo.nickName,
            };

            await this.$store.dispatch("applyAlert", alert);
        },

        async cancelApply(seq) {
            let jtonnyApply = this.jtonnyQuestList[seq];

            this.stompClient.send("/pub/jtonny/apply/cancel", JSON.stringify(jtonnyApply), {});
            delete this.jtonnyQuestList[seq];
            delete this.jtonnyApplyQuestList[seq];

            let alert = {
                userSeq: this.userInfo.seq,
                clientSeq: jtonnyApply.client.seq,
                helperSeq: this.userInfo.seq,
                taskCode: jtonnyApply.taskCode,
                content: "즉시 통역 헬퍼 신청을 취소했습니다.",
                clientNickname: jtonnyApply.client.nickName,
                helperNickname: this.userInfo.nickName,
            };

            await this.$store.dispatch("applyCancelAlert", alert);
        },

        unsubscribe() {
            this.subs.forEach((sub) => {
                sub.unsubscribe();
            });
        },
    },

    mounted() {
        const serverURL = http.getUri() + "/stomp";
        let possibleLanguageList = this.userInfo.helperInfo.possibleLanguageList;
        let socket = new SockJS(serverURL);

        this.stompClient = Stomp.over(socket);

        // console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);

        this.stompClient.connect(
            {},
            () => {
                // 소켓 연결 성공
                this.connected = true;
                console.log("소켓 연결 성공");

                // 본인 seq 를 구독합니다.
                possibleLanguageList.forEach((lang) => {
                    let sub = this.stompClient.subscribe(
                        `/sub/jtonny/request/${lang.value}`,
                        (res) => {
                            // console.log(`즉시통역(${lang.name}) 요청이 도착했습니다.`, res.body);

                            // 받은 데이터를 json 으로 파싱하고 dictionary 에 넣어줍니다.
                            let request = JSON.parse(res.body);
                            this.jtonnyQuestList[request.client.seq] = request;
                        }
                    );
                    this.subs.push(sub);

                    sub = this.stompClient.subscribe(
                        `/sub/jtonny/request/${lang.value}/cancel`,
                        (res) => {
                            // console.log("즉시통역 요청이 취소되었습니다.", res.body);

                            let request = JSON.parse(res.body);
                            delete this.jtonnyQuestList[request.client.seq];
                            delete this.jtonnyApplyQuestList[request.client.seq];
                        }
                    );
                    this.subs.push(sub);
                });

                this.stompClient.subscribe(`/sub/jtonny/accept/${this.userInfo.seq}`, (res) => {
                    // console.log("즉시통역 매칭 완료. 오픈비두 이동", res.body);

                    const data = JSON.parse(res.body);
                    this.$store.commit("SET_START_RES_DATA", data);

                    /*
                        let jtonny = JSON.parse(res.body);

                        오픈비두 이동 router.PUSH
                        param? query? 는 jtonny


                    */

                    this.modalName = "accept";

                    this.$store.commit("TOGGLE_ALARM_MODAL");
                });

                this.stompClient.subscribe(`/sub/jtonny/reject/${this.userInfo.seq}`, (res) => {
                    let jtonny = JSON.parse(res.body);

                    this.modalName = "reject";
                    this.$store.commit("TOGGLE_ALARM_MODAL");

                    delete this.jtonnyQuestList[jtonny.client.seq];
                    delete this.jtonnyApplyQuestList[jtonny.client.seq];
                });
            },
            (error) => {
                // 소켓 연결 실패
                console.log("소켓 연결 실패", error);
                this.connected = false;
            }
        );
    },

    beforeUnmount() {
        this.unsubscribe();
        this.stompClient.disconnect();
    },

    created() {
        window.scrollTo(0, 0);

        // this.$store.dispatch("getYTonnyList");
    },
};
</script>

<style lang="scss" scoped>
.acceptContent {
    justify-content: space-evenly;
    align-items: flex-start;
}

.quest {
    cursor: default;
    padding: 24px 24px;

    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;

    .questProfileWrap {
        background-color: var(--thin-color);
        border-radius: 8px 8px 0 0;
        padding: 8px 8px;
        margin-top: 8px;
    }

    .questProfile {
        display: flex;

        align-items: center;
        width: 100%;
        height: 100px;

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

.yTonny {
    border-bottom: 1px solid var(--light-color);
    padding-bottom: 10px;
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
