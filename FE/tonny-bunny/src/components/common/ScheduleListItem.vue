<template>
    <div class="scheduleListItemContainer">
        <div class="scheduleListItemWrap" :class="color">
            <div class="scheduleListItem">
                <span class="material-symbols-outlined icon"> done </span>
                <div class="info">
                    <span class="itemDate">
                        <span v-if="today" class="date">오늘</span>
                        <span v-else class="date">{{ date }}</span>
                        <span class="time">{{ startTime }} ~ {{ endTime }}</span>
                    </span>
                    <span class="itemText text-truncate col">{{ text }}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ScheduleListItem",

    components: {},

    props: {
        color: {
            type: String,
            default: "light",
        },

        text: {
            type: String,
            default: "일정내용일정내용일정내용일정내용일정내용일정내용일정내용",
        },

        today: {
            type: Boolean,
        },

        startDateTime: {
            type: String,
        },

        endDateTime: {
            type: String,
        },
    },

    computed: {
        date() {
            return this.startDateTime.split("T")[0];
        },
        startTime() {
            return this.startDateTime.split("T")[1];
        },
        endTime() {
            return this.endDateTime.split("T")[1];
        },
    },
};
</script>

<style lang="scss" scoped>
.scheduleListItemContainer {
    cursor: default;
    margin-bottom: 8px;
}

.scheduleListItemWrap {
    padding: 8px 12px;
    border-radius: 6px;
    box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.05);

    transition: all 0.13s;

    &.light {
        background-color: var(--white-color);
        border: 1px solid rgba(0, 0, 0, 0.13);
        font-size: 0.2rem;

        .icon {
            color: var(--main-color);
        }
        .itemText {
            font-size: 1rem;
        }
    }
    &.carrot {
        background: var(--carrot-color);

        .icon {
            color: var(--background-color);
        }

        .info {
            .itemDate {
                span {
                    color: var(--background-color);
                }
            }
        }

        .itemText {
            // font-weight: 0.5rem;
            color: #fff;
        }
    }
    &.primary {
        background-color: var(--primary-color);

        .icon {
            color: var(--background-color);
        }

        .info {
            .itemDate {
                span {
                    color: var(--background-color);
                }
            }
        }

        .itemText {
            color: #fff;
        }
    }

    &:hover {
        opacity: 0.8;
    }
}

.scheduleListItem {
    display: flex;
    align-items: center;
    // font-size: 1.2rem;

    .icon {
        margin-right: 8px;
        font-size: 1.3rem;
    }

    .info {
        width: 80%;
        display: flex;
        align-items: center;
        // flex-direction: column;
        // justify-content: space-between;

        span {
            font-size: 1rem;
            // font-weight: 500;
        }

        .itemDate {
            margin-right: 8px;

            span {
                font-size: 1rem;
                margin-right: 4px;
                font-weight: 600;
            }
        }

        .itemText {
            font-size: 1rem;
            margin-right: 4px;
            // margin-top: 2px;
        }
    }
}
</style>
