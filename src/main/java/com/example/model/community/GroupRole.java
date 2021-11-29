package com.example.model.community;

import java.util.HashSet;
import java.util.Set;

public enum GroupRole {
    participant("Участник"),
    editor("Редактор"){
        @Override
        public Set<GroupRole> getRoles() {
            Set<GroupRole> ret = super.getRoles();
            ret.add(GroupRole.participant);
            return ret;
        }
    },
    admin("Администратор"){
        @Override
        public Set<GroupRole> getRoles() {
            Set<GroupRole> ret = super.getRoles();
            ret.add(GroupRole.participant);
            ret.add(GroupRole.editor);
            return ret;
        }
    };

    private final String title;

    GroupRole(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Set<GroupRole> getRoles(){
        Set<GroupRole> ret =  new HashSet<>();
        ret.add(this);
        return ret;
    }
}
