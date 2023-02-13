<template>
    <title-banner title="자유게시판" text="자유롭게 이야기를 나누어보아요 ^.^" />
    <div class="BoardCreateConatiner">
        <div class="BoardCreateWrap">
            <title-text title="게시글 작성" />
            <form class="customForm">
                <title-text important type="h2" title="제목" />
                <input
                    type="text"
                    :name="title.id"
                    :id="title.id"
                    :pattern="title.pattern"
                    @input="changeInput"
                    placeholder="제목을 입력해주세요" />
                <title-text important type="h2" title="내용" />

                <textarea
                    type="text"
                    :name="content.id"
                    :id="content.id"
                    :pattern="content.pattern"
                    @input="changeInput"
                    placeholder="내용을 입력해주세요" />
                <medium-btn
                    style="width: 100%"
                    text="게시글 작성"
                    color="carrot"
                    @click="insertBoard" />
                <router-link :to="{ name: 'BoardDetailPage', params: { id: 1 } }"> </router-link>
            </form>

            <input type="file" accept="image/*" @change="insertBoardImageList" />
            <div class="fileListWrap">
                <div
                    v-for="(boardImageItem, index) in boardImageList"
                    :key="boardImageItem"
                    @click="removeBoardImageList(index)">
                    <span>
                        {{ boardImageItem.name }}
                    </span>
                    <span class="material-symbols-outlined"> close </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
// import axios from "axios";
import TitleText from "@/components/common/TitleText.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
export default {
    components: { TitleText, MediumBtn, TitleBanner },
    data() {
        return {
            title: {
                id: "title",
                value: "",
                notice: "",
            },

            content: {
                id: "content",
                value: "",
                notice: "",
            },

            boardImageList: [],
        };
    },

    methods: {
        changeInput(e) {
            // v-model 대체용
            this[e.target.id].value = e.target.value;
        },

        insertBoard() {
            const formData = new FormData();

            formData.append("userSeq", this.$store.state.account.userInfo.seq);
            formData.append("title", this.title.value);
            formData.append("content", this.content.value);
            for (let i = 0; i < this.boardImageList.length; i++) {
                formData.append("file", this.boardImageList[i]);
            }

            this.$store.dispatch("insertBoard", formData);
        },

        insertBoardImageList(e) {
            this.boardImageList.push(e.target.files[0]);
        },

        removeBoardImageList(index) {
            this.boardImageList.splice(index, 1);
        },
    },
};
</script>

<style lang="scss" scoped></style>
