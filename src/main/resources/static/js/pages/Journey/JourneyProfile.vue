<template>
  <div class="edit-form">
    <h4>Journey</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title"
               v-model="currentJourney.title"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description"
               v-model="currentJourney.description"
        />
      </div>

      <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentJourney.status}}
      </div>
    </form>

    <button class="btn-danger" @click="deleteJourney">
      Delete
    </button>

    <button type="submit" class="btn-success" @click="updateJourney">
      Update
    </button>
  </div>
</template>

<script>
import JourneyService from "store/journeyService.js"

export default {
  name: "JourneyProfile",
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
            console.log(e);
          });
    },
    updateJourney() {
      JourneyService.update(this.id, this.currentJourney)
          .then(response => {
          })
          .catch(e => {
            console.log(e);
          });
    },
    deleteJourney() {
      JourneyService.delete(this.id)
          .then(response => {
            this.$router.push({path: '/journey/'});
          })
          .catch(e => {
            console.log(e);
          });
    },
  }
}
</script>
<style scoped>
</style>
<!--<template>-->
<!--  <div id="journey-profile">-->
<!--    <h1>{{this.journey.title}}</h1>-->
<!--      <article>{{this.journey.description}}</article>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import JourneyService from "store/journeyService.js"-->
<!--export default {-->
<!--  name: "JourneyProfile",-->
<!--  data() {-->
<!--    return {-->
<!--      id: this.$route.params.id,-->
<!--      journey: {}-->
<!--    }-->
<!--  },-->
<!--  mounted() {-->
<!--    this.refreshJourney()-->
<!--  },-->
<!--  methods:{-->
<!--    refreshJourney() {-->
<!--      JourneyService.get(this.id)-->
<!--        .then(response=>{-->
<!--          this.journey = response.data;-->
<!--          console.log(response.data);-->
<!--        })-->
<!--        .catch(e =>{-->
<!--          console.log(e);-->
<!--        })-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--</style>-->