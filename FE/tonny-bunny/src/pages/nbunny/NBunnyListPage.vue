divdiv
<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div class="customForm">
            <title-banner title="🐰 번역 의뢰 목록" text="의뢰의 상세내용을 확인할 수 있습니다" />
            <div class="d-flex flex-row justify-content-between">
                <div class="d-flex flex-row w-75 mb-5">
                    <div class="col-3 me-3">
                        <DropdownInput
                            class="w-100"
                            :dropdownArray="getLangCode"
                            placeholder="언어"
                            @toggle="(e) => (lang = e)"
                        />
                    </div>
                    <div class="col-3">
                        <DropdownInput
                            class="w-100"
                            :dropdownArray="getBunnySituCode"
                            placeholder="카테고리"
                            @toggle="(e) => (category = e)"
                        />
                    </div>
                </div>
                <medium-btn text="검색" color="carrot" @click.prevent="search" />
            </div>
            <div>상단정보</div>
            <div>필터</div>
            <div>퀘스트카드리스트</div>

            <div v-for="(bunny, index) in getBunnyList" :key="index">
                {{ bunny }}

                <br />
                <br />
            </div>
        </div>
    </div>
</template>

<script>
import TitleBanner from "@/components/common/TitleBanner.vue";
import DropdownInput from "@/components/common/input/DropdownInputCode.vue";
import MediumBtn from "@/components/common/button/MediumBtn.vue";
import { mapGetters } from "vuex";

export default {
    data() {
        return {
            lang: "",
            category: "",
        };
    },

    components: {
        TitleBanner,
        DropdownInput,
        MediumBtn,
    },

    computed: {
        ...mapGetters({ getBunnyList: "getBunnyList" }),
        ...mapGetters({ getLangCode: "getLangCode" }),
        ...mapGetters({ getBunnySituCode: "getBunnySituCode" }),
    },

    created() {
        const payload = {
            lang: this.lang,
            category: this.category,
        };
        this.$store.dispatch("getBunnyList", payload);
    },

    methods: {
        search() {
            const payload = {
                lang: this.lang,
                category: this.category,
            };
            if (this.lang == "0020000") {
                payload.lang = "";
            }
            if (this.category == "0050003") {
                payload.category = "";
            }
            console.log(payload);

            this.$store.dispatch("getBunnyList", payload);
        },
    },
};
</script>

<style></style>
