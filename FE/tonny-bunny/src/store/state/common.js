export default {
    isLoading: false,

    /* Modal */
    isAlarmModalOpen: false,
    isTonnyModalOpen: false,
    isBunnyModalOpen: false,

    /* Code */
    // langCode: {
    //     한국어: "0020001",
    //     영어: "0020002",
    //     일본어: "0020003",
    // },

    // bunnySituCode: {
    //     인쇄물: "0050001",
    //     영상: "0050002",
    //     기타: "0050003",
    // },

    bunnyStateCode: {
        모집중: "0100001",
        진행중: "0100002",
        완료됨: "0100003",
    },

    langCodeList: ["0020000", "0020001", "0020002"], // 기타, 한국어, 영어
    taskCodeList: ["0030001", "0030002", "0030003"], // 즉시통역, 예약통역, 번역
    tonnySituCodeList: ["0040000", "0040001", "0030002", "0030003"], // 기타, 일상, 경찰..
    quotationStateCodeList: ["0070001", "0070002", "0070003", "0070004"], // 선택, 미선택, 완료됨, 거절됨
    taskStateCodeList: ["0090001", "0090002", "0090003"], // 모집중, 진행중, 완료됨
    hourCodeList: ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"], // 시간
    minuteCodeList: ["05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"], // 분

    userCode: null,
    langCode: null,
    taskCode: null,
    tonnySituCode: null,
    bunnySituCode: null,
    quotationStateCode: null,
    taskStateCode: null,
    pointLogStatusCode: null,
};
