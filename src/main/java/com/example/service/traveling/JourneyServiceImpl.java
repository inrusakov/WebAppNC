package com.example.service.traveling;

import com.example.model.Traveling.Journey;
import com.example.model.Traveling.JourneyRequestForm;
import com.example.model.Traveling.JourneyRole;
import com.example.model.User;
import com.example.repos.TravelRepository;
import com.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

@Service
public class JourneyServiceImpl implements JourneyService{

    @Autowired
    TravelRepository travelRepository;

    @Override
    public boolean isJourneyExist(Integer journey_id) {
        Optional<Journey> journeyOptional = travelRepository.findById(journey_id);
        return journeyOptional.isPresent();
    }

    @Override
    public boolean hasJourneyRole(Journey journey, User user, JourneyRole role){
        if( journey == null || user == null || role == null) {return false;}
        switch (role){
            case creator:
                return AuthenticationService.isAuthenticated();
            case admin:
            case editor:
            case participant:
                return isJourneyParticipant(journey, user);
            default:
        }
        return false;
    }

    @Override
    public boolean hasJourneyRole(Journey journey, JourneyRole role) {
        User user = AuthenticationService.getCurrentUser();
        return hasJourneyRole(journey, user, role);
    }

    @Override
    public boolean delete(Journey journey) {
        if(isJourneyExist(journey.getId()) && hasJourneyRole(journey, JourneyRole.admin)) {
            travelRepository.delete(journey);
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Journey journey) {
        if(hasJourneyRole(journey, JourneyRole.creator)){
            // Add Owner to participants // TODO: [TRAVELING] Учасники путешествия - учасники группы
            journey.addParticipants(AuthenticationService.getCurrentUser());

            // TODO: проставлять время сохранения в БД.
            journey.setCreation_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
            travelRepository.save(journey);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Journey journey) {
        if(isJourneyExist(journey.getId()) && hasJourneyRole(journey, JourneyRole.editor)) {
            travelRepository.save(journey);
            return true;
        }
        return false;
    }

    @Override
    public Journey findById(Integer id) {
        Optional<Journey> journeyOptional = travelRepository.findById(id);
        return journeyOptional.orElse(null);
    }

    /**
     * Функция проверяет, является ли пользователь учасником путешествия
     * <p>
     * Функция вернёт true, если пользователь принадлежит участникам путешествия,
     * и вернёт false, если пользователь не является участником путешествия,
     * или один из переданных параметров принимает значение null.
     *
     * @param  journey  Journey class instance, может быть null
     * @param  user     User class instance, может быть null
     * @return          true - если user принадлежит journey.participants
     */
    @Override
    public boolean isJourneyParticipant(Journey journey, User user) {
        if(journey == null || user == null) {return false;}

        java.util.Set<User> participants = journey.getParticipants();
        for (User participant : participants){
            if(participant.equals(user)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isJourneyParticipant(Journey journey) {
        User user = AuthenticationService.getCurrentUser();
        return isJourneyParticipant(journey, user);
    }

    public java.util.List<Journey>JourneyForm_SQLQuery(JourneyRequestForm form){
        try{
            return travelRepository.JourneyForm_SQLQuery(form);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
