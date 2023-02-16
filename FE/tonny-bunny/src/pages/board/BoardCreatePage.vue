<template>
    <title-banner title="게시글 작성" text="글을 작성해볼까요?" />
    <div class="d-flex justify-content-center align-items-center" style="margin-top: 100px">
        <div class="BoardCreateConatiner customForm">
            <div class="BoardCreateWrap">
                <form class="customForm">
                    <title-text important type="h2" title="제목" top="20" />
                    <input
                        type="text"
                        :name="title.id"
                        :id="title.id"
                        :pattern="title.pattern"
                        @input="changeInput"
                        placeholder="제목을 입력해주세요" />

                    <title-text important type="h2" title="내용" top="50" />
                    <textarea
                        type="text"
                        :name="content.id"
                        :id="content.id"
                        :pattern="content.pattern"
                        @input="changeInput"
                        rows="7"
                        placeholder="내용을 입력해주세요" />
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
                <medium-btn
                    text="게시글 작성"
                    class="w-100"
                    style="margin-top: 24px"
                    color="carrot"
                    @click="insertBoard" />
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

            this.$store.dispatch("insertBoard", formData).then((res) => {
                if (res.resultCode == "SUCCESS") {
                    this.$router.push({ name: "BoardDetailPage", params: { id: res.data } });
                }
            });
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

<style lang="scss" scoped>
.BoardCreateConatiner {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.BoardCreateWrap {
    padding: 32px 24px;
    border: 1px solid rgba(0, 0, 0, 0.08);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--thin-color);
    border-radius: 6px;
}
</style>
