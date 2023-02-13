export default {
    isLoading: false,

    /* Modal */
    isAlarmModalOpen: false,
    isTonnyModalOpen: false,
    isBunnyModalOpen: false,
    isScheduleModalOpen: false,

    /* Code */
    bunnyStateCode: [
        { value: "0100001", name: "모집중" },
        { value: "0100002", name: "진행중" },
        { value: "0100003", name: "완료됨" },
    ],

    hourCodeList: ["00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"], // 시간
    minuteCodeList: ["00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"], // 분

    allCode: {
        "0030001": "즉시통역",
        "0030002": "예약통역",
        "0030003": "번역",
        "0070001": "미선택",
        "0070002": "선택",
        "0070003": "작업완료",
        "0070004": "거절",
        "0040000": "기타",
        "0040001": "일상",
        "0040002": "의료",
        "0040003": "법",
        "0020000": "기타",
        "0020001": "한국어",
        "0020002": "영어",
        "0020003": "일본어",
        "0110001": "충전",
        "0110002": "출금",
        "0110003": "거래",
        "0090001": "모집중",
        "0090002": "진행중",
        "0090003": "완료됨",
        "0050001": "인쇄물",
        "0050002": "영상",
        "0050003": "기타",
        "0010001": "고객",
        "0010002": "헬퍼",
        "0010003": "관리자",
    },
    userCode: null,
    langCode: null,
    taskCode: null,
    tonnySituCode: null,
    bunnySituCode: null,
    quotationStateCode: null,
    taskStateCode: null,
    pointLogStatusCode: null,
};
