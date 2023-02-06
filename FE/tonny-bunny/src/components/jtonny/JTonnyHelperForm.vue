<template lang="">
    <div>
        <div>헬퍼가 대기하는 곳</div>
    </div>
</template>
<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

import { mapGetters } from "vuex";

export default {
    data() {
        return {
            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyList: {},
            jtonnyRequst: {},
            subs: [], // 페이지 이탈할 때 unsubscribe()
        };
    },
    methods: {
        apply() {
            // this.jtonnyRequest 에 단가, this.userInfo.seq 넣기
            this.stompClient.send("/pub/jtonny/apply", JSON.stringify(this.jtonnyRequst), {});
        },
        cancel() {
            this.stompClient.send(
                "/pub/jtonny/apply/cancel",
                JSON.stringify(this.jtonnyRequst),
                {}
            );
        },
    },
    mounted() {
        let possibleLanguageList = this.userInfo.possibleLanguageList;
        const serverURL = "http://localhost:8080/stomp";
        let socket = new SockJS(serverURL);
        this.stompClient = Stomp.over(socket);
        console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`);
        this.stompClient.connect(
            {},
            () => {
                // 소켓 연결 성공
                this.connected = true;
                console.log("소켓 연결 성공");
                // 본인 seq 를 구독합니다.
                possibleLanguageList.forEach((lang) => {
                    let sub = this.stompClient.subscribe(`/sub/jtonny/request/${lang}`, (res) => {
                        console.log("즉시통역 요청이 도착했습니다.", res.body);

                        // 받은 데이터를 json 으로 파싱하고 dictionary 에 넣어줍니다.
                        let request = JSON.parse(res.body);
                        this.jtonnyList[request.clientSeq] = request;
                    });
                    this.subs.push(sub);

                    sub = this.stompClient.subscribe(
                        `/sub/jtonny/request/${lang}/cancel`,
                        (res) => {
                            console.log("즉시통역 요청이 취소되었습니다.", res.body);

                            let request = JSON.parse(res.body);
                            delete this.jtonnyList[request.clientSeq];
                        }
                    );
                    this.subs.push(sub);
                });
            },
            (error) => {
                // 소켓 연결 실패
                console.log("소켓 연결 실패", error);
                this.connected = false;
            }
        );
    },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),
    },
};
</script>
<style lang=""></style>
