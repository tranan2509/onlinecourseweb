/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author A556U
 */
@Entity
public class StudentExcercise implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Course course;
    
    @ManyToOne
    private Chap chap;
    
    @ManyToOne
    private Part part;
    
    @ManyToOne
    private Excercise excercise;
    
    private boolean correct;
    
    //Lần làm thứ 
    private int time;
    
    private String selectedAnswer;
    
    public StudentExcercise(){
        
    }

    public StudentExcercise(int id, User user, Course course, Chap chap, Part part, Excercise excercise, boolean correct, int time, String selectedAnswer) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.chap = chap;
        this.part = part;
        this.excercise = excercise;
        this.correct = correct;
        this.time = time;
        this.selectedAnswer = selectedAnswer;
    }

    public StudentExcercise(User user, Course course, Chap chap, Part part, Excercise excercise, boolean correct, int time, String selectedAnswer) {
        this.user = user;
        this.course = course;
        this.chap = chap;
        this.part = part;
        this.excercise = excercise;
        this.correct = correct;
        this.time = time;
        this.selectedAnswer = selectedAnswer;
    }


    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Chap getChap() {
        return chap;
    }

    public void setChap(Chap chap) {
        this.chap = chap;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Excercise getExercise() {
        return excercise;
    }

    public void setExercise(Excercise excercise) {
        this.excercise = excercise;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Excercise getExcercise() {
        return excercise;
    }
    
    
}
