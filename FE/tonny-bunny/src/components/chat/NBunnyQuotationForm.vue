<template lang="">
    <div class="d-flex justify-content-center customFormWrap w-100">
        <form class="customForm" @submit.prevent="submitForm(event)">
            <title-text
                important
                type="h2"
                title="고객의 어떤 공고를 해결하시겠어요?"
                text="해결하고 싶은 공고를 선택해주세요"
                class="mb-3" />

            <div class="">
                <label for=""></label>
                <DropdownInput
                    :dropdownArray="bunntTitleList"
                    placeholder="공고를 선택하세요"
                    @toggle="selectBunny"
                    @toggleInx="findIdx" />
            </div>

            <title-text type="h2" title="제목" />

            <input type="text" v-model="title" readonly />

            <title-text type="h2" title="내용" />

            <textarea type="textarea" v-model="content" readonly></textarea>

            <title-text type="h2" title="언어" bottom="20" />

            <div class="d-flex flex-row w-100 mb-5">
                <div class="w-100">
                    <input type="text" v-model="startLang" readonly />
                </div>

                <div class="swap">
                    <span class="material-symbols-outlined"> compare_arrows </span>
                </div>

                <div class="w-100">
                    <input type="text" v-model="endLang" readonly />
                </div>
            </div>

            <title-text type="h2" title="카테고리" class="mb-3" />

            <input type="text" v-model="category" readonly />

            <title-text type="h2" title="마감 기한" class="mb-3" />

            <div class="w120">
                <input type="text" v-model="date" readonly />
            </div>

            <title-text
                important
                type="h2"
                title="번역의 금액을 설정해주세요"
                text="번역 시 받으실 금액을 설정해주세요"
                class="mb-3" />

            <div class="d-flex">
                <div class="col-11">
                    <input
                        type="text"
                        @input="changeInput"
                        class="w-100"
                        placeholder="ex)1000"
                        v-model="totalPrice" />
                </div>
                <div class="backlabel col-2">
                    <h3>캐럿</h3>
                </div>
            </div>

            <!-- <title-text type="h2" title="[선택] 사진" text="추가 사진을 올려주세요" />
            <input
                type="file"
                multiple
                accept="image/*"
                @change="insertImageList"
                class="quotationFileList"
            /> -->

            <agree-input @toggle="(e) => (agreeValue = e)" />
            <medium-btn
                style="width: 100%"
                text="작성하기"
                color="main"
                @click.prevent="submitForm(event)" />
        </form>
    </div>
</template>
<script>
import MediumBtn from "../common/button/MediumBtn.vue";
import AgreeInput from "../common/input/AgreeInput.vue";
import DropdownInput from "../common/input/DropdownInput.vue";
import TitleText from "../common/TitleText.vue";
import http from "@/common/axios";
import { mapGetters } from "vuex";

export default {
    name: "BunnyQuotationForm",
    components: { TitleText, DropdownInput, MediumBtn, AgreeInput },
    data() {
        return {
            // 어떤 공고를 골랐는지
            dropdownValue: "",
            bunnyList: "",
            bunntTitleList: [],
            title: "",
            content: "",
            startLang: "",
            endLang: "",
            category: "",
            date: "",

            // 견적서 POST FORM
            s_index: "",
            totalPrice: "",
            fileList: [],

            agreeValue: false,
        };
    },

    computed: {
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ getBunnySituCode: "getBunnySituCode" }),
        ...mapGetters({ getAllCode: "getAllCode" }),
    },

    methods: {
        selectBunny(e) {
            this.dropdownValue = e;
        },

        findIdx(index) {
            // console.log(index);
            this.s_index = index;
            this.title = this.bunnyList[index].title;
            this.content = this.bunnyList[index].content;
            this.startLang = this.getAllCode[this.bunnyList[index].startLangCode];
            this.endLang = this.getAllCode[this.bunnyList[index].endLangCode];
            this.category = this.getAllCode[this.bunnyList[index].bunnySituCode];
            this.date =
                this.bunnyList[index].endDateTime.slice(0, 4) +
                "년 " +
                this.bunnyList[index].endDateTime.slice(5, 7) +
                "월 " +
                this.bunnyList[index].endDateTime.slice(8, 10) +
                "일";
            console.log(this.bunnyList[index].endDateTime.slice(0, 4));
        },

        async submitForm() {
            if (!this.dropdownValue) {
                alert("공고를 선택하지 않으셨습니다!");
                return;
            }

            if (!this.totalPrice) {
                alert("금액을 입력하지 않으셨습니다!");
                return;
            }

            if (!this.agreeValue) {
                alert("약관에 동의하지 않으셨습니다!");
                return;
            }

            const s_bunny = this.bunnyList[this.s_index];
            // console.log("bunnySeq : ", s_bunny.seq);
            // console.log("clientSeq : ", s_bunny.client.seq);
            // console.log("helperSeq : ", this.$store.state.account.userInfo.seq);
            // console.log("startLangCode : ", s_bunny.startLangCode);
            // console.log("endLangCode : ", s_bunny.endLangCode);
            // console.log("startDateTime : ", s_bunny.startDateTime);
            // console.log("endDateTime : ", s_bunny.endDateTime);
            // console.log("title : ", s_bunny.title);
            // console.log("content : ", s_bunny.content);
            // console.log("totalPrice : ", this.totalPrice);
            // console.log("agreeValue : ", this.agreeValue);

            const payload = {
                bunnySeq: s_bunny.seq,
                clientSeq: s_bunny.client.seq,
                helperSeq: this.$store.state.account.userInfo.seq,
                startLangCode: s_bunny.startLangCode,
                endLangCode: s_bunny.endLangCode,
                startDateTime: s_bunny.startDateTime,
                endDateTime: s_bunny.endDateTime,
                title: s_bunny.title,
                content: s_bunny.content,
                totalPrice: this.totalPrice,
                bunnyQuotationImageRequestDtoList: this.fileList,
            };

            await this.$store.dispatch("insertBunnyQuotation", payload);
            const bunnyQuotationSeq = this.$store.state.bunny.createdQuotationSeq; // TODO : insert 하고나서 quotation seq 받기!
            this.enterChatRoom(bunnyQuotationSeq);
            // // file upload
            // let formData = new FormData();
            // this.fileList = document.querySelector(".quotationFileList").files;
            // console.log("bunnyQuotationImageRequestDtoList : ", this.fileList);

            // if (this.fileList.length > 0) {
            //     const quotationFileList = Array.from(this.fileList);
            //     quotationFileList.forEach((file) => formData.append("file", file));
            // }
        },
        enterChatRoom(bunnyQuotationSeq) {
            const s_bunny = this.bunnyList[this.s_index];
            const title = s_bunny.title;
            const clientSeq = s_bunny.client.seq;
            const userSeq = this.$store.getters.getUserInfo.seq;
            this.$store.dispatch("getChatRoomInfo", {
                userSeq: userSeq,
                anotherUserSeq: clientSeq,
            });
            this.$store.commit("SET_TRUE_SEND_URL_MESSAGE");
            this.$store.commit("SET_URL_MESSAGE", {
                urlPage: "QuotationDetailPage",
                urlPageSeq: bunnyQuotationSeq,
                message: "'" + title + "' 에 대한 견적서가 도착했습니다!",
            });
            this.$router.push({ name: "ChatDetailPage" });
        },
    },

    async created() {
        const clientSeq = this.$route.params.clientSeq;
        let res = await http.get(`/bunny/${clientSeq}/user`, clientSeq);

        const SUCCESS = "SUCCESS";
        const FAIL = "FAIL";

        try {
            // service logic
            switch (res.data.resultCode) {
                case SUCCESS:
                    // context.commit("SET_BUNNY_SEQ", res.data.data);
                    console.log(res.data.data);
                    this.bunnyList = res.data.data;
                    this.bunnyList.forEach((bunny) => {
                        this.bunntTitleList.push(bunny.title);
                    });
                    break;
                case FAIL:
                    break;
            }
            return res.data;
        } catch (err) {
            console.error(err);

            // exception
            if (err.response.status == 403) {
                alert("로그인 하세요");
            }

            return res.data;
        }
    },
};
</script>

<style lang="scss">
@import "@/scss/input.scss";
</style>
