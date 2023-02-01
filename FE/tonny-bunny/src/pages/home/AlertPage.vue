<template lang="">
    <div>
        <TitleText title="알림" />
        <div class="alertContainer">
            <div class="alertWrap">
                <!-- <div class="noReadContent">읽지 않은 알림 5개</div> -->
                <div
                    class="alertContent"
                    v-for="alertItem in alertList"
                    :key="alertItem.alertLogSeq">
                    <AlertItem
                        :alertItem="alertItem"
                        @putAlert="putAlert(alertItem.alertLogSeq)"
                        @deleteAlert="deleteAlert(alertItem.alertLogSeq)" />
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import TitleText from "@/components/common/TitleText.vue";
import AlertItem from "@/components/home/AlertItem.vue";
import { mapGetters } from "vuex";

export default {
    name: "AlertPage",
    data() {
        return {};
    },
    components: {
        TitleText,
        AlertItem,
    },

    computed: {
        ...mapGetters({ alertList: "getAlertList" }),
        ...mapGetters({ userInfo: "getUserInfo" }),
    },

    methods: {
        putAlert(alertLogSeq) {
            this.$store.dispatch("putAlert", {
                userSeq: this.userInfo.seq,
                alertLogSeq: alertLogSeq,
            });
        },
        deleteAlert(alertLogSeq) {
            this.$store.dispatch("deleteAlert", {
                userSeq: this.userInfo.seq,
                alertLogSeq: alertLogSeq,
            });
        },
    },

    mounted() {
        this.$store.dispatch("getAlertList", this.userInfo.seq);
    },
};
</script>
<style lang="scss"></style>
