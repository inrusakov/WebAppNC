package com.example.model.community;

import com.example.model.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor

@Entity
@Table(name = "Users_Groups")
public class Users_Groups{

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        @Column(name = "USER_GROUP_ID")
        private Integer id;

        @ManyToOne(cascade =  CascadeType.MERGE)
        @JoinColumn(name = "GROUP_ID")
        private Group group;

        @ManyToOne(cascade =  CascadeType.MERGE)
        @JoinColumn(name = "USER_ID")
        private User user;

        @Enumerated(value = EnumType.STRING)
        @Column(name = "USER_ROLE")
        private GroupRole userRole;

        public Users_Groups(Group group, User user, GroupRole role) {
                this.group = group;
                this.user = user;
                this.userRole = role;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Group getGroup() {
                return group;
        }

        public void setGroup(Group group) {
                this.group = group;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }

        public GroupRole getUserRole() {
                return userRole;
        }

        public void setUserRole(GroupRole userRole) {
                this.userRole = userRole;
        }
}
