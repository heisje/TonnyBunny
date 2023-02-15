<template>
    <div>
        <div>
            <title-banner title="고객센터" text="🐰고객센터 페이지입니다." />

            <div class="noticeWrap">
                <hr />
                <span class="d-flex justify-content-between">
                    <div>공지사항</div>
                    <router-link :to="{ name: 'NoticeListPage' }">
                        <div class="btn btn-sm grey">자세히 보기</div>
                    </router-link>
                </span>
                <hr />
                <span class="d-flex justify-content-between">
                    <div>자주 묻는 질문</div>
                    <router-link :to="{ name: 'FAQPage' }">
                        <div class="btn btn-sm grey">자세히 보기</div>
                    </router-link>
                </span>
                <hr />
                <span class="d-flex justify-content-between">
                    <div>이메일 문의</div>
                    <div class="grey" @click="openAlert">복사하기</div>
                </span>
                <hr />
                <span class="d-flex justify-content-between">
                    <div>고객센터 전화</div>
                    <div class="grey" @click="openAlert">복사하기</div>
                </span>
                <hr />
                <span class="d-flex justify-content-between">
                    <div>이용약관</div>
                    <div class="grey">자세히 보기</div>
                </span>
                <hr />
                <span>
                    <div><span class="red" @click="openWarning">회원탈퇴</span></div>
                </span>
                <hr />
            </div>
        </div>
    </div>
</template>

<script>
import TitleBanner from "@/components/common/TitleBanner.vue";

export default {
    name: "NoticePage",
    components: {
        TitleBanner,
    },
    methods: {
        openAlert() {
            alert("복사되었습니다.");
        },
        openWarning() {
            alert("하지마세요.");
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
        color: #f96a63;
    }
    .grey {
        color: #a1aeb7;
    }
}
</style>
