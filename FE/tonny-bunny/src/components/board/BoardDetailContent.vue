<template lang="">
    <div class="boardTopContainer">
        <div class="boardTopWrap">
            <square-tag sub text="자유"></square-tag>
            <h1>{{ boardDetail?.title }}자유게시판글임</h1>
            <div class="boardDate">
                작성일 <span>{{ boardDetail?.createdAt }}</span>
            </div>
            <!-- 조회수 -->
            <h3>{{ boardDetail?.user }}닉네임임</h3>
            <hr />
            <div>{{ boardDetail?.content }}내용임</div>

            <div class="boardImageWrap">
                <!-- <div
                    class="boardImageItem"
                    v-for="boardImageItem in boardDetail?.boardImageList"
                    :key="boardImageItem">
                    <img v-if="boardItem?.boardImageItem" :src="boardItem?.boardImageItem" alt="" />
                    <img v-else src="@/assets/noBoardImg.png" alt="" />
                </div> -->

                <div class="boardImageContent" v-for="i in 7" :key="i">
                    <img
                        v-if="boardDetail?.boardImageItem"
                        :src="boardDetail?.boardImageItem"
                        alt="" />
                    <img v-else src="@/assets/noBoardImg.png" alt="" />
                </div>
            </div>

            <form class="customForm">
                <input type="text" />
            </form>

            <div class="boardCommentContainer">
                <div class="boardCommentWrap">
                    <!-- <div
                        class="boardCommentContent"
                        v-for="boardCommentItem in boardDetail?.boardCommentList"
                        :key="boardCommentItem">
                        <div>{{ boardCommentItem }}내용</div>
                    </div> -->

                    <div class="boardCommentContent" v-for="i in 6" :key="i">
                        <img
                            v-if="boardDetail?.boardImageItem"
                            :src="boardDetail?.boardImageItem"
                            alt="" />
                        <img v-else src="@/assets/noProfile.png" alt="" />
                        <h3>닉네임</h3>
                        <div class="commentContent">
                            내용이 짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이
                            짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이
                            짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이
                            짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이 짱길면내용이
                            짱길면내용이 짱길면내용이 짱길면내용이 짱길면
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

export default {
    components: {
        SquareTag,
    },

    computed: {
        ...mapGetters({ boardDetail: "getBoardDetail" }),
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
        .boardDate {
            margin-top: 12px;
        }
        hr {
            border-top: 1px solid var(--disable-color);
        }
    }

    .boardImageWrap {
        width: 100vw;
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
        .boardCommentWrap {
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
                    padding: 1rem 0rem;
                }

                hr {
                    border-top: 1px solid var(--disable-color);
                    margin: 0;
                }
            }
        }
    }

    .customForm {
        margin-bottom: 1rem;
    }
}
</style>
