<template>
    <div class="modalContainer" @click="closeModal">
        <Transition name="bounce" v-show="isScheduleModalOpen">
            <div class="modalWrap">
                <div class="modalCustom">
                    <div><h1>하이루</h1></div>
                </div>
            </div>
        </Transition>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    computed: {
        ...mapGetters({ isScheduleModalOpen: "getIsScheduleModalOpen" }),
    },

    methods: {
        closeModal(e) {
            e.preventDefault();
            this.$store.commit("CLOSE_SCHEDULE_MODAL");
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/modal.scss";
</style>
