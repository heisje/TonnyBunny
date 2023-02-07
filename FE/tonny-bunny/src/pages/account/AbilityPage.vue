<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <TitleText title="능력 어필" center text="추후에 변경이 가능합니다" />

            <!-- 언어 선택 -->
            <TitleText title="언어 선택" type="h2" text="하실 수 있는 언어를 선택해주세요" />

            <dropdown-input-code :dropdownArray="langCode" @toggleItem="toggleLangItem" />

            <br />

            <span v-for="(lang, index) in possibleLanguageCodeList" :key="index">
                <div class="selected">
                    {{ lang.name }}
                    <span @click="cancleSelect(index)">❌</span>
                </div>
            </span>

            <!-- 자격증 추가 -->
            <TitleText title="자격증 추가" type="h2" text="어학 관련 자격증을 추가해주세요" />
            <!-- 등록한 자격증 목록 -->
            <div v-for="(certificate, index) in certificateList" :key="index">
                [{{ allCode[certificate.langCode] }}] {{ certificate.certName }} :
                {{ certificate.content }}
                <SmallBtn text="삭제" @click="deleteCertificate(index)"></SmallBtn>
            </div>
            <!-- 자격증 폼 -->
            <div>
                <dropdown-input-code
                    :dropdownArray="langCode"
                    @toggleItem="
                        (e) => {
                            certificateLang = e;
                        }
                    " />
                <input type="text" placeholder="자격증 이름" v-model="certName" />
                <input type="text" placeholder="내용" v-model="contentInput" />
                <SmallBtn text="등록" @click="addCertificate"></SmallBtn>
            </div>

            <br /><br />

            <smallBtn style="width: 100%" text="확인" @click="submitForm"></smallBtn><br /><br />
            <smallBtn style="width: 100%" text="건너뛰기" @click="goSignUpCompletePage"></smallBtn>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import http from "@/common/axios.js";
import { mapGetters } from "vuex";
import DropdownInputCode from "@/components/common/input/DropdownInputCode.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
// import http from "@/common/axios";

export default {
    name: "AbilityPage",
    props: {
        userSeq: {
            type: String,
            default: "",
        },
    },
    components: {
        TitleText,
        DropdownInputCode,
        SmallBtn,
    },

    data() {
        return {
            // select box
            selected: "",
            possibleLanguageList: [],
            possibleLanguageCodeList: [],

            // certificate
            certificateLang: "",
            certificateList: [],
            certName: "",
            contentInput: "",
        };
    },

    methods: {
        // 언어 선택
        toggleLangItem(e) {
            if (e.value == "") {
                return;
            }
            const isExist = this.possibleLanguageCodeList.some((lang) => {
                return lang == e;
            });

            if (!isExist) {
                this.possibleLanguageCodeList.push(e);
            }
        },

        // 언어 선택 취소
        cancleSelect(index) {
            this.possibleLanguageList.splice(index, 1);
            this.possibleLanguageCodeList.splice(index, 1);
        },

        // 자격증 추가
        addCertificate() {
            if (this.certificateLang == "" || this.certName == "" || this.contentInput == "") {
                return;
            }

            const data = {
                certName: this.certName,
                content: this.contentInput,
                langCode: this.certificateLang.value,
            };

            this.certificateList.push(data);
            this.certName = "";
            this.contentInput = "";
        },

        // 자격증 등록 취소
        deleteCertificate(index) {
            this.certificateList.splice(index, 1);
        },

        // 폼 제출
        async submitForm(event) {
            event.preventDefault();

            let userSeq2 = this.userSeq * 1;

            const possibleLanguageList = this.possibleLanguageCodeList;
            const jsonData = {
                certificateList: this.certificateList,
                helperInfoImageReqeustDtoList: [],
                introduction: "new introduction",
                oneLineIntroduction: "new oneLineIntroduction",
                possibleLanguageList: possibleLanguageList,
            };
            // 일단 잠시 주석
            try {
                let res = await http.post(`/mypage/${userSeq2}/helper`, jsonData);

                if (res.data.resultCode == "SUCCESS") {
                    console.log(res);
                    // 헬퍼정보 등록 성공 후 완료 페이지로
                    this.$router.push({ name: "SignUpCompletePage" });
                } else {
                    // 헬퍼정보 등록 실패
                    console.log("실패");
                }
            } catch (error) {
                console.log(error);
            }
        },

        goSignUpCompletePage(event) {
            event.preventDefault();
            this.$router.push({ name: "SignUpCompletePage" });
        },
    },
    computed: {
        ...mapGetters({
            langCode: "getLangCode",
            allCode: "getAllCode",
        }),
    },
};
</script>

<style lang="scss">
.selected {
    padding: 10px 20px;
    background-color: pink;
    display: inline-block;
    border-radius: 20px;
}
</style>
