<template lang="">
    <div class="dropdown w-100">
        <button
            class="btn dropdown-toggle w-100"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false">
            <span>{{ value ? value : placeholder }}</span>
        </button>
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
</template>
<script>
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
    },
};
</script>
<style lang="scss">
@import "@/scss/input.scss";
</style>
