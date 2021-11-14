package com.example.service.traveling;

import com.example.model.Traveling.Journey;
import com.example.model.Traveling.JourneyRole;
import com.example.model.User;
import com.example.model.community.Group;
import com.example.util.constants.JourneyConst;

import java.util.List;
import java.util.Set;

public interface JourneyService {

    boolean isJourneyExist(Journey journey);
    boolean isJourneyParticipant(Journey journey);
    boolean isJourneyParticipant(Journey journey, User user);

    Journey findById(Integer id);
    boolean create(Journey journey);
    boolean create(Journey journey, Group group);
    boolean delete(Journey journey);
    boolean edit(Journey journey);

    boolean hasJourneyRole(Journey journey, User user, JourneyRole role);
    boolean hasJourneyRole(Journey journey, JourneyRole role);

    List<Journey> getJourney_isParticipant(User user, String ttl);
    Set<JourneyRole> getRoles(Journey journey, User user);
    Set<JourneyRole> getRoles(Journey journey);

    /**
     * Проводит коррекцию строки title:
     *  - убирает пробельные символы спереди и сзади => String.trim()
     *  - заменяет 2 и более идущих подряд пробельных символа в строке на "
     * @return Исправленная строка - если title != null
     *         null                - если title == null
     */
    static String journeyTitleCorrector(String title) {
        String response;
        if(title != null){
            response = title.trim().replaceAll("[\\s]{2,}"," ");
        }else{
            response = null;
        }
        return response;
    }

    static boolean isValidJourneyId(String journey_id) {
        try{
            Integer.parseInt(journey_id.trim());
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }
    static boolean isValidJourneyTitle(String title) {
        if(title == null) return false;
        return title.length() >= JourneyConst.title_length_min &&
                title.length() <= JourneyConst.title_length_max &&
                title.matches(JourneyConst.title_validator_regEx);
    }
    static boolean isValidJourneySearchTitle(String searchTitle) {
        if(searchTitle == null) return false;
        return  searchTitle.length() >= 1 &&
                searchTitle.length() <= JourneyConst.title_length_max &&
                searchTitle.matches(JourneyConst.title_search_validator_regEx);
    }

    /**
     * Преобразует searchTitle к виду field Journey.id и возвращает результат.
     * @param searchTitle - строка, которую нужно преобразовать к виду Journey.id
     * @return Integer - значение Journey.id - если удалось преобразовать к виду Journey.id
     *         null                          - если не удалось преобразовать к виду Journey.id
     */
    static Integer getJourneyIdFromString(String searchTitle){
        Integer result;
        if(isValidJourneyId(searchTitle)){
            result = Integer.parseInt(searchTitle);
        }else {
            result = null;
        }
        return result;
    }
}
