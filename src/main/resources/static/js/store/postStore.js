import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    profile: frontendData.profile,
    blog: frontendData.blog,
    post: frontendData.post,
    postcomments: frontendData.postcomments // ??????? - связь с postcommentStore ???????
  },
  getters:{
      blog: state => {
        return state.blog;
      },
      post: state => {
        return state.post;
      }
  }
  ,
    mutations: {
      updatePostMutation(state, post){
          state.post = post;
      }
    },
    actions: {
        async updatePostAction({commit}, post){
            const result = await axios.put('/post/' + post.postId, post);
            console.log(result.data)

            commit('updatePostMutation', result.data);
        }
    }
})