<template>
	<div class="d-flex justify-content-center customFormWrap w-100">
		<form class="customForm" @submit.prevent="submitForm(event)">
			<title-text
				important
				type="h2"
				title="언어 선택"
				text="어떤 언어를 통역하실건가요?"
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

			<title-text
				important
				type="h2"
				title="날짜 선택"
				text="언제 통역을 예약하실건가요?"
				top="70"
				bottom="20" />

			<div class="w120">
				<input type="date" class="w-100" v-model="startDate" />
				<!-- <input
                    type="date"
                    class="w-100 mb-5"
                    :pattern="input1.pattern"
                    @input="changeInput"
                    placeholder="ex)30" /> -->
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
				<!-- <input
                    type="time"
                    class="w-100 mb-5"
                    :pattern="input1.pattern"
                    @input="changeInput"
                    placeholder="ex) 30" /> -->
			</div>

			<title-text
				important
				class="w-100"
				type="h2"
				title="예상 소요 시간"
				text="해당 상황이 마무리될 때까지 대략 몇 분 정도 걸릴 것 같나요?"
				bottom="20" />

			<div class="d-flex">
				<div class="col-6 d-flex flex-row">
					<div class="w-100">
						<DropdownInput
							class="w-100"
							:dropdownArray="hourCodeList"
							placeholder="시간"
							@toggle="(e) => (estimateHour = e)" />
					</div>
					<div class="backlabel w-50">
						<h5>시간</h5>
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
					<div class="backlabel w-50">
						<h5>분</h5>
					</div>
				</div>
			</div>

			<title-text
				important
				type="h2"
				title="제목"
				text="최상단에 노출 될 제목입니다"
				top="70"
				bottom="20" />

			<!-- <input
                type="text"
                :pattern="title.pattern"
                @input="changeInput"
                :value="title.value"
                class="mb-5"
                placeholder="제목을 입력해주세요" /> -->
			<input type="text" class="mb-5" placeholder="제목을 입력해주세요" v-model="title" />

			<title-text
				important
				type="h2"
				title="예약통역 지불 캐럿"
				text="현재 보유하신 캐럿까지만 설정하실 수 있습니다."
				bottom="20" />

			<div class="d-flex mb-5">
				<div class="col-11">
					<input type="text" placeholder="ex) 1000" v-model="estimatePrice" />
				</div>
				<div class="backlabel col-2">
					<h5>P/5분</h5>
				</div>
			</div>

			<title-text
				type="h2"
				title="[선택] 상황 카테고리"
				text="해당 상황을 한 단어로 요약하자면?"
				top="70"
				bottom="20" />

			<DropdownInput
				class="w120"
				:dropdownArray="tonnySituCodeList"
				placeholder="상황 선택"
				@toggle="(e) => (tonnySituCode = e)" />

			<title-text
				type="h2"
				title="[선택] 상황 설명"
				text="어떤 상황인가요?"
				top="70"
				bottom="20" />

			<textarea
				type="textarea"
				placeholder="내용을 입력해주세요"
				class="mb-5"
				v-model="content" />

			<agree-input @toggle="(e) => (agreeValue = e)" />
			<medium-btn text="예약하기" class="w-100" color="main" @click.prevent="insertYTonny" />
		</form>
	</div>
</template>

<script>
import { mapGetters } from "vuex";

import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInput from "../common/input/DropdownInput.vue";
import TitleText from "../common/TitleText.vue";

export default {
	name: "YTonnyClientForm",

	components: {
		TitleText,
		DropdownInput,
		MediumBtn,
		AgreeInput
	},

	data() {
		return {
			input1: {
				id: "input1",
				value: "",
				pattern: "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", // 유효성검사 조건(HTML 용)
				validate: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, // 유효성검사 조건(JS 용)
				notice: "" // 유효성검사 결과 텍스트
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
			estimatePrice: null,
			tonnySituCode: "",
			title: "",
			content: ""
		};
	},

	computed: {
		...mapGetters({
			langCodeList: "getLangCodeList",
			tonnySituCodeList: "getTonnySituCodeList",
			yTonnyForm: "getYTonnyForm",
			hourCodeList: "getHourCodeList",
			minuteCodeList: "getMinuteCodeList"
		})
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
			console.log(this.clientSeq);
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
				clientSeq: 1,

				title: this.title,
				content: this.content,

				startDateTime: `${this.startDate}T${this.startTime}`,
				estimateTime: `${this.estimateHour}:${this.estimateMinute}`,
				estimatePrice: this.estimatePrice,

				startLangCode: this.startLangCode,
				endLangCode: this.endLangCode,

				tonnySituCode: this.tonnySituCode
			};

			console.log(payload);
			this.$store.dispatch("insertYTonny", payload).then((id) => {
				if (id != -1) {
					this.$store.commit("TOGGLE_ALARM_MODAL");
				}
			});
		}
	}
};
</script>

<style lang="scss">
@import "@/scss/input.scss";
</style>
