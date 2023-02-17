<template>
    <div id="Footer" class="footerContainer">
        <div class="footerWrap container">
            <div class="footer d-flex flex-column">
                <div class="tonnyBunnyInfo d-flex mb-2">
                    <div class="site">
                        <h2>TonnyBunny</h2>
                        <ul>
                            <li>
                                <router-link :to="{ name: 'TonnyPage' }">
                                    <span>통역하기</span>
                                </router-link>
                            </li>
                            <li>
                                <router-link :to="{ name: 'BunnyPage' }">
                                    <span>번역하기</span>
                                </router-link>
                            </li>
                            <li>
                                <router-link :to="{ name: 'BoardPage' }">
                                    <span>커뮤니티</span>
                                </router-link>
                            </li>
                        </ul>
                    </div>

                    <div class="team">
                        <h2>잘해오조</h2>
                        <ul>
                            <li class="d-flex align-items-center">
                                <a href="https://github.com/baebug">
                                    <span>배충현</span>
                                </a>
                            </li>
                            <li class="d-flex align-items-center">
                                <a href="https://github.com/heisje">
                                    <span>김희제</span>
                                </a>
                            </li>
                            <li class="d-flex align-items-center">
                                <a href="https://github.com/SeungtaeRyu">
                                    <span>류승태</span>
                                </a>
                            </li>
                            <li class="d-flex align-items-center">
                                <a href="https://github.com/yeomss">
                                    <span>염정아</span>
                                </a>
                            </li>
                            <li class="d-flex align-items-center">
                                <a href="https://github.com/noonmap">
                                    <span>정채원</span>
                                </a>
                            </li>
                            <li class="d-flex align-items-center">
                                <a href="https://github.com/Hello1Robot">
                                    <span>최권민</span>
                                </a>
                            </li>
                        </ul>
                    </div>

                    <div class="notice">
                        <h2>고객센터</h2>
                        <ul>
                            <li>
                                <router-link :to="{ name: 'NoticeListPage' }">
                                    <span>공지사항</span>
                                </router-link>
                            </li>
                            <li>
                                <router-link :to="{ name: 'FAQPage' }">
                                    <span>자주 묻는 질문</span>
                                </router-link>
                            </li>
                            <li>
                                <router-link :to="{ name: 'NoticePage' }">
                                    <span>이메일 문의</span>
                                </router-link>
                            </li>
                        </ul>
                    </div>
                </div>

                <hr />

                <div class="tonnyBunnyDesc mt-3">
                    <div class="d-flex align-items-center mb-2">
                        <div>
                            <img src="@/assets/logo.png" alt="TonnyBunny" width="88" />
                        </div>
                        <h2 class="ms-2" @click="goNotice">개인정보처리방침 |&nbsp;</h2>
                        <h2>약관</h2>
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div>&copy;SSAFY. ALL RIGHTS RESERVED</div>
                        </div>

                        <div class="icon mt-3 pb-2">
                            <i class="fab fa-github fs-2"></i>
                            <i class="fa-brands fa-gitlab fs-2"></i>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        goNotice() {
            this.$router.push({ name: "NoticePage" });
        },
    },
};
</script>

<style lang="scss" scoped>
#Footer {
    height: 500px;
    // width: 100vw;
}

.footerContainer {
    background-color: var(--light-color);
    // position: relative;
    bottom: 0;
    margin-top: 100px;
    padding: 40px 0;

    cursor: default;
}

.footerWrap {
    // display: flex;
    // justify-content: center;
    // align-content: center;
}

.footer {
    .tonnyBunnyInfo {
        // justify-content: space-between;
        flex-wrap: wrap;
        div {
            font-size: 1rem;
            margin-right: 80px;
            // background-color: red;
        }

        div > ul {
            padding: 0;
            margin-top: 10px;

            li {
                font-size: 0.9rem;
                list-style: none;
                margin: 2px;
                cursor: pointer;
            }
        }
    }

    .tonnyBunnyDesc {
        .icon {
            i {
                cursor: pointer;
                margin-right: 12px;
            }
        }
    }
}
a {
    text-decoration: none;
}
</style>
