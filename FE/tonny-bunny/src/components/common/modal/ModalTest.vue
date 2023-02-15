<template>
    <div>
        <h1>Modal Test</h1>

        <br />
        <medium-btn text="modal test" @click="openModal"></medium-btn>

        <!-- <alarm-modal v-show="isOpen" :isOpen="isOpen"></alarm-modal> -->
        <alarm-modal
            title="경고"
            type="danger"
            btnText1="아니오"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @click-btn1="closeModal"
            @click-btn2="checkTest">
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
        AlarmModal,
    },

    props: {},

    methods: {
        openModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        closeModal() {
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        checkTest() {
            this.$router.push("/jtonny/form");
        },
    },
};
</script>

<style></style>
