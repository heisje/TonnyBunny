<template lang="">
    <div class="dropdown w-100">
        <button
            class="btn dropdown-toggle w-100"
            :class="[disable === true ? 'disable' : '']"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false">
            <span>{{ value ? value : placeholder }}</span>
        </button>
        <div v-show="disable == false">
            <ul class="dropdown-menu w-100">
                <li v-for="dropdownItem in dropdownArray" :key="dropdownItem.value">
                    <button
                        class="dropdown-item w-100"
                        :class="value === dropdownItem.value ? 'ariaCurrent' : ''"
                        @click="changeValue(dropdownItem)"
                        type="button">
                        {{ dropdownItem.name }}
                    </button>
                </li>
            </ul>
        </div>
    </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
    name: "DropdownInput",
    data() {
        return {
            value: "",
        };
    },
    methods: {
        changeValue(item) {
            this.value = item.name;
            this.$emit("toggle", item.value);
            this.$emit("toggleItem", item);
        },

        changeEventParent(changeValue) {
            this.value = this.allCode[changeValue];
        },
    },
    props: {
        dropdownArray: {
            type: Array,
            description: "dropdownArray",
        },

        placeholder: {
            type: String,
            default: "선택해주세요",
            description: "placeholder",
        },

        disable: {
            type: Boolean,
            default: false,
            description: "변경불가",
        },
    },

    computed: {
        ...mapGetters({
            allCode: "getAllCode",
        }),
    },
};
</script>
<style lang="scss">
@import "@/scss/input.scss";
</style>
