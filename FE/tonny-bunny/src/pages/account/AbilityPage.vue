<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <TitleText title="능력 어필" center text="추후에 변경이 가능합니다" />

            <TitleText title="언어 선택" type="h2" text="하실 수 있는 언어를 선택해주세요" />
            <select v-model="selected" :onchange="selectLang">
                <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                    {{ item.name }}
                </option></select
            ><br />
            <span v-for="(lang, index) in selectedLangList" :key="index">
                <div
                    style="
                        padding: 10px 20px;
                        background-color: pink;
                        display: inline-block;
                        border-radius: 20px;
                    ">
                    {{ lang }}
                    <span @click="cancleSelect(index)">❌</span>
                </div>
            </span>

            <TitleText title="자격증 추가" type="h2" text="어학 관련 자격증을 추가해주세요" />
            <select v-model="selected2">
                <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                    {{ item.name }}
                </option></select
            ><br />
            <input type="text" placeholder="자격증 이름" />
            <input type="text" placeholder="내용" />
            <br /><br />

            <smallBtn style="width: 100%" text="확인" @click="submitForm"></smallBtn><br /><br />
            <smallBtn style="width: 100%" text="건너뛰기" @click="goSignUpCompletePage"></smallBtn>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";

export default {
    components: {
        TitleText,
        smallBtn,
    },

    data() {
        return {
            selected: "",
            selected2: "",
            selectList: [
                { name: "언어", value: "" },
                { name: "영어", value: "영어" },
                { name: "일본어", value: "일본어" },
                { name: "스페인어", value: "스페인어" },
            ],
            selectedLangList: [],
        };
    },

    methods: {
        selectLang(event) {
            if (event.target.value == "") {
                return;
            }
            const isExist = this.selectedLangList.some((lang) => {
                return lang == event.target.value;
            });

            if (!isExist) {
                this.selectedLangList.push(event.target.value);
            }
        },

        cancleSelect(index) {
            this.selectedLangList.splice(index, 1);
        },

        submitForm(event) {
            event.preventDefault();
            // 자격증 정보 저장 axios 요청 후 회원가입 완료 페이지 이동
            this.$router.push({ name: "SignUpCompletePage" });
        },

        goSignUpCompletePage(event) {
            event.preventDefault();
            this.$router.push({ name: "SignUpCompletePage" });
        },
    },
};
</script>

<style></style>
