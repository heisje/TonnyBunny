<template lang="">
    <div class="helperProfileContainer">
        <div class="helperProfileWrap">
            <div>
                <!-- <TitleText title="헬퍼프로필" type="h2" /> -->
                <helper-Card disable :userInfo="userInfo" />
                <TitleText title="어학능력" type="h2" />

                <div v-if="userInfo?.helperInfo?.possibleLanguageList">
                    <span
                        v-for="possibleLanguageItem in userInfo?.helperInfo?.possibleLanguageList"
                        :key="possibleLanguageItem">
                        <circle-tag :text="allCode[possibleLanguageItem?.value]" />
                    </span>
                </div>
                <div v-else>없음</div>

                <TitleText title="자격증" type="h2" />
                <div v-if="userInfo?.helperInfo?.certificateList">
                    <div
                        v-for="certificateItem in userInfo?.helperInfo?.certificateList"
                        :key="certificateItem">
                        <div class="mb-2">
                            <SquareTag sub :text="allCode[certificateItem?.langCode]" />
                            {{ certificateItem?.certName }}
                            {{ certificateItem?.content }}
                        </div>
                    </div>
                </div>
                <div v-else>없음</div>

                <TitleText title="헬퍼 소개" type="h2" />
                <div>
                    <div class="oneLine">
                        {{ userInfo?.helperInfo?.oneLineIntroduction }}
                    </div>
                    <div>
                        {{ userInfo?.helperInfo?.introduction }}
                    </div>
                </div>

                <div class="boardCommentContainer">
                    <TitleText :title="`리뷰(${userReview?.length})`" top="24" type="h2" />
                    <hr />
                    <div class="boardCommentWrap">
                        <div
                            class="boardCommentContent"
                            v-for="userReviewItem in userReview"
                            :key="userReviewItem">
                            <div>
                                <user-profile-img
                                    :profileImagePath="userReviewItem?.user?.profileImagePath"
                                    width="40" />

                                <h3>{{ userReviewItem?.nickName }}</h3>
                                <span>{{ userReviewItem?.createdAt }}</span>
                            </div>

                            <div class="commentContent">
                                {{ userReviewItem?.comment }}
                            </div>

                            <hr />
                        </div>
                    </div>
                </div>
                <div v-if="userInfo?.seq != myInfo.seq">
                    <LargeBtn text="채팅하기" color="carrot" />
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";
import TitleText from "./TitleText.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import CircleTag from "@/components/common/tag/CircleTag.vue";
import SquareTag from "@/components/common/tag/SquareTag.vue";
import UserProfileImg from "./UserProfileImg.vue";

export default {
    components: {
        HelperCard,
        TitleText,
        CircleTag,
        LargeBtn,
        SquareTag,
        UserProfileImg,
    },
    props: {
        userInfo: {
            type: Object,
        },
    },
    methods: {
        goProfileUpdate(event) {
            event.preventDefault();
            this.$router.push({
                name: "ProfileUpdatePage",
                params: { userSeq: this.userInfo.seq },
            });
        },
    },

    computed: {
        ...mapGetters({ allCode: "getAllCode" }),
        ...mapGetters({ userReview: "getUserReview" }),
        ...mapGetters({ myInfo: "getUserInfo" }),
    },

    mounted() {
        this.$store.dispatch("getUserReview", this.userInfo.seq);
    },
};
</script>
<style lang="scss" scoped>
.helperProfileContainer {
    .helperProfileWrap {
        margin: auto;
        max-width: 400px;
        .oneLine {
            font-weight: 600;
        }
    }
}

.boardCommentContainer {
    width: 100%;
    margin-top: 32px;
    padding: 4px 24px;
    border-radius: 16px;
    border: 1px solid var(--thin-color);
    background-color: var(--thin-color);
    .boardCommentWrap {
        margin-top: 1rem;
        .boardCommentContent {
            margin-bottom: 1rem;
            span {
                margin-left: 1rem;
            }

            img {
                width: 2.5rem;
                height: 2.5rem;
                margin-right: 1rem;
            }

            .commentContent {
                padding: 0.4rem 0rem 1rem 0rem;
            }

            hr {
                border-top: 1px solid var(--disable-color);
                margin: 0;
            }
        }
    }
}
</style>
