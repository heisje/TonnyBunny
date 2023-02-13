<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div>
                <TitleText title="헬퍼프로필 변경" center text="헬퍼 정보를 변경하세요!" />
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
			<TitleText title="한 줄 자기소개" type="h2" text="자신을 표현할 간단한 자기소개를 입력해주세요." />
			<input type="text" :value="oneLineIntroduction">

			<TitleText title="사진 첨부" type="h2" text="본인을 어필할 수 있는 사진을 첨부해주세요." />

			<TitleText title="본인 소개" type="h2" text="자유롭게 본인을 소개해주세요." />
			<textarea cols="10" rows="5" v-model="introduction"></textarea>

            <div>
                <smallBtn
                    color="carrot"
                    style="width: 100%"
                    text="확인"
                    @click="submitForm"
                ></smallBtn>
            </div>

            <div style="margin-top: 8px">
                <smallBtn
                    color="outline"
                    font="main"
                    style="width: 100%"
                    text="취소"
                    @click="goMyPage"
                ></smallBtn>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import http from "@/common/axios.js";
import { mapGetters } from "vuex";
import DropdownInputCode from "@/components/common/input/DropdownInputCode.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
// import http from "@/common/axios";

export default {
    name: "ProfileUpdatePage",
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

			// others
			helperInfoImageList: [],
            oneLineIntroduction: "",
            introduction: "",
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
                let res = await http.put(`/mypage/${userSeq2}/helper`, jsonData);

                if (res.data.resultCode == "SUCCESS") {
                    console.log(res);
                    // 헬퍼정보 등록 성공 후 완료 페이지로
                    this.$router.push({ name: "MyPage" });
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
	created(){
		this.$store.dispatch("getHelper", this.userSeq).then(() => {});
	},

	mounted(){
		this.oneLineIntroduction = this.$store.state.account.userInfo.helperInfo.oneLineIntroduction;
		this.introduction = this.$store.state.account.userInfo.helperInfo.introduction;
		this.certificateList = this.$store.state.account.userInfo.helperInfo.certificateList;
		this.possibleLanguageCodeList = this.$store.state.account.userInfo.helperInfo.possibleLanguageList;
		this.helperInfoImageList = this.$store.state.account.userInfo.helperInfo.helperInfoImageList;

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

