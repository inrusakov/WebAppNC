import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    messages: frontendData.messages,
    links: [
       ['mdi-inbox-arrow-down', 'Inbox'],
       ['mdi-send', 'Send'],
       ['mdi-delete', 'Trash'],
       ['mdi-alert-octagon', 'Spam'],
    ]
  },
  getters:{
      sortedComments: state => {
        return state.messages;  //.sort((a, b) => (a.id - b-id));
      }
  },
  mutations: {
    addCommentMutation(state, comment){
        state.messages = [
            ...state.messages,
            comment
        ]
    },
    updateCommentMutation(state, comment){
        const index = state.messages.findIndex(item => item.id === comment.id);
        state.messages = [
            ...state.messages.slice(0, index),
            comment,
            ...state.messages.slice(index)
        ]
    },
    deleteCommentMutation(state, comment){
        const index = state.messages.findIndex(item => item.id === comment.id);

        if(index > -1){
            state.messages = [
                    ...state.messages.slice(0, index),
                    ...state.messages.slice(index)
            ]
        }
    }
  },
  actions: {
     async addCommentAction({commit}, comment){
        const result = await axios.post('/postcomments/', comment);
        console.log(result.data);

        commit('addCommentMutation', result.data);
     },
     async updateCommentAction({commit}, comment){
         const result = await axios.put('/postcomments/' + comment.id, comment);
         console.log(result.data)

         commit('updateCommentMutation', result.data);
     },
     async deleteCommentAction({commit}, comment){
        const result = await axios.delete('/postcomments/' + comment.id)
        console.log(result.data);

        commit('deleteCommentMutation', comment);
     }
  }
})