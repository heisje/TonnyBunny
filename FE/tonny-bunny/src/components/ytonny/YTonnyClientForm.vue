<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <div class="situation mt-5">
                <title-text
                    important
                    type="h2"
                    title="제목"
                    text="헬퍼에게 어떤 상황인지 제목으로 알려볼까요?"
                    bottom="20" />

                <input type="text" placeholder="제목을 입력해주세요" v-model="title" />

                <title-text
                    type="h2"
                    title="[선택] 상황 카테고리"
                    text="어떤 상황의 통역을 원하시나요?"
                    top="70"
                    bottom="20" />

                <DropdownInputCode
                    class="w120"
                    :dropdownArray="tonnySituCodeList"
                    placeholder="상황 선택"
                    @toggle="(e) => (tonnySituCode = e)" />

                <title-text
                    type="h2"
                    title="[선택] 상황 설명"
                    text="통역이 필요한 상황을 헬퍼에게 설명해볼까요?"
                    top="70"
                    bottom="20" />

                <textarea
                    type="textarea"
                    placeholder="내용을 입력해주세요"
                    rows="7"
                    v-model="content" />
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
                    <dropdown-input-code
                        class="w-100"
                        ref="startLangCode"
                        :disable="changLangCount % 2 ? false : true"
                        :dropdownArray="langCodeList"
                        placeholder="한국어"
                        @toggle="(e) => (startLangCode = e)" />
                </div>

                <div class="swap" @click="changLangCode">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>

                <div class="w-100">
                    <DropdownInputCode
                        ref="endLangCode"
                        :disable="changLangCount % 2 ? true : false"
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
                </div>
                <div class="col-6 d-flex flex-row">
                    <div class="w-100">
                        <DropdownInput
                            class="w-100"
                            :dropdownArray="minuteCodeList"
                            placeholder="분"
                            @toggle="(e) => (estimateMinute = e)" />
                    </div>
                </div>
            </div>

            <title-text
                important
                type="h2"
                title="의뢰 지불 캐럿"
                text="현재 보유하신 캐럿까지만 설정하실 수 있습니다."
                top="70"
                bottom="20" />

            <div class="d-flex mb-5">
                <div class="col-12">
                    <input type="text" placeholder="ex) 1000" v-model="estimatePrice" />
                </div>
                <!-- <div class="backlabel col-2">
					<h5>CRT/5분</h5>
				</div> -->
            </div>

            <agree-input class="" @toggle="(e) => (agreeValue = e)" style="margin-top: 120px" />
            <medium-btn
                text="예약하기"
                class="w-100"
                color="carrot"
                font="white"
                @click.prevent="insertYTonny" />
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
    name: "YTonnyClientForm",

    components: {
        TitleText,
        DropdownInput,
        MediumBtn,
        AgreeInput,
        DropdownInputCode,
    },

    data() {
        return {
            changLangCount: 0,

            agreeValue: false,

            // ytonny Form
            title: "",
            tonnySituCode: "0040000",
            content: "",
            startLangCode: "0020001",
            endLangCode: "0020002",
            startDate: "",
            startTime: "",
            estimateHour: "",
            estimateMinute: "",
            estimatePrice: "",
        };
    },

    computed: {
        ...mapGetters({
            langCodeList: "getLangCode",
            tonnySituCodeList: "getTonnySituCode",
            hourCodeList: "getHourCodeList",
            minuteCodeList: "getMinuteCodeList",
            userInfo: "getUserInfo",
            yTonnySeq: "getYTonnySeq",
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

        insertYTonny() {
            console.log("insertYTonny");
            console.log(this.startLangCode);
            console.log(this.endLangCode);
            console.log(this.startDate);
            console.log(this.startTime);
            console.log(this.estimateHour);
            console.log(this.estimateMinute);
            console.log(this.estimatePrice);
            console.log(this.tonnySituCode);
            console.log(this.title);
            console.log(this.content);

            let payload = {
                clientSeq: this.userInfo.seq,
                title: this.title,
                tonnySituCode: this.tonnySituCode,
                content: this.content,
                startLangCode: this.startLangCode,
                endLangCode: this.endLangCode,
                startDateTime: `${this.startDate}T${this.startTime}`,
                estimateTime: `${this.estimateHour}:${this.estimateMinute}`,
                estimatePrice: this.estimatePrice,
            };

            console.log(payload);

            if (this.startLangCode == "" || this.endLangCode == "") {
                alert("언어를 선택해주세요");
            } else if (this.startDate == "" || this.startTime == "") {
                alert("날짜를 선택해주세요");
            } else if (this.estimateHour == "" || this.estimateMinute == "") {
                alert("시간을 선택해주세요");
            } else if (this.title == "") {
                alert("제목을 선택해주세요");
            } else if (this.estimatePrice == "") {
                alert("지불 금액을 설정해주세요");
            } else {
                this.$store.dispatch("insertYTonny", payload).then((id) => {
                    if (id != -1) {
                        this.$store.commit("TOGGLE_ALARM_MODAL");
                        this.$router.push({
                            name: "YTonnyDetailPage",
                            params: { id: this.yTonnySeq },
                        });
                    }
                });
            }
        },

        changLangCode() {
            const temp = this.startLangCode;
            this.startLangCode = this.endLangCode;
            this.endLangCode = temp;
            this.$refs.startLangCode.changeEventParent(this.startLangCode);
            this.$refs.endLangCode.changeEventParent(this.endLangCode);
            this.changLangCount += 1;
        },
    },

    created() {
        window.scrollTo(0, 0);
        console.log("userInfo: ", this.userInfo);
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";

.mb {
    margin-bottom: 60px;
}

.situation {
    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--thin-color);
    // background-color: var(--background-color);
    border-radius: 6px;
    padding-bottom: 32px;
}
</style>
