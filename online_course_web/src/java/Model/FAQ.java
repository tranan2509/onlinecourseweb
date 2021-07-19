package Model;


import Model.Course;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author A556U
 */
@Entity
public class FAQ implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int FAQId;
    
    private int FAQOrder;
    @ManyToOne
    private Course course;
    
    private String Question;
    private String Answer;

    public FAQ(){
        
    }

    public FAQ(int FAQId, int FAQOrder, Course course, String Question, String Answer) {
        this.FAQId = FAQId;
        this.FAQOrder = FAQOrder;
        this.course = course;
        this.Question = Question;
        this.Answer = Answer;
    }

    public FAQ(int FAQOrder, Course course, String Question, String Answer) {
        this.FAQOrder = FAQOrder;
        this.course = course;
        this.Question = Question;
        this.Answer = Answer;
    }
    
    
    public int getFAQId() {
        return FAQId;
    }

    public void setFAQId(int FAQId) {
        this.FAQId = FAQId;
    }

    public int getFAQOrder() {
        return FAQOrder;
    }

    public void setFAQOrder(int FAQOrder) {
        this.FAQOrder = FAQOrder;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }
    
    
    
}
