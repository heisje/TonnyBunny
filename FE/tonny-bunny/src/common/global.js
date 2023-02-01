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
};
