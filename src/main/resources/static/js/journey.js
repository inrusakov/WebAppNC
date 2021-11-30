import Vue from 'vue'
import '@babel/polyfill'
import VueResource from 'vue-resource'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import Vuex from 'vuex'

import JourneyMain from "pages/Journey/JourneyMain.vue";
import JourneyProfile from "pages/Journey/JourneyProfile.vue";

Vue.use(Vuex)
Vue.use(Vuetify)
Vue.use(VueResource);

new Vue({
    el: '#JorMain',
    render: a => a(JourneyMain)
})

new Vue({
    el: '#JorProfile',
    render: a => a(JourneyProfile)
})

