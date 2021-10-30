package com.example.service.traveling;

import com.example.model.Traveling.Journey;
import com.example.model.Traveling.JourneyRole;
import com.example.model.User;
import com.example.util.constants.JourneyConst;

public interface JourneyService {

    boolean isJourneyExist(Integer journey_id);
    boolean isJourneyParticipant(Journey journey);
    boolean isJourneyParticipant(Journey journey, User user);

    Journey findById(Integer id);
    boolean create(Journey journey);
    boolean delete(Journey journey);
    boolean edit(Journey journey);

    boolean hasJourneyRole(Journey journey, User user, JourneyRole role);
    boolean hasJourneyRole(Journey journey, JourneyRole role);

    static String journeyTitleCorrector(String title) {
        return title.trim().replaceAll("[\\s]{2,}"," ");
    }
    static boolean isValidJourneyId(String journey_id) {
        try{
            Integer.parseInt(journey_id.strip());
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    static boolean isValidJourneyTitle(String title) {
        return title.length() >= JourneyConst.title_length_min &&
                title.length() <= JourneyConst.title_length_max &&
                title.matches(JourneyConst.title_validator_regEx);
    }
    static boolean isValidJourneySearchTitle(String searchTitle) {
        return  searchTitle.length() >= 1 &&
                searchTitle.length() <= JourneyConst.title_length_max &&
                searchTitle.matches(JourneyConst.title_search_validator_regEx);
    }
    static Integer getIdFromString(String searchTitle){
        Integer result;
        if(isValidJourneyId(searchTitle)){
            result = Integer.parseInt(searchTitle);
        }else {
            result = null;
        }
        return result;
    }
}
