<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div v-if="$route.query.mypage">
                <title-banner title="능력 어필" text="🐰당신의 능력을 어필해주세요!" />
            </div>
            <div v-else>
                <TitleText
                    title="능력 어필"
                    center
                    text="추후에 마이페이지에서 변경이 가능합니다"
                />
            </div>

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

            <!-- 자격증 폼 -->
            <div>
                <div class="certificateWrap">
                    <dropdown-input-code
                        :dropdownArray="langCode"
                        @toggleItem="
                            (e) => {
                                certificateLang = e;
                            }
                        "
                    />
                    <input
                        class="certificateItem"
                        type="text"
                        placeholder="자격증 이름"
                        v-model="certName"
                    />
                </div>
                <div class="certificateWrap">
                    <input type="text" placeholder="내용" v-model="contentInput" />
                    <MediumBtn class="mButton" text="등록" @click="addCertificate"></MediumBtn>
                </div>
            </div>

            <!-- 등록한 자격증 목록 -->
            <div class="selected" v-for="(certificate, index) in certificateList" :key="index">
                [{{ allCode[certificate.langCode] }}] {{ certificate.certName }} :
                {{ certificate.content }}
                <span @click="deleteCertificate(index)">❌</span>
            </div>

            <br /><br />

            <div>
                <smallBtn
                    color="carrot"
                    style="width: 100%"
                    text="확인"
                    @click="submitForm"
                ></smallBtn>
            </div>

            <div v-if="$route.query.mypage" style="margin-top: 8px">
                <smallBtn
                    color="outline"
                    font="main"
                    style="width: 100%"
                    text="취소"
                    @click="goMyPage"
                ></smallBtn>
            </div>

            <div v-else style="margin-top: 8px">
                <smallBtn
                    color="outline"
                    font="main"
                    style="width: 100%"
                    text="다음에 하기"
                    @click="goMyPage"
                ></smallBtn>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner";
import http from "@/common/axios.js";
import { mapGetters } from "vuex";
import DropdownInputCode from "@/components/common/input/DropdownInputCode.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
// import http from "@/common/axios";

export default {
    name: "AbilityPage",
    props: {
        userSeq: {
            type: String,
            default: "",
        },
        mypage: {
            type: Boolean,
            default: false,
        },
    },
    components: {
        TitleText,
        TitleBanner,
        DropdownInputCode,
        SmallBtn,
        MediumBtn,
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
                    this.$router.push({ name: "AbilityCompletePage" });
                } else {
                    // 헬퍼정보 등록 실패
                    console.log("실패");
                }
            } catch (error) {
                console.log(error);
            }
        },

        goMyPage(event) {
            event.preventDefault();
            this.$router.push({ name: "MyPage" });
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

<style lang="scss" scoped>
.selected {
    padding: 8px 16px;
    background-color: var(--white-color);
    border: 1px solid var(--disable-color);
    margin-right: 4px;
    display: inline-block;
    border-radius: 20px;
}

.certificateWrap {
    display: flex;
    margin-bottom: 4px;
    .certificateItem {
        margin: 0px 4px;
    }
    .mButton {
        width: 100px;
        margin-left: 4px;
        align-items: center;
    }
}
</style>
