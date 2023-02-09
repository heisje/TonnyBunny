<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text important type="h2" title="어떤 언어통역이 필요하신가요?" />

            <div class="d-flex flex-row">
                <div class="">
                    <label for="">내 언어</label>
                    <DropdownInputCode
                        ref="startLangCode"
                        :disable="changLangCount % 2 ? false : true"
                        :dropdownArray="langCode"
                        placeholder="한국어"
                        @toggle="(e) => (jtonnyRequest.startLangCode = e)" />
                </div>

                <div class="swap" @click="changLangCode">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>

                <div class="">
                    <label for="">필요 언어</label>
                    <DropdownInputCode
                        ref="endLangCode"
                        :disable="changLangCount % 2 ? true : false"
                        :dropdownArray="langCode"
                        placeholder="필요 언어"
                        @toggle="(e) => (jtonnyRequest.endLangCode = e)" />
                </div>
            </div>

            <title-text
                important
                class="w-100"
                type="h2"
                title="예상 소요 시간"
                text="해당 상황이 마무리될 때까지 대략 몇 분 정도 걸릴 것 같나요?"
                top="70"
                bottom="20" />

            <div class="d-flex">
                <div class="col-6 d-flex flex-row me-2">
                    <div class="w-100">
                        <DropdownInput
                            class=""
                            :dropdownArray="hourCodeList"
                            placeholder="시간"
                            @toggle="(e) => (jtonnyRequest.estimateHour = e)" />
                    </div>
                </div>
                <div class="col-6 d-flex flex-row">
                    <div class="w-100">
                        <DropdownInput
                            class="w-100"
                            :dropdownArray="minuteCodeList"
                            placeholder="분"
                            @toggle="(e) => (jtonnyRequest.estimateMinute = e)" />
                    </div>
                </div>
            </div>

            <title-text
                type="h2"
                title="[선택] 상황 카테고리"
                text="해당 상황을 한 단어로 요약하자면?" />

            <DropdownInputCode
                class="w120"
                :dropdownArray="tonnySituCode"
                placeholder="상황 선택"
                @toggle="(e) => (jtonnyRequest.tonnySituCode = e)" />

            <title-text type="h2" title="[선택] 상황 설명" text="어떤 상황인가요?" />

            <textarea
                type="textarea"
                placeholder="내용을 입력해주세요"
                v-model="jtonnyRequest.content" />

            <agree-input @toggle="(e) => (agreeValue = e)" />
            <!-- <router-link :to="{ name: 'JTonnyWaitingPage' }"> -->
            <medium-btn @click="test" style="width: 100%" text="즉시찾기" color="carrot" />
            <!-- </router-link> -->
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

        test() {
            this.jtonnyRequest.client.seq = this.userInfo.seq;
            this.jtonnyRequest.client.nickName = this.userInfo.nickName;
            this.jtonnyRequest.estimateTime = `${this.jtonnyRequest.estimateHour}:${this.jtonnyRequest.estimateMinute}`;

            console.log("jtonnyRequest", this.jtonnyRequest);

            this.$store.commit("SET_JTONNY_REQUEST", this.jtonnyRequest);
            this.$router.push({ name: "JTonnyWaitingPage" });
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
};
</script>

<style lang="scss" scoped>
@import "@/scss/input.scss";
</style>
