<template lang="">
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text important type="h2" title="제안 금액" />

            <div class="d-flex w-100">
                <div class="col-11">
                    <input type="number" v-model="estimatePrice" placeholder="ex)1000" />
                </div>
                <div class="backlabel col-2">
                    <h3>캐럿</h3>
                </div>
            </div>
            <br /><br /><br /><br /><br /><br /><br />
            <div>
                <div class="d-flex w-100">
                    <div class="col-2">
                        <medium-btn
                            style="width: 100%"
                            text="취소"
                            color="main"
                            @click.prevent="clickCancle"
                        />
                    </div>
                    <div class="backlabel col-10">
                        <medium-btn
                            style="width: 100%"
                            text="고객에게 제안하기"
                            :color="isPossible"
                            @click.prevent="submitForm"
                        />
                    </div>
                </div>
            </div>
        </form>
    </div>
</template>
<script>
import TitleText from "@/components/common/TitleText.vue";
import MediumBtn from "../common/button/MediumBtn.vue";
import { mapGetters } from "vuex";

export default {
    components: {
        TitleText,
        MediumBtn,
    },

    computed: {
        ...mapGetters({ getBunnyDetail: "getBunnyDetail" }),
        isPossible() {
            if (this.estimatePrice != "") {
                return "carrot";
            } else {
                return `main`;
            }
        },
    },

    data() {
        return {
            estimatePrice: "",
        };
    },

    methods: {
        clickCancle() {
            this.$router.go(-1);
        },

        submitForm() {
            if (this.estimatePrice === "") {
                alert("금액을 입력해주세요!");
                return;
            }

            const payload = {
                userSeq: this.$store.state.account.userInfo.seq,
                bunnySeq: this.getBunnyDetail.seq,
                estimatePrice: this.estimatePrice,
            };

            console.log("bunny helper payload", payload);

            this.$store.dispatch("insertBunnyApply", payload);
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },
    },
};
</script>
<style lang=""></style>
