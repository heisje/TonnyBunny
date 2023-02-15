<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div>
                <title-banner title="🐰 번역 견적서" text="헬퍼의 견적서 내용을 확인하고 있어요!" />
            </div>
            <div class="bunnyQuotation">
                <div class="row">
                    <div class="col-4"><h2>제목</h2></div>
                    <div class="col-8">
                        <h3>
                            {{ bunnyQuotation.title }}
                        </h3>
                    </div>
                </div>
                <hr />
                <div class="row">
                    <div class="col-4"><h2>내용</h2></div>
                    <div class="col-8">
                        <h3>
                            {{ bunnyQuotation.content }}
                        </h3>
                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-4"><h2>통역 언어</h2></div>
                    <div class="col-8">
                        <SquareTag
                            :text="
                                getAllCode[bunnyQuotation?.startLangCode] +
                                ' ↔ ' +
                                getAllCode[bunnyQuotation?.endLangCode]
                            "
                            sub></SquareTag>
                    </div>
                </div>

                <hr />
                <div class="row">
                    <div class="col-4"><h2>마감 기한</h2></div>
                    <div class="col-8">
                        <h3>
                            {{ bunnyQuotation.endDate }}
                        </h3>
                    </div>
                </div>
            </div>
            <br /><br />

            <div class="bunnyQuotation">
                <title-text
                    type="h2"
                    title="헬퍼가 제안한 금액입니다"
                    class="mb-3"
                    style="color: var(--primary-color)"
                    top="0" />

                <div class="d-flex">
                    <div class="col-11">
                        <input
                            type="text"
                            @input="changeInput"
                            class="w-100"
                            placeholder="ex)1000"
                            v-model="bunnyQuotation.totalPrice"
                            readonly />
                    </div>
                    <div class="backlabel col-2">
                        <h3>캐럿</h3>
                    </div>
                </div>
            </div>

            <br /><br />
            <!-- <title-text type="h2" title="[선택] 사진" text="추가 사진을 올려주세요" />
            <input
                type="file"
                multiple
                accept="image/*"
                @change="insertImageList"
                class="quotationFileList"
            /> -->

            <medium-btn
                v-show="isClient"
                style="width: 100%"
                text="수락하기"
                color="carrot"
                @click.prevent="openAcceptModal(event)" />
        </div>
        <AlarmModal
            title="주의"
            type="danger"
            btnText1="예"
            btnText2="아니오"
            btnColor1="carrot"
            btnColor2="main"
            btnFontColor1="white"
            btnFontColor2="white"
            @clickBtn1="acceptQuotation"
            @clickBtn2="closeModal">
            <template #content>
                견적서를 수락하시겠습니까? <br /><br />
                수락하시면 매칭이 완료됩니다!
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import http from "@/common/axios";
import { mapGetters } from "vuex";

export default {
    name: "QuotationDetailPage",
    components: { TitleText, MediumBtn, TitleBanner, SquareTag, AlarmModal },
    data() {
        return {
            bunnyQuotation: {},

            // 견적서 POST FORM
            s_index: "",
            totalPrice: "",
            fileList: [],

            isClient: false,
            isHelper: false,
        };
    },

    computed: {
        ...mapGetters({ getAllCode: "getAllCode" }),
    },

    methods: {
        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        openAcceptModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        acceptQuotation() {
            this.$store.dispatch("updateQuotation", {
                bunnySeq: this.bunnyQuotation.bunnySeq,
                seq: this.bunnyQuotation.seq,
            });
            this.enterChatRoom();
            // TODO 여기서 채팅방으로 다시 연결하기 +
        },
        async enterChatRoom() {
            // const s_bunny = this.bunnyList[this.s_index];
            const title = this.bunnyQuotation.title;
            const helperSeq = this.bunnyQuotation.helperSeq;
            const clientSeq = this.bunnyQuotation.clientSeq;
            await this.$store.dispatch("getChatRoomInfo", {
                userSeq: clientSeq,
                anotherUserSeq: helperSeq,
            });
            this.$store.commit("SET_TRUE_SEND_URL_MESSAGE");
            this.$store.commit("SET_URL_MESSAGE", {
                urlPage: "NBunnyDetailPage",
                urlPageSeq: this.bunnyQuotation.bunnySeq,
                message: "'" + title + "' 에 대한 견적서가 수락되었습니다!",
            });
            this.$router.push({ name: "ChatDetailPage" });
        },
        checkIsClient() {
            this.bunnyQuotation.clientSeq == this.$store.state.account.userInfo.seq
                ? (this.isClient = true)
                : (this.isClient = false);
        },

        checkIsHelper() {
            this.bunnyQuotation.helperSeq == this.$store.state.account.userInfo.seq
                ? (this.isHelper = true)
                : (this.isHelper = false);
        },
    },

    async created() {
        const bunnyQuotationSeq = this.$route.params.id;
        let res = await http.get(`/bunny/quotation/${bunnyQuotationSeq}`);

        const SUCCESS = "SUCCESS";
        const FAIL = "FAIL";

        try {
            // service logic
            switch (res.data.resultCode) {
                case SUCCESS:
                    this.bunnyQuotation = res.data.data;
                    this.bunnyQuotation["endDate"] =
                        this.bunnyQuotation.startDateTime.substr(0, 10) +
                        " ~ " +
                        this.bunnyQuotation.endDateTime.substr(0, 10);
                    this.checkIsClient();
                    this.checkIsHelper();
                    break;
                case FAIL:
                    break;
            }
            return res.data;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }
        }
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";

.bunnyQuotation {
    cursor: default;
    padding: 32px 24px;

    // box-shadow: 1px 1px 1px black;
    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;
}
</style>
