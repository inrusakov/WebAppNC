package com.example.service.traveling;

import com.example.model.Traveling.Journey.Journey;
import com.example.model.Traveling.Journey.JourneyRole;
import com.example.model.User;
import com.example.model.community.Group;
import com.example.model.community.GroupRole;
import com.example.repos.CriteriaBuilder.JourneyRequestForm;
import com.example.repos.TravelRepository;
import com.example.service.AuthenticationService;
import com.example.service.community.GroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // TODO: Внедрить роли: роли пользователя взять из возврата метода: Set<JourneyRole> JourneyService.getRoles(Journey, User)
    @Override
    public boolean hasJourneyRole(Journey journey, User user, JourneyRole role){
        if (role == null) return false;
        if (role == JourneyRole.creator) return AuthenticationService.isAuthenticated();
        if( journey == null || user == null) return false;
        boolean response = false;
        switch (role){
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
    public List<Journey> getJourney_isParticipant(String title){
        return getJourney_isParticipant(AuthenticationService.getCurrentUser(), title);
    }

    @Override
    public List<Journey> getJourney_isParticipant(User user, String title) {
        List<Journey> response;
        if (user == null){
            if(JourneyService.isValidJourneySearchTitle(title)){
                response = travelRepository.findByIsPrivateFalse(title);
            }else{
                response = travelRepository.findByIsPrivateFalse();
            }
        }else{
            if (JourneyService.isValidJourneySearchTitle(title)){
                response = travelRepository.findByIsParticipant(user.getId(), title);
            }else{
                response = travelRepository.findByIsParticipant(user.getId());
            }
        }
        return response;
    }

    @Override
    public Set<JourneyRole> getRoles(Journey journey, User user) {
        if (journey == null || user == null) return null;
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
    public ResponseEntity<HttpStatus> delete(Journey journey) {
        if (!isJourneyExist(journey)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(hasJourneyRole(journey, JourneyRole.admin)){
            travelRepository.delete(journey);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Journey> create(Journey journey, Group group) {
        if(hasJourneyRole(journey, JourneyRole.creator)){
            journey.setGroup(group);
            journey.setCreation_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
            travelRepository.save(journey);
            return new ResponseEntity<>(journey, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Journey> create(Journey journey) {
        if(hasJourneyRole(journey, JourneyRole.creator)){
            User user = AuthenticationService.getCurrentUser();

            Group group = new Group();
            group.setName(journey.getTitle());
            group.addParticipants(user);

            journey.setGroup(group);
            journey.setCreation_time(new Timestamp(Calendar.getInstance().getTime().getTime()));
            travelRepository.save(journey);
            return new ResponseEntity<>(journey, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Journey> edit(Journey journey) {
        if(!isJourneyExist(journey)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(hasJourneyRole(journey, JourneyRole.editor)) {
            travelRepository.save(journey);
            return new ResponseEntity<>(journey, HttpStatus.OK);
        }else{
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public Map<String, Object> findById(Integer id) {
        HashMap<String, Object> response = new HashMap<>();
        Journey journey = travelRepository.findById(id).orElse(null);
        if(journey == null) {
            response.put("Journey", null);
            response.put("JourneyRole", null);
            response.put("HttpStatus", HttpStatus.NOT_FOUND);
        }else{
            response.put("Journey", journey);
            response.put("JourneyRole", getRoles(journey));
            response.put("HttpStatus", HttpStatus.OK);
        }
        return response;
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
