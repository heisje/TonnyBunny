<template>
    <div class="d-flex justify-content-center customFormWrap w-100">
        <div ckass="wrap customFormWrap">
            <TitleText title="회원가입" center text="당신의 귀여운 통역가, TonnyBunny!" />
            <!-- <SubText /> -->

            <h4>어떤 회원가입을 원하시나요?</h4>
            <div>
                <router-link :to="{ name: 'AgreePage', params: { select: 'client' } }"
                    ><button><TitleText title="일반 회원가입"></TitleText></button
                ></router-link>

                <router-link :to="{ name: 'AgreePage', params: { select: 'helper' } }"
                    ><button><TitleText title="헬퍼 회원가입"></TitleText></button
                ></router-link>
            </div>
            <br />
            <div style="background-color: beige">
                <h4>일반 회원이란?</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Neque ex eaque commodi
                    voluptatum ad doloremque omnis libero eum dignissimos repellat odio est quos,
                    deleniti architecto, iusto inventore vitae amet molestiae?
                </p>
            </div>
            <br />
            <div style="background-color: beige">
                <h4>헬퍼 회원이란?</h4>
                <p>
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Neque ex eaque commodi
                    voluptatum ad doloremque omnis libero eum dignissimos repellat odio est quos,
                    deleniti architecto, iusto inventore vitae amet molestiae?
                </p>
            </div>
        </div>
    </div>
</template>

<script>
import TitleText from "@/components/common/TitleText.vue";
// import SubText from "@/components/common/SubText.vue";
// import DescriptionItem from "@/components/account/DescriptionItem.vue";

export default {
    components: {
        TitleText,
        // SubText,
        // DescriptionItem,
    },
};
</script>

<style></style>
