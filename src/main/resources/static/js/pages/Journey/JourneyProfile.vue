<template>
  <div id="journey_profile">
    <div id="edit_block" v-if="!no_role">
      <router-link
          :to="{name: 'journey-edit', params:{ id: this.id }}"
          v-if="user_role.includes('editor') || user_role.includes('admin')"
      >
        <button id="edit_button" type="button" class="btn-success">
          Edit
        </button>
      </router-link>
      <div>
        <article><strong>Role: </strong><span v-for="role in user_role">{{role}}</span></article>
      </div>
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
  props:{
    id: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      journey: {},
      user_role: {},
      no_role: true
    }
  },
  mounted() {
    this.refreshJourney()
  },
  methods:{
    refreshJourney() {
      JourneyService.get(this.id)
        .then(response=>{
          this.journey = response.data.Journey;
          this.user_role = response.data.JourneyRole;
          this.no_role = (this.user_role === null);
        })
        .catch(e =>{
          console.log(e.responses);
        })
    },
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