<template>
    <div>
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text important type="h2" title="어떤 언어통역이 필요하신가요?" />

            <div class="d-flex flex-row">
                <div class="">
                    <label for="">내 언어</label>
                    <DropdownInput
                        :dropdownArray="['아이템1', '아이템2', '아이템3']"
                        placeholder="내 언어"
                        @toggle="(e) => (dropdownValue = e)" />
                </div>
                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>
                <div class="">
                    <label for="">필요 언어</label>
                    <DropdownInput
                        :dropdownArray="['아이템1', '아이템2', '아이템3']"
                        placeholder="필요 언어"
                        @toggle="(e) => (dropdownValue = e)" />
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

            <DropdownInput
                class="w120"
                :dropdownArray="['아이템1', '아이템2', '아이템3']"
                placeholder="상황 선택"
                @toggle="(e) => (dropdownValue = e)" />

            <title-text type="h2" title="[선택] 상황 설명" text="어떤 상황인가요?" />

            <textarea type="textarea" placeholder="내용을 입력해주세요" value="" />

            <agree-input @toggle="(e) => (agreeValue = e)" />
            <router-link :to="{ name: 'JTonnyWaitingPage' }">
                <medium-btn style="width: 100%" text="즉시찾기" color="carrot" />
            </router-link>
        </form>
    </div>
</template>

<script>
import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInput from "../common/input/DropdownInput.vue";
import TitleText from "../common/TitleText.vue";

export default {
    name: "JTonnyClientForm",
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

<style lang="scss" scoped>
@import "@/scss/input.scss";
</style>
