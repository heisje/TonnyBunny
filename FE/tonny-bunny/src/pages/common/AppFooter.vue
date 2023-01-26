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
                            <li>배충현</li>
                            <li>김희제</li>
                            <li>류승태</li>
                            <li>염정아</li>
                            <li>정채원</li>
                            <li>최권민</li>
                        </ul>
                    </div>

                    <div class="notice">
                        <h2>고객센터</h2>
                        <ul>
                            <li>토니바니란?</li>
                            <li>통역하기</li>
                            <li>번역하기</li>
                            <li>커뮤니티</li>
                        </ul>
                    </div>
                </div>

                <hr />

                <div class="tonnyBunnyDesc mt-3">
                    <div class="d-flex align-items-center mb-2">
                        <div>
                            <img src="@/assets/logo.png" alt="TonnyBunny" width="100" />
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
        justify-content: space-between;

        div {
            font-size: 1rem;
            margin-right: 8px;
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
</style>
