<template>
    <div class="mainBtnWrap">
        <div class="mainBtn">
            <router-link :to="{ name: href }">
                <div class="btnIcon">
                    <slot name="icon">아이콘</slot>
                </div>
                <span class="btnText">
                    {{ text }}
                </span>
            </router-link>
        </div>
    </div>
</template>

<script>
export default {
    name: "MainBtn",

    components: {},

    props: {
        text: {
            type: String,
            default: "버튼",
        },

        href: {
            type: String,
            default: "HomePage",
        },
    },
};
</script>

<style lang="scss" scoped>
.mainBtnWrap {
    display: inline-block;
    cursor: pointer;
}
.mainBtn {
    width: 200px;
    height: 200px;

    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;

    background-color: var(--light-color);
    box-shadow: 1px 1px 2px 1px rgba(0, 0, 0, 0.05);
    border-radius: 100%;

    transition: all 0.13s;

    .btnIcon {
        &::v-deep span {
            font-size: 5rem;
            margin-bottom: 1rem;
        }
    }

    a {
        text-align: center;
        text-decoration: none;
    }
    .btnText {
        font-size: 1.5rem;
        font-weight: 600;
    }

    &:hover {
        opacity: 0.8;
    }
}
</style>
