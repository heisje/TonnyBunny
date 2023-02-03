export default {
    /* Loading */
    setIsLoading(context, bool) {
        if (bool) {
            context.commit("SET_ISLOADING", true);
            console.log("로딩시작");
        } else {
            console.log("로딩끝");
            return setTimeout(() => {
                context.commit("SET_ISLOADING", false);
            }, 300);
        }
    },
};
