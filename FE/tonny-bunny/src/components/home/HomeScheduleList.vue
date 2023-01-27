<template>
    <div class="scheduleContainer">
        <div class="scheduleWrap">
            <div class="schedule">
                <div class="title">
                    <title-text title="오늘 일정" type="h1-small" top="0" bottom="8"></title-text>
                    <div class="more">
                        <router-link :to="{ name: 'SchedulePage' }">더보기</router-link>
                    </div>
                </div>
                <div class="item">
                    <div v-for="(item, index) in scheduleList" :key="index">
                        <schedule-list-item
                            :color="item.color"
                            :dates="item.dates"
                            :text="item.text">
                        </schedule-list-item>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
/* eslint-disable */
import TitleText from "@/components/common/TitleText.vue";
import ScheduleListItem from "@/components/common/ScheduleListItem.vue";

export default {
    name: "ScheduleList",

    components: {
        TitleText,
        ScheduleListItem,
    },

    data() {
        return {
            scheduleList: [
                { color: "carrot", dates: { date: "오늘", time: "시간" }, text: "일정내용1" },
                { color: "light", dates: { date: "오늘", time: "시간" }, text: "일정내용1" },
            ],
        };
    },
};
</script>

<style lang="scss" scoped>
.title {
    display: flex;
}

.more {
    display: inline-block;
    width: 40px;
    text-decoration: underline;
    cursor: pointer;

    a {
        color: var(--sub-color);
    }
}

@media (min-width: 1200px) {
    .scheduleContainer {
        // width: 100%;
        margin-right: 1rem;
    }
}
</style>
