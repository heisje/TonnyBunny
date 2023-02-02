<template>
    <div class="boardListItemContainer">
        <div class="boardListItemWrap">
            <div class="boardListItem">
                <div class="thumnail">
                    <img
                        v-if="boardItem?.boardImageList[0]"
                        :src="boardItem?.boardImageList[0]"
                        alt="" />
                    <img v-else src="@/assets/noBoardImg.png" alt="" />
                </div>

                <div class="info mt-1 w-100">
                    <div @click="toggleDetailPage" class="title">
                        <div class="tag">
                            <square-tag sub text="자유"></square-tag>
                        </div>
                        <div class="titleText text-truncate">
                            {{ boardItem?.title }}
                        </div>
                    </div>

                    <div class="d-flex flex-column h-100 justify-content-between">
                        <div @click="toggleDetailPage" class="mt-2 desc text-truncate">
                            {{ boardItem?.content }}
                        </div>

                        <div class="meta">
                            <div class="comment">
                                <span class="material-symbols-outlined fs-5 icon"> chat </span>
                                <span class="count">{{ boardItem?.count }}</span>
                            </div>
                            <div class="time">{{ boardItem?.createdAt }}</div>
                        </div>
                    </div>
                </div>
            </div>
            <hr />
        </div>
    </div>
</template>

<script>
import SquareTag from "./tag/SquareTag.vue";

export default {
    name: "BoardListItem",

    components: {
        SquareTag,
    },

    props: {
        boardItem: {
            type: Object,
            // default: () => {
            //     return {
            //         seq: 1,
            //         user: null,
            //         title: "게시글입니다",
            //         content: "내용입니다.",
            //         createdAt: "2023-02-01T16:54:19.8261845",
            //         updatedAt: "2023-02-01T16:54:19.8261845",
            //         boardImageList: [],
            //         boardCommentList: [],
            //     };
            // },
        },
    },

    computed: {
        countComment() {
            return this.boardItem.boardCommentList.length();
        },
    },

    methods: {
        toggleDetailPage() {
            this.$emit("toggleDetailPage");
        },
    },
};
</script>

<style lang="scss" scoped>
.t-d-none {
    text-decoration: none;
}
.boardListItemContainer {
    width: 100%;
    cursor: default;
}

.boardListItem {
    display: flex;
    // height: 100px;

    .thumnail {
        display: inline-block;
        width: 100px;
        height: 100px;
        background-color: var(--light-color);
        img {
            width: 100px;
            height: 100px;
            background-color: var(--light-color);
            border-radius: 0.5rem;
        }
        border-radius: 8px;
        margin-right: 16px;
        // cursor: pointer;
    }

    .info {
        display: flex;
        flex-direction: column;
        height: 100px;

        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;
        .title {
            // display: inline-block;
            width: 100%;
            display: flex;
            align-items: center;
            flex-direction: row;
            font-weight: 600;
            a {
                display: inline-block;
                color: var(--main-color);
                text-decoration: none;
            }

            .tag {
                display: inline-block;
                margin-right: 6px;
            }

            .titleText {
                font-size: 1rem;
                font-weight: 600;
                cursor: pointer;

                &:hover {
                    // color: var(--primary-color);
                    text-decoration: underline;
                }
            }
        }

        .desc {
            color: var(--sub-color);
            // cursor: pointer;
        }
    }

    .meta {
        display: flex;
        justify-content: space-between;
        align-items: center;
        // position: relative;
        // margin-top: 10%;
        // bottom: 0;
        // width: 500px;

        .comment {
            display: flex;
            align-items: center;
            .icon {
                color: var(--main-color);
                margin-right: 4px;
            }

            .count {
            }
        }
        .time {
            // float: right;
            // position: absolute;
            // right: 10%;
        }
    }
}
</style>
