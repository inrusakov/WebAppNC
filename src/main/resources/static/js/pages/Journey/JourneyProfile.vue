<template>
  <div id="journey_profile">
    <div id="edit_block">
      <router-link :to="{name: 'journey-edit', params:{ id: this.id }}">
        <button id="edit_button" type="submit" class="btn-success"
                @click="editJourney"
        >
          Edit
        </button>
      </router-link>
    </div>
    <div id="journey_description">
      <h1>{{this.journey.title}}</h1>
        <article>{{this.journey.description}}</article>
    </div>
  </div>
</template>

<script>
import JourneyService from "store/journeyService.js"

export default {
  name: "JourneyProfile",
  data() {
    return {
      id: this.$route.params.id,
      journey: {}
    }
  },
  mounted() {
    this.refreshJourney()
  },
  methods:{
    refreshJourney() {
      JourneyService.get(this.id)
        .then(response=>{
          this.journey = response.data;
          console.log(response.data);
        })
        .catch(e =>{
          console.log(e.responses);
        })
    },
    editJourney() {

    }
  }
}
</script>

<style scoped>
#edit_button {
  padding: 0px 10px;
  border-radius: 5px;
}
#edit_button:focus {
  box-shadow: none;
  border: none;
  outline:none;
}
</style>