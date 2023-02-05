<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner title="🐰 번역 의뢰 요청" text="의뢰의 상세내용을 확인할 수 있습니다" />
            <div>
                {{ getBunnyDetail }}
                <br /><br />
            </div>
            <div class="d-flex justify-content-between">
                <SquareTag text="번역의뢰" sub></SquareTag>
                <SquareTag
                    v-if="getBunnyDetail.bunnyStateCode == getBunnyStateCode['모집중']"
                    success></SquareTag>
                <SquareTag
                    v-if="getBunnyDetail.bunnyStateCode == getBunnyStateCode['진행중']"
                    info></SquareTag>
                <SquareTag
                    v-if="getBunnyDetail.bunnyStateCode == getBunnyStateCode['완료됨']"
                    white></SquareTag>
            </div>
            <TitleText type="h2" :title="getBunnyDetail.title"></TitleText>

            <!-- 이미지 추후 삽입 -->
            <div>getBunnyDetail.clientSeq로 axios 요청을 보내서 닉네임과 이미지패스를 받기</div>
            <br /><br />
            <div>
                통역언어<br />
                <SquareTag
                    :text="`${getKeyByValue(
                        getLangCode,
                        getBunnyDetail.startLangCode
                    )} <-> ${getKeyByValue(getLangCode, getBunnyDetail.endLangCode)}`"
                    sub></SquareTag>
            </div>

            <br /><br />

            <div>
                마감기한<br />
                <h3>
                    {{ getBunnyDetail.startDate.substr(0, 10) }} ~
                    {{ getBunnyDetail.endDate.substr(0, 10) }}
                </h3>
            </div>

            <br /><br />

            <div>
                예상 금액<br />
                <h3>{{ getBunnyDetail.estimatePrice }} 캐럿</h3>
            </div>

            <br /><br />
            <div>
                카테고리<br />
                <SquareTag
                    :text="`${getKeyByValue(getBunnySituCode, getBunnyDetail.bunnySituCode)}`"
                    sub></SquareTag>
            </div>

            <br /><br />

            <div>
                내용<br />
                <h3>{{ getBunnyDetail.content }}</h3>
            </div>

            <br /><br />

            <div class="w-100">
                사진<br />
                <h3>{{ getBunnyDetail.bunnyImageList }}</h3>
                <div v-for="(image, index) in getBunnyDetail.bunnyImageList" :key="index">
                    image{{ index }}
                </div>
            </div>

            <hr />

            <div class="w-100">
                <div class="d-flex justify-content-between">
                    <div class="w-75">
                        <TitleText
                            type="h2"
                            top="0"
                            bottom="0"
                            :title="`가격을 제안한 헬퍼들(${getBunnyDetail.bunnyApplyList.length})`"></TitleText>
                    </div>
                    <div>
                        <router-link
                            :to="{
                                name: 'NBunnyHelperListPage',
                                params: { id: getBunnyDetail.seq },
                            }"
                            style="color: var(--sub-text)"
                            >더보기</router-link
                        >
                    </div>
                </div>
                <div
                    v-for="(apply, index) in getBunnyDetail.bunnyApplyList"
                    :key="index"
                    class="w-100">
                    {{ Apply }}
                    <helper-card
                        class="w-100 m-0 mb-3"
                        nickName="아스파라거스"
                        oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
                        :fareText="apply.estimatePrice"
                        starText="4.0"
                        countText="100"
                        likeText="10"
                        isLikeEmpty
                        rightBtnText="상담하기"
                        @remove-card="close"
                        @toggle-like-btn="toggleLike"
                        @click-btn1="check1"
                        @click-btn2="check2"></helper-card>
                </div>
            </div>
            <br /><br />
            <div v-if="$store.state.account.userInfo.seq == getBunnyDetail.clientSeq">
                <medium-btn
                    style="width: 100%"
                    text="의뢰 취소 하기"
                    color="active"
                    @click.prevent="deleteBunny(getBunnyDetail.seq)" />
            </div>
            <div v-else-if="$store.state.account.isHelper">
                <div v-if="isApplyed(getBunnyDetail.bunnyApplyList)">
                    <medium-btn
                        style="width: 100%"
                        text="신청 취소 하기"
                        color="active"
                        @click.prevent="deleteApply" />
                </div>
                <div v-else>
                    <medium-btn
                        style="width: 100%"
                        text="의뢰 신청 하기"
                        color="carrot"
                        @click.prevent="goToBunnyApplyPage" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SquareTag from "@/components/common/tag/SquareTag.vue";
import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";

export default {
    name: "NBunnyDetailPage",

    components: {
        SquareTag,
        TitleText,
        HelperCard,
        TitleBanner,
        MediumBtn,
    },

    computed: {
        ...mapGetters({ getBunnyDetail: "getBunnyDetail" }),
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ getBunnySituCode: "getBunnySituCode" }),
        ...mapGetters({ getBunnyStateCode: "getBunnyStateCode" }),
    },

    data() {
        return {
            startLangCode: "",
            endLangCode: "",
            bunnySituCode: "",
            bunnyStateCode: "0100001",

            startDate: "",
            endDate: "",

            estimatePrice: "",
            title: "",
            content: "",

            input1: {
                id: "input1",
                value: "",
                pattern: "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", // 유효성검사 조건(HTML 용)
                validate: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, // 유효성검사 조건(JS 용)
                notice: "", // 유효성검사 결과 텍스트
            },
            agreeValue: false,
        };
    },

    created() {
        this.$store.dispatch("getBunnyDetail", this.$route.params.id);
    },

    methods: {
        getKeyByValue(obj, value) {
            return Object.keys(obj).find((key) => obj[key] === value);
        },
        isApplyed(ApplyList) {
            return ApplyList.find(
                (apply) => apply.userSeq === this.$store.state.account.userInfo.seq
            );
        },
        deleteBunny(bunnySeq) {
            this.$store.dispatch("removeBunny", bunnySeq);
        },
        deleteApply() {
            const payload = {
                bunnySeq: this.getBunnyDetail.seq,
                bunnyApplySeq: 0,
            };
            this.getBunnyDetail.bunnyApplyList.forEach((apply) => {
                if (apply.userSeq == this.$store.state.account.userInfo.seq) {
                    payload.bunnyApplySeq = apply.seq;
                }
            });
            this.$store.dispatch("removeBunnyApply", payload);
        },
        goToBunnyApplyPage() {
            this.$router.push({
                name: "NBunnyMatchingPage",
            });
        },
    },
};
</script>

<style></style>
