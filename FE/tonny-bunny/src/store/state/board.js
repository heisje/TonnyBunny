export default {
    // pagination
    totalPages: 0, // 모든 페이지 개수
    pageSize: 5, // 보여주는 개수
    pageNumber: 0, // 현재페이지

    // limit: 10,
    // offset: 0,
    // listRowCount: 10,
    // pageLinkCount: 10,
    // currentPageIdx: 1,

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
