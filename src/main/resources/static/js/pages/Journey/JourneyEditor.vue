<template>
  <div class="edit-form">
    <h4>Journey</h4>
    <form>
      <div class="form-group">
        <label for="title"><strong>Title</strong></label>
        <input type="text" class="form-control" id="title"
               v-model="journey.title"
        />
      </div>
      <div class="form-group">
        <label for="description"><strong>Description</strong></label>
        <input type="text" class="form-control" id="description"
               v-model="journey.description"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ journey.status}}
      </div>
    </form>

    <button type="submit" class="btn-success btn_menu" @click="updateJourney">
      Update
    </button>

    <button class="btn-danger btn_menu" @click="deleteJourney"
            v-if="user_role.includes('admin')"
    >
      Delete
    </button>
  </div>
</template>

<script>
import JourneyService from "store/journeyService.js"

export default {
  name: "JourneyEditor",
  props:{
    id: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      journey: {},
      user_role: [],
    }
  },
  mounted() {
    this.refreshJourney()
  },
  methods: {
    refreshJourney() {
      JourneyService.get(this.id)
          .then(response => {
            this.journey = response.data.Journey;
            this.user_role = response.data.JourneyRole;
          })
          .catch(e => {
            console.log(e.responses);
          });
    },
    updateJourney() {
      JourneyService.update(this.id, this.journey)
          .then(response => {
            this.$router.push({ name: 'journey-details', params: {id: this.id}});
          })
          .catch(e => {
            console.log(e.responses);
            this.$router.push({ name: 'journey-details', params: {id: this.id}, query: {status: e.response.status}});
          });
    },
    deleteJourney() {
      JourneyService.delete(this.id)
          .then(response => {
            this.$router.push({path: '/journey/'});
          })
          .catch(e => {
            console.log(e.responses);
            this.$router.push({ name: 'journey-details', params: {id: this.id}, query: {status: e.response.status}});
          });
    },
  }
}
</script>
<style scoped>
.btn_menu {
  padding: 0px 10px;
  border-radius: 5px;
}
.btn_menu:focus {
  box-shadow: none;
  border: none;
  outline:none;
}
</style>