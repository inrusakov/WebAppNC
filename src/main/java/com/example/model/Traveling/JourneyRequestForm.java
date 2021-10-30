package com.example.model.Traveling;

import com.example.model.User;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter
@Setter
public class JourneyRequestForm {
    String title = "";
    LinkedHashMap<JourneyStatus, Boolean> journeyStatusMap = new LinkedHashMap<>();
    LinkedHashMap<JourneyAccess, Boolean> journeyAccessMap = new LinkedHashMap<>();
    LinkedHashMap<JourneyRole, Boolean> journeyRoleMap = new LinkedHashMap<>();
    User applicant;

    public JourneyRequestForm(){
        for(JourneyStatus journeyStatus : JourneyStatus.values()){
            if(journeyStatus.CanUseInForms()){
                this.journeyStatusMap.put(journeyStatus, false);
            }
        }

        for(JourneyAccess journeyAccess : JourneyAccess.values()){
            this.journeyAccessMap.put(journeyAccess, false);
        }

        for(JourneyRole journeyRole : JourneyRole.values()){
            if(journeyRole.CanUseInForms()){
                this.journeyRoleMap.put(journeyRole, false);
            }
        }
    }
}
