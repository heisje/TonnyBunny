<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <TitleText title="능력 어필" center text="추후에 변경이 가능합니다" />

            <!-- 언어 선택 -->
            <TitleText title="언어 선택" type="h2" text="하실 수 있는 언어를 선택해주세요" />
            <select v-model="selected" :onchange="selectLang">
                <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                    {{ item.name }}
                </option></select
            ><br />
            <span v-for="(lang, index) in possibleLanguageList" :key="index">
                <div
                    style="
                        padding: 10px 20px;
                        background-color: pink;
                        display: inline-block;
                        border-radius: 20px;
                    "
                >
                    {{ lang }}
                    <span @click="cancleSelect(index)">❌</span>
                </div>
            </span>

            <!-- 자격증 추가 -->
            <TitleText title="자격증 추가" type="h2" text="어학 관련 자격증을 추가해주세요" />
            <!-- 등록한 자격증 목록 -->
            <div v-for="(certificate, index) in certificateList" :key="index">
                [{{ certificate.language }}] {{ certificate.title }} : {{ certificate.content }}
                <SmallBtn text="삭제" @click="deleteCertificate(index)"></SmallBtn>
            </div>
            <!-- 자격증 폼 -->
            <div>
                <select v-model="selected2">
                    <option v-for="(item, index) in selectList" :key="index" :value="item.value">
                        {{ item.name }}
                    </option>
                </select>
            </div>

            <input type="text" placeholder="자격증 이름" v-model="titleInput" />
            <input type="text" placeholder="내용" v-model="contentInput" />
            <SmallBtn text="등록" @click="addCertificate"></SmallBtn>
            <br /><br />

            <smallBtn style="width: 100%" text="확인" @click="submitForm"></smallBtn><br /><br />
            <smallBtn style="width: 100%" text="건너뛰기" @click="goSignUpCompletePage"></smallBtn>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import smallBtn from "@/components/common/button/SmallBtn.vue";
import SmallBtn from "@/components/common/button/XSmallBtn.vue";
// import http from "@/common/axios";

export default {
    components: {
        TitleText,
        smallBtn,
        SmallBtn,
    },

    data() {
        return {
            // select box
            selected: "",
            selectList: [
                { name: "언어", value: "" },
                { name: "영어", value: "영어" },
                { name: "일본어", value: "일본어" },
                { name: "스페인어", value: "스페인어" },
            ],
            possibleLanguageList: [],

            // certificate
            selected2: "",
            certificateList: [],
            titleInput: "",
            contentInput: "",
        };
    },

    methods: {
        // 언어 선택
        selectLang(event) {
            if (event.target.value == "") {
                return;
            }
            const isExist = this.possibleLanguageList.some((lang) => {
                return lang == event.target.value;
            });

            if (!isExist) {
                this.possibleLanguageList.push(event.target.value);
            }
        },

        // 언어 선택 취소
        cancleSelect(index) {
            this.possibleLanguageList.splice(index, 1);
        },

        // 자격증 추가
        addCertificate() {
            if (this.selected2 == "" || this.titleInput == "" || this.contentInput == "") {
                return;
            }

            const data = {
                title: this.titleInput,
                content: this.contentInput,
                language: this.selected2,
            };

            this.certificateList.push(data);
            this.titleInput = "";
            this.contentInput = "";
            this.selected2 = "";
        },

        // 자격증 등록 취소
        deleteCertificate(index) {
            this.certificateList.splice(index, 1);
        },

        // 폼 제출
        async submitForm(event) {
            event.preventDefault();

            // 일단 잠시 주석
            // try {
            //     let res = await http.post("/mypage/{userSeq}/helper", {
            //         possibleLanguageList: this.possibleLanguageList,
            //         certificateList: this.certificateList,
            //     });

            //     if (res.data.resultCodef == "SUCCESS") {
            //         // 헬퍼정보 등록 성공 후 완료 페이지로
            //         this.$router.push({ name: "SignUpCompletePage" });
            //     } else {
            //         // 헬퍼정보 등록 실패
            //         console.log("실패");
            //     }
            // } catch (error) {
            //     console.log(error);
            // }
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
