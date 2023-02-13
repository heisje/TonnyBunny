<template>
    <div>
        <board-detail-content />

        <CommentListItem />

        <alarm-modal
            title="경고"
            type="danger"
            btnText1="아니오"
            btnText2="예"
            btnColor1="main"
            btnColor2="carrot"
            btnFontColor1="white"
            btnFontColor2="white"
            @clickBtn1="closeModal"
            @clickBtn2="clickBtn2">
            <template #content>
                안녕하세요
                <br />
                안녕하십니까 안녕하세요 안녕하세요 안녕하세요 안녕하세요
            </template>
        </alarm-modal>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

import CommentListItem from "@/components/board/CommentListItem.vue";
import BoardDetailContent from "@/components/board/BoardDetailContent.vue";
import AlarmModal from "@/components/common/modal/AlarmModal.vue";

export default {
    components: {
        CommentListItem,
        BoardDetailContent,
        AlarmModal,
    },

    computed: {
        ...mapGetters({ boardDetail: "getBoardDetail" }),
    },

    mounted() {
        this.$store.dispatch("getBoardDetail", this.$route.params.id);
    },
};
</script>

<style></style>
