<template>
    <div>
        <!---<input name="userId" type="hidden" th:value="${user.id}"/>
        <input name="postId" type="hidden" th:value="${post.id}"/> --->
    <input type="text" name="comm_body" id="" cols="90" rows="7" v-model="commentBody"></textarea>
        <input type="button" value="Submit" name="addComment" @click="save"></button>
    </div>
</template>

<script>
    import { mapActions } from 'vuex'

    export default{
        props: ['messageAttr'],
        data: function(){
            return {
                commentBody: '',
                id: ''
            }
        },
        watch:{
            messageAttr: function(newVal, oldVal){
                this.commentBody = newVal.commentBody;
                this.id = newVal.id;

            }
        },
        methods: {
            ...mapActions(['addCommentAction', 'updateCommentAction']),
            save: function(){
                //console.log({
               //       id: this.id,
                //      commentBody: this.commentBody
                //    })
                if(this.id){
                    var comment = {
                        id: this.id,
                        commentBody: this.commentBody
                    };
                    this.updateCommentAction(comment);
                }else{
                    var comment = {commentBody: this.commentBody};
                    this.addCommentAction(comment);
                }
                this.commentBody = '';
                this.id = '';
            }
        }
    }
</script>

<style>

</style>