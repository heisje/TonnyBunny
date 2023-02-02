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
                    @click="insertBoard" />
                <router-link :to="{ name: 'BoardDetailPage', params: { id: 1 } }"> </router-link>
            </form>
        </div>
    </div>
</template>

<script>
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
                id: "title",
                value: "",
                notice: "",
            },
        };
    },

    methods: {
        changeInput(e) {
            // v-model 대체용
            this[e.target.id].value = e.target.value;
        },

        insertBoard() {
            const temp = {
                title: this.title.value,
                content: this.content.value,
            };
            this.$store.dispatch("insertBoard", temp);
        },
    },
};
</script>

<style lang="scss" scoped></style>
