<template>
    <div>
        <div v-if="isFind">
            <title-text title="즉시 통역 찾기" text="지금 당장 번역이 필요한 고객을 찾습니다" />
            <JTonnyLoading />
        </div>
        <div v-else>
            <title-text
                title="즉시 통역 요청이 왔어요!"
                text="통역 요청글을 자세히 보고 가능한 통역을 신청하세요!" />
            <JTonnyLoading />
        </div>
        <router-link :to="{ name: 'JTonnyMatchingPage' }"><button>다음페이지</button></router-link>
        <large-btn text="대기 취소" @click.prevent="openModal" />

        <div v-for="i in 5" :key="i">
            <helper-card />
        </div>

        <AlarmModal
            v-show="isOpen"
            :isOpen="isOpen"
            title="확인"
            type="success"
            btnText2="다음"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @close-modal="closeModal"
            :link="{ name: 'HomePage' }">
            <template #content> 대기가 취소되었습니다<br />또 이용해주세요! </template>
        </AlarmModal>
    </div>
</template>

<script>
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

import { mapGetters } from "vuex";

export default {
    components: {
        JTonnyLoading,
        TitleText,
        LargeBtn,
        HelperCard,
        AlarmModal,
    },
    data() {
        return {
            isFind: false,
            isOpen: false,

            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyList: {},
            jtonnyRequst: {},
            subs: [], // 페이지 이탈할 때 unsubscribe()
        };
    },
    methods: {
        openModal(e) {
            e.preventDefault();
            this.isOpen = true;
        },

        closeModal() {
            this.isOpen = false;
        },
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
        console.log("userInfo", this.userInfo);
        console.log("helperInfo", this.userInfo.helperInfo);
        console.log("possibleLanguageList", this.userInfo.helperInfo.possibleLanguageList);

        let possibleLanguageList = this.userInfo.possibleLanguageList;
        const serverURL = "http://localhost:8080/api/stomp";
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

// import JTonnyLoading from '@/components/jtonny/JTonnyLoading.vue';
// export default {
//   components: { JTonnyLoading },};
</script>

<style></style>
