package com.example.model.Traveling.SQL_Query;

import com.example.model.Traveling.Journey;
import com.example.model.Traveling.JourneyRequestForm;

public interface JourneyRepositoryCustom{
    java.util.List<Journey>JourneyForm_SQLQuery(JourneyRequestForm form);
    java.util.List<Journey>SQLQuery(String string);
}
