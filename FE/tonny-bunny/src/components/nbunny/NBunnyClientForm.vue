divdiv
<template lang="">
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <div class="situation mt-5">
                <title-text
                    important
                    type="h2"
                    title="언어 선택"
                    text="어떤 언어를 번역하실건가요?"
                    bottom="20"
                />

                <div class="d-flex flex-row w-100 mb-5">
                    <div class="w-100">
                        <label for="">내 언어</label>
                        <DropdownInput
                            class="w-100"
                            :dropdownArray="getLangCode"
                            placeholder="내 언어"
                            @toggle="(e) => (startLangCode = e)"
                        />
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
                            @toggle="(e) => (endLangCode = e)"
                        />
                    </div>
                </div>

                <title-text
                    important
                    type="h2"
                    title="번역 카테고리"
                    text="어떤 번역을 원하시나요?"
                    top="70"
                    bottom="20"
                />

                <DropdownInput
                    class="w120"
                    :dropdownArray="getBunnySituCode"
                    placeholder="카테고리 선택"
                    @toggle="(e) => (bunnySituCode = e)"
                />

                <title-text
                    important
                    type="h2"
                    title="마감기한"
                    text="해당 번역이 마무리될 때까지 대략 몇 일 정도 걸릴 것 같나요?"
                    top="70"
                    bottom="20"
                />

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
                    title="의뢰 지불 금액 (캐럿)"
                    text="현재 보유하신 캐럿까지만 설정하실 수 있습니다."
                    top="70"
                    bottom="20"
                />

                <div class="d-flex w-100">
                    <div class="col-11">
                        <input type="number" v-model="estimatePrice" placeholder="ex)1000" />
                    </div>
                    <div class="backlabel col-2">
                        <h3>캐럿</h3>
                    </div>
                </div>

                <title-text
                    important
                    type="h2"
                    title="제목"
                    text="최상단에 노출 될 제목입니다"
                    top="70"
                    bottom="20"
                />

                <input type="text" v-model="title" placeholder="제목을 입력해주세요" />

                <title-text
                    type="h2"
                    title="[선택] 내용"
                    text="번역에 대한 내용을 작성해주세요"
                    top="70"
                    bottom="20"
                />

                <textarea
                    type="textarea"
                    placeholder="내용을 입력해주세요"
                    value=""
                    rows="7"
                    @input="changeInput"
                ></textarea>
            </div>

            <!-- <title-text type="h2" title="[선택] 사진" text="작업물의 예시를 올려주세요" /> -->

            <agree-input @toggle="(e) => (agreeValue = e)" style="margin-top: 120px" />
            <medium-btn
                style="width: 100%"
                text="의뢰하기"
                :color="isPossible[0]"
                :font="isPossible[1]"
                @click.prevent="submitForm"
            />
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
            bunnyStateCode: "0090001",

            startDate: "",
            endDate: "",

            estimatePrice: "",
            title: "",
            content: "",

            agreeValue: false,
        };
    },

    computed: {
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ getBunnySituCode: "getBunnySituCode" }),
        isPossible() {
            if (
                this.startLangCode != "" &&
                this.endLangCode != "" &&
                this.bunnySituCode != "" &&
                this.startDate != "" &&
                this.endDate != "" &&
                this.estimatePrice != "" &&
                this.title != "" &&
                this.agreeValue
            ) {
                return ["carrot", "white"];
            } else {
                return [`outline`, "active"];
            }
        },
    },

    methods: {
        changeInput(e) {
            this.content = e.target.value;
        },
        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
        openModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
        submitForm(e) {
            e.preventDefault();

            if (!this.startLangCode) {
                alert("시작 언어가 입력되지 않았어요!");
                return;
            }

            if (!this.endLangCode) {
                alert("종료 언어가 입력되지 않았어요!");
                return;
            }

            if (!this.bunnySituCode) {
                alert("카테고리가 입력되지 않았어요!");
                return;
            }

            if (!this.startDate) {
                alert("시작 날짜가 입력되지 않았어요!");
                return;
            }

            if (!this.endDate) {
                alert("종료 날짜가 입력되지 않았어요!");
                return;
            }

            if (!this.estimatePrice) {
                alert("예상 금액이 입력되지 않았어요!");
                return;
            }

            if (this.estimatePrice > this.$store.state.account.userInfo.point) {
                alert("보유 포인트가 부족해요!");
                return;
            }

            if (!this.title) {
                alert("제목이 입력되지 않았어요!");
                return;
            }

            if (!this.agreeValue) {
                alert("약관에 동의하지 않으셨습니다.");
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

            this.$store.dispatch("insertBunny", payload);
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";

.situation {
    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--thin-color);
    // background-color: var(--background-color);
    border-radius: 6px;
    padding-bottom: 32px;
}
</style>
