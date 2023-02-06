<template>
    <div>
        <div v-if="!isFind">
            <title-text title="🐰통역을 도와줄 헬퍼를 찾는 중입니다.." />
            <large-btn color="light" font="live" text="찾기 취소" style="width: 100%" />
            <JTonnyLoading></JTonnyLoading>
        </div>
        <div v-else>
            <title-text
                title="🐰통역 가능한 헬퍼가 도착하고 있습니다..."
                text="헬퍼의 프로필을 자세히 보고 통역을 부탁한 헬퍼를 수락해주세요" />
            <large-btn color="light" font="live" text="찾기 취소" style="width: 100%" />
            <JTonnyLoading></JTonnyLoading>
            <div v-for="i in 2" :key="i">
                <helper-card />
            </div>
        </div>
        <small-btn color="light" font="live" text="찾으면" @click.prevent="찾아짐()" />
        <small-btn color="light" font="live" text="수락하기누름" @click.prevent="openModal" />

        <AlarmModal
            v-show="isOpen1"
            :isOpen="isOpen1"
            title="경고"
            type="danger"
            btnText1="취소"
            btnText2="진행"
            btnColor1="light"
            btnColor2="carrot"
            btnFontColor1="sub"
            btnFontColor2="white"
            @close-modal="closeModal"
            :link="{ name: 'LivePage' }">
            <template #content>
                수락하시면 바로 해당 헬퍼와 동시 통역이 진행됩니다. 진행하시겠습니까?
            </template>
        </AlarmModal>
    </div>
</template>

<script>
import JTonnyLoading from "@/components/jtonny/JTonnyLoading.vue";
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import HelperCard from "@/components/common/card/HelperCard.vue";
import LargeBtn from "@/components/common/button/LargeBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import { mapGetters } from "vuex";

import Stomp from "webstomp-client";
import SockJS from "sockjs-client";

export default {
    data() {
        return {
            isFind: false,
            isOpen1: false,
            stompClient: null, // 페이지 이탈할 때 끊어주기
            jtonnyList: {},
        };
    },
    components: {
        JTonnyLoading,
        TitleText,
        SmallBtn,
        HelperCard,
        LargeBtn,
        AlarmModal,
    },
    methods: {
        찾아짐() {
            this.isFind = true;
        },
        openModal(e) {
            e.preventDefault();
            this.isOpen1 = true;
        },

        closeModal() {
            this.isOpen1 = false;
        },
        accept() {
            this.stompClient.send(`/pub/jtonny/accept`, JSON.stringify(this.jtonnyRequest), {});
        },
        reject() {
            this.stompClient.send(`/pub/jtonny/reject`, JSON.stringify(this.jtonnyRequest), {});
        },
    },
    mounted() {
        let clientSeq = this.jtonnyRequest.clientSeq;
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
                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}`, (res) => {
                    console.log("즉시통역 요청이 도착했습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    this.jtonnyList[request.helperSeq] = request;
                });
                this.stompClient.subscribe(`/sub/jtonny/apply/${clientSeq}/cancel`, (res) => {
                    console.log("즉시통역 요청이 취소되었습니다.", res.body);

                    // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
                    let request = JSON.parse(res.body);
                    delete this.jtonnyList[request.helperSeq];
                });
                this.stompClient.send(
                    `/pub/jtonny/request`,
                    JSON.stringify(this.jtonnyRequest),
                    {}
                );
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
            jtonnyRequest: "getJtonnyRequest",
        }),
    },
};
</script>

<style></style>
