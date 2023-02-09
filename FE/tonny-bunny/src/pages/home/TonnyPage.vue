<template>
    <v-container class="tonnyContainer">
        <div class="tonnyWrap">
            <div class="tonnyPage row">
                <div class="tonnyCreate col-md-2 col-12">
                    <div class="d-flex flex-column w-100 px-2">
                        <div class="d-flex">
                            <span class="material-symbols-outlined fs-5 mb-3"> add </span>
                            <div>신청 하기</div>
                        </div>
                        <!-- <title-text title="통역 신청" top="0"></title-text> -->
                        <router-link :to="{ name: 'JTonnyFormPage' }">
                            <medium-btn
                                text="즉시통역"
                                class="w-100"
                                color="outline"
                                font="active"></medium-btn>
                        </router-link>
                        <router-link :to="{ name: 'JTonnyApplyPage' }">
                            <medium-btn
                                text="즉시통역 받기"
                                class="w-100"
                                color="outline"
                                font="active"></medium-btn>
                        </router-link>
                        <router-link :to="{ name: 'YTonnyFormPage' }">
                            <medium-btn
                                text="예약통역"
                                class="w-100"
                                color="outline"
                                font="active"></medium-btn>
                        </router-link>
                    </div>
                </div>

                <div class="col-md-4 col-12">
                    <div>
                        <title-text title="오늘 일정" top="0"></title-text>
                        <div class="">오늘 일정</div>
                    </div>
                    <div>
                        <title-text title="히스토리"></title-text>
                        <div class="">히스토리</div>
                    </div>
                </div>

                <div class="col-md-6 col-12">
                    <title-text title="모집중인 예약 통역 목록" top="0"></title-text>
                    <div class="">
                        예약통역 리스트
                        <div v-for="(yTonny, index) in yTonnyList" :key="index">
                            <!-- {{ yTonny }} -->
                            <router-link
                                class="d-flex"
                                :to="{
                                    name: 'YTonnyDetailPage',
                                    params: { id: yTonny.seq, userSeq: this.userInfo.seq },
                                }">
                                <div>{{ yTonny.title }}</div>
                                <div>{{ yTonny.content }}</div>
                                <div>{{ yTonny.startDateTime }}</div>
                                <div>{{ yTonny.estimateTime }}</div>
                                <div>{{ yTonny.estimatePrice }}</div>
                                <div>{{ yTonny.createdAt }}</div>
                            </router-link>
                        </div>
                    </div>
                </div>

                <!-- <div>내가 한 예약통역</div>
				<div>예약통역 리스트</div>

				<div>히스토리</div>
				<div>찜한 헬퍼 리스트 조회</div>
				<div>오늘 일정</div> -->
            </div>
        </div>
    </v-container>
</template>

<script>
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";
import TitleText from "@/components/common/TitleText.vue";

export default {
    name: "TonnyPage",

    components: { MediumBtn, TitleText },

    computed: {
        ...mapGetters({
            yTonnyList: "getYTonnyList",
            userInfo: "getUserInfo",
            isHelper: "getIsHelper",
        }),
    },

    created() {
        console.log("userInfo: ", this.userInfo);
        console.log("isHelper: ", this.isHelper);
        // this.$store.dispatch("getYTonnyList");
    },
};
</script>

<style lang="scss" scoped>
.tonnyContainer {
    margin-top: 24px;
}
.tonnyPage {
    display: flex;
}

.tonnyCreate {
    display: flex;
    height: 220px;
    // background-color: var(--thin-color);
    padding: 0 10px 0 0;

    a {
        margin: 0 10px 10px 0px;
    }
}

.contentWrap {
    padding: 24px 24px;

    border: 1px solid rgba(0, 0, 0, 0.13);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    border-radius: 6px;
}
</style>
