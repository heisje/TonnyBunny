<template lang="">
    <div>
        <h1>공통 컴포넌트 테스트 페이지</h1>

        <button>titles</button>
        <div>
            <title-banner
                title="🐰 실시간 동시 통역"
                text="실시간으로 필요한 통역 서비스를 신청합니다" />
            <title-text
                title="🐰 실시간 동시 통역"
                text="실시간으로 필요한 통역 서비스를 신청합니다" />
            <title-text important type="h1" title="Default Title" text="Description" bottom="12" />
            <title-text title="Default Title" text="Description" />
        </div>
        <hr />

        <button>Buttons</button>
        <div>
            <LargeBtn color="outline" font="active" disable text="마이 페이지" />
            <MediumBtn color="carrot" font="white" text="마이 페이지" />
            <SmallBtn color="active" font="white" text="마이 페이지" />
            <XSmallBtn color="light" font="main" text="마이 페이지" />
        </div>
        <hr />

        <button>Tags</button>
        <div>
            <square-tag text="하이루" sub></square-tag>
            <square-tag success></square-tag>
            <square-tag info></square-tag>
            <square-tag white></square-tag>

            <circle-tag text="활성태그"></circle-tag>
            <circle-tag text="비활성태그" disable></circle-tag>
        </div>
        <hr />

        <button>cards</button>
        <div style="display: flex; flex-direction: column">
            <client-card />
            <helper-card
                nickName="아스파라거스"
                oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
                fareText="2000"
                starText="4.0"
                countText="100"
                likeText="10"
                isLikeEmpty
                :userInfo="{
                    seq: 1,
                    userCode: '1',
                    email: 'test251@gmail.com',
                    nickName: 'nickck111',
                    phoneNumber: null,
                    point: 0,
                    profileImagePath: '/img/default.jpg',
                    helperInfo: {
                        seq: 1,
                        avgScore: 3.5,
                        reviewCount: 213,
                        unitPrice: 123,
                        oneLineIntroduction: 'ㄻㄴㄴㄻㅁㄹㄴ',
                        introduction: 'ㄹㄴㅁㅁㄻㄴㄹ',
                        possibleLanguageList: [
                            {
                                name: 'Korean',
                                value: '0000111',
                            },
                            {
                                name: 'English',
                                value: '0010101',
                            },
                        ],
                        certificateList: [
                            {
                                seq: null,
                                langCode: null,
                                certName: 'Korean',
                                content: 'Korean',
                            },
                            {
                                seq: null,
                                langCode: null,
                                certName: 'Canana',
                                content: 'Canana',
                            },
                        ],
                    },
                }"
                rightBtnText="상담하기"
                @remove-card="close"
                @toggle-like-btn="toggleLike"
                @click-btn1="check1"
                @click-btn2="check2"></helper-card>
            <!-- <ClientCard />
            -->
            <quest-card
                :questDetail="{
                    seq: 2,
                    client: {
                        imagePath: 'image',
                        nickName: 'nicknamasdfadsfasdfe1',
                        seq: '1',
                    },
                    helper: {
                        imagePath: 'image',
                        nickName: 'nicknamadsffadsdfe2',
                        seq: '1',
                    },
                    title: '제목',
                    content: '내용',
                    startDate: '2023-02-07T00:00:00',
                    endDate: '2023-02-08T00:00:00',
                    estimatePrice: 100,
                    startLangCode: '0020001',
                    endLangCode: '0020002',
                    bunnySituCode: '0050001',
                    bunnyStateCode: '0100001',
                    taskCode: '0030003',
                    bunnyApplyList: [
                        {
                            seq: 7,
                            userSeq: 3,
                            bunnySeq: 2,
                            estimatePrice: 100,
                        },
                    ],
                    bunnyImageList: [],
                    bunnyQuotationList: [],
                }"
                @click-btn1="check1"
                @click-btn2="check2"></quest-card>
            <quest-card
                :questDetail="{
                    ytonnySeq: 1,
                    clientSeq: 1,

                    title: 'title1 수정',
                    content: 'content1 수정',

                    estimateDate: '2023-02-02',
                    estimateStartTime: '12:01',
                    estimateTime: '12:01',
                    estimatePrice: 9999,

                    startLangCode: '0020001',
                    endLangCode: '0020002',
                    tonnySituCode: '0040001',
                    taskStateCode: '0090002',
                }"
                @click-btn1="check1"
                @click-btn2="check2"></quest-card>
        </div>
        <hr />

        <button>inputs</button>
        <div>
            <input-test />
        </div>
        <hr />

        <button>modals</button>
        <div>
            <SmallBtn text="모달버튼" @click="openModal" />

            <alarm-modal
                :isOpen="isOpen"
                title="경고"
                type="danger"
                btnText1="아니오"
                btnText2="예"
                btnColor1="main"
                btnColor2="carrot"
                btnFontColor1="white"
                btnFontColor2="white"
                @clickBtn1="closeModal"
                @clickBtn2="clickBtn2">
                <template #content>
                    안녕하세요
                    <br />
                    안녕하십니까 안녕하세요 안녕하세요 안녕하세요 안녕하세요
                </template>
            </alarm-modal>
            <!-- <LoginModalVue /> -->
        </div>
        <hr />
    </div>
</template>
<script>
import TitleBanner from "@/components/common/TitleBanner.vue";
import TitleText from "@/components/common/TitleText.vue";

import LargeBtn from "@/components/common/button/LargeBtn.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import XSmallBtn from "@/components/common/button/XSmallBtn.vue";

import CircleTag from "@/components/common/tag/CircleTag.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";

import InputTest from "@/components/common/input/InputTest.vue";

import ClientCard from "@/components/common/card/ClientCard.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import QuestCard from "@/components/common/card/QuestCard.vue";

import AlarmModal from "@/components/common/modal/AlarmModal.vue";
// import LoginModalVue from "@/components/common/modal/LoginModal.vue";
export default {
    name: "ComponentsTestPage",
    components: {
        TitleBanner,
        TitleText,

        LargeBtn,
        MediumBtn,
        SmallBtn,
        XSmallBtn,

        CircleTag,
        SquareTag,

        InputTest,

        ClientCard,
        HelperCard,
        QuestCard,

        AlarmModal,
        // LoginModalVue,
    },
    data() {
        return {
            isOpen: false,
        };
    },
    methods: {
        openModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        closeModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        clickBtn2() {
            this.$router.push({ name: "HomePage" });
        },
    },
};
</script>
<style lang="scss" scoped></style>
