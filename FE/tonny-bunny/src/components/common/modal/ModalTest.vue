<template>
	<div>
		<h1>Modal Test</h1>

		{{ isOpen }}
		<br />
		<medium-btn text="modal test" @click="openModal"></medium-btn>

		<!-- <alarm-modal v-show="isOpen" :isOpen="isOpen"></alarm-modal> -->
		<alarm-modal
			:isOpen="isOpen"
			title="경고"
			type="danger"
			btnText1="아니오"
			btnText2="예"
			btnColor1="main"
			btnColor2="carrot"
			btnFontColor1="white"
			btnFontColor2="white"
			@close-modal="closeModal">
			<template #content>
				안녕하세요
				<br />
				안녕하십니까 안녕하세요 안녕하세요 안녕하세요 안녕하세요
			</template>
		</alarm-modal>
	</div>
</template>

<script>
import MediumBtn from "../button/MediumBtn.vue";
import AlarmModal from "./AlarmModal.vue";

export default {
	name: "ModalTest",

	components: {
		MediumBtn,
		AlarmModal
	},

	props: {},

	data() {
		return {
			isOpen: false
		};
	},

	methods: {
		openModal(e) {
			e.preventDefault();
			this.isOpen = true;
		},

		closeModal() {
			this.isOpen = false;
		}
	}

	// setup() {
	//     // data 호출
	//     const state = reactive({
	//         isModalOpen: false,
	//     });

	//     // method 호출
	//     const changeIsModalOpen = (e) => {
	//         e.preventDefault();
	//         console.log("hihi");
	//         console.log(state.isModalOpen);
	//         // state.isModalOpen = !state.isModalOpen;
	//     };

	//     return { state, changeIsModalOpen };
	// },
};
</script>

<style></style>
