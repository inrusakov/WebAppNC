<template>
    <div><i>({{message.id}})</i> {{message.commentBody}}
    <span>
        <input type="button" value="Edit" @click="editComment">
        <input type="button" value="X" @click="deleteComment">
    </span>
    </div>
</template>

<script>
     import { mapActions } from 'vuex'
     export default{
        props: ['message', 'editMethod'],
        methods:{
            ...mapActions(['deleteCommentAction']),
            editComment: function(){
                this.editMethod(this.message);
            },
            deleteComment: function(){
                this.deleteCommentAction(this.message);
            }
        }
     }
</script>

<style>

</style>
