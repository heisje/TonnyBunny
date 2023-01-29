<template>
    <div>
        <h1>회원관리 - 약관 동의 페이지</h1>

        <TitleText title="약관 동의" center text="당신의 귀여운 통역가, TonnyBunny!" />
        {{ select }}
        <TitleText />
        <!-- 공통 약관 -->
        <div style="background-color: beige">
            <h4>약관 내용 1</h4>
            <p>약관 내용 ~~</p>
        </div>
        <input type="checkbox" name="color" /> (필수) 약관에 동의합니다.
        <br />
        <br />
        <div style="background-color: beige">
            <h4>약관 내용 2</h4>
            <p>약관 내용 ~~</p>
        </div>
        <input type="checkbox" name="color" /> (필수) 약관에 동의합니다.

        <!-- 헬퍼 추가 약관 -->
        <div v-if="select == 'helper'">
            <br />
            <div style="background-color: beige">
                <h4>약관 내용 3</h4>
                <p>약관 내용 ~~</p>
            </div>
            <input type="checkbox" name="color" /> (필수) 약관에 동의합니다.
        </div>

        <smallBtn style="width: 100%" text="다음" @click="goSignUpCreatePage"></smallBtn>

        <alarm-modal
            v-show="isOpen"
            :isOpen="isOpen"
            title="경고"
            type="danger"
            btnText1="닫기"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal">
            <template #content> 필수 약관에 동의해주세요. </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
// import SubText from "@/components/common/SubText.vue";
// import DescriptionItem from "@/components/account/DescriptionItem.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    components: {
        TitleText,
        // SubText,
        // DescriptionItem,
        smallBtn,
        AlarmModal,
    },

    data() {
        return {
            isOpen: false,
        };
    },

    props: {
        select: {
            type: String,
            default: "",
        },
    },

    methods: {
        goSignUpCreatePage(event) {
            event.preventDefault();

            const checkboxs = document.querySelectorAll("input");
            let result = true;
            checkboxs.forEach((checkbox) => {
                if (!checkbox.checked) {
                    result = false;
                }
            });

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
