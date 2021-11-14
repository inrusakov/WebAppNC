package com.example.service.traveling;

import com.example.model.Traveling.Journey;
import com.example.model.Traveling.JourneyRole;
import com.example.model.User;
import com.example.model.community.Group;
import com.example.model.community.GroupRole;
import com.example.repos.CriteriaBuilder.JourneyRequestForm;
import com.example.repos.TravelRepository;
import com.example.service.AuthenticationService;
import com.example.service.community.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service("JourneyServiceImpl")
public class JourneyServiceImpl implements JourneyService{

    @Autowired
    TravelRepository travelRepository;

    @Autowired
    GroupServiceImpl groupService;

    @Override
    public boolean isJourneyExist(Journey journey) {
        if(journey == null){
            return false;
        }
        Optional<Journey> journeyOptional = travelRepository.findById(journey.getId());
        return journeyOptional.isPresent();
    }

    @Override
    public boolean hasJourneyRole(Journey journey, User user, JourneyRole role){
        if( journey == null || user == null || role == null) {return false;}
        boolean response = false;
        switch (role){
            case creator:
                response = AuthenticationService.isAuthenticated();
                break;
            case admin:
            case editor:
            case participant:
                response = isJourneyParticipant(journey, user);
                break;
            default:
                break;
        }
        return response;
    }

    @Override
    public boolean hasJourneyRole(Journey journey, JourneyRole role) {
        User user = AuthenticationService.getCurrentUser();
        return hasJourneyRole(journey, user, role);
    }

    @Override
    public List<Journey> getJourney_isParticipant(User user, String ttl) {
        List<Journey> response;
        if (user == null){
            if(JourneyService.isValidJourneySearchTitle(ttl)){
                response = travelRepository.findByIsPrivateFalse(ttl);
            }else{
                response = travelRepository.findByIsPrivateFalse();
            }
        }else{
            if (JourneyService.isValidJourneySearchTitle(ttl)){
                response = travelRepository.findByIsParticipant(user.getId(), ttl);
            }else{
                response = travelRepository.findByIsParticipant(user.getId());
            }
        }
        return response;
    }

    @Override
    public Set<JourneyRole> getRoles(Journey journey, User user) {
        if (journey == null) return null;
        Set<GroupRole> roles = groupService.getRoles(journey.getGroup(), user);
        Set<JourneyRole> response = new HashSet<>();
        if(roles.contains(GroupRole.participant)){
            response.add(JourneyRole.participant);
        }
        if(roles.contains(GroupRole.editor)){
            response.add(JourneyRole.editor);
        }
        if(roles.contains(GroupRole.admin)){
            response.add(JourneyRole.admin);
        }
        return response;
    }

    @Override
    public Set<JourneyRole> getRoles(Journey journey) {
        User user = AuthenticationService.getCurrentUser();
        return getRoles(journey, user);
    }

    @Override
    public boolean delete(Journey journey) {
        if(isJourneyExist(journey) && hasJourneyRole(journey, JourneyRole.admin)) {
            travelRepository.delete(journey);
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Journey journey, Group group) {
        if(hasJourneyRole(journey, JourneyRole.creator)){
            journey.setGroup(group);
            journey.setCreation_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
            travelRepository.save(journey);
            return true;
        }
        return false;
    }

    @Override
    public boolean create(Journey journey) {
        if(hasJourneyRole(journey, JourneyRole.creator)){
            User user = AuthenticationService.getCurrentUser();

            Group group = new Group();
            group.setName(journey.getTitle());
            group.addParticipants(user);

            journey.setGroup(group);
            journey.setCreation_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
            travelRepository.save(journey);
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(Journey journey) {
        if(isJourneyExist(journey) && hasJourneyRole(journey, JourneyRole.editor)) {
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
        if(journey == null) {return false;}
        return groupService.isParticipant(journey.getGroup(), user);
    }

    @Override
    public boolean isJourneyParticipant(Journey journey) {
        User user = AuthenticationService.getCurrentUser();
        return isJourneyParticipant(journey, user);
    }

    public java.util.List<Journey>JourneyForm_SQLQuery(JourneyRequestForm form){
        return travelRepository.JourneyForm_SQLQuery(form);
    }
}
