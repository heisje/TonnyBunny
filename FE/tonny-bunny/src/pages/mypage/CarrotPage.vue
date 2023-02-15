<template>
    <div class="carrotContainer">
        <div class="carrotWrap">
            <title-banner
                title="포인트 관리"
                text="🐰캐럿은 토니바니의 포인트입니다. 미리 충전한 만큼 이용하실 수 있어요!"
            />

            <div class="moneyWrap">
                <small-btn class="moneyContent" text="충전" @click="charge" />
                <small-btn class="moneyContent" text="환전" @click="refund" />
                <small-btn
                    class="moneyContent"
                    color="disable"
                    text="결제수단관리"
                    @click="payment"
                />
            </div>

            <title-text
                title="포인트 거래 내역"
                text="캐럿은 토니바니의 포인트제도입니다. 미리 포인트를 충전한 만큼
		이용하실 수 있어요!"
            />

            <div class="pointListWrap">
                <div v-for="pointItem in pointList" :key="pointItem" class="pointItemWrap">
                    <div class="pointItemContent">
                        <h3>
                            {{ pointItem?.pointRequestCodeTrans }}
                        </h3>
                    </div>
                    <hr />
                    <div class="pointItemContent">
                        {{ pointItem?.amount }}
                    </div>
                    <div class="pointItemContent">
                        {{ pointItem?.createdAt }}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";

export default {
    name: "CarrotPage",

    components: {
        TitleText,
        TitleBanner,
        SmallBtn,
    },

    computed: {
        ...mapGetters({ pointList: "getPointList" }),
    },

    methods: {
        //포인트 변동(충전)
        charge() {
            const payload = {
                bankAccount: "string",
                fromUserSeq: this.$store.state.account.userInfo.seq,
                pointAmount: 100,
                pointRequestType: "충전",
                toUserSeq: 0,
            };
            this.$store.dispatch("putPoints", payload);
        },
        //포인트 변동(출금)
        refund() {
            const payload = {
                bankAccount: "string",
                fromUserSeq: this.$store.state.account.userInfo.seq,
                pointAmount: 100,
                pointRequestType: "출금",
                toUserSeq: 0,
            };
            this.$store.dispatch("putPoints", payload);
        },
        payment() {},
        getHistory() {
            const payload = {
                logCount: 1000,
                userSeq: this.$store.state.account.userInfo.seq,
            };
            this.$store.dispatch("postPointLog", payload);
        },
    },

    mounted() {
        this.getHistory();
    },
};
</script>

<style lang="scss" scoped>
.carrotContainer {
    .carrotWrap {
        .moneyWrap {
            .moneyContent {
                margin-right: 1rem;
            }
        }
        .pointListWrap {
            .pointItemWrap {
                background: var(--thin-color);
                margin: 1rem 0rem;
                padding: 1rem 1.5rem;
                border-radius: 1rem;
            }
        }
    }
}
</style>
