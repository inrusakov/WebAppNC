<template>
  <div id="journey-page">
    <nav class="navbar navbar-expand navbar-dark bg-dark">
      <router-link to="/journey" class="navbar-brand">NC-WebApp</router-link>
      <div class="navbar-nav mr-auto">
        <li class="nav-item">
          <router-link to="/journey" class="nav-link">My Journeys</router-link>
        </li>
        <li class="nav-item">
          <router-link to="/journey/add" class="nav-link">Create</router-link>
        </li>
      </div>
    </nav>

    <div  class="container mt-3">
      <router-view/>
    </div>
  </div>
</template>

<script>
import AppHeader from "../../components/app-header.vue";

export default {
  components: {AppHeader},
  data () {
    return {
    }
  },
  methods:{
  }
}
</script>

<style>
body{
  margin: 0;
}
</style>