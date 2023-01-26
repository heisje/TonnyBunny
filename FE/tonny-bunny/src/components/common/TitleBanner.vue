<template lang="">
    <div id="titleBanner">
        <div class="bannerWrap">
            <div class="fullSizeBanner">
                <div>
                    <h1>{{ title }}</h1>
                    <p>{{ text }}</p>
                </div>
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
    // position: static;
    width: 100%;
    height: 120px;
    left: 0;

    margin-bottom: 64px;

    cursor: default;

    .fullSizeBanner {
        height: 120px;

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

.bannerWrap {
    background-color: var(--thin-color);
    position: absolute;
    left: 0;
    width: 100%;
}
</style>
