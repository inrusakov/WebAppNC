import Vue from 'vue'
import '@babel/polyfill'
import VueResource from 'vue-resource'
import VueRouter from 'vue-router'
import 'vuetify/dist/vuetify.min.css'
import 'bootstrap/dist/css/bootstrap.min.css';

import JourneyMain from "pages/Journey/JourneyMain.vue";
import JourneyRoutes from './JourneyRoutes'

Vue.use(VueResource);
Vue.use(VueRouter);

const router = new VueRouter({
    routes: JourneyRoutes,
    mode: 'history'
})

new Vue({
    el: '#JorMain',
    render: a => a(JourneyMain),
    router: router
})



