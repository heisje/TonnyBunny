<template lang="">
    <div class="boardTopContainer">
        <!-- {{ boardDetail }} -->
        <div class="boardTopWrap">
            <square-tag sub text="자유"></square-tag>
            <h1>{{ boardDetail?.title }}</h1>

            <!-- 조회수 -->
            <div class="boardUserWrap">
                <UserProfileImg
                    class="profileImg"
                    :profileImagePath="boardDetail?.user?.profileImagePath"
                    width="42" />

                <span
                    ><h3>{{ boardDetail?.user.nickName }}</h3></span
                >
                <span>{{ boardDetail?.createdAt }}</span>
            </div>

            <hr />
            <div>{{ boardDetail?.content }}</div>

            <div class="boardImageWrap">
                <div
                    class="boardImageContent"
                    v-for="boardImageItem in boardDetail?.boardImageList"
                    :key="boardImageItem">
                    <img-item width="100" :imagePath="boardItem?.boardImageItem" />
                </div>
            </div>

            <div class="commentCreateWrap">
                <input class="input" type="text" :id="content.id" @input="changeInput" />
                <div class="commentCreateBtn">
                    <SmallBtn text="댓글 작성" @click="clickCommentCreateBtn" />
                </div>
            </div>
            <br />
            <div class="boardCommentContainer">
                <TitleText
                    :title="`댓글(${boardDetail?.boardCommentList?.length})`"
                    top="24"
                    type="h2" />
                <hr />
                <div class="boardCommentWrap">
                    <div
                        class="boardCommentContent"
                        v-for="boardCommentItem in boardDetail?.boardCommentList"
                        :key="boardCommentItem">
                        <div>
                            <user-profile-img
                                :profileImagePath="boardCommentItem?.user?.profileImagePath"
                                width="40" />

                            <h3>{{ boardCommentItem?.user?.nickName }}</h3>
                            <span>{{ boardCommentItem?.createdAt }}</span>
                        </div>

                        <div class="commentContent">
                            {{ boardCommentItem?.content }}
                        </div>

                        <hr />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

import SquareTag from "../common/tag/SquareTag.vue";
import SmallBtn from "../common/button/SmallBtn.vue";
import TitleText from "../common/TitleText.vue";
import UserProfileImg from "../common/UserProfileImg.vue";
import ImgItem from "../common/ImgItem.vue";
export default {
    components: {
        SquareTag,
        SmallBtn,
        TitleText,
        UserProfileImg,
        ImgItem,
    },

    data() {
        return {
            content: {
                id: "content",
                value: "",
            },
        };
    },

    computed: {
        ...mapGetters({ boardDetail: "getBoardDetail" }),
    },

    methods: {
        clickCommentCreateBtn() {
            const payload = {
                boardSeq: this.boardDetail.seq,
                content: this.content.value,
                userSeq: this.$store.state.account.userInfo.seq,
            };
            this.$store.dispatch("insertBoardComment", payload).then(() => {
                this.$store.dispatch("getBoardDetail", this.$route.params.id);
            });
        },

        changeInput(e) {
            this[e.target.id].value = e.target.value;
        },
    },
};
</script>
<style lang="scss" scoped>
.boardTopContainer {
    margin-top: 32px;
    .boardTopWrap {
        h1 {
            margin-left: 0.5rem;
        }
        hr {
            border-top: 1px solid var(--disable-color);
        }
        .boardUserWrap {
            margin-top: 1rem;
            display: flex;
            align-items: center;
            span {
                margin-left: 0.5rem;
            }
        }
    }

    .boardImageWrap {
        width: 100vw;
        margin-top: 48px;
        .boardImageContent {
            display: inline-block;
            padding: 0.5rem 0.5rem 0.5rem 0.5rem;
            img {
                width: 80px;
                height: 80px;
                background-color: var(--light-color);
            }
        }
    }

    .boardCommentContainer {
        width: 100%;

        padding: 4px 24px;
        border-radius: 16px;
        border: 1px solid var(--thin-color);
        background-color: var(--thin-color);
        .boardCommentWrap {
            margin-top: 1rem;
            .boardCommentContent {
                margin-bottom: 1rem;
                span {
                    margin-left: 1rem;
                }

                img {
                    width: 2.5rem;
                    height: 2.5rem;
                    margin-right: 1rem;
                }

                .commentContent {
                    padding: 0.4rem 0rem 1rem 0rem;
                }

                hr {
                    border-top: 1px solid var(--disable-color);
                    margin: 0;
                }
            }
        }
    }

    .commentCreateWrap {
        display: flex;
        .commentCreateBtn {
            margin-left: 4px;
            min-width: 6rem;
        }
    }
}
</style>
