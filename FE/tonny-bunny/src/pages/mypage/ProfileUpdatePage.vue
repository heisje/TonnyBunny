<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <div>
                <title-banner title="헬퍼 정보 수정" text="헬퍼 정보를 수정하실 수 있습니다" />
            </div>

            <div style="margin-bottom: 100px"></div>

            <!-- 언어 선택 -->
            <TitleText title="언어 선택" type="h2" text="사용 가능한 언어를 선택해주세요" />

            <dropdown-input-code :dropdownArray="langCode" @toggleItem="toggleLangItem" />

            <br />

            <span v-for="(lang, index) in possibleLanguageCodeList" :key="index">
                <div class="selected">
                    <div class="d-flex align-items-center">
                        {{ allCode[lang.value] }}
                        <span
                            @click="cancleSelect(index)"
                            style="cursor: pointer; color: var(--danger-color); font-size: 1.4rem"
                            class="material-symbols-outlined ms-2">
                            close
                        </span>
                    </div>
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
                        " />
                    <input
                        class="certificateItem"
                        type="text"
                        placeholder="자격증 이름"
                        v-model="certName" />
                </div>
                <div class="certificateWrap">
                    <input type="text" placeholder="내용" v-model="contentInput" />
                    <MediumBtn
                        class="mButton"
                        color="light"
                        font="active"
                        text="등록"
                        @click="addCertificate"></MediumBtn>
                </div>
            </div>

            <!-- 등록한 자격증 목록 -->
            <div class="selected" v-for="(certificate, index) in certificateList" :key="index">
                <div class="d-flex align-items-center">
                    [{{ allCode[certificate.langCode] }}] {{ certificate.certName }} :
                    {{ certificate.content }}
                    <span
                        @click="deleteCertificate(index)"
                        style="cursor: pointer; color: var(--danger-color); font-size: 1.4rem"
                        class="material-symbols-outlined ms-2">
                        close
                    </span>
                </div>
            </div>

            <br /><br />
            <TitleText title="기본요금 설정" type="h2" text="5분당 기본요금을 설정해주세요." />
            <input type="text" v-model="unitPrice" />
            <div v-show="noticeUnitPrice" style="color: red">{{ noticeUnitPrice }}</div>

            <TitleText
                title="한 줄 자기소개"
                type="h2"
                text="자신을 표현할 간단한 자기소개를 입력해주세요." />
            <input type="text" v-model="oneLineIntroduction" />

            <!-- <TitleText
                title="사진 첨부"
                type="h2"
                text="본인을 어필할 수 있는 사진을 첨부해주세요."
            />
            <div v-if="helperInfoImageList.length != 0">
                <div v-for="(helperImage, index) in helperInforImageList" :key="index">
                    <img-item width="100" :imagePath="helperImage?.imagePath" />
                </div>
            </div>
            <div v-else>
                <TitleText title="등록된 사진이 없습니다.🐾" center type="h2" />
            </div> -->

            <TitleText title="본인 소개" type="h2" text="자유롭게 본인을 소개해주세요." />
            <textarea cols="10" rows="5" v-model="introduction"></textarea>

            <div style="margin-top: 100px"></div>
            <div>
                <smallBtn
                    color="carrot"
                    style="width: 100%"
                    text="확인"
                    @click="submitForm"></smallBtn>
            </div>

            <div style="margin-top: 8px">
                <smallBtn
                    color="light"
                    font="active"
                    style="width: 100%"
                    text="취소"
                    @click="goMyPage"></smallBtn>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import TitleText from "@/components/common/TitleText.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import http from "@/common/axios.js";
import DropdownInputCode from "@/components/common/input/DropdownInputCode.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
// import ImgItem from "@/components/common/ImgItem.vue";
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
        TitleBanner,
        // ImgItem,
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
            unitPrice: 0,

            noticeUnitPrice: "",
        };
    },

    methods: {
        // 언어 선택
        toggleLangItem(e) {
            if (e.value == "") {
                return;
            }
            const isExist = this.possibleLanguageCodeList.some((lang) => {
                return lang.value == e.value;
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
            if (this.unitPrice > 9999) {
                this.noticeUnitPrice = "기본 가격은 1만 아래로 설정해주세요.";
                return;
            }

            this.noticeUnitPrice = "";
            let userSeq2 = this.userSeq * 1;

            const possibleLanguageList = this.possibleLanguageCodeList;
            const jsonData = {
                certificateList: this.certificateList,
                introduction: this.introduction,
                oneLineIntroduction: this.oneLineIntroduction,
                possibleLanguageList: possibleLanguageList,
                unitPrice: this.unitPrice,
            };

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
    created() {
        this.$store.dispatch("getHelper", this.userSeq).then(() => {});
    },

    mounted() {
        this.oneLineIntroduction =
            this.$store.state.account.userInfo.helperInfo.oneLineIntroduction;
        this.introduction = this.$store.state.account.userInfo.helperInfo.introduction;
        this.certificateList = this.$store.state.account.userInfo.helperInfo.certificateList;
        this.possibleLanguageCodeList =
            this.$store.state.account.userInfo.helperInfo.possibleLanguageList;
        this.helperInfoImageList =
            this.$store.state.account.userInfo.helperInfo.helperInfoImageList;
        this.unitPrice = this.$store.state.account.userInfo.helperInfo.unitPrice;
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
