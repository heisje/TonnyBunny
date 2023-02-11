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
                    v-if="getBunnyDetail?.bunnyStateCode == getBunnyStateCode[0]?.value"
                    success></SquareTag>
                <SquareTag
                    v-if="getBunnyDetail?.bunnyStateCode == getBunnyStateCode[1]?.value"
                    info></SquareTag>
                <SquareTag
                    v-if="getBunnyDetail?.bunnyStateCode == getBunnyStateCode[2]?.value"
                    white></SquareTag>
            </div>
            <TitleText type="h2" :title="getBunnyDetail?.title"></TitleText>

            <!-- 이미지 추후 삽입 -->
            <div>getBunnyDetail?.clientSeq로 axios 요청을 보내서 닉네임과 이미지패스를 받기</div>
            <br /><br />
            <div>
                통역언어<br />
                <SquareTag
                    :text="
                        allCode[getBunnyDetail?.startLangCode] +
                        ' ↔ ' +
                        allCode[getBunnyDetail?.endLangCode]
                    "
                    sub></SquareTag>
            </div>

            <br /><br />

            <div>
                마감기한<br />
                <h3 v-if="getBunnyDetail?.startDateTime">
                    {{ getBunnyDetail?.startDateTime.substr(0, 10) }} ~
                    {{ getBunnyDetail?.endDateTime.substr(0, 10) }}
                </h3>
            </div>

            <br /><br />

            <div>
                예상 금액<br />
                <h3>{{ getBunnyDetail?.estimatePrice }} 캐럿</h3>
            </div>

            <br /><br />
            <div>
                카테고리<br />
                <SquareTag :text="allCode[getBunnyDetail?.bunnySituCode]" sub></SquareTag>
            </div>

            <br /><br />

            <div>
                내용<br />
                <h3>{{ getBunnyDetail?.content }}</h3>
            </div>

            <br /><br />

            <div class="w-100">
                사진<br />
                <h3>{{ getBunnyDetail?.bunnyImageList }}</h3>
                <div v-for="(image, index) in getBunnyDetail?.bunnyImageList" :key="index">
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
                            :title="`가격을 제안한 헬퍼들(${getBunnyDetail?.bunnyApplyList.length})`"></TitleText>
                    </div>
                    <div>
                        <router-link
                            :to="{
                                name: 'NBunnyHelperListPage',
                                params: { id: getBunnyDetail?.seq },
                            }"
                            style="color: var(--sub-text)"
                            >더보기</router-link
                        >
                    </div>
                </div>
                <div
                    v-for="(apply, index) in getBunnyDetail?.bunnyApplyList"
                    :key="index"
                    class="w-100">
                    {{ apply }}
                    <helper-card
                        class="w-100 m-0 mb-3"
                        :userInfo="apply"
                        :fareText="apply.estimatePrice"
                        rightBtnText="상담하기"
                        @remove-card="close"
                        @toggle-like-btn="toggleLike"
                        @click-btn1="check1"
                        @click-btn2="enterChatRoom"></helper-card>
                </div>
            </div>
            <br /><br />
            <div v-if="$store.state.account.userInfo.seq == getBunnyDetail?.client.seq">
                <large-btn
                    style="width: 100%"
                    text="의뢰 취소 하기"
                    color="active"
                    @click.prevent="deleteBunny(getBunnyDetail?.seq)" />
            </div>
            <div v-else-if="$store.state.account.userInfo.userCode === `helper`">
                <div v-if="isApplyed(getBunnyDetail?.bunnyApplyList)">
                    <large-btn
                        style="width: 100%"
                        text="신청 취소 하기"
                        color="active"
                        @click.prevent="deleteApply" />
                </div>
                <div v-else>
                    <large-btn
                        style="width: 100%"
                        text="의뢰 신청 하기"
                        color="carrot"
                        @click.prevent="goToBunnyApplyPage" />
                </div>
            </div>
        </div>
        <AlarmModal
            title="성공"
            type="success"
            btnText2="완료"
            btnColor1="main"
            btnColor2="main"
            btnFontColor1="white"
            btnFontColor2="white"
            @clickBtn2="clickBtn2">
            <template #content> 의뢰가 취소되었습니다! </template>
        </AlarmModal>
    </div>
    {{ getLangCode }}
    {{ getBunnySituCode }}
    {{ getBunnyStateCode }}
</template>

<script>
import SquareTag from "@/components/common/tag/SquareTag.vue";
import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import { mapGetters } from "vuex";
import LargeBtn from "@/components/common/button/LargeBtn.vue";

export default {
    name: "NBunnyDetailPage",

    components: {
        SquareTag,
        TitleText,
        HelperCard,
        TitleBanner,
        AlarmModal,
        LargeBtn,
    },

    computed: {
        ...mapGetters({ getBunnyDetail: "getBunnyDetail" }),
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ allCode: "getAllCode" }),
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
        console.log("getBunnyDetail", this.$route.params.id);
        this.$store.dispatch("getBunnyDetail", this.$route.params.id);
    },

    methods: {
        getKeyByValue(obj, value) {
            const findObj = obj.find((e) => e.value == value);
            return findObj.name;
        },
        isApplyed(ApplyList) {
            return ApplyList.find(
                (apply) => apply.userSeq === this.$store.state.account.userInfo.seq
            );
        },
        deleteBunny(bunnySeq) {
            this.$store.commit("TOGGLE_ALARM_MODAL");
            this.$store.dispatch("removeBunny", bunnySeq);
        },
        async deleteApply() {
            const payload = {
                bunnySeq: this.getBunnyDetail?.seq,
                bunnyApplySeq: 0,
            };
            this.getBunnyDetail?.bunnyApplyList.forEach((apply) => {
                if (apply.userSeq == this.$store.state.account.userInfo.seq) {
                    payload.bunnyApplySeq = apply.seq;
                }
            });
            await this.$store.dispatch("removeBunnyApply", payload);
            this.$store.dispatch("getBunnyDetail", this.$route.params.id);
        },
        goToBunnyApplyPage() {
            this.$router.push({
                name: "NBunnyMatchingPage",
            });
        },
        clickBtn2() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
            this.$router.push({ name: "HomePage" });
        },
        enterChatRoom(helperSeq) {
            const userSeq = this.getBunnyDetail.client.seq;
            this.$store.dispatch("getChatRoomInfo", {
                userSeq: userSeq,
                anotherUserSeq: helperSeq,
            });
            this.$router.push({ name: "ChatDetailPage" });
        },
    },
};
</script>

<style></style>
