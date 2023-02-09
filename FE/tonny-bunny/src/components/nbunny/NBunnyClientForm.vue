divdiv
<template lang="">
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text type="h2" title="🐇번역 신청" />
            <title-text important type="h2" title="어떤 언어통역이 필요하신가요?" />
            <div class="d-flex flex-row w-100 mb-5">
                <div class="w-100">
                    <label for="">내 언어</label>
                    <DropdownInput
                        class="w-100"
                        :dropdownArray="getLangCode"
                        placeholder="내 언어"
                        @toggle="(e) => (startLangCode = e)" />
                </div>
                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>
                <div class="w-100">
                    <label for="">필요 언어</label>
                    <DropdownInput
                        class="w-100"
                        :dropdownArray="getLangCode"
                        placeholder="필요 언어"
                        @toggle="(e) => (endLangCode = e)" />
                </div>
            </div>

            <title-text important type="h2" title="카테고리" />

            <DropdownInput
                class="w120"
                :dropdownArray="getBunnySituCode"
                placeholder="카테고리 선택"
                @toggle="(e) => (bunnySituCode = e)" />

            <title-text
                important
                type="h2"
                title="마감 기한을 설정해주세요"
                text="번역이 마감되어야할 날짜를 선택해주세요" />

            <div class="d-flex flex-row w-100 mb-5">
                <div class="w-100">
                    <input type="date" v-model="startDate" />
                </div>
                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>
                <div class="w-100">
                    <input type="date" v-model="endDate" />
                </div>
            </div>

            <title-text
                important
                type="h2"
                title="예상 금액을 설정해주세요"
                text="번역의 예상 금액을 입력해주세요" />

            <div class="d-flex w-100">
                <div class="col-11">
                    <input type="number" v-model="estimatePrice" placeholder="ex)1000" />
                </div>
                <div class="backlabel col-2">
                    <h3>캐럿</h3>
                </div>
            </div>

            <title-text important type="h2" title="제목" text="최상단에 노출 될 제목입니다" />

            <input type="text" v-model="title" placeholder="제목을 입력해주세요" />

            <title-text type="h2" title="내용" text="번역에 대한 내용을 작성해주세요" />

            <textarea
                type="textarea"
                placeholder="내용을 입력해주세요"
                value=""
                @input="changeInput"></textarea>

            <title-text type="h2" title="[선택] 사진" text="작업물의 예시를 올려주세요" />

            <agree-input @toggle="(e) => (agreeValue = e)" />
            <medium-btn
                style="width: 100%"
                text="예약 의뢰 하기"
                color="carrot"
                @click.prevent="submitForm" />
        </form>
    </div>
</template>
<script>
import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInput from "../common/input/DropdownInputCode.vue";
import TitleText from "../common/TitleText.vue";
import { mapGetters } from "vuex";

export default {
    name: "NBunnyClientForm",
    components: { TitleText, DropdownInput, MediumBtn, AgreeInput },
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

    computed: {
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ getBunnySituCode: "getBunnySituCode" }),
    },

    methods: {
        changeInput(e) {
            this.content = e.target.value;
        },
        submitForm(e) {
            e.preventDefault();
            console.log(this.startLangCode, this.getLangCode[this.startLangCode]);
            console.log(this.endLangCode, this.getLangCode[this.endLangCode]);
            console.log(this.bunnySituCode, this.getBunnySituCode[this.bunnySituCode]);
            console.log(this.startDate);
            console.log(this.endDate);
            console.log(this.estimatePrice);
            console.log(this.title);
            console.log(this.content);
            console.log(this.agreeValue);

            if (!this.agreeValue) {
                return;
            }

            const payload = {
                clientSeq: this.$store.state.account.userInfo.seq,
                startLangCode: this.startLangCode,
                endLangCode: this.endLangCode,
                bunnySituCode: this.bunnySituCode,
                startDateTime: `${this.startDate}T00:00:00.000Z`,
                endDateTime: `${this.endDate}T00:00:00.000Z`,
                estimatePrice: this.estimatePrice,
                title: this.title,
                content: this.content,
                bunnyImageList: [],
            };

            console.log(payload);

            this.$store.dispatch("insertBunny", payload);
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";
</style>
