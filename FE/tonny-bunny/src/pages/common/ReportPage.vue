<template>
    <div class="d-flex flex-column align-items-center">
        <TitleText title="신고하기" center />
        <div class="d-flex justify-content-center">
            <img src="@/assets/noProfile.png" alt="" />
        </div>
        <div class="d-flex justify-content-center">
            <div>닉네임</div>
        </div>
        <TitleText title="신고하시는 사유를 골라주세요" type="h2" center />

        <form class="customForm d-flex flex-column align-items-center">
            <DropdownInput
                :dropdownArray="['욕설/비방', '범죄', '허위정보', '기타']"
                placeholder="카테고리"
                @toggle="(e) => (dropdownValue = e)" />
            <TitleText title="자세한 신고내용을 적어주세요" type="h2" center />
            <textarea class="w-100" v-model="content"></textarea>
        </form>

        <div class="d-flex justify-content-center">
            <router-link :to="{ name: 'HomePage' }">
                <small-btn class="mr-3" text="취소" />
            </router-link>

            <!-- <small-btn text="신고하기" @click.prevent="openModal" /> -->
            <small-btn class="ml-3" text="신고하기" @click.prevent="openModal" />
        </div>
        <alarm-modal
            title="확인"
            type="success"
            btnText2="예"
            btnColor2="carrot"
            btnFontColor2="white"
            @clickBtn1="closeModal"
            @clickBtn2="clickBtn2">
            <template #content>
                신고가 완료되었습니다.
                <br /><br />
                항상 노력하는 토니버니가 되겠습니다.
            </template>
        </alarm-modal>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
import DropdownInput from "@/components/common/input/DropdownInput.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";
import http from "@/common/axios";

export default {
    data() {
        return {
            isOpen: false,
            dropdownValue: "",
            content: "",
        };
    },
    props: {
        userSeq: {
            type: Number,
            default: 1,
        },

        helperSeq: {
            type: Number,
            default: 2,
        },
    },
    components: {
        TitleText,
        DropdownInput,
        SmallBtn,
        AlarmModal,
    },
    methods: {
        openModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        closeModal(e) {
            e.preventDefault();
            this.$store.commit("TOGGLE_ALARM_MODAL");
        },

        clickBtn2() {
            this.submitForm();
        },
        async submitForm() {
            if (this.content == "") {
                alert("내용을 입력해주세요.");
            }
            try {
                let res = await http.post(`/mypage/${this.userSeq}/report/${this.helperSeq}`);
                if (res.data.resultCode == "SUCCESS") {
                    console.log("신고성공");
                    console.log(res);
                    this.$store.commit("TOGGLE_ALARM_MODAL");
                    this.$router.push({ name: "HomePage" });
                } else {
                    console.log("신고실패");
                    this.$store.commit("TOGGLE_ALARM_MODAL");
                    this.$router.push({ name: "HomePage" });
                }
            } catch (error) {
                console.log(error);
                console.log("신고실패");
                this.$store.commit("TOGGLE_ALARM_MODAL");
                this.$router.push({ name: "HomePage" });
            }
        },
    },
};
</script>

<style></style>
