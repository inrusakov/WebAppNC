<template>
  <div class="list row">
    <div class="col-md-8">
      <div id="input__text"class="input-group mb-3">
        <input type="text" class="form-control" v-model="search_title" placeholder="Journey search:"/>
        <div class="input-group-append">
          <button type="button" class="btn-close align-middle" @click="clean_input" aria-label="Close"></button>
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
      if(this.search_title?.length > 0){
        this.$router.push({path: '/journey/', query:{q: this.search_title}}).catch(()=>{});
      }else{
        this.$router.push({path: '/journey/'}).catch(()=>{});
      }
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
    this.getJourneyList()
  }
}
</script>

<style scoped>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}

#input__text {
  display:flex;
  flex-direction:row;
  border:1px solid grey;
  border-radius: 5px;
}
#input__text input{
  flex-grow:2;
  border: none;
}
#input__text input:focus {
  box-shadow: none;
  border: none;
  outline:none;
}
#input__text button.btn-close:focus {
  box-shadow: none;
  border: none;
  outline:none;
}
#input__text button.btn-outline-secondary{
  border: none;
  border-left: 1px solid;
  border-bottom-left-radius: 0px;
  border-top-left-radius: 0px ;
}
#input__text button.btn-outline-secondary:focus{
  box-shadow: none;
  border: none;
  outline:none;
}
</style>