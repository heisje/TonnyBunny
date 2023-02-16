<template>
    <div class="myPageContainer">
        <div class="myPageWrap">
            <div class="profileContent">
                <title-banner title="마이페이지" text="내 정보를 확인할 수 있습니다." />
                <div class="profileWrap">
                    <div width="">
                        <title-text title="내 프로필" type="h2" />
                        <ClientCard />
                    </div>
                    <!-- 유저카드 구성요소
                프로필이미지아이템
                정보수정버튼
                포인트아이템 -->
                </div>

                <div v-if="userInfo?.userCode == '0010002'" class="profileWrap">
                    <div>
                        <title-text title="헬퍼 프로필" type="h2" />
                        <div>
                            <helper-card
                                removeClose
                                :userInfo="userInfo"
                                rightBtnText="수정하기"
                                @clickBtn1="goProfileDetail"
                                @clickBtn2="goProfileUpdate"
                            ></helper-card>
                        </div>
                    </div>
                </div>
            </div>

            <br />

            <div class="iconsContainer">
                <div class="iconsWarp">
                    <router-link :to="{ name: 'HistoryPage' }">
                        <div class="iconsContent" @click="toggleTonnyModalOpen">
                            <div>
                                <img src="@/assets/homeTonnyIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">이용내역</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
                <div class="iconsWarp">
                    <router-link :to="{ name: 'NoticePage' }">
                        <div class="iconsContent" @click="toggleBunnyModalOpen">
                            <div>
                                <img src="@/assets/homeBunnyIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">고객센터</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
                <div class="iconsWarp" v-if="userInfo?.userCode == '0010001'">
                    <router-link
                        :to="{
                            name: 'HelperChangePage',
                            params: { userSeq: this.$store.state.account.userInfo.seq },
                            query: { mypage: true },
                        }"
                    >
                        <div class="iconsContent" @click="toggleBunnyModalOpen">
                            <div>
                                <img src="@/assets/homeSearchIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">헬퍼 정보 등록</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
            </div>
            <AlarmModal
                title="완료"
                type="success"
                btnText2="예"
                btnColor2="carrot"
                btnFontColor1="white"
                btnFontColor2="white"
                @clickBtn2="clickBtn3"
            >
                <template #content> 로그아웃이 완료되었습니다 </template>
            </AlarmModal>
        </div>
    </div>
</template>

<script>
import ClientCard from "@/components/common/card/ClientCard.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import { mapGetters } from "vuex";

export default {
    name: "MyPage",
    data() {
        return {
            isHelper: true,
            isOpen: false,
        };
    },
    components: {
        ClientCard,
        HelperCard,
        TitleText,
        TitleBanner,
        AlarmModal,
    },
    methods: {
        openModal(e) {
            e.preventDefault();
            this.$store.dispatch("logout");
            this.$router.push({ name: "HomePage" });
        },

        clickBtn3() {
            this.$store.dispatch("logout");
            this.$store.commit("TOGGLE_ALARM_MODAL");
            this.$router.push({ name: "HomePage" });
        },
        goProfileUpdate() {
            this.$router.push({
                name: "ProfileUpdatePage",
                params: {
                    userSeq: this.userInfo.seq,
                },
            });
        },
    },

    mounted() {
        this.$store.dispatch("getMypage");
    },

    computed: {
        ...mapGetters({ userInfo: "getUserInfo" }),
    },
};
</script>

<style lang="scss" scoped>
.myPageContainer {
    width: 100%;
    .myPageWrap {
        width: 100%;
        .profileContent {
            max-width: 400px;
            margin: auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            .profileWrap {
                width: 100%;
            }
        }
    }
}
.iconsContainer {
    display: flex;
    justify-content: center;
    width: 100%;
    max-width: 420px;
    margin: auto;
    .iconsWarp {
        position: relative;
        width: 30%;
        max-width: 150px;
        padding-bottom: #{"min(150px, 30%)"};
        margin: 2%;

        .iconsContent {
            position: absolute;
            display: flex;
            width: 100%;
            height: 100%;

            max-height: 150px;
            flex-direction: column;
            justify-content: center;
            border-radius: 10%;
            border: 1px solid var(--light-color);
            cursor: pointer;

            transition: all 0.13s;
            &:hover {
                background-color: var(--primary-color-20);
                h1 {
                    margin-top: 10px;
                    width: 100%;
                    text-align: center;
                    color: var(--primary-color);
                }
            }
            div {
                width: 100%;
                display: flex;
            }
            img {
                display: inline-block;
                margin: auto;
                width: 50%;
            }
            h1 {
                margin-top: 10px;
                width: 100%;
                text-align: center;
            }
            &.searchIcon {
                background: var(--carrot-color);
                color: var(--white-color);
                h1 {
                    color: var(--white-color);
                    font-weight: 400;
                    letter-spacing: -0.05rem;
                }
                &:hover {
                    background: var(--primary-color-50);
                    h1 {
                        color: var(--primary-color);
                    }
                }
            }
        }
    }
}

.settingWrap {
    max-width: 400px;
    margin-top: 1rem;
    margin: auto;
    border-left: 1px solid var(--light-color);
    border-right: 1px solid var(--light-color);
    a {
        cursor: pointer;
        display: inline-block;
        text-decoration: none;
        color: var(--main-color);
        padding: 1rem;
        width: 100%;
        &:hover {
            div {
                color: var(--primary-color);
            }
        }
    }
    hr {
        border-top: 1px solid var(--sub-color);
        padding: 0;
        margin: 0;
    }
    .logout {
        color: var(--danger-color);
    }
}
</style>
