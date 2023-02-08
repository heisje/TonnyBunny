<template>
    <div class="acceptContainer container">
        <div class="acceptWrap">
            <div class="acceptContent row">
                <div class="customForm col-md-6 col-12 d-flex flex-column justify-content-center">
                    <title-text
                        title="즉시통역 신청이 들어왔어요!"
                        text="고객들이 신청한 즉시통역 내역이에요" />
                    <hr />
                    <v-lazy
                        v-model="jtonnyQuestList"
                        :options="{ threshold: 0.5 }"
                        transition="fade-transition">
                        <div v-if="jtonnyQuestListLength > 0" class="mb-5">
                            <transition-group name="slide-up">
                                <div
                                    v-for="(quest, index) in jtonnyQuestList"
                                    :key="index"
                                    class="quest">
                                    {{ quest }}
                                    <medium-btn
                                        text="더보기"
                                        color="outline"
                                        font="active"
                                        class="w-100"></medium-btn>

                                    <div class="questProfileWrap">
                                        <div class="questProfile mt-3">
                                            <div class="profile ms-3">
                                                <img
                                                    class="profileImg"
                                                    src="@/assets/noProfile_white.png"
                                                    width="70"
                                                    height="70" />
                                                <div class="userType ms-2">
                                                    <span class="label">고객</span>
                                                    <h4 class="" v-if="quest?.client?.nickName">
                                                        {{ quest?.client?.nickName }}
                                                    </h4>
                                                    <h4 class="" v-else>미지정</h4>
                                                </div>
                                            </div>

                                            <div>
                                                <span class="material-symbols-outlined arrow">
                                                    sync_alt
                                                </span>
                                            </div>

                                            <div class="profile me-3">
                                                <div class="userType me-2">
                                                    <span class="label text-end">헬퍼</span>
                                                    <h4
                                                        class="text-end"
                                                        v-if="quest?.helper?.nickName">
                                                        {{ quest?.helper?.nickName }}
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

                                        <div class="d-flex">
                                            <td class="d-flex flex-row align-items-center">
                                                <square-tag
                                                    :text="quest.startLangCode"
                                                    sub
                                                    class="me-2"></square-tag>
                                                <div class="me-2">
                                                    <span class="material-symbols-outlined">
                                                        compare_arrows
                                                    </span>
                                                </div>
                                                <square-tag
                                                    :text="quest.endLangCode"
                                                    sub></square-tag>
                                            </td>

                                            <tr>
                                                <td>지불 캐럿</td>
                                                <td>
                                                    {{ quest.unitPrice }}
                                                    <span class="label">CRT</span>
                                                </td>
                                            </tr>
                                        </div>
                                    </div>

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
                                                        :text="quest.startLangCode"
                                                        sub
                                                        class="me-2"></square-tag>
                                                    <div class="me-2">
                                                        <span class="material-symbols-outlined">
                                                            compare_arrows
                                                        </span>
                                                    </div>
                                                    <square-tag
                                                        :text="quest.endLangCode"
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
                                                    {{ quest.unitPrice }}
                                                    <span class="label">CRT</span>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>상황 카테고리</td>
                                                <td>
                                                    <square-tag :text="quest.tonnySituCode" sub>
                                                    </square-tag>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td>상황 설명</td>
                                                <td class="pt-3">{{ quest.content }}</td>
                                            </tr>
                                        </tbody>
                                    </v-table>
                                </div>
                                <!-- <div
                                    v-for="jtonny in Object.values(jtonnyQuestList)"
                                    :key="jtonny.client.seq">
                                    <quest-card
                                        :questDetail="jtonny"
                                        rightBtnText="신청하기"
                                        @clickBtn2="apply(jtonny.client.seq)" />
                                </div> -->
                            </transition-group>
                        </div>
                        <div v-else>들어온 즉시통역 신청 카드가 없습니다.</div>
                    </v-lazy>

                    <!-- <JTonnyLoading /> -->

                    <router-link :to="{ name: 'JTonnyMatchingPage' }">
                        <button>다음페이지</button>
                    </router-link>
                </div>
                <div class="customForm col-md-6 col-12">
                    <div>
                        <title-text
                            title="신청한 즉시통역 목록"
                            text="현재 신청한 즉시통역 내역이에요"></title-text>
                        <hr />
                    </div>
                    <div>
                        <title-text
                            title="통역 예약 목록"
                            text="통역 예약도 받아볼까요?"></title-text>
                        <hr />
                    </div>
                </div>
            </div>

            <AlarmModal
                v-show="isOpen"
                :isOpen="isOpen"
                title="확인"
                type="success"
                btnText2="다음"
                btnColor1="main"
                btnColor2="carrot"
                btnFontColor1="white"
                btnFontColor2="white"
                @close-modal="closeModal"
                :link="{ name: 'HomePage' }">
                <template #content> 대기가 취소되었습니다<br />또 이용해주세요! </template>
            </AlarmModal>
        </div>

        <!-- <div v-if="isFind">
            <title-text title="즉시 통역 찾기" text="지금 당장 번역이 필요한 고객을 찾습니다" />
            <JTonnyLoading />
        </div>
        <div v-else>
            <title-text
                title="즉시 통역 요청이 왔어요!"
                text="통역 요청글을 자세히 보고 가능한 통역을 신청하세요!" />
            <JTonnyLoading />
        </div> -->

        <!-- 얘도 click 하면 unsubscribe() -->
        <!-- <large-btn text="대기 취소" color="carrot" font="white" @click.prevent="openModal" /> -->
    </div>
</template>

<script>
// import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
// import LargeBtn from "@/components/common/button/LargeBtn.vue";
// import QuestCard from "@/components/common/card/QuestCard.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

import { mapGetters } from "vuex";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";

export default {
    components: {
        // JTonnyLoading,
        TitleText,
        // LargeBtn,
        // QuestCard,
        AlarmModal,
        SquareTag,
        MediumBtn,
    },

    data() {
        return {
            isFind: false,
            isOpen: false,

            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyQuestList: {},
            jtonnyRequest: {},
            subs: [], // 페이지 이탈할 때 unsubscribe()
        };
    },

    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),

        jtonnyQuestListLength() {
            return Object.keys(this.jtonnyQuestList).length;
        },
    },

    methods: {
        openModal(e) {
            e.preventDefault();
            this.isOpen = true;
        },

        closeModal() {
            this.isOpen = false;
        },
        apply(seq) {
            // this.jtonnyRequest 에 단가, this.userInfo.seq 넣기
            let jtonnyApply = this.jtonnyQuestList[seq];
            console.log("jta", jtonnyApply);
            jtonnyApply.helper = {
                seq: this.userInfo.seq,
                nickName: this.userInfo.nickName,
            };
            jtonnyApply.unitPrice = 5000;

            this.stompClient.send("/pub/jtonny/apply", JSON.stringify(jtonnyApply), {});
        },
        cancel() {
            this.stompClient.send(
                "/pub/jtonny/apply/cancel",
                JSON.stringify(this.jtonnyRequest),
                {}
            );
        },
        unsubscribe() {
            this.subs.forEach((sub) => {
                sub.unsubscribe();
            });
        },
    },

    mounted() {
        let possibleLanguageList = this.userInfo.helperInfo.possibleLanguageList;
        console.log("userInfo", this.userInfo);
        console.log("helperInfo", this.userInfo.helperInfo);
        console.log("가능언어", possibleLanguageList);
        console.log("타입", typeof possibleLanguageList);

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
                possibleLanguageList.forEach((lang) => {
                    let sub = this.stompClient.subscribe(
                        `/sub/jtonny/request/${lang.value}`,
                        (res) => {
                            console.log(`즉시통역(${lang.name}) 요청이 도착했습니다.`, res.body);

                            // 받은 데이터를 json 으로 파싱하고 dictionary 에 넣어줍니다.
                            let request = JSON.parse(res.body);
                            this.jtonnyQuestList[request.client.seq] = request;
                        }
                    );
                    this.subs.push(sub);

                    sub = this.stompClient.subscribe(
                        `/sub/jtonny/request/${lang.value}/cancel`,
                        (res) => {
                            console.log("즉시통역 요청이 취소되었습니다.", res.body);

                            let request = JSON.parse(res.body);
                            delete this.jtonnyQuestList[request.client.seq];
                        }
                    );
                    this.subs.push(sub);
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
    },

    created() {
        console.log("jtonnyQuestList: ", this.jtonnyQuestList);
        window.scrollTo(0, 0);
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
        border-radius: 8px;
        padding: 8px 8px;
        margin: 8px 0;
    }

    .questProfile {
        display: flex;
        justify-content: space-between;

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
</style>
