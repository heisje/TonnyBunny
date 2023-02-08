<template lang="">
    <div>
        <div v-for="i in [1, 2, 3, 4, 5]" :key="i">
            <QuestCard @clickRightBtn="clickRightBtn" />
        </div>
    </div>
</template>
<script>
import QuestCard from "../common/card/QuestCard.vue";
export default {
    components: {
        QuestCard,
    },
    methods: {
        clickRightBtn() {
            this.$router.push({ name: "YTonnyDetailPage", params: { id: 1 } });
        },
    },
};
</script>
<style lang=""></style>
