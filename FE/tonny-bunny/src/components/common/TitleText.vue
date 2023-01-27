<template>
    <div id="TitleText" :style="`margin-top:${top}px`" :class="center ? 'center' : ''">
        <p v-if="important" class="important">*필수</p>

        <h1 v-if="type == 'h1'">{{ title }}</h1>
        <h1 v-if="type == 'h1-small'" class="h1-small">{{ title }}</h1>
        <h2 v-if="type == 'h2'" class="h2">{{ title }}</h2>
        <p :style="`margin-bottom:${bottom}px`">{{ text }}</p>
    </div>
</template>

<script>
export default {
    name: "TitleText",

    props: {
        important: {
            type: Boolean,
            default: false,
            description: "필수",
        },
        title: {
            type: String,
            default: "title",
            description: "title",
        },
        type: {
            type: String,
            default: "h1",
            description: "type",
        },
        small: {
            type: Boolean,
            default: false,
            description: "small",
        },
        text: {
            type: String,
            default: "",
            description: "text",
        },
        top: {
            type: String,
            default: "36",
            description: "margin-top",
        },
        bottom: {
            type: String,
            default: "4",
            description: "margin-bottom",
        },
        center: {
            type: Boolean,
            default: false,
            description: "text-align-center",
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/variable.scss";

#TitleText {
    // margin-top: 36px;
    cursor: default;

    width: 100%;
    &.center {
        text-align: center;
    }
    .important {
        color: var(--danger-color);
    }
    h1 {
        margin-bottom: 4px;
    }
    h2 {
        margin-bottom: 4px;
    }
    p {
        color: var(--sub-color);
    }
}
</style>
