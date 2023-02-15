<template>
    <div>
        <h2 class="px-3 py-2">{{ title }}</h2>
        <textarea cols="10" rows="3" style="border:solid 1px #a1aeb7; background-color: #d9d9d980; overflow:hidden; text-overflow:ellipsis" disabled :value="content">
        </textarea>
        <hr>
    </div>
    
</template>

<script>
// import TitleText from "@/components/common/TitleText.vue";

export default {
    name: "NoticePostItem",
    components:{
        // TitleText,
    },

    props:{
        title:{
            type:String,
            default: "",
        },
        content:{
            type:String,
            default:"",
        }

    }

}
</script>

<style>

</style>