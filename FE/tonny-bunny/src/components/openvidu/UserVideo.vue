<template>
    <span v-if="streamManager" style="display: inline-block" class="w-100">
        <ov-video :stream-manager="streamManager" />
        <!-- <div>
            <p>{{ clientData }}</p>
        </div> -->
    </span>
</template>

<script>
import OvVideo from "./OvVideo";

export default {
    name: "UserVideo",

    components: {
        OvVideo,
    },

    props: {
        streamManager: Object,
    },

    // computed: {
    //     clientData() {
    //         const { clientData } = this.getConnectionData();
    //         return clientData;
    //     },
    // },

    // methods: {
    //     getConnectionData() {
    //         const { connection } = this.streamManager.stream;
    //         return JSON.parse(connection.data);
    //     },
    // },
};
</script>
