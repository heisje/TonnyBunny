<template>
    <transition name="fade">
        <div v-if="isDeleted == false" class="d-flex align-items-center justify-content-between">
            <div class="d-flex justify-content-around align-items-center mb-3">
                <user-profile-img
                    :profileImagePath="helperInfo?.profileImagePath"
                    class="mr-5"
                    width="48" />
                <title-text type="h2" :title="helperInfo.nickName" class="text-center mt-4" />
            </div>
            <x-small-btn :text="btnText" color="carrot" font="white" @click="clickBtn" />
        </div>
    </transition>
</template>

<script>
import XSmallBtn from "../common/button/XSmallBtn.vue";
import TitleText from "@/components/common/TitleText.vue";
import UserProfileImg from "../common/UserProfileImg.vue";
export default {
    name: "UserProfileItem",
    components: {
        XSmallBtn,
        TitleText,
        UserProfileImg,
    },
    data() {
        return {
            isDeleted: false,
        };
    },
    props: {
        helperInfo: {
            type: Object,
        },
        btnText: {
            type: String,
            default: "Click!",
        },
    },
    methods: {
        clickBtn() {
            this.isDeleted = true;
            this.$emit("clickBtn");
        },
    },
};
</script>
<style>
.fade-leave-active {
    transition: opacity 0.3s;
}
.fade-leave-to {
    opacity: 0;
}
</style>
