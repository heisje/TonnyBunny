<template lang="">
    <div>
        <h1>gkdl</h1>
        <div v-for="i in [1, 2, 3, 4, 5]" :key="i">
            <QuestCard />
        </div>
    </div>
</template>
<script>
import QuestCard from "../common/card/QuestCard.vue";
export default {
    components: {
        QuestCard,
    },
};
</script>
<style lang=""></style>
