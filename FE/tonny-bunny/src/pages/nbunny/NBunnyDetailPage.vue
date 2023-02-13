<template>
    <div class="d-flex justify-content-center customFormWrap w-100 flex-column">
        <div>
            <title-banner title="🐰 번역 의뢰 요청" text="의뢰의 상세내용을 확인할 수 있습니다" />
        </div>
        {{ getBunnyDetail }}
        <div class="d-flex justify-content-center row">
            <div class="customForm bunnyDetail col-md-6 col-12">
                <div class="d-flex justify-content-between">
                    <SquareTag text="번역의뢰" sub></SquareTag>
                    <SquareTag
                        v-if="getBunnyDetail?.taskStateCode == getTaskStateCode[0]?.value"
                        success
                    ></SquareTag>
                    <SquareTag
                        v-if="getBunnyDetail?.taskStateCode == getTaskStateCode[1]?.value"
                        info
                    ></SquareTag>
                    <SquareTag
                        v-if="getBunnyDetail?.taskStateCode == getTaskStateCode[2]?.value"
                        white
                    ></SquareTag>
                </div>
                <title-text
                    type="h1"
                    :title="getBunnyDetail?.title"
                    top="10"
                    bottom="10"
                ></title-text>
                <div class="label">
                    {{
                        getBunnyDetail?.createdAt.substr(0, 10) +
                        " " +
                        getBunnyDetail?.createdAt.substr(11, 12)
                    }}
                </div>

                <div class="d-flex align-items-center mt-3">
                    <div>
                        <img
                            class="profileImg"
                            src="@/assets/noProfile_white.png"
                            width="50"
                            height="50"
                        />
                    </div>
                    <div class="userType">
                        <h4 class="overflow" v-if="getBunnyDetail?.client?.nickName">
                            {{ getBunnyDetail?.client?.nickName }}
                        </h4>
                        <h4 class="overflow" v-else>미지정</h4>
                    </div>
                </div>
                <br /><br />
                <hr />
                <div class="row">
                    <div class="col-4"><h2>통역 언어</h2></div>
                    <div class="col-8">
                        <SquareTag
                            :text="
                                allCode[getBunnyDetail?.startLangCode] +
                                ' ↔ ' +
                                allCode[getBunnyDetail?.endLangCode]
                            "
                            sub
                        ></SquareTag>
                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-4"><h2>마감 기한</h2></div>
                    <div class="col-8">
                        <h3 v-if="getBunnyDetail?.startDateTime">
                            {{ getBunnyDetail?.startDateTime.substr(0, 10) }} ~
                            {{ getBunnyDetail?.endDateTime.substr(0, 10) }}
                        </h3>
                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-4"><h2>예상 금액</h2></div>
                    <div class="col-8">
                        <h3>{{ getBunnyDetail?.estimatePrice }} 캐럿</h3>
                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-4"><h2>카테고리</h2></div>
                    <div class="col-8">
                        <SquareTag :text="allCode[getBunnyDetail?.bunnySituCode]" sub></SquareTag>
                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-4"><h2>내용</h2></div>
                    <div class="col-8">
                        <h3>
                            {{ getBunnyDetail?.content ? getBunnyDetail?.content : "내용없음" }}
                        </h3>
                    </div>
                </div>

                <!-- <div class="w-100">
                    사진<br />
                    <h3>{{ getBunnyDetail?.bunnyImageList }}</h3>
                    <div v-for="(image, index) in getBunnyDetail?.bunnyImageList" :key="index">
                        image{{ index }}
                    </div>
                </div> -->
                <div v-if="isCreator">
                    <br /><br />
                    <medium-btn
                        class="w-100"
                        color="main"
                        font="white"
                        text="의뢰 삭제하기"
                        @click.prevent="deleteBunny(getBunnyDetail?.seq)"
                    ></medium-btn>
                </div>
                <div v-else-if="$store.state.account.userInfo.userCode === `0010002`">
                    <div v-if="isApplyed(getBunnyDetail?.bunnyApplyList)">
                        <br /><br />
                        <medium-btn
                            class="w-100"
                            color="main"
                            font="white"
                            text="신청 삭제하기"
                            @click.prevent="deleteApply"
                        ></medium-btn>
                    </div>
                    <div v-else>
                        <br /><br />
                        <medium-btn
                            class="w-100"
                            color="carrot"
                            font="white"
                            text="신청 제안하기"
                            @click.prevent="goToBunnyApplyPage"
                        ></medium-btn>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-12 apply">
                <div class="w-100">
                    <div class="d-flex justify-content-between">
                        <div class="w-75">
                            <TitleText
                                type="h2"
                                top="0"
                                bottom="0"
                                :title="`가격을 제안한 헬퍼들(${getBunnyDetail?.bunnyApplyList.length})`"
                            ></TitleText>
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
                        class="w-100"
                    >
                        <helper-card
                            class="w-100 mb-3"
                            :userInfo="apply"
                            :fareText="apply.estimatePrice"
                            rightBtnText="상담하기"
                            @remove-card="close"
                            @toggle-like-btn="toggleLike"
                            @click-btn1="check1"
                            @click-btn2="enterChatRoom"
                        ></helper-card>
                    </div>
                </div>
                <!-- <br /><br />
                <div v-if="$store.state.account.userInfo.seq == getBunnyDetail?.client.seq">
                    <large-btn
                        style="width: 100%"
                        text="의뢰 취소 하기"
                        color="active"
                        @click.prevent="deleteBunny(getBunnyDetail?.seq)"
                    />
                </div>
                <div v-else-if="$store.state.account.userInfo.userCode === `helper`">
                    <div v-if="isApplyed(getBunnyDetail?.bunnyApplyList)">
                        <large-btn
                            style="width: 100%"
                            text="신청 취소 하기"
                            color="active"
                            @click.prevent="deleteApply"
                        />
                    </div>
                    <div v-else>
                        <large-btn
                            style="width: 100%"
                            text="의뢰 신청 하기"
                            color="carrot"
                            @click.prevent="goToBunnyApplyPage"
                        />
                    </div>
                </div> -->
            </div>
            <AlarmModal
                v-show="modalName == `removeBunnyModal`"
                title="주의"
                type="danger"
                btnText1="예"
                btnText2="아니오"
                btnColor1="main"
                btnColor2="main"
                btnFontColor1="white"
                btnFontColor2="white"
                @clickBtn1="clickBtn2"
                @clickBtn2="clickBtn2"
            >
                <template #content> 의뢰를 취소하시겠습니까? </template>
            </AlarmModal>
            <AlarmModal
                v-show="modalName == `removeApplyModal`"
                title="성공"
                type="success"
                btnText2="완료"
                btnColor1="main"
                btnColor2="main"
                btnFontColor1="white"
                btnFontColor2="white"
                @clickBtn1="clickBtn2"
                @clickBtn2="clickBtn2"
            >
                <template #content> 제안이 취소되었습니다! </template>
            </AlarmModal>
        </div>
    </div>
</template>

<script>
import SquareTag from "@/components/common/tag/SquareTag.vue";
import TitleText from "@/components/common/TitleText.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";

export default {
    name: "NBunnyDetailPage",

    components: {
        SquareTag,
        TitleText,
        HelperCard,
        TitleBanner,
        AlarmModal,
        MediumBtn,
    },

    computed: {
        ...mapGetters({ getBunnyDetail: "getBunnyDetail" }),
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ allCode: "getAllCode" }),
        ...mapGetters({ getBunnySituCode: "getBunnySituCode" }),
        ...mapGetters({ getTaskStateCode: "getTaskStateCode" }),
    },

    data() {
        return {
            startLangCode: "",
            endLangCode: "",
            bunnySituCode: "",

            startDate: "",
            endDate: "",

            estimatePrice: "",
            title: "",
            content: "",

            agreeValue: false,
            isCreator: false,
            modalName: "",
        };
    },

    async created() {
        console.log("getBunnyDetail", this.$route.params.id);
        await this.$store.dispatch("getBunnyDetail", this.$route.params.id);
        this.checkIsCreator();
    },

    methods: {
        openRemoveBunnyModal() {
            this.modalName = "removeBunnyModal";
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
        openRemoveApplyModal() {
            this.modalName = "removeApplyModal";
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        checkIsCreator() {
            let bunnyCreatorSeq = this.getBunnyDetail.client.seq;

            if (bunnyCreatorSeq == this.$store.state.account.userInfo.seq) {
                this.isCreator = true;
            }
        },

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
        async enterChatRoom(helperSeq) {
            const clientSeq = this.getBunnyDetail.client.seq;
            const userSeq = this.$store.getters.getUserInfo.seq;
            const anotherUserSeq = userSeq == clientSeq ? helperSeq : clientSeq;

            await this.$store.dispatch("getChatRoomInfo", {
                userSeq: userSeq,
                anotherUserSeq: anotherUserSeq,
            });
            if (userSeq == clientSeq) {
                this.$store.commit("SET_TRUE_SEND_URL_MESSAGE");
                this.$store.commit("SET_URL_MESSAGE", {
                    // 어쩔 수 없다. "상담하기" 버튼을 누르면 무조건 메세지가 간다!
                    // 공고가 아니라 유저 단위로 채팅방이 만들어지고 유지되기 때문에, 해당 공고에서 처음 접속한 시점을 잡기가 어렵..다?
                    urlPage: "NBunnyDetailPage",
                    urlPageSeq: this.getBunnyDetail.seq,
                    message:
                        "'" +
                        this.getBunnyDetail.title +
                        "' 을/를 통해서 온 고객입니다. 상담을 요청합니다!",
                });
            }
            this.$router.push({ name: "ChatDetailPage" });
        },
    },
};
</script>

<style lang="scss" scoped>
.bunnyDetail {
    cursor: default;
    padding: 32px 24px;
    margin-right: 24px;

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

.apply {
    width: 500px;
    padding: 0px;
    margin-right: 24px;
}
</style>
