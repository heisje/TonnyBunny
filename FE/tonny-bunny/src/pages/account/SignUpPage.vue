<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <TitleText title="회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
            <TitleText title="어떤 회원가입을 원하시나요?" type="h2" center />

            <div class="iconsContainer">
                <div class="iconsWarp">
                    <router-link :to="{ name: 'SignUpCreatePage', params: { select: 'client' } }">
                        <div class="iconsContent" @click="toggleTonnyModalOpen">
                            <div>
                                <img src="@/assets/homeTonnyIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">일반<br />회원가입</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
                <div class="iconsWarp">
                    <router-link :to="{ name: 'SignUpCreatePage', params: { select: 'helper' } }">
                        <div class="iconsContent" @click="toggleBunnyModalOpen">
                            <div>
                                <img src="@/assets/homeBunnyIcon.png" alt="" />
                            </div>
                            <div>
                                <h1 class="h3">번역/통역가(헬퍼)<br />회원가입</h1>
                            </div>
                        </div>
                    </router-link>
                </div>
            </div>

            <br />
            <TitleText title="회원 안내" type="h1-small" center />
            <div class="p-4 m-2 answer">
                <h3>일반 회원이란?</h3>
                <p>
                    일반 회원은 즉시 통역 또는 번역 등 언어 서비스를 받고 싶은 일반 회원님이
                    가입하시면 됩니다.
                </p>
            </div>
            <br />
            <div class="p-4 m-2 answer">
                <h3>번역사/통역가(헬퍼) 회원이란?</h3>
                <p>
                    번역사/통역가은
                    <span
                        >통역 또는 번역이 가능하여 도움이 필요한 고객에게 서비스를 제공할 수 있는
                        회원</span
                    >으로 가입하시고 싶은 분이 선택하시면 됩니다.
                </p>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";

export default {
    components: {
        TitleText,
    },
};
</script>

<style lang="scss" scoped>
.answer {
    border: 1px solid var(--light-color);
}
.iconsContainer {
    display: flex;
    justify-content: center;
    width: 100%;

    .iconsWarp {
        position: relative;
        width: 40%;
        max-width: 150px;
        padding-bottom: 40%;
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
</style>
