import JourneyProfile from "pages/Journey/JourneyProfile.vue";
import JourneyList from "./pages/Journey/JourneyList.vue";
import JourneyEditor from "./pages/Journey/JourneyEditor.vue";
// import JourneyCreator from "./pages/Journey/JourneyCreator.vue";

export default [
    {path: '/journey', name: "journey-list", component: JourneyList},
    {path: '/journey/profile/:id', name: "journey-details", component: JourneyProfile},
    {path: '/journey/add', name: "journey-form", component: JourneyEditor},
    {path: '/journey/profile/:id', name: "journey-edit", component: JourneyEditor},
]