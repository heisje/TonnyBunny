import http from "@/common/axios";

export default {
    /*
        게시글 CRUD
    */

    // GET /api/board 게시글 리스트를 조회합니다.
    async getBoardList(context) {
        console.log("게시글 리스트를 조회합니다.");
        let params = {};

        try {
            let { data } = await http.get("/board", { params });
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    context.commit("SET_BOARD_LIST", data.data);
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // POST /api/board 게시글을 작성합니다.
    async insertBoard(context, tempJson) {
        console.log("게시글을 작성합니다.");

        let { data } = await http.post(`/board`, tempJson);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }

            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    // GET /api/board/{boardSeq} 게시글을 열람합니다.
    async getBoardDetail(context, id) {
        console.log("게시글을 열람합니다.");

        try {
            let { data } = await http.get(`/board/${id}`);
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    context.commit("SET_BOARD_DETAIL", data.data);
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // PUT /api/board/{boardSeq} 게시글을 수정합니다.
    async updateBoard(context, id, json) {
        console.log("게시글을 수정합니다.");

        let { data } = await http.put(`/board/${id}`, json);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }
            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    // DELETE /api/board/{boardSeq} 게시글을 삭제합니다.
    async removeBoard(context, id) {
        console.log("게시글을 삭제합니다.");

        let { data } = await http.delete(`/board/${id}`);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }
            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    /*
        게시글 댓글 CRUD
    */

    // 불필요
    // GET /api/board/{boardSeq}/comment 게시글 댓글을 조회합니다.
    async getBoardCommentList(context, boardId) {
        console.log("게시글 댓글을 조회합니다.");
        let params = {};

        try {
            let { data } = await http.get(`/board/${boardId}/comment`, { params });
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }
        } catch (err) {
            console.error(err);
        }
    },

    // 불필요
    async getBoardCommentDetail() {},

    // POST /api/board/{boardSeq}/comment 게시글의 댓글을 작성합니다.
    async insertBoardComment(context, boardId, json) {
        console.log("게시글의 댓글을 작성합니다.");

        let { data } = await http.post(`/board/${boardId}/comment`, json);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }

            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    // PUT /api/board/${boardId}/comment 게시글의 댓글을 수정합니다.
    async updateBoardComment(context, boardId, json) {
        console.log("게시글의 댓글을 수정합니다.");

        let { data } = await http.post(`/board/${boardId}/comment`, json);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }

            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },

    // DELETE /api/board/{boardSeq}/comment/{commentSeq} 게시글의 댓글을 삭제합니다.
    async removeBoardComment(context, boardId, commentId) {
        console.log("게시글의 댓글을 삭제합니다.");

        let { data } = await http.post(`/board/${boardId}/comment/${commentId}`);

        try {
            console.log("async function : ", data);

            // service logic
            switch (data.resultCode) {
                case "success":
                    break;
                case "fail":
                    break;
            }

            return data.resultCode;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return data.resultCode;
        }
    },
};
