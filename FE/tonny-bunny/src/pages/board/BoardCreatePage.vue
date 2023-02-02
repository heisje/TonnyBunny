<template>
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
                    @click="submitFiles" />
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
import axios from "axios";
import TitleText from "@/components/common/TitleText.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
export default {
    components: { TitleText, MediumBtn },
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
            // const payload = {
            //     title: this.title.value,
            //     content: this.content.value,
            //     boardImageList: this.boardImageList,
            // };
            // this.$store.dispatch("insertBoard", payload);
            this.submitFiles();
            // this.$store.dispatch("submitFiles", payload);
        },

        insertBoardImageList(e) {
            this.boardImageList.push(e.target.files[0]);
        },

        removeBoardImageList(index) {
            this.boardImageList.splice(index, 1);
        },

        async submitFiles() {
            const formData = new FormData();
            formData.append("title", this.title);
            formData.append("content", this.content);

            for (let i = 0; i < this.boardImageList.length; i++) {
                formData.append("files", this.boardImageList[i]);
            }

            const response = await axios.get("http://localhost:8080/api/board/img", formData, {
                withCredentials: true,
            });
            console.log(response);
        },
    },
};
</script>

<style lang="scss" scoped></style>
