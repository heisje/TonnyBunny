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
                text="어떤 언어를 통/번역 하실건가요?"
                bottom="20" />

            <div class="d-flex flex-row w-100 mb-5">
                <div class="w-100">
                    <DropdownInput
                        class="w-100"
                        :dropdownArray="langCodeList"
                        placeholder="내 언어"
                        @toggle="(e) => (startLangCode = e)" />
                </div>

                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>

                <div class="w-100">
                    <DropdownInput
                        :dropdownArray="langCodeList"
                        placeholder="필요 언어"
                        @toggle="(e) => (endLangCode = e)" />
                </div>
            </div>

            <title-text important type="h2" title="카테고리" class="mb-3" />

            <DropdownInput
                class="w120"
                :dropdownArray="['인쇄물', '아이템2', '아이템3']"
                placeholder="카테고리 선택"
                @toggle="(e) => (dropdownValue = e)" />

            <title-text
                important
                type="h2"
                title="마감 기한을 설정해주세요"
                text="번역이 마감되어야할 날짜를 선택해주세요"
                class="mb-3" />

            <div class="w120">
                <input
                    type="date"
                    :name="input1.id"
                    :id="input1.id"
                    :pattern="input1.pattern"
                    @input="changeInput"
                    placeholder="시작일 마감일 선택" />
            </div>

            <title-text
                important
                type="h2"
                title="번역의 금액을 설정해주세요"
                text="번역 시 받으실 금액을 설정해주세요"
                class="mb-3" />

            <div class="d-flex">
                <div class="w-100">
                    <input
                        type="text"
                        :name="input1.id"
                        :id="input1.id"
                        :pattern="input1.pattern"
                        @input="changeInput"
                        class="w-100"
                        placeholder="ex)1000" />
                </div>
                <div class="backlabel">
                    <h3>CRT</h3>
                </div>
            </div>

            <title-text type="h2" title="제목" text="최상단에 노출 될 제목입니다" />

            <input
                type="number"
                :name="input1.id"
                :id="input1.id"
                :pattern="input1.pattern"
                @input="changeInput"
                placeholder="제목을 입력해주세요" />

            <title-text important type="h2" title="내용" text="번역에 대한 내용을 작성해주세요" />

            <textarea type="textarea" placeholder="내용을 입력해주세요" value="" />

            <title-text type="h2" title="[선택] 사진" text="추가 사진을 올려주세요" />
            <input type="file" />

            <agree-input @toggle="(e) => (agreeValue = e)" />
            <medium-btn
                style="width: 100%"
                text="작성하기"
                color="main"
                @click.prevent="submitForm(event)" />
        </form>
    </div>
</template>
<script>
import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInput from "../common/input/DropdownInput.vue";
import TitleText from "../common/TitleText.vue";
export default {
    name: "BunnyQuotationForm",
    components: { TitleText, DropdownInput, MediumBtn, AgreeInput },
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
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";
</style>
