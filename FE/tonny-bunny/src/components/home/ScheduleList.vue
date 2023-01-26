<template>
    <div class="scheduleContainer">
        <div class="scheduleWrap">
            <div class="schedule">
                <div class="title">
                    <title-text title="오늘 일정" type="h1-small" top="0" bottom="0"></title-text>
                </div>
                <div class="item">
                    <schedule-list-item></schedule-list-item>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ScheduleListItem from "./ScheduleListItem.vue";
import TitleText from "../common/TitleText.vue";

export default {
    name: "ScheduleList",

    components: { ScheduleListItem, TitleText },
};
</script>

<style lang="scss" scoped></style>
