<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner
                title="🐰 번역 의뢰 지원자 목록"
                text="지원자들의 프로필 관람, 상담 신청이 가능해요"
            />
            <TitleText
                type="h2"
                top="0"
                bottom="0"
                :title="`가격을 제안한 헬퍼들(${getBunnyDetail.bunnyApplyList.length})`"
            ></TitleText>
            <br />
            <div v-for="(apply, index) in getBunnyDetail.bunnyApplyList" :key="index" class="w-100">
                {{ Apply }}
                <helper-card
                    class="w-100 mb-3"
                    :userInfo="apply"
                    :fareText="apply.estimatePrice"
                    rightBtnText="상담하기"
                    @remove-card="close"
                    @toggle-like-btn="toggleLike"
                    @click-btn1="goHelperProfile"
                    @click-btn2="enterChatRoom"
                ></helper-card>
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
    methods: {
        goHelperProfile() {
            // TODO 헬퍼 프로필 페이지로 이동하기
        },

        enterChatRoom(helperSeq) {
            const clientSeq = this.getBunnyDetail.client.seq;
            const userSeq = this.$store.getters.getUserInfo.seq;
            const anotherUserSeq = userSeq == clientSeq ? helperSeq : clientSeq;

            this.$store.dispatch("getChatRoomInfo", {
                userSeq: userSeq,
                anotherUserSeq: anotherUserSeq,
            });
            if (userSeq == clientSeq) {
                this.$store.commit("SET_TRUE_SEND_URL_MESSAGE");
                this.$store.commit("SET_URL_MESSAGE", {
                    // 어쩔 수 없다. "상담하기" 버튼을 누르면 무조건 메세지가 간다!
                    // 공고가 아니라 유저 단위로 채팅방이 만들어지고 유지되기 때문에, 해당 공고에서 처음 접속한 시점을 잡기가 어렵..다?
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
