import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    blogs: frontendData.blogs,
    blog: frontendData.blog,
    posts: frontendData.posts,
    post: frontendData.post
  },
  getters:{
      blogs: state => {
        return state.blogs;
      },
      blog: state => {
              return state.blog;
      },
      posts: state => {
              return state.posts;
      },
      post: state => {
              return state.post;
      }
  },
     mutations: {
       addPostMutation(state, post){
               state.posts = [
                   ...state.posts,
                   post
               ]
       },
       deletePostMutation(state, post){
             const index = state.posts.findIndex(item => item.postId === post.postId);

             if(index > -1){
                 state.posts = [
                 ...state.posts.slice(0, index),
                 ...state.posts.slice(index + 1)
                 ]
             }
       }
     },
     actions: {
          async addPostAction({commit}, post){
              const result = await axios.post('/post/', post);
              console.log(result.data);

              commit('addPostMutation', result.data);
          },

          async deletePostAction({commit}, post){
              const result = await axios.delete('/post/' + post.id)
              commit('deletePostMutation', post);
          }
     }
})