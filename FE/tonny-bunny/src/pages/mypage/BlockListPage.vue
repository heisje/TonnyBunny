<template>
    <title-banner title="차단목록" text="차단 내역을 확인할 수 있습니다." />

    <div class="container w-75">
        <div v-if="blockCnt != 0">
            <div v-for="block in blockList" :key="block.seq">
                <UserProfileItem
                    btnText="차단해제"
                    :helperInfo="block"
                    @clickBtn="deleteBlock(block.seq)"
                />
            </div>
        </div>
        <div v-else>
            <br />
            <br />
            <title-text type="h2" title="차단한 한 유저가 없습니다🐾" class="text-center" />
        </div>
        <br />
        <div class="w-50 mx-auto">
            <router-link :to="{ name: 'MyPage' }">
                <smallBtn color="carrot" class="w-100" text="확인"></smallBtn>
            </router-link>
        </div>
    </div>
</template>

<script>
import UserProfileItem from "@/components/mypage/UserProfileItem.vue";
import TitleText from "@/components/common/TitleText.vue";
import SmallBtn from "@/components/common/button/SmallBtn.vue";
import TitleBanner from "@/components/common/TitleBanner.vue";
import { mapGetters } from "vuex";
import http from "@/common/axios";
export default {
    name: "BlockListPage",
    data() {
        return {
            blockList: [],
            blockCnt: 0,
        };
    },

    components: {
        UserProfileItem,
        TitleText,
        SmallBtn,
        TitleBanner,
    },
    methods: {
        async getBlockList(seq) {
            try {
                console.log("SEQ : " + seq);
                let res = await http.get(`/mypage/${seq}/block`);
                console.log(res);
                if (res.data.resultCode == "SUCCESS") {
                    console.log("블락리스트 불러오기 성공");
                    this.blockList = res.data.data;
                    this.blockCnt = res.data.data.length;
                    console.log("blockList : " + this.blockList);
                }
            } catch (error) {
                console.log(error);
                console.log("블락 리스트 불러오기 실패...");
            }
        },

        async deleteBlock(helperSeq) {
            const userSeq = this.userInfo.seq;
            try {
                let res = await http.delete(`mypage/${userSeq}/block/${helperSeq}`);

                console.log(res);
                if (res.data.resultCode == "SUCCESS") {
                    console.log("성공");
                    this.blockCnt -= 1;
                }
            } catch (error) {
                console.log(error);
            }
        },
    },
    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),
    },
    created() {
        const userSeq = this.userInfo.seq;
        this.getBlockList(userSeq);
    },
};
</script>

<style></style>
