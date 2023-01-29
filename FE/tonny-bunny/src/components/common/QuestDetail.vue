<template lang="">
    <div>
        <square-tag text="번역의뢰" sub></square-tag>
        <square-tag text="모집중" sub></square-tag>

        <TitleText title="바이어 미팅건으로 급하게 번역을 요청합니다." type="h1-small" top="0" />

        <TitleText title="번역서비스 요청자" type="h2" />
        <img src="" alt="" />
        <span>고객닉네임</span>
        <TitleText title="번역서비스 제공자" type="h2" />
        <img src="" alt="" />
        <span>헬퍼닉네임</span>

        <TitleText title="번역언어" type="h2" />
        <square-tag text="한국어 <-> 영어" sub></square-tag>

        <TitleText title="마감기한" type="h2" />
        <h3>2012.01.01 (수) ~ 2012.01.01 (수)</h3>

        <TitleText title="금액" type="h2" />
        <h3>10000캐럿/5분당</h3>

        <TitleText title="카테고리" type="h2" />
        <square-tag text="인쇄물" sub></square-tag>

        <TitleText title="내용" type="h2" />
        <p>자동차 관련 회사입니다. 생활 영어 능숙하신 분 찾습니다.</p>

        <TitleText title="사진" type="h2" />
        <div style="width: 100px; height: 100px; background-color: #999999"></div>
    </div>
</template>
<script>
import TitleText from "./TitleText.vue";
import SquareTag from "./tag/SquareTag.vue";
export default {
    name: "QuestDetail",
    components: {
        TitleText,
        SquareTag,
    },
    data() {
        return {
            isHelper: false,
        };
    },
    props: {
        // 전체 공통
        category: {
            type: String,
            default: "jtonny",
            description:
                "즉시통역 / 예약통역 / 번역의뢰 / 번역상담 [jtonny / ytonny / nbunny / pbunny]",
        },

        process: {
            type: String,
            default: "success",
            description: "success:모집중, info:진행중, white:완료됨",
        },

        language: {
            type: String,
            default: "영어 ↔ 한국어",
            description: "통번역 카테고리 [즉시통역, 통역예약 etc..]",
        },

        clientNickname: {
            type: String,
            default: "고객명",
        },

        helperNickname: {
            type: String,
            default: "헬퍼명",
        },
    },

    computed: {
        categoryToStr() {
            if (this.category == "jtonny") return "즉시통역";
            else if (this.category == "ytonny") return "예약통역";
            else if (this.category == "nbunny") return "번역의뢰";
            else return "번역상담";
        },
    },
};
</script>
<style lang=""></style>
