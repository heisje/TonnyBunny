export default {
    SET_TODAY_SCHEDULE_LIST(state, payload) {
        state.schedule.todayScheduleList = payload;
    },
    SET_SCHEDULE_DETAIL(state, payload) {
        state.schedule.scheduleDetail = payload;
    },
};
