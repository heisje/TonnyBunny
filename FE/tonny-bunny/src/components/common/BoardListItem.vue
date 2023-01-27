<template>
    <div class="boardListItemContainer">
        <div class="boardListItemWrap">
            <div class="boardListItem">
                <div class="thumnail">
                    <img src="" alt="" width="100px" />
                </div>

                <div class="info mt-1 w-100">
                    <div class="title">
                        <div class="tag">
                            <square-tag sub text="자유"></square-tag>
                        </div>
                        <div class="titleText">
                            <router-link :to="{ name: 'BoardDetailPage', params: { id: boardId } }">
                                {{ title }}
                            </router-link>
                        </div>
                    </div>

                    <div class="d-flex flex-column h-100 justify-content-between">
                        <div class="desc text-truncate">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus rem
                            dolorum soluta fugit nobis blanditiis incidunt deserunt eum, porro modi
                            consequuntur voluptatem aperiam accusantium qui voluptas sit laudantium
                            fugiat ullam?
                        </div>

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
            default: "커뮤니티 글 제목",
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
.boardListItemContainer {
    cursor: default;
}

.boardListItem {
    display: flex;
    // height: 100px;

    .thumnail {
        width: 120px;
        height: 100px;
        background-color: var(--light-color);
        border-radius: 8px;
        margin-right: 16px;
        // cursor: pointer;
    }

    .info {
        display: flex;
        flex-direction: column;
        height: 100px;

        .title {
            display: flex;
            cursor: pointer;

            a {
                color: var(--main-color);
                text-decoration: none;
            }

            &:hover {
                text-decoration: underline;
            }

            .tag {
                margin-right: 6px;
            }

            .titleText {
                font-size: 1rem;
                font-weight: 600;
            }
        }

        .desc {
            width: 300px;
            margin-top: 8px;
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
            margin-left: 200px;
        }
    }
}
</style>
