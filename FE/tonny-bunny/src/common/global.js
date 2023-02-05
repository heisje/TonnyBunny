// common > global.js
// 모든 컴포넌트가 공유할 수 있는 글로벌 영역 입니다.

export default {
    setDate(time) {
        const options = {
            year: "numeric",
            month: "short",
            day: "numeric",
        };

        const currentTime = Date.now();
        const oldTime = new Date(time);
        const pastTime = new Date(currentTime - oldTime);
        const sec = Math.floor(pastTime / 1000);

        let temp;
        if (sec >= 86400) {
            temp = oldTime.toLocaleDateString("ko-KR", options);
        } else if (sec >= 3600) {
            temp = Math.floor(sec / 3600) + "시간 지남";
        } else {
            temp = Math.floor(sec / 60) + "분 지남";
        }
        return temp;
    },

    // 공통코드 변환 코드. 지금 백에서 변환되어 오기때문에 필요없음
    // setUserCode(userCode) {
    //     let isHelper = false;
    //     // let isAdmin = false;
    //     console.log(userCode);

    //     // 앞 세글자는 유저코드
    //     const FRONT = userCode.substr(0, 3);
    //     const BACK = userCode.substr(0, 3);

    //     console.log(FRONT);
    //     console.log(BACK);

    //     switch (FRONT) {
    //         case "001":
    //             isHelper = false;
    //             break;
    //         case "002":
    //             isHelper = true;
    //             break;
    //         case "003":
    //             // isAdmin = true;
    //             break;
    //     }

    //     // 뒤 네글자는 언어코드
    //     switch (BACK) {
    //         case "0001":
    //             break;
    //         case "0002":
    //             break;
    //         case "003":
    //             break;
    //     }
    //     return { isHelper: isHelper, possibleLanguage: 1 };
    // },
};
