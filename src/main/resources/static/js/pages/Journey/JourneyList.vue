<template>
  <div class="list row">
    <div class="col-md-8">
      <div class="input-group mb-3">
        <input type="text" class="form-control" v-model="search_title" placeholder="Journey search:"/>
        <div class="input-group-append">
          <button type="button" class="btn-close" @click="clean_input" aria-label="Close"></button>
          <button class="btn btn-outline-secondary" type="button" @click="searchByTitle">Search</button>
        </div>
      </div>
    </div>
    <div class="col-md-6" v-if="journey_list.length>0">
      <h4>Journey List</h4>
      <ul class="list-group">
        <li class="list-group-item"
            v-for="(journey, index) in journey_list"
            :key="index">
            <router-link :to="'/journey/profile/' + journey.id">
              <h5>{{ journey.title }}</h5>
            </router-link>
        </li>
      </ul>
    </div>
    <div v-else class="col-md-6">
      <h4>No Journeys found.</h4>
    </div>
  </div>
</template>

<script>
import JourneyService from "store/journeyService.js"

export default {
  name: "JourneyList",
  data() {
    return{
      journey_list: [],
      search_title: "",
    }
  },
  watch:{
    search_title: function (){
      this.searchByTitle();
    }
  },
  methods:{
    clean_input(){
      this.search_title = "";
    },

    getJourneyList() {
      JourneyService.getAll()
          .then(response => {
            this.journey_list = response.data;
          })
          .catch(e => {
            console.log(e);
          });
    },

    refreshList() {
      this.getJourneyList();
      this.currentJourney = null;
      this.currentIndex = -1;
    },

    searchByTitle() {
      JourneyService.findByTitle(this.search_title)
          .then(response => {
            this.journey_list = response.data;
          })
          .catch(e => {
            console.log(e);
          });
    }
  },
  mounted() {
    this.refreshList();
  }
}
</script>

<style scoped>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>