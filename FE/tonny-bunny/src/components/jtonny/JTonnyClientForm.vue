<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text important type="h2" title="어떤 언어통역이 필요하신가요?" />

            <div class="d-flex flex-row">
                <div class="">
                    <label for="">내 언어</label>
                    <DropdownInputCode
                        :dropdownArray="langCode"
                        placeholder="내 언어"
                        @toggle="(e) => (jtonnyRequest.startLangCode = e)" />
                </div>
                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>
                <div class="">
                    <label for="">필요 언어</label>
                    <DropdownInputCode
                        :dropdownArray="langCode"
                        placeholder="필요 언어"
                        @toggle="(e) => (jtonnyRequest.endLangCode = e)" />
                </div>
            </div>

            <title-text
                important
                type="h2"
                title="예상 소요 시간"
                text="해당 상황이 마무리될 때까지 대략 몇 분 정도 걸릴 것 같나요?" />

            <div class="d-flex">
                <div class="w120">
                    <input
                        type="number"
                        :name="input1.id"
                        :id="input1.id"
                        :pattern="input1.pattern"
                        v-model="jtonnyRequest.estimateTime"
                        @input="changeInput"
                        placeholder="ex)30" />
                </div>
                <div class="backlabel">
                    <h3>분</h3>
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
import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInputCode from "../common/input/DropdownInputCode.vue";
import TitleText from "../common/TitleText.vue";
import { mapGetters } from "vuex";

export default {
    name: "JTonnyClientForm",
    components: { TitleText, DropdownInputCode, MediumBtn, AgreeInput },
    data() {
        return {
            dropdownValue: "",
            input1: {
                id: "input1",
                value: "",
                pattern: "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", // 유효성검사 조건(HTML 용)
                validate: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, // 유효성검사 조건(JS 용)
                notice: "", // 유효성검사 결과 텍스트
            },
            agreeValue: false,
            jtonnyRequest: {
                clientSeq: 0,
                startLangCode: "",
                endLangCode: "",
                tonnySituCode: "",
                content: "",
                estimateTime: 0,
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
            this.jtonnyRequest.clientSeq = this.userInfo.seq;
            console.log("jtonnyRequest", this.jtonnyRequest);
            this.$store.commit("SET_JTONNY_REQUEST", this.jtonnyRequest);

            this.$router.push({ name: "JTonnyWaitingPage" });
        },
    },
    computed: {
        ...mapGetters({
            langCode: "getLangCode",
            tonnySituCode: "getTonnySituCode",
            userInfo: "getUserInfo",
        }),
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/input.scss";

.customFormWrap {
    // width: 500px;
}
</style>
