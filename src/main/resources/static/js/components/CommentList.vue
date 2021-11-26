<template>
    <div>
    <comment-form :messageAttr="message"></comment-form>
    <comment-row v-for="message in sortedComments" :key="message.id" :message="message" :editMethod="editMethod" :deleteMethod="deleteMethod"/>
    </div>
</template>

<script>
    import { mapGetters } from 'vuex'
    import CommentRow from 'components/CommentRow.vue'
    import CommentForm from 'components/CommentForm.vue'

    export default{
        components: {
            CommentRow,
            CommentForm
        },
        data: function(){
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedComments'])
        ,
        methods:{
            editMethod: function(message){
                    this.message = message;
            }
        }
    }
</script>

<style>

</style>