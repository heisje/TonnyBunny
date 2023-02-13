<template>
    <div class="cardContainer">
        <div class="cardWrap">
            <div class="helperCard pt-2">
                <!-- 1. x 버튼 라인 -->
                <div v-show="!removeClose" class="closeBtn" @click="removeCard">
                    <span class="material-symbols-outlined"> close </span>
                </div>

                <!-- 2. 정보 라인 -->
                <div class="helperInfo">
                    <!-- 1.1 프로필 이미지, 닉네임, 한줄 소개 라인 -->
                    <div class="helperProfile">
                        <UserProfileImg
                            class="profileImg"
                            :profileImagePath="userInfo?.profileImagePath" />
                        <div class="likeBtn" @click="toggleLikeBtn">
                            <span
                                v-if="isLikeEmpty"
                                class="material-symbols-outlined likeIcon empty">
                                favorite
                            </span>
                            <span v-else class="material-symbols-outlined likeIcon">
                                favorite
                            </span>
                            <span class="likeText">{{ userInfo?.helperInfo?.likeCount }}</span>
                        </div>
                    </div>

                    <!-- 1.2 헬퍼 요금 소개 라인 -->
                    <div class="helperDesc">
                        <!-- 1.2.1 닉네임 & 한 줄 소개 -->
                        <div class="helperNick mb-2">
                            <h3 class="nickName">{{ userInfo?.nickName }}</h3>
                            <div class="oneLine label mt-1">
                                {{ userInfo?.helperInfo?.oneLineIntroduction }}
                            </div>
                        </div>
                        <!-- 1.2.2 실력 표시 라인 -->
                        <div class="helperSkill">
                            <div class="fare">
                                <div class="label">기본요금</div>
                                <div class="fareText">
                                    {{ userInfo?.helperInfo?.unitPrice }}
                                    <span class="label" style="display: inline">캐럿</span>
                                </div>
                            </div>
                            <div class="reviewGrade">
                                <div class="label">평점</div>
                                <div class="star">
                                    <span class="material-symbols-outlined starIcon"> star </span>
                                    <div class="starText">
                                        {{ userInfo?.helperInfo?.avg }}
                                        <div class="label" style="display: inline">점</div>
                                    </div>
                                </div>
                            </div>
                            <div class="reviewCount">
                                <div class="label">리뷰건수</div>
                                <div class="countText">
                                    {{ userInfo?.helperInfo?.reviewCount }}
                                    <div class="label" style="display: inline">건</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 3. 하단 버튼 라인 -->

                <div v-if="!disable" class="helperBtns">
                    <XSmallBtn text="자세히보기" color="white" font="active" @click="clickBtn1" />
                    <XSmallBtn :text="rightBtnText" color="carrot" @click="clickBtn2" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import XSmallBtn from "@/components/common/button/XSmallBtn.vue";
import UserProfileImg from "../UserProfileImg.vue";
export default {
    name: "HelperCard",

    components: {
        UserProfileImg,
        XSmallBtn,
    },
    props: {
        removeClose: {
            type: Boolean,
            default: false,
        },
        userInfo: {
            type: Object,
        },
        rightBtnText: {
            type: String,
            default: "수락하기",
            description: "오른쪽 버튼 텍스트 [수락하기, 상담하기 etc]",
        },

        isLikeEmpty: {
            type: Boolean,
            default: false,
            description: "like(favorite) 하트 아이콘 비었는가 아닌가",
        },

        disable: {
            type: Boolean,
            default: false,
        },
    },
    methods: {
        removeCard(e) {
            e.preventDefault();
            this.$emit("removeCard");
        },

        toggleLikeBtn(e) {
            e.preventDefault();
            this.$emit("toggleLikeBtn");
        },

        clickBtn1(e) {
            e.preventDefault();
            this.$router.push({ name: "ProfilePage", params: { id: this.userInfo.seq } });
            this.$emit("clickBtn1");
        },

        clickBtn2(e) {
            e.preventDefault();
            this.$emit("clickBtn2", this.userInfo.userSeq);
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/card.scss";
</style>
