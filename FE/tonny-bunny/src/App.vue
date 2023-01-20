<template>
    <router-view name="header"></router-view>
    <main>
        <router-view></router-view>
    </main>
    <router-view name="footer"></router-view>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";

export default {
    name: "App",

    components: {},

    data() {
        return {
            tt: 200,
        };
    },

    setup() {
        const store = useStore();

        // console.log(store);
        const counter = computed(() => store.state.counter);
        const count = store.state.temp.count;

        return { counter, count };
    },
};
</script>

<style lang="scss">
@import "@/scss/global.scss";
</style>
