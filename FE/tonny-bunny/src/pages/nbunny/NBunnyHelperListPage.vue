<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner
                title="🐰 번역 의뢰 지원자 목록"
                text="지원자들의 프로필 관람, 상담 신청이 가능해요" />
            <TitleText
                type="h2"
                top="0"
                bottom="0"
                :title="`가격을 제안한 헬퍼들(${getBunnyDetail.bunnyApplyList.length})`"></TitleText>
            <br />
            <div v-for="(apply, index) in getBunnyDetail.bunnyApplyList" :key="index" class="w-100">
                {{ Apply }}
                <helper-card
                    class="w-100 m-0 mb-3"
                    nickName="아스파라거스"
                    oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
                    :fareText="apply.estimatePrice"
                    starText="4.0"
                    countText="100"
                    likeText="10"
                    isLikeEmpty
                    rightBtnText="상담하기"
                    @remove-card="close"
                    @toggle-like-btn="toggleLike"
                    @click-btn1="check1"
                    @click-btn2="enterChatRoom"></helper-card>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import { mapGetters } from "vuex";

export default {
    components: {
        TitleText,
        TitleBanner,
        HelperCard,
    },

    computed: {
        ...mapGetters({ getBunnyDetail: "getBunnyDetail" }),
    },
    method: {
        enterChatRoom(helperSeq) {
            const clientSeq = this.getBunnyDetail.client.seq;
            const userSeq = this.$store.getters.getUserInfo.seq;
            const anotherUserSeq = userSeq == clientSeq ? helperSeq : clientSeq;
            this.$store.dispatch("getChatRoomInfo", {
                userSeq: userSeq,
                anotherUserSeq: anotherUserSeq,
            });
            this.$store.commit("SET_TRUE_SEND_URL_MESSAGE");
            if (userSeq == clientSeq) {
                this.$store.commit("SET_URL_MESSAGE", {
                    // 어쩔 수 없다. "상담하기" 버튼을 누르면 무조건 메세지가 간다!
                    urlPage: "NBunnyDetailPage",
                    urlPageSeq: this.getBunnyDetail.seq,
                    message:
                        "'" +
                        this.getBunnyDetail.title +
                        "' 을/를 통해서 온 고객입니다. 상담을 요청합니다!",
                });
            }
            this.$router.push({ name: "ChatDetailPage" });
        },
    },
};
</script>

<style></style>
