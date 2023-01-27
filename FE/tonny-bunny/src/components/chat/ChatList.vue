<template lang="">
    <div>
        <div v-for="i in [1, 2, 3, 4, 5]" :key="i">
            <router-link :to="{ name: 'ChatDetailPage' }" class="nav-link" aria-current="page">
                <chat-item />
            </router-link>
        </div>
    </div>
</template>
<script>
import ChatItem from "./ChatItem.vue";
export default {
    components: {
        ChatItem,
    },
};
</script>
<style lang=""></style>
