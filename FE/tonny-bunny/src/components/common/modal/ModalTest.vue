<template>
    <div>
        <h1>Modal Test</h1>

        {{ isModalOpen }}
        <br />
        <medium-btn text="modal test" @click="changeIsModalOpen"></medium-btn>
        <!-- <alarm-modal v-show="isModalOpen"></alarm-modal> -->
        <alarm-modal
            v-show="isOpen"
            :isOpen="isOpen"
            title="경고"
            type="danger"
            btnText1="안녕"
            btnText2="안녕2"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="active">
            <template #content>
                안녕하세요
                <br />
                안녕하십니까
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
        AlarmModal,
    },

    props: {},

    data() {
        return {
            isOpen: false,
        };
    },

    methods: {
        setIsOpen(e) {
            e.preventDefault();
            this.isOpen = !this.isOpen;
        },
    },

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
