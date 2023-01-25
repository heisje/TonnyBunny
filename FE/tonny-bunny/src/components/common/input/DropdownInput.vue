<template lang="">
    <div class="dropdown">
        <button
            class="btn dropdown-toggle"
            type="button"
            data-bs-toggle="dropdown"
            aria-expanded="false">
            <span>{{ value ? value : placeholder }}</span>
        </button>
        <ul class="dropdown-menu">
            <li v-for="dropdownItem in dropdownArray" :key="dropdownItem">
                <button
                    class="dropdown-item"
                    :class="value === dropdownItem ? 'ariaCurrent' : ''"
                    @click="changeValue(dropdownItem)"
                    type="button">
                    {{ dropdownItem }}
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
            this.value = item;
            this.$emit("toggle", this.value);
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
<style lang="scss"></style>
