package com.example.repos.CriteriaBuilder;

import com.example.model.Traveling.Journey;

public interface JourneyRepositoryCustom{
    java.util.List<Journey>JourneyForm_SQLQuery(JourneyRequestForm form);
    java.util.List<Journey>SQLQuery(String string);
}
