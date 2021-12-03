<template>
  <div class="edit-form">
    <h4>Journey</h4>
    <form>
      <div class="form-group">
        <label for="title"><strong>Title</strong></label>
        <input type="text" class="form-control" id="title"
               v-model="currentJourney.title"
        />
      </div>
      <div class="form-group">
        <label for="description"><strong>Description</strong></label>
        <input type="text" class="form-control" id="description"
               v-model="currentJourney.description"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentJourney.status}}
      </div>
    </form>

    <button class="btn-danger btn_menu" @click="deleteJourney">
      Delete
    </button>

    <button type="submit" class="btn-success btn_menu" @click="updateJourney">
      Update
    </button>
  </div>
</template>

<script>
import JourneyService from "store/journeyService.js"

export default {
  name: "JourneyEditor",
  data() {
    return {
      id: this.$route.params.id,
      currentJourney: {
        title: '',
        description: '',
        status: '',
      },
    }
  },
  mounted() {
    this.refreshJourney()
  },
  methods: {
    refreshJourney() {
      JourneyService.get(this.id)
          .then(response => {
            this.currentJourney = response.data;
          })
          .catch(e => {
            console.log(e.responses);
          });
    },
    updateJourney() {
      JourneyService.update(this.id, this.currentJourney)
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