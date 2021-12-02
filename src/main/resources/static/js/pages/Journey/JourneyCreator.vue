<template>
  <div id="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input
            type="text"
            class="form-control"
            id="title"
            required
            v-model="journey.title"
            name="title"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <input
            class="form-control"
            id="description"
            required
            v-model="journey.description"
            name="description"
        />
      </div>
      <button @click="saveJourney" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newJourney">Add</button>
    </div>
  </div>
</template>

<script>
import JourneyService from "store/journeyService.js"

export default {
  name: "JourneyEditor",
  props:{
  },
  data(){
    return{
      journey: {
        title: "",
        description: "",
        isPrivate: false,
      },
      submitted: false
    }
  },
  methods:{
    newJourney(){
      this.submitted = false;
      this.journey = {
        title: "",
        description: "",
        isPrivate: false,
      };
    },
    saveJourney(){
      const data = {
        title: this.journey.title,
        description: this.journey.description,
        isPrivate: this.journey.isPrivate,
      };
      console.log(data)
      console.log(this.journey)
      JourneyService.create(data)
          .then(response => {
            this.journey.id = response.data.id;
            this.submitted = true;
          })
          .catch(e => {
            console.log(e.responses);
          });
    },
  },
}
</script>

<style scoped>
#submit-form {
  max-width: 300px;
  margin: auto;
  color: #6c757d;
}
</style>