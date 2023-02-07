<template>
    <div class="cardContainer">
        <div class="cardWrap">
            <div class="helperCard pt-2">
                <!-- 2. 정보 라인 -->
                <div class="helperInfo">
                    <!-- 1.1 프로필 이미지, 닉네임, 한 줄 소개 라인 -->
                    <div class="helperProfile">
                        <img class="profileImg" :src="userInfo?.profileImagePath" />
                        <img class="profileImg" src="@/assets/noProfile_white.png" />
                    </div>

                    <!-- 1.2 헬퍼 요금 소개 라인 -->
                    <div class="helperDesc">
                        <!-- 1.2.1 닉네임 & 한 줄 소개 -->
                        <div class="helperNick mb-2">
                            <h3 class="nickName">
                                {{ userInfo?.nickName
                                }}<span class="label edit carrotSetting" @click="toggleEdit"
                                    >정보수정</span
                                >
                            </h3>
                        </div>
                        <!-- 1.2.2 실력 표시 라인 -->
                        <div class="clientPoint">
                            <div>보유 캐럿</div>
                            <div class="carrot">{{ userInfo?.point }}개</div>
                            <div class="carrotSetting" @click="togglePoint">포인트관리</div>
                        </div>
                    </div>
                </div>

                <!-- 3. 하단 버튼 라인 -->
                <div class="helperBtns">
                    <XSmallBtn text="자세히보기" color="white" font="active" @click="clickBtn1" />
                    <XSmallBtn :text="rightBtnText" color="carrot" @click="clickBtn2" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name: "ClientCard",

    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),
    },

    methods: {
        togglePoint() {
            this.$router.push({ name: "CarrotPage" });
        },

        toggleEdit() {
            this.$router.push({ name: "InfoUpdatePage" });
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/card.scss";

.carrotSetting {
    cursor: pointer;
    &:hover {
        color: var(--primary-color);
    }
}
</style>
