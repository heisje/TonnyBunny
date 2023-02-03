export default {
    // pagination
    limit: 10,
    offset: 0,
    listRowCount: 10,
    pageLinkCount: 10,
    currentPageIdx: 1,

    // ytonny form object
    ytonnyForm: {
        page: 0,
        size: 5,

        clientSeq: 0, // 공고를 만드는 client seq

        title: "", // 제목
        content: "", // 내용

        startDateTime: "2023-02-01T12:00:00", // 날짜선택
        estimateTime: "00:30", // 소요시간
        estimatePrice: 0, // 예상 캐럿

        startLangCode: "0020001", // 내언어
        endLangCode: "0020002", // 통역언어

        tonnySituCode: "0040000", // 카테고리
    },

    // ytonny
    ytonnyListTotalCount: 0,
    ytonnyList: [],
    ytonnyDetail: {},
};
