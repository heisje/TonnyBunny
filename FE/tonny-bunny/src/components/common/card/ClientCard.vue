<template>
    <div class="cardContainer">
        <div class="cardWrap">
            <div class="clientCard">
                <div class="profile">
                    <img class="profileImg" src="@/assets/noProfile_white.png" alt="" width="70" />
                </div>
                <div class="info">
                    <div class="edit">
                        <h3>{{ userInfo.ninkName }}</h3>
                        <div>
                            <router-link :to="{ name: 'InfoPage' }">정보수정</router-link>
                        </div>
                    </div>
                    <div class="carrot">
                        <div>{{ userInfo.point }}</div>
                        <div>{{ userInfo.point }}개</div>
                        <div>
                            <router-link :to="{ name: 'CarrotPage' }">포인트관리</router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
    name: "ClientCard",

    computed: {
        ...mapGetters({
            userInfo: "getUserInfo",
        }),
    },
};
</script>

<style lang="scss" scoped>
@import "@/scss/card.scss";
</style>
