<template>
    <span class="videoContainer w-100" v-if="streamManager" style="display: inline-block">
        <ov-video :stream-manager="streamManager" />
        <div class="ms-2">
            <h2>{{ clientData }}</h2>
        </div>
    </span>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
    name: "UserVideo",

    components: {
        OvVideo,
    },

    props: ["streamManager"],

    computed: {
        clientData() {
            const { clientData } = this.getConnectionData();
            return clientData;
        },
    },

    methods: {
        getConnectionData() {
            const { connection } = this.streamManager.stream;
            return JSON.parse(connection.data);
        },
    },
};
</script>

<style lang="scss" scoped>
.videoContainer {
    border: 1px solid rgba(0, 0, 0, 0.08);
    box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.08);
    background-color: var(--thin-color);
    padding: 12px;
    border-radius: 8px;
}
</style>
