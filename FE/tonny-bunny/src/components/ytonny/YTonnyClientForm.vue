<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text important type="h2" title="어떤 언어를 통역하실건가요?" bottom="10" />

            <div class="d-flex flex-row w-100">
                <div class="w-100">
                    <label for="">내 언어</label>
                    <DropdownInput
                        class="w-100"
                        :dropdownArray="['아이템1', '아이템2', '아이템3']"
                        placeholder="내 언어"
                        @toggle="(e) => (dropdownValue = e)" />
                </div>
                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>
                <div class="w-100">
                    <label for="">통역 언어</label>
                    <DropdownInput
                        :dropdownArray="['아이템1', '아이템2', '아이템3']"
                        placeholder="필요 언어"
                        @toggle="(e) => (dropdownValue = e)" />
                </div>
            </div>

            <title-text important type="h2" title="날짜를 선택해주세요" />

            <div class="w120">
                <input
                    type="number"
                    :name="input1.id"
                    :id="input1.id"
                    :pattern="input1.pattern"
                    @input="changeInput"
                    placeholder="ex)30" />
            </div>

            <title-text important type="h2" title="예상 시작 시간" />

            <div class="w120">
                <input
                    type="number"
                    :name="input1.id"
                    :id="input1.id"
                    :pattern="input1.pattern"
                    @input="changeInput"
                    placeholder="ex)30" />
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
                        @input="changeInput"
                        placeholder="ex)30" />
                </div>
                <div class="backlabel">
                    <h3>분</h3>
                </div>
            </div>

            <title-text important type="h2" title="제목" text="최상단에 노출 될 제목입니다" />

            <input
                type="number"
                :name="input1.id"
                :id="input1.id"
                :pattern="input1.pattern"
                @input="changeInput"
                placeholder="제목을 입력해주세요" />

            <title-text
                important
                type="h2"
                title="[선택] 의뢰 해결 시 제공할 금액을 설정해주세요"
                text="현재 보유하신 캐럿까지만 설정하실 수 있습니다." />

            <div class="d-flex">
                <div class="w120">
                    <input
                        type="number"
                        :name="input1.id"
                        :id="input1.id"
                        :pattern="input1.pattern"
                        @input="changeInput"
                        placeholder="ex)1000" />
                </div>
                <div class="backlabel">
                    <h3>캐럿/5분당</h3>
                </div>
            </div>

            <title-text
                type="h2"
                title="[선택] 상황 카테고리"
                text="해당 상황을 한 단어로 요약하자면?" />

            <DropdownInput
                class="w120"
                :dropdownArray="['아이템1', '아이템2', '아이템3']"
                placeholder="상황 선택"
                @toggle="(e) => (dropdownValue = e)" />

            <title-text type="h2" title="[선택] 상황 설명" text="어떤 상황인가요?" />

            <textarea type="textarea" placeholder="내용을 입력해주세요" value="" />

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
import DropdownInput from "../common/input/DropdownInput.vue";
import TitleText from "../common/TitleText.vue";

export default {
    name: "YTonnyClientForm",
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
        submitForm(e) {
            this.$emit("toggleSubmit", e);
        },
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";

.dropdown {
}
</style>
