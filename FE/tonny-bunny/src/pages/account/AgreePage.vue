<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <TitleText title="약관 동의" center text="당신의 귀여운 통역가, TonnyBunny!" />
            <!-- 공통 약관 -->
            <div v-for="(term, index) in clientTerms" :key="index">
                <div style="background-color: beige">
                    <h4>{{ term.title }}</h4>
                    <p>{{ term.content }}</p>
                </div>
                <input type="checkbox" name="color" @click="clientTermToggle(index)" /> (필수)
                약관에 동의합니다.
                <br />
                <br />
            </div>
            <!-- 헬퍼 약관 -->
            <div v-show="select == 'helper'">
                <div v-for="(term, index) in helperTerms" :key="index">
                    <div style="background-color: beige">
                        <h4>{{ term.title }}</h4>
                        <p>{{ term.content }}</p>
                    </div>
                    <input type="checkbox" name="color" @click="helperTermToggle(index)" /> (필수)
                    약관에 동의합니다.
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
            @clickBtn2="closeModal">
            <template #content> 필수 약관에 동의해주세요. </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    components: {
        TitleText,
        smallBtn,
        AlarmModal,
    },

    data() {
        return {
            isOpen: false,
            clientTerms: [
                {
                    title: "약관 1",
                    content: "내용 1",
                    isAgree: false,
                },
                {
                    title: "약관 2",
                    content: "내용 2",
                    isAgree: false,
                },
            ],
            helperTerms: [
                {
                    title: "약관 3",
                    content: "내용 3",
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

<style></style>
