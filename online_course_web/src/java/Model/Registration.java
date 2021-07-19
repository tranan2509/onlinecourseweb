/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
/**
 *
 * @author ad
 */
@Entity
public class Registration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int registrationId;
    
    @ManyToOne
    private User user;
    @ManyToOne
    private Course course;
    public Registration(){
        
    }
    public Registration(int registrationId, User user, Course course){
        this.registrationId = registrationId;
        this.user = user;
        this.course = course;
    }

    public Registration(User user, Course course) {
        this.user = user;
        this.course = course;
    }
    
    
    
    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
