<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner title="약관 동의" text="🐰약관을 모두 읽고 동의해주세요." />
            <!-- 공통 약관 -->
            <div v-for="(term, index) in clientTerms" :key="index">
                <div class="p-2 m-2">
                    <input type="checkbox" name="color" @click="clientTermToggle(index)" />
                    <span> {{ term.title }}<span>(필수)</span> </span>
                </div>
            </div>
            <!-- 헬퍼 약관 -->
            <div v-show="select == 'helper'">
                <div v-for="(term, index) in helperTerms" :key="index">
                    <div class="p-4 m-2 answer">
                        <h4>{{ term.title }}</h4>
                        <p>{{ term.content }}</p>
                    </div>
                    <div class="p-4 m-2">
                        <input type="checkbox" name="color" @click="helperTermToggle(index)" />
                        (필수) 약관에 동의합니다.
                    </div>
                    <br />
                    <br />
                </div>
            </div>

            <smallBtn style="width: 100%" text="다음" @click="goSignUpCreatePage"></smallBtn>
        </div>
        <alarm-modal
            title="경고"
            type="danger"
            btnText2="닫기"
            btnColor2="carrot"
            btnFontColor2="white"
            @clickBtn2="closeModal"
        >
            <template #content> 필수 약관에 동의해주세요. </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleBanner from "@/components/common/TitleBanner.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    components: {
        TitleBanner,
        smallBtn,
        AlarmModal,
    },

    data() {
        return {
            isOpen: false,
            clientTerms: [
                {
                    title: "만 14세 이상입니다.",
                    isAgree: false,
                },
                {
                    title: "서비스 이용약관에 동의합니다.",
                    isAgree: false,
                },
                {
                    title: "개인정보 수집·이용에 동의합니다.",
                    isAgree: false,
                },
                {
                    title: "개인정보 제3자 제공에 동의합니다.",
                    isAgree: false,
                },
            ],
            helperTerms: [
                {
                    title: "중계서비스 이용약관에 동의합니다.",
                    isAgree: false,
                },
            ],
        };
    },

    props: {
        select: {
            type: String,
            default: "",
        },
    },

    methods: {
        clientTermToggle(index) {
            if (this.clientTerms[index].isAgree) {
                this.clientTerms[index].isAgree = false;
            } else {
                this.clientTerms[index].isAgree = true;
            }
        },

        helperTermToggle(index) {
            if (this.helperTerms[index].isAgree) {
                this.helperTerms[index].isAgree = false;
            } else {
                this.helperTerms[index].isAgree = true;
            }
        },

        goSignUpCreatePage(event) {
            this.$store.commit("TOGGLE_ALARM_MODAL");

            event.preventDefault();
            let result = true;
            this.clientTerms.forEach((term) => {
                if (!term.isAgree) {
                    result = false;
                }
            });

            if (this.$route.params.select == "helper") {
                this.helperTerms.forEach((term) => {
                    if (!term.isAgree) {
                        result = false;
                    }
                });
            }

            if (result) {
                this.$router.push({ name: "SignUpCreatePage", params: { select: this.select } });
            } else {
                this.isOpen = true;
            }
        },

        closeModal() {
            this.isOpen = false;
        },
    },
};
</script>

<style lang="scss">
.answer {
    border: 1px solid var(--light-color);
}
</style>
