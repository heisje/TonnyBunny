<template>
    <div class="myPageContainer">
        <div class="myPageWrap">
            <h1>홈 - 마이 페이지</h1>

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

            <div v-if="isHelper === true" class="profileWrap">
                <div>
                    <title-text title="헬퍼 프로필" type="h2" />
                    <div>
                        <helper-card
                            :userInfo="{
                                seq: 1,
                                userCode: '1',
                                email: 'test251@gmail.com',
                                nickName: 'nickck111',
                                phoneNumber: null,
                                point: 0,
                                profileImagePath: '/img/default.jpg',
                                helperInfo: {
                                    seq: 1,
                                    avgScore: 3.5,
                                    reviewCount: 213,
                                    unitPrice: 123,
                                    oneLineIntroduction: 'ㄻㄴㄴㄻㅁㄹㄴ',
                                    introduction: 'ㄹㄴㅁㅁㄻㄴㄹ',
                                    possibleLanguageList: [
                                        {
                                            name: 'Korean',
                                            value: '0000111',
                                        },
                                        {
                                            name: 'English',
                                            value: '0010101',
                                        },
                                    ],
                                    certificateList: [
                                        {
                                            seq: null,
                                            langCode: null,
                                            certName: 'Korean',
                                            content: 'Korean',
                                        },
                                        {
                                            seq: null,
                                            langCode: null,
                                            certName: 'Canana',
                                            content: 'Canana',
                                        },
                                    ],
                                },
                            }"
                            nickName="아스파라거스"
                            oneLine="안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요안녕하세요"
                            fareText="2000"
                            starText="4.0"
                            countText="100"
                            likeText="10"
                            isLikeEmpty
                            rightBtnText="상담하기"></helper-card>
                    </div>
                </div>
                <!-- 
				자세히보기 XSmallBtn
				수정하기 XSmallBtn 
			-->
            </div>

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
                    <router-link :to="{ name: 'FavoriteListPage' }">
                        <div class="iconsContent" @click="toggleBunnyModalOpen">
                            <div>
                                <img src="@/assets/homeBunnyIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">즐겨찾기</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
                <div class="iconsWarp" v-if="true">
                    <router-link :to="{ name: 'SchedulePage' }">
                        <div class="iconsContent" @click="toggleHelperBtn">
                            <div>
                                <img src="@/assets/homeSearchIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">일정</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
            </div>

            <div class="settingWrap">
                <hr />
                <router-link :to="{ name: 'BlockListPage' }">
                    <div>차단목록</div>
                </router-link>
                <hr />
                <!-- <div>
                    <router-link :to="{ name: 'PushAlarmPage' }">푸시알림(미구현)</router-link>
                </div>
                <hr /> -->
                <div></div>
                <router-link :to="{ name: 'NoticePage' }">
                    <div>고객센터</div>
                </router-link>
                <hr />
                <router-link
                    :to="{
                        name: 'AbilityPage',
                        params: { userSeq: this.$store.state.account.userInfo.seq, mypage: true },
                    }">
                    <div>헬퍼 인증 및 변경</div>
                </router-link>

                <hr />

                <router-link :to="{ name: 'HelperChangePage' }"
                    ><span class="logout">로그아웃</span></router-link
                >

                <hr />
            </div>
            <AlarmModal
                v-show="isOpen"
                :isOpen="isOpen"
                title="완료"
                type="success"
                btnText2="예"
                btnColor1="main"
                btnColor2="carrot"
                btnFontColor1="white"
                btnFontColor2="white"
                @close-modal="closeModal"
                :to="{ name: 'HomePage' }">
                <template #content> 로그아웃이 완료되었습니다 </template>
            </AlarmModal>
        </div>
    </div>
</template>

<script>
import ClientCard from "@/components/common/card/ClientCard.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import TitleText from "@/components/common/TitleText.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

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
        AlarmModal,
    },
    methods: {
        openModal(e) {
            e.preventDefault();
            this.isOpen = true;
        },

        closeModal() {
            this.isOpen = false;
        },
    },

    mounted() {
        this.$store.dispatch("getMypage");
    },
};
</script>

<style lang="scss" scoped>
.myPageContainer {
    width: 100%;
    .myPageWrap {
        width: 100%;
        .profileWrap {
            width: 100%;
        }
    }
}
.iconsContainer {
    display: flex;
    justify-content: center;
    width: 100%;

    .iconsWarp {
        position: relative;
        width: 30%;
        max-width: 150px;
        padding-bottom: 30%;
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
    margin-top: 1rem;
    a {
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
