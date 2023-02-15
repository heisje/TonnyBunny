<template lang="">
    <div id="BottomNavbar">
        <div class="centerIconWrap" v-show="showCenterBtn">
            <div class="centerBtn" @click="clickCenterBtn1">
                <img src="@/assets/roket.png" alt="" width="40" />
                <div>통역</div>
            </div>
            <div class="centerBtn" @click="clickCenterBtn2">
                <img src="@/assets/book.png" alt="" width="40" />
                <div>번역</div>
            </div>
            <div v-if="isHelper" class="centerBtn" @click="clickCenterBtn3">
                <img src="@/assets/bag.png" alt="" width="40" />
                <div>헬퍼</div>
            </div>
        </div>
        <div class="container">
            <ul>
                <li @click="disableCenterBtn">
                    <router-link :to="{ name: 'HomePage' }">
                        <span class="material-symbols-outlined icon"> home </span>
                        <span>홈</span>
                    </router-link>
                </li>
                <li @click="disableCenterBtn">
                    <router-link :to="{ name: 'BoardPage' }">
                        <span class="material-symbols-outlined icon"> dvr </span>
                        <span>커뮤니티</span>
                    </router-link>
                </li>
                <li>
                    <a class="centerWrap" @click="toggleCenterIcon">
                        <img class="centerIcon" src="@/assets/bottomBtn.png" alt="" />
                    </a>
                </li>
                <li @click="disableCenterBtn">
                    <router-link :to="{ name: 'ChatPage' }">
                        <span class="material-symbols-outlined icon"> forum </span>
                        <span>채팅</span>
                    </router-link>
                </li>
                <li @click="disableCenterBtn">
                    <router-link :to="{ name: 'MyPage' }">
                        <span class="material-symbols-outlined icon"> account_circle </span>
                        <span>내 정보</span>
                    </router-link>
                </li>
            </ul>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
    data() {
        return {
            showCenterBtn: false,
        };
    },

    methods: {
        toggleCenterIcon() {
            this.showCenterBtn = !this.showCenterBtn;
        },

        clickCenterBtn1() {
            this.showCenterBtn = !this.showCenterBtn;
            this.$router.push({ name: "TonnyPage" });
        },

        clickCenterBtn2() {
            this.showCenterBtn = !this.showCenterBtn;
            this.$router.push({ name: "BunnyPage" });
        },

        clickCenterBtn3() {
            this.showCenterBtn = !this.showCenterBtn;
            this.$router.push({ name: "HelperHomePage" });
        },

        disableCenterBtn() {
            this.showCenterBtn = false;
        },
    },

    computed: {
        ...mapGetters({
            isHelper: "getIsHelper",
        }),
    },
};
</script>
<style lang="scss" scoped>
.router-link {
    color: var(--primary-color);
}

.route-active {
    span {
        color: var(--primary-color);
    }
}

#BottomNavbar {
    position: fixed;
    bottom: 0;
    left: 0;

    width: 100vw;
    height: 60px;
    background-color: var(--background-color);
    box-shadow: 0px -1px -1px 5px rgba(0, 0, 0, 0.05);
    border: 1px solid rgba(0, 0, 0, 0.062);

    display: flex;
    justify-content: center;
    align-items: center;

    z-index: 101;

    ul {
        // width: 100vw;

        display: flex;
        justify-content: space-around;
        align-content: center;

        list-style: none;
        padding: 0;
        margin: 0;

        li {
            a {
                display: flex;
                flex-direction: column;
                align-items: center;

                text-decoration: none;

                span:nth-child(1) {
                    font-size: 1.3rem;
                }
                span:nth-child(2) {
                    font-size: 0.8rem;
                    font-weight: 500;
                }
            }
            .centerWrap {
                display: block;
                position: relative;
                width: 100%;
                cursor: pointer;
                .centerIcon {
                    width: 50px;
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    transform: translate3d(-50%, -30%, 0);
                }
            }
        }
    }

    .centerIconWrap {
        display: flex;
        position: fixed;
        bottom: 60px;
        left: 0px;
        width: 100vw;
        height: 100px;
        justify-content: center;
        align-items: center;
        .centerBtn {
            border: 1px solid var(--light-color);
            border-radius: 10px;
            background: var(--white-color);
            padding: 3px;
            margin: 6px;
            width: 80px;
            text-align: center;
            cursor: pointer;
        }
    }
}

.material-symbols-outlined {
    font-variation-settings: "FILL" 0, "wght" 400, "GRAD" 0, "opsz" 48;
}

@media only screen and (min-device-width: 100px) and (max-device-width: 768px) {
    #BottomNavbar {
        width: 100vw;
        left: 0;
        right: 0;
    }
}

@media (min-width: 768px) {
}

@media (min-width: 1200px) {
    #BottomNavbar {
        display: none;
    }
}
</style>
