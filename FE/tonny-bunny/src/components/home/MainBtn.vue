<template>
    <div class="mainBtnWrap">
        <router-link :to="{ name: href }">
            <div class="mainBtn">
                <div class="btnIcon">
                    <slot name="icon">아이콘</slot>
                </div>
                <span class="btnText">
                    {{ text }}
                </span>
            </div>
        </router-link>
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

    a {
        text-decoration: none;
        text-align: center;
    }
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

    .btnText {
        font-size: 1.5rem;
        font-weight: 600;
    }

    &:hover {
        opacity: 0.8;
    }
}
</style>
