import Vue from 'vue'

import '@babel/polyfill'
import postcommentsStore from 'store/postcommentsStore'
import blogStore from 'store/blogStore'
import postStore from 'store/postStore'

import VueResource from 'vue-resource'

import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import Vuex from 'vuex'


import App from 'pages/App.vue'
import BlogListApp from 'pages/BlogListApp.vue'
import BlogPageApp from 'pages/BlogPageApp.vue'
import PostPageApp from 'pages/PostPageApp.vue'
import AddPost from 'pages/AddPost.vue'

Vue.use(Vuex)
Vue.use(Vuetify)
Vue.use(VueResource);


new Vue({
  el: '#app',
  store: postcommentsStore,
  vuetify: new Vuetify(),
  render: a => a(App)
})

new Vue({
  el: '#blogListApp',
  store: blogStore,
  vuetify: new Vuetify(),
  render: a => a(BlogListApp)
})

new Vue({
  el: '#blogPageApp',
  store: blogStore,
  vuetify: new Vuetify(),
  render: a => a(BlogPageApp)
})

new Vue({
  el: '#postPageApp',
  store: postStore,
  vuetify: new Vuetify(),
  render: a => a(PostPageApp)
})

new Vue({
  el: '#addPostPageApp',
  store: blogStore,
  vuetify: new Vuetify(),
  render: a => a(AddPost)
})

