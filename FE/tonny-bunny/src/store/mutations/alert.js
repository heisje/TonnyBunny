export default {
    /*
        Alert
    */
    // alert list 변경
    SET_ALERT_LIST(state, alertList) {
        console.log(alertList);
        state.alert.alertList = alertList;
    },
};
