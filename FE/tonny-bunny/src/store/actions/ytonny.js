import http from "@/common/axios";

const SUCCESS = "SUCCESS";
const FAIL = "FAIL";

export default {
	/* 
        예약 통역 CREATE
    */

	// POST /api/ytonny 고객의 예약 통역 공고 생성
	async insertYTonny(context, json) {
		console.log("예약통역 공고 생성");

		let { data } = await http.post(`/ytonny`, json);

		try {
			console.log("async insertYTonny : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					context.commit("SET_Y_TONNY_SEQ", data.data);
					return data.data;
				case FAIL:
					return -1;
			}
		} catch (err) {
			console.error(err);

			// exception
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
		}
	},

	// POST /api/ytonny 고객의 예약 통역 공고 생성
	async insertYTonnyApply(context, json) {
		console.log("예약통역 공고 신청 생성");

		console.log(json);
		let yTonnySeq = json.ytonnySeq;
		let { data } = await http.post(`/ytonny/${yTonnySeq}/apply`, json);

		try {
			console.log("async insertYTonnyApply : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					return data.data;
				case FAIL:
					return -1;
			}
		} catch (err) {
			console.error(err);

			// exception
			if (err.response.status == 403) {
				alert("로그인 하세요");
			}
		}
	},

	/* 
        예약 통역 READ
    */
	// GET /api/ytonny 예약 통역 공고 목록 조회
	async getYTonnyList(context) {
		console.log("예약 통역 공고 목록 조회");
		let params = {};

		try {
			let { data } = await http.get(`/ytonny`, { params });
			console.log("async getYTonnyList : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					context.commit("SET_YTONNY_LIST", data.data);
					break;
				case FAIL:
					break;
			}
		} catch (err) {
			console.error(err);
		}
	},

	// GET /api/ytonny/{yTonnySeq} 예약 통역 공고 상세 조회
	async getYTonnyDetail(context, yTonnySeq) {
		console.log("예약 통역 공고 상세 조회");

		try {
			let { data } = await http.get(`/ytonny/${yTonnySeq}`);
			console.log("async getYTonnyDetail : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					context.commit("SET_Y_TONNY_DETAIL", data.data);
					break;
				case FAIL:
					break;
			}
		} catch (err) {
			console.error(err);
		}
	},

	// GET /api/ytonny/{yTonnySeq}/apply 예약 통역 공고 목록 조회
	async getYTonnyApplyList(context, yTonnySeq) {
		console.log("예약 통역 공고 목록 신청 조회");
		let params = {
			page: 0,
			size: 5
		};

		try {
			let { data } = await http.get(`/ytonny/${yTonnySeq}/apply`, { params });
			console.log("async getYTonnyApplyList : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					context.commit("SET_Y_TONNY_APPLY_LIST", data.data);
					break;
				case FAIL:
					break;
			}
		} catch (err) {
			console.error(err);
		}
	},

	// PUT /api/ytonny/{yTonnyNotiSeq} 게시글을 수정합니다.
	async updateYtonny(context, yTonnyNotiSeq, json) {
		console.log("게시글을 수정합니다.");

		let { data } = await http.put(`/ytonny/${yTonnyNotiSeq}`, json);

		try {
			console.log("async function : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					context.commit("SET_YTONNY_DETAIL", data.data);
					break;
				case FAIL:
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

	// DELETE /api/ytonny/{yTonnyNotiSeq} 고객의 예약 통역 공고 취소
	async removeYtonny(context, yTonnyNotiSeq) {
		console.log("게시글을 삭제합니다.");

		let { data } = await http.delete(`/ytonny/${yTonnyNotiSeq}`);

		try {
			console.log("async function : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					break;
				case FAIL:
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
        기타
    */
	// POST /api/ytonny/enroll 헬퍼의 예약 통역 신청 등록
	async insertYtonnyEnroll(context, json) {
		console.log("헬퍼의 예약 통역 신청 등록");

		let { data } = await http.post(`/ytonny/enroll`, json);

		try {
			console.log("async function : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					break;
				case FAIL:
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

	// DELETE /api/ytonny/enroll/{yTonnyNotiHelperSeq} 헬퍼의 예약 통역 신청 취소
	async removeYtonnyEnroll(context, yTonnyNotiHelperSeq) {
		console.log("헬퍼의 예약 통역 신청 취소");

		let { data } = await http.delete(`/ytonny/enroll/${yTonnyNotiHelperSeq}`);

		try {
			console.log("async function : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					break;
				case FAIL:
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

	// GET /api/ytonny/enroll/{yTonnyNotiSeq} 예약 통역 신청 목록 조회
	async getYtonnyEnroll(context, yTonnyNotiSeq) {
		console.log("예약 통역 공고 상세 조회");

		try {
			let { data } = await http.get(`/ytonny/enroll/${yTonnyNotiSeq}`);
			console.log("async function : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					context.commit("SET_YTONNY_DETAIL", data.data);
					break;
				case FAIL:
					break;
			}
		} catch (err) {
			console.error(err);
		}
	},

	// POST /api/ytonny/match/{yTonnyNotiSeq}/{yTonnyNotiHelperSeq} 예약 통역 공고에서 헬퍼의 신청을 수락
	async insertYtonnyMatch(context, yTonnyNotiSeq, yTonnyNotiHelperSeq) {
		console.log("예약 통역 공고에서 헬퍼의 신청을 수락");

		let { data } = await http.post(
			`/ytonny/match/${yTonnyNotiSeq}/${yTonnyNotiHelperSeq}`,
			yTonnyNotiSeq,
			yTonnyNotiHelperSeq
		);

		try {
			console.log("async function : ", data);

			// service logic
			switch (data.resultCode) {
				case SUCCESS:
					break;
				case FAIL:
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
	}
};
