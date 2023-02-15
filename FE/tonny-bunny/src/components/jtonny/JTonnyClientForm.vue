<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <div class="situation mt-5">
                <title-text
                    important
                    type="h2"
                    title="언어 선택"
                    text="어떤 언어를 통역하실건가요?"
                    bottom="20"
                />

                <div class="d-flex flex-row w-100 mb-5">
                    <div class="w-100">
                        <dropdown-input-code
                            class="w-100"
                            ref="startLangCode"
                            :disable="changLangCount % 2 ? false : true"
                            :dropdownArray="langCode"
                            placeholder="한국어"
                            @toggle="(e) => (jtonnyRequest.startLangCode = e)"
                        />
                    </div>

                    <div class="swap" @click="changLangCode">
                        <span class="material-symbols-outlined"> compare_arrows </span>
                    </div>

                    <div class="w-100">
                        <DropdownInputCode
                            ref="endLangCode"
                            :disable="changLangCount % 2 ? true : false"
                            :dropdownArray="langCode"
                            placeholder="필요 언어"
                            @toggle="(e) => (jtonnyRequest.endLangCode = e)"
                        />
                    </div>
                </div>

                <title-text
                    important
                    class="w-100"
                    type="h2"
                    title="예상 소요 시간"
                    text="해당 상황이 마무리될 때까지 대략 몇 분 정도 걸릴 것 같나요?"
                    top="70"
                    bottom="20"
                />

                <div class="d-flex">
                    <div class="col-6 d-flex flex-row me-2">
                        <div class="w-100">
                            <DropdownInput
                                class=""
                                :dropdownArray="hourCodeList"
                                placeholder="시간"
                                @toggle="(e) => (jtonnyRequest.estimateHour = e)"
                            />
                        </div>
                    </div>
                    <div class="col-6 d-flex flex-row">
                        <div class="w-100">
                            <DropdownInput
                                class="w-100"
                                :dropdownArray="minuteCodeList"
                                placeholder="분"
                                @toggle="(e) => (jtonnyRequest.estimateMinute = e)"
                            />
                        </div>
                    </div>
                </div>

                <title-text
                    type="h2"
                    title="[선택] 상황 카테고리"
                    text="어떤 상황의 통역을 원하시나요?"
                    top="70"
                    bottom="20"
                />

                <DropdownInputCode
                    class="w120"
                    :dropdownArray="tonnySituCode"
                    placeholder="상황 선택"
                    @toggle="(e) => (jtonnyRequest.tonnySituCode = e)"
                />

                <title-text
                    type="h2"
                    title="[선택] 상황 설명"
                    text="통역이 필요한 상황을 헬퍼에게 설명해볼까요?"
                    top="70"
                    bottom="20"
                />

                <textarea
                    type="textarea"
                    placeholder="내용을 입력해주세요"
                    rows="7"
                    v-model="jtonnyRequest.content"
                />
            </div>

            <agree-input @toggle="(e) => (agreeValue = e)" style="margin-top: 100px" />
            <medium-btn
                @click="insertJTonnyRequest"
                style="width: 100%"
                text="통역하기"
                :color="isPossible"
            />
        </form>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInputCode from "../common/input/DropdownInputCode.vue";
import DropdownInput from "../common/input/DropdownInput.vue";
import TitleText from "../common/TitleText.vue";

export default {
    name: "JTonnyClientForm",

    components: {
        TitleText,
        DropdownInputCode,
        MediumBtn,
        AgreeInput,
        DropdownInput,
    },

    computed: {
        ...mapGetters({
            allCode: "getAllCode",
            userInfo: "getUserInfo",
            langCode: "getLangCode",
            tonnySituCode: "getTonnySituCode",
            hourCodeList: "getHourCodeList",
            minuteCodeList: "getMinuteCodeList",
        }),
        isPossible() {
            if (
                this.jtonnyRequest.startLangCode != "" &&
                this.jtonnyRequest.endLangCode != "" &&
                this.jtonnyRequest.estimateHour != "" &&
                this.jtonnyRequest.estimateMinute != "" &&
                this.agreeValue
            ) {
                return "carrot";
            } else {
                return `main`;
            }
        },
    },

    data() {
        return {
            changLangCount: 0,

            agreeValue: false,

            jtonnyRequest: {
                client: {
                    seq: 0,
                    nickName: "",
                },
                helper: {
                    seq: 0,
                    nickName: "",
                },
                taskCode: "0030001",
                taskStateCode: "0090001",
                startLangCode: "0020001",
                endLangCode: "",
                tonnySituCode: "",
                content: "",
                unitPrice: 0,
                estimateTime: "",
                estimateHour: "",
                estimateMinute: "",
            },
        };
    },

    methods: {
        changeInput(e) {
            // v-model 대체용
            this[e.target.id].value = e.target.value;

            // 유효성 검사
            this[e.target.id].notice = "";
            if (!this[e.target.id].validate.test(this[e.target.id].value)) {
                this[e.target.id].notice =
                    "최소 8자 이상, 숫자와 문자를 포함한 비밀번호를 입력해주세요.";
            }
        },

        insertJTonnyRequest() {
            if (this.jtonnyRequest.endLangCode == "") {
                alert("언어를 선택해주세요");
            } else if (
                this.jtonnyRequest.estimateHour == "" ||
                this.jtonnyRequest.estimateMinute == ""
            ) {
                alert("시간을 선택해주세요");
            } else if (
                this.jtonnyRequest.estimateHour == "00" &&
                this.jtonnyRequest.estimateMinute == "00"
            ) {
                alert("예상 소요 시간을 제대로 선택해주세요");
            } else {
                this.jtonnyRequest.client.seq = this.userInfo.seq;
                this.jtonnyRequest.client.nickName = this.userInfo.nickName;
                this.jtonnyRequest.estimateTime = `${this.jtonnyRequest.estimateHour}:${this.jtonnyRequest.estimateMinute}`;

                this.$store.commit("SET_JTONNY_REQUEST", this.jtonnyRequest);
                this.$router.push({ name: "JTonnyWaitingPage" });
            }
        },

        changLangCode() {
            const temp = this.jtonnyRequest.startLangCode;
            this.jtonnyRequest.startLangCode = this.jtonnyRequest.endLangCode;
            this.jtonnyRequest.endLangCode = temp;
            this.$refs.startLangCode.changeEventParent(this.jtonnyRequest.startLangCode);
            this.$refs.endLangCode.changeEventParent(this.jtonnyRequest.endLangCode);
            this.changLangCount += 1;
        },
    },

    created() {
        window.scrollTo(0, 0);
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/input.scss";

.situation {
    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--thin-color);
    border-radius: 6px;
    padding-bottom: 32px;
}
</style>
