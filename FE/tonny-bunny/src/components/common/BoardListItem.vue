<template>
    <div class="boardListItemContainer">
        <div class="boardListItemWrap">
            <div class="boardListItem">
                <div class="thumnail">
                    <img src="" alt="" width="100px" />
                </div>

                <div class="info mt-1 w-100">
                    <router-link
                        class="t-d-none"
                        :to="{ name: 'BoardDetailPage', params: { id: boardId } }">
                        <div class="title">
                            <div class="tag">
                                <square-tag sub text="자유"></square-tag>
                            </div>
                            <div class="titleText text-truncate">
                                {{ title }}
                            </div>
                            <!-- <router-link :to="{ name: 'BoardDetailPage', params: { id: boardId } }">
                            <div class="titleText">{{ title }}</div>
                        </router-link> -->
                        </div>
                    </router-link>

                    <div class="d-flex flex-column h-100 justify-content-between">
                        <router-link
                            class="t-d-none"
                            :to="{ name: 'BoardDetailPage', params: { id: boardId } }">
                            <div class="mt-2 desc text-truncate">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus
                                rem dolorum soluta fugit nobis blanditiis incidunt deserunt eum,
                                porro modi consequuntur voluptatem aperiam accusantium qui voluptas
                                sit laudantium fugiat ullam?
                            </div>
                        </router-link>

                        <div class="meta">
                            <div class="comment">
                                <span class="material-symbols-outlined fs-5 icon"> chat </span>
                                <span class="count">{{ count }}</span>
                            </div>
                            <div class="time">{{ time }}</div>
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
    name: "BoardItem",

    components: {
        SquareTag,
    },

    props: {
        boardId: {
            type: Number,
            default: 1,
        },

        title: {
            type: String,
            default: "커뮤니티 글 제목 커뮤니티 글 제목 커뮤니티 글 제목",
        },

        count: {
            type: String,
            default: "댓글갯수",
        },

        time: {
            type: String,
            default: "오늘",
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
        width: 100px;
        height: 100px;
        img {
            width: 100px;
            height: 100px;
            background-color: var(--light-color);
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
