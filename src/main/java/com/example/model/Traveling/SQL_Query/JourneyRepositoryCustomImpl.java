package com.example.model.Traveling.SQL_Query;

import com.example.model.Traveling.*;
import com.example.model.User;
import com.example.service.traveling.JourneyService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.service.traveling.JourneyService.isValidJourneySearchTitle;

@Repository
public class JourneyRepositoryCustomImpl implements JourneyRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Journey> JourneyForm_SQLQuery(JourneyRequestForm form) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Journey> q = cb.createQuery(Journey.class);
        Root<Journey> journeyRoot = q.from(Journey.class);

        List<Predicate> statusPredicates = getStatusPredicatesList(form, cb, journeyRoot);
        List<Predicate> AccessPredicates = getAccessPredicatesList(form, cb, journeyRoot);
        List<Predicate> RolePredicates = getRolePredicatesList(form, cb, journeyRoot);
        Predicate titlePredicate = getTitlePredicate(form, cb, journeyRoot);

        q.select(journeyRoot)
            .where(cb.and(
                    cb.or(statusPredicates.toArray(new Predicate[statusPredicates.size()])),
                    cb.or(AccessPredicates.toArray(new Predicate[AccessPredicates.size()])),
                    cb.or(RolePredicates.toArray(new Predicate[RolePredicates.size()])),
                    titlePredicate
            ));

        // Выполнение SQL-запроса.
        Query query = entityManager.createQuery(q);
        // Вывод SQL-строки в консоль
        System.out.println(query.unwrap(org.hibernate.Query.class).getQueryString());
        return query.getResultList();
    }

    @Override
    public List<Journey> SQLQuery(String SQL) {
        // implementation below
        return null;
    }

    private List<Predicate> getStatusPredicatesList(
            JourneyRequestForm form,
            CriteriaBuilder cb,
            Root<Journey> journeyRoot
    ){
        List<Predicate> predicatesList = new ArrayList<>();

        // Добавить поиск по статусу путешествия вне зависимости от авторизации пользователя.
        LinkedHashMap<JourneyStatus, Boolean> statusBooleanLinkedHashMap = form.getJourneyStatusMap();
        for(Map.Entry<JourneyStatus, Boolean> entry : statusBooleanLinkedHashMap.entrySet()){
            if(entry.getValue()){
                predicatesList.add(cb.equal(journeyRoot.get("status"), entry.getKey()));
            }
        }
        // Добавляет always true predicate, если коллекция статусов пуста.
        if(predicatesList.isEmpty()){
            predicatesList.add(alwaysTrue(cb));
        }
        return predicatesList;
    }

    private List<Predicate> getAccessPredicatesList(
            JourneyRequestForm form,
            CriteriaBuilder cb,
            Root<Journey> journeyRoot
    ){
        List<Predicate> AccessPredicates = new ArrayList<>();

        // Если у формы есть отправитель, то...
        LinkedHashMap<JourneyAccess, Boolean> accessBooleanLinkedHashMap = form.getJourneyAccessMap();
        for(Map.Entry<JourneyAccess,Boolean> entry : accessBooleanLinkedHashMap.entrySet()){
            if(entry.getValue()){
                JourneyAccess journeyAccess = entry.getKey();
                switch (journeyAccess){
                    case PUBLIC:
                        AccessPredicates.add(accessPredicates_Public(cb, journeyRoot));
                        break;
                    case PRIVATE:
                        AccessPredicates.add(accessPredicates_Private(cb, journeyRoot));
                        break;
                    default: break;
                }
            }
        }
        if(AccessPredicates.isEmpty()){
            AccessPredicates.add(alwaysTrue(cb));
        }
        return AccessPredicates;
    }
    private Predicate alwaysTrue(CriteriaBuilder cb){
        return cb.and();
    }
    private Predicate accessPredicates_Public(CriteriaBuilder cb, Root<Journey> journeyRoot){
        return cb.isFalse(journeyRoot.get("isPrivate"));
    }
    private Predicate accessPredicates_Private(CriteriaBuilder cb, Root<Journey> journeyRoot){
        return cb.isTrue(journeyRoot.get("isPrivate"));
    }

    private List<Predicate> getRolePredicatesList(
            JourneyRequestForm form,
            CriteriaBuilder cb,
            Root<Journey> journeyRoot
    ){
        List<Predicate> RolePredicates = new ArrayList<>();
        User applicant = form.getApplicant();

        if(applicant != null){
            //TODO : Функционал поиска по ролям.
            LinkedHashMap<JourneyRole, Boolean> roleBooleanLinkedHashMap = form.getJourneyRoleMap();
            for(Map.Entry<JourneyRole,Boolean> entry : roleBooleanLinkedHashMap.entrySet()){
                if(entry.getValue()){
                    JourneyRole journeyRole = entry.getKey();
                    switch (journeyRole){
                        case admin:
                        case editor:
                        case participant:
                            RolePredicates.add(rolePredicates_Participant(cb, journeyRoot, applicant));
                            break;
                        default: break;
                    }
                }
            }
            if(RolePredicates.isEmpty()){
                RolePredicates.add(alwaysTrue(cb));
            }
        }else{
            RolePredicates.add(alwaysTrue(cb));
        }
        return RolePredicates;
    }
    private Predicate rolePredicates_Participant(CriteriaBuilder cb, Root<Journey> journeyRoot, User applicant){
        Predicate predicate;
        predicate=cb.isMember(applicant, journeyRoot.get("participants"));
        return predicate;
    }
    private Predicate getTitlePredicate(
            JourneyRequestForm form,
            CriteriaBuilder cb,
            Root<Journey> journeyRoot
    ){
        Predicate predicate;
        String title = form.getTitle();
        if(isValidJourneySearchTitle(title)) {
            Integer journeyId = JourneyService.getIdFromString(title);
            if (journeyId == null) {
                predicate=cb.like(journeyRoot.get("title"), "%"+title+"%");
            }else{
                predicate=cb.equal(journeyRoot.get("id"), journeyId);
            }
        }else {
            predicate=alwaysTrue(cb);
        }
        return predicate;
    }
}
