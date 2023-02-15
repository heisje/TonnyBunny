<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner title="🐰 동시통역 종료!" text="통역에 대한 리뷰를 남겨주세요" />

            <div v-show="isClient">
                <title-text :title="getMessage(startResData.helper.nickName)" />
                <br />
                <div class="row completeCard">
                    <div class="col-4">
                        <h2>소모시간</h2>
                    </div>
                    <div class="col-8">
                        <h3>{{ timeToHHMMSS }} 초</h3>
                    </div>
                    <div class="mt-2"><hr /></div>
                    <div class="col-4">
                        <h2>결제금액</h2>
                    </div>
                    <div class="col-8">
                        <h3>{{ completeData.totalPrice }} CRT</h3>
                    </div>
                </div>
                <br />
                <medium-btn
                    class="w-100"
                    color="carrot"
                    font="white"
                    text="리뷰 쓰기"
                    @click.prevent="goReviewPage"
                ></medium-btn>
            </div>
            <div v-show="isHelper">
                <title-text :title="getMessage(startResData.client.nickName)" />
                <br />
                <div class="row completeCard">
                    <div class="col-4">
                        <h2>소모시간</h2>
                    </div>
                    <div class="col-8">
                        <h3>{{ timeToHHMMSS }} 초</h3>
                    </div>
                    <div class="mt-2"><hr /></div>
                    <div class="col-4">
                        <h2>결제금액</h2>
                    </div>
                    <div class="col-8">
                        <h3>{{ completeData.totalPrice }} CRT</h3>
                    </div>
                </div>
            </div>
            <br /><br />
            <div>
                <TonnyResult />
            </div>
        </div>
    </div>
</template>

<script>
import TonnyResult from "@/components/live/TonnyResult.vue";
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";

export default {
    name: "LiveClosePage",

    components: {
        TonnyResult,
        TitleText,
        TitleBanner,
        MediumBtn,
    },

    computed: {
        ...mapGetters({ startResData: "getStartResData" }),
        ...mapGetters({ completeData: "getCompleteData" }),
        timeToHHMMSS() {
            var myNum = this.completeData.totalTime;
            var hours = Math.floor(myNum / 3600);
            var minutes = Math.floor((myNum - hours * 3600) / 60);
            var seconds = myNum - hours * 3600 - minutes * 60;

            if (hours < 10) {
                hours = "0" + hours;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }
            return hours + ":" + minutes + ":" + seconds;
        },
    },

    data() {
        return {
            isClient: false,
            isHelper: false,
        };
    },

    methods: {
        getMessage(nickname) {
            return `${nickname} 님과의 통역이 종료되었습니다!`;
        },

        goReviewPage() {
            this.$router.push({
                name: "ReviewPage",
                params: {
                    helperSeq: this.startResData.helper.seq,
                    historySeq: this.$store.state.live.historySeq,
                },
            });
        },
    },

    created() {
        this.$store.state.account.userInfo.seq == this.startResData.client.seq
            ? (this.isClient = true)
            : (this.isClient = false);
        this.$store.state.account.userInfo.seq == this.startResData.helper.seq
            ? (this.isHelper = true)
            : (this.isHelper = false);
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";
.completeCard {
    cursor: default;
    padding: 32px 24px;
    margin: 0px;
    width: 100%;

    // box-shadow: 1px 1px 1px black;
    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;
}
</style>
