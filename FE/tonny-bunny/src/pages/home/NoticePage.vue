<template>
    <div>
        <div>
            <title-text title="고객센터" />

            <div class="noticeWrap" @click="loading">
                <hr />
                <div>공지사항</div>
                <hr />
                <div>자주 묻는 질문</div>
                <hr />
                <div>이메일 문의</div>
                <hr />
                <div>고객센터 전화</div>
                <hr />
                <div>이용약관</div>
                <hr />
                <div><span class="red">회원탈퇴</span></div>
                <hr />
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";

export default {
    name: "NoticePage",
    components: {
        TitleText,
    },
    methods: {
        loading() {
            alert("준비중인 서비스입니다.");
        },
    },
};
</script>

<style lang="scss" scoped>
.noticeWrap {
    margin-top: 2rem;
    div {
        padding: 1rem 1em;
        cursor: pointer;
    }
    hr {
        padding: 0;
        margin: 0;
    }
    .red {
        color: var(--danger-color);
    }
}
</style>
