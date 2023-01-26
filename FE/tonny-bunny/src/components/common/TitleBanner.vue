<template lang="">
    <div id="titleBanner">
        <div class="fullSizeBanner">
            <div>
                <h1>{{ title }}</h1>
                <p>{{ text }}</p>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "TitleBanner",
    props: {
        title: {
            type: String,
            default: "title",
            description: "button tag",
        },
        text: {
            type: String,
            default: "description",
            description: "button link",
        },
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/variable.scss";

#titleBanner {
    width: 100vw;
    height: 100px;
    left: 0;
    position: absolute;

    .fullSizeBanner {
        position: absolute;
        // top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 100vw;
        height: 100px;
        background-color: var(--thin-color);

        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;

        h1 {
            margin-bottom: 4px;
        }

        p {
            color: var(--sub-color);
            @extend .label;
        }
    }
}
</style>
