<template>
    <div id="Footer" class="footerContainer">
        <div class="footerWrap container">
            <div class="footer d-flex flex-column">
                <div class="tonnyBunnyInfo d-flex mb-2">
                    <div class="site">
                        <h2>TonnyBunny</h2>
                        <ul>
                            <li>토니바니란?</li>
                            <li>통역하기</li>
                            <li>번역하기</li>
                            <li>커뮤니티</li>
                        </ul>
                    </div>

                    <div class="team">
                        <h2>잘해오조</h2>
                        <ul>
                            <li class="d-flex align-items-center">
                                <span>배충현</span>
                                <span class="material-symbols-outlined fs-5">
                                    arrow_right_alt
                                </span>
                            </li>
                            <li class="d-flex align-items-center">
                                <span>김희제</span>
                                <span class="material-symbols-outlined fs-5">
                                    arrow_right_alt
                                </span>
                            </li>
                            <li class="d-flex align-items-center">
                                <span>류승태</span>
                                <span class="material-symbols-outlined fs-5">
                                    arrow_right_alt
                                </span>
                            </li>
                            <li class="d-flex align-items-center">
                                <span>염정아</span>
                                <span class="material-symbols-outlined fs-5">
                                    arrow_right_alt
                                </span>
                            </li>
                            <li class="d-flex align-items-center">
                                <span>정채원</span>
                                <span class="material-symbols-outlined fs-5">
                                    arrow_right_alt
                                </span>
                            </li>
                            <li class="d-flex align-items-center">
                                <span>최권민</span>
                                <span class="material-symbols-outlined fs-5">
                                    arrow_right_alt
                                </span>
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
                            <li>이메일 문의</li>
                        </ul>
                    </div>
                </div>

                <hr />

                <div class="tonnyBunnyDesc mt-3">
                    <div class="d-flex align-items-center mb-2">
                        <div>
                            <img src="@/assets/logo.png" alt="TonnyBunny" width="88" />
                        </div>
                        <h2 class="ms-2">개인정보처리방침 |&nbsp;</h2>
                        <h2>약관</h2>
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <div>SSAFY</div>
                            <div>팀장 : 배충현</div>
                            <div>이메일 :</div>
                            <div>전화번호 :</div>
                            <div>전화번호 :</div>
                            <div>&copy;SSAFY. ALL RIGHTS RESERVED</div>
                        </div>

                        <div class="icon mt-3">
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
export default {};
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
