<template lang="">
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text
                important
                type="h2"
                title="고객의 어떤 공고를 해결하시겠어요?"
                text="해결하고 싶은 공고를 선택해주세요"
                class="mb-3" />

            <div class="">
                <label for=""></label>
                <DropdownInput
                    :dropdownArray="['아이템1', '아이템2', '아이템3']"
                    placeholder="공고를 선택하세요"
                    @toggle="(e) => (dropdownValue = e)" />
            </div>

            <title-text
                important
                type="h2"
                title="언어 선택"
                text="어떤 언어를 통역하실건가요?"
                top="70"
                bottom="20" />

            <div class="d-flex flex-row w-100 mb-5">
                <div class="w-100">
                    <DropdownInputCode
                        class="w-100"
                        :dropdownArray="langCodeList"
                        placeholder="내 언어"
                        @toggle="(e) => (startLangCode = e)" />
                </div>

                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>

                <div class="w-100">
                    <DropdownInputCode
                        :dropdownArray="langCodeList"
                        placeholder="필요 언어"
                        @toggle="(e) => (endLangCode = e)" />
                </div>
            </div>

            <title-text
                important
                type="h2"
                title="날짜 선택"
                text="언제 통역을 예약하실건가요?"
                top="70"
                bottom="20" />

            <div class="w120">
                <input type="date" class="w-100" v-model="startDate" />
            </div>

            <title-text
                important
                type="h2"
                title="시간 선택"
                text="언제 통역을 시작하실건가요?"
                top="70"
                bottom="20" />

            <div class="w-100">
                <input type="time" class="w-100" v-model="startTime" />
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
                            @toggle="(e) => (estimateHour = e)" />
                    </div>
                    <!-- <div class="backlabel w-50">
						<h5>시간</h5>
					</div> -->
                </div>
                <div class="col-6 d-flex flex-row">
                    <div class="w-100">
                        <DropdownInput
                            class="w-100"
                            :dropdownArray="minuteCodeList"
                            placeholder="분"
                            @toggle="(e) => (estimateMinute = e)" />
                    </div>
                    <!-- <div class="backlabel w-50">
						<h5>분</h5>
					</div> -->
                </div>
            </div>

            <title-text
                type="h2"
                title="[선택] 상황 카테고리"
                text="해당 상황을 한 단어로 요약하자면?"
                top="70"
                bottom="20" />

            <DropdownInputCode
                class="w120"
                :dropdownArray="tonnySituCodeList"
                placeholder="상황 선택"
                @toggle="(e) => (tonnySituCode = e)" />

            <title-text
                important
                type="h2"
                title="예약통역 지불 캐럿"
                text="현재 보유하신 캐럿까지만 설정하실 수 있습니다."
                top="70"
                bottom="20" />

            <div class="d-flex mb-5">
                <div class="col-11">
                    <input type="text" placeholder="ex) 1000" v-model="unitPrice" />
                </div>
                <div class="backlabel col-2">
                    <h5>CRT/5분</h5>
                </div>
            </div>

            <title-text
                type="h2"
                title="[선택] 사진"
                text="추가 사진을 올려주세요"
                top="70"
                bottom="30" />
            <input type="file" class="quotationFileList mb" multiple />

            <agree-input @toggle="(e) => (agreeValue = e)" />
            <medium-btn
                style="width: 100%"
                text="작성하기"
                color="main"
                @click.prevent="insertYTonnyQuotation(event)" />
        </form>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInput from "../common/input/DropdownInput.vue";
import DropdownInputCode from "../common/input/DropdownInputCode.vue";
import TitleText from "../common/TitleText.vue";

export default {
    name: "YTonnyQuotationForm",

    components: {
        TitleText,
        DropdownInput,
        DropdownInputCode,
        MediumBtn,
        AgreeInput,
    },

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

            // ytonny Form
            clientSeq: null,
            startLangCode: "",
            endLangCode: "",
            startDate: "",
            startTime: "",
            estimateHour: "",
            estimateMinute: "",
            unitPrice: null,
            tonnySituCode: "",
            title: "",
            content: "",
            fileList: [],
        };
    },

    computed: {
        ...mapGetters({
            langCodeList: "getLangCode",
            tonnySituCodeList: "getTonnySituCode",
            yTonnyForm: "getYTonnyForm",
            hourCodeList: "getHourCodeList",
            minuteCodeList: "getMinuteCodeList",
        }),
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

        insertYTonnyQuotation() {
            // form data
            let formData = new FormData();

            formData.append("yTonnySeq", 1);
            formData.append("clientSeq", 1);
            formData.append("helperSeq", 2);
            formData.append("startLangCode", this.startLangCode);
            formData.append("endLangCode", this.endLangCode);
            formData.append("startDateTime", `${this.startDate}T${this.startTime}`);
            formData.append("startTime", `${this.estimateHour}:${this.estimateMinute}`);
            formData.append("unitPrice", this.unitPrice);

            // file upload
            this.fileList = document.querySelector(".quotationFileList").files;

            if (this.fileList.length > 0) {
                const quotationFileList = Array.from(this.fileList);
                quotationFileList.forEach((file) => formData.append("file", file));
            }

            this.$store.dispatch("insertYTonnyQuotation", formData).then((id) => {
                console.log(id);
                // if (id != -1) {
                // 	this.$store.commit("TOGGLE_ALARM_MODAL");
                // }
            });
        },
    },

    created() {
        // this.$store.dispatch("setCommonCode");
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/input.scss";

.mb {
    margin-bottom: 60px;
}
</style>
