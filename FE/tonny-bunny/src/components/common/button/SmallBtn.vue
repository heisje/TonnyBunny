<template>
    <div class="mediumBtn" :class="color">
        <a :href="link">{{ text }}</a>
    </div>
</template>

<script>
export default {
    name: "MediumBtn",

    props: {
        text: {
            type: String,
            default: "작은 버튼",
            description: "button tag",
        },

        link: {
            type: String,
            default: "/",
            description: "button link",
        },

        color: {
            type: String,
            default: "main",
            description: "button color",
        },

        disable: {
            type: Boolean,
            default: false,
            description: "button disable ",
        },
    },
};
</script>

<style lang="scss" scoped>
.mediumBtn {
    display: flex;
    align-items: center;
    justify-content: center;

    color: white;
    font-size: 0.85rem;
    font-weight: 600;

    width: 96px;
    height: 36px;
    border-radius: 4px;

    transition: all 0.15s;

    a {
        text-decoration: none;
        color: #fff;
    }

    &:hover {
        opacity: 0.9;
    }
}

.white {
    color: #505d68;
    background: #fff;
    border: 1px solid #505d68;
    font-weight: 600;

    a {
        color: #505d68;
    }
}

.main {
    background: linear-gradient(126.73deg, #ff8a65 15.12%, #ff7765 86.23%);
}

.carrot {
    background: #ff7765;
}

.dark {
    background: #424b5a;
}
.gray {
    background: #505d68;
}

.lightgray {
    color: #424b5a;
    background: #f2f5f7;
    font-weight: 600;

    a {
        color: #424b5a;
    }
}

.disable {
    color: #a1aeb7;

    a {
        color: #a1aeb7;
    }

    &:hover {
        opacity: 1;
    }
}
</style>
