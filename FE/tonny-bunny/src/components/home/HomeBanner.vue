<template>
    <div class="homeBannerContainer w-100">
        <div class="homeBannerWrap">
            <div class="homeBanner">
                <div class="image">
                    <div class="d-flex justify-content-center h-100">
                        <div class="imageText">
                            <div class="icon">
                                <img src="@/assets/emoji/rabbit_face.png" alt="" />
                            </div>

                            <div class="texts">
                                <div class="sub">
                                    <span>해외에서 말을 못해 난처하다구요?</span>
                                    <br />
                                    <span>그런 당신을 위한 안성맞춤의 서비스!</span>
                                    <br />
                                </div>

                                <div class="title">
                                    <span>Let's TonnyBunny!</span>
                                </div>
                            </div>
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
.homeBannerContainer {
    position: absolute;
    top: 73px;
    left: 0;
}

.homeBannerWrap {
    // position: relative;
    left: 0;
    // width: 100vw !important;
    // left: 0;
    // width: 100%;
    background-color: red;
    background: linear-gradient(rgba(255, 255, 255, 0.7), rgba(255, 255, 255, 0.7)),
        url("@/assets/images/banner1.jpg") no-repeat center;
    background-size: cover;
}

.homeBanner {
    cursor: default;
    .image {
        height: 400px;
        // background: linear-gradient(rgba(255, 255, 255, 0.7), rgba(255, 255, 255, 0.7)),
        //     url("@/assets/images/banner1.jpg") no-repeat center;
        // background-size: cover;

        .imageText {
            display: flex;
            justify-content: center;
            align-items: center;

            .icon {
                margin-bottom: 40px;
                margin-right: 10px;
                img {
                    // width: 150px;
                }
            }

            .texts {
                .sub {
                    span {
                        position: relative;
                        top: -35px;

                        color: var(--active-color);
                        font-size: 1.5rem;
                        font-weight: 400;
                        letter-spacing: -1px;
                    }

                    span:nth-child(3) {
                        left: 100px;
                    }
                }
                .title {
                    span {
                        font-size: 7rem;
                        font-weight: 900;
                        letter-spacing: -4px;

                        color: var(--primary-color);
                        text-shadow: 1px 1px 1px #b85d51;
                    }
                }
            }
        }
    }
}
</style>
