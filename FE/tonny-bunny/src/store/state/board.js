export default {
    // pagination
    limit: 10,
    offset: 0,
    listRowCount: 10,
    pageLinkCount: 10,
    currentPageIdx: 1,

    // board
    boardListTotalCount: 0,
    boardList: [
        {
            seq: 1,
            user: null,
            title: "게시글입니다",
            content: "내용입니다.",
            createdAt: "2023-02-01T16:54:19.8261845",
            updatedAt: "2023-02-01T16:54:19.8261845",
            boardImageList: [],
            boardCommentList: [],
        },
    ],
    boardDetail: {
        seq: 1,
        user: null,
        title: "게시글입니다",
        content: "내용입니다.",
        createdAt: "2023-02-01T16:54:19.8261845",
        updatedAt: "2023-02-01T16:54:19.8261845",
        boardImageList: [],
        boardCommentList: [],
    },

    // comment
    commentListTotalCount: 0,
    commentList: [],
    commentDetail: {},
};
