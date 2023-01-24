<template lang="">
    <div>
        <h1>InputTest</h1>
        <!-- 폼에 해당 클래스를 사용시 커스텀 사용가능 -->
        <form class="customForm" @submit.prevent="submitForm(event)">
            <!--
                설명
                label(선택)
                input
                    class
                        inputText = input[type:text] scss 적용
                        disable = 수정불가능 모양
                        success = 성공 모양
                        danger = 실패 모양
                    readonly = 수정불가능
                    pattern = 정규표현식 valid 체크
                    @input = v-model대체용
                div.nocice = 유효성검사 결과 텍스트
            -->
            <label :for="input1.id">기본</label>
            <input
                type="text"
                :name="input1.id"
                :id="input1.id"
                :pattern="input1.pattern"
                @input="changeInput"
                placeholder="내용을 입력해주세요" />
            <div class="notice" v-text="input1.notice"></div>
            <br />

            <label for="name1">textArea</label>
            <textarea type="textarea" placeholder="내용을 입력해주세요" value="" />
            <br />
        </form>
    </div>
</template>
<script>
export default {
    components: {},
    data() {
        return {
            input1: {
                id: "input1",
                value: "",
                pattern: "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", // 유효성검사 조건(HTML 용)
                validate: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, // 유효성검사 조건(JS 용)
                notice: "", // 유효성검사 결과 텍스트
            },
        };
    },
    methods: {
        submitForm(e) {
            console.log(e);
        },
        changeInput(e) {
            // v-model 대체용
            this[e.target.id].value = e.target.value;

            // 유효성 검사
            this[e.target.id].notice = "";
            if (!this[e.target.id].validate.test(this[e.target.id].value)) {
                this[e.target.id].notice =
                    "최소 8자 이상, 숫자와 문자를 포함한 비밀번호를 입력해주세요.";
            }
        },
    },
};
</script>
<style lang="scss">
@import "@/scss/input.scss";
</style>
