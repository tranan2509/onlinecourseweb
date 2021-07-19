package Model;


import Model.Course;
import Model.Chap;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Excercise implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ExcerciseId;
    private int ExcerciseOrder;
    
    @ManyToOne
    private Course course;
    
    @ManyToOne
    private Chap chap;
    
    @ManyToOne
    private Part part;
    
    @OneToMany (mappedBy = "Excercise", cascade = CascadeType.REMOVE)
    private List<StudentExcercise> studentExcercises;
    
    private String Question;
    private String AnswerA;
    private String AnswerB;
    private String AnswerC;
    private String AnswerD;
    
    private String CorrectAnswer;
    private String Explaination;

    
    public Excercise()
    {
        
    }

    public Excercise(int ExcerciseId, int ExcerciseOrder, Course course, Chap chap, Part part, String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, String CorrectAnswer, String Explaination) {
        this.ExcerciseId = ExcerciseId;
        this.ExcerciseOrder = ExcerciseOrder;
        this.course = course;
        this.chap = chap;
        this.part = part;
        this.Question = Question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.CorrectAnswer = CorrectAnswer;
        this.Explaination = Explaination;
    }

    public Excercise(int ExcerciseOrder, Course course, Chap chap, Part part, String Question, String AnswerA, String AnswerB, String AnswerC, String AnswerD, String CorrectAnswer, String Explaination) {
        this.ExcerciseOrder = ExcerciseOrder;
        this.course = course;
        this.chap = chap;
        this.part = part;
        this.Question = Question;
        this.AnswerA = AnswerA;
        this.AnswerB = AnswerB;
        this.AnswerC = AnswerC;
        this.AnswerD = AnswerD;
        this.CorrectAnswer = CorrectAnswer;
        this.Explaination = Explaination;
    }

    public int getExcerciseId() {
        return ExcerciseId;
    }

    public void setExcerciseId(int ExcerciseId) {
        this.ExcerciseId = ExcerciseId;
    }

    public int getExcerciseOrder() {
        return ExcerciseOrder;
    }

    public void setExcerciseOrder(int ExcerciseOrder) {
        this.ExcerciseOrder = ExcerciseOrder;
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

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswerA() {
        return AnswerA;
    }

    public void setAnswerA(String AnswerA) {
        this.AnswerA = AnswerA;
    }

    public String getAnswerB() {
        return AnswerB;
    }

    public void setAnswerB(String AnswerB) {
        this.AnswerB = AnswerB;
    }

    public String getAnswerC() {
        return AnswerC;
    }

    public void setAnswerC(String AnswerC) {
        this.AnswerC = AnswerC;
    }

    public String getAnswerD() {
        return AnswerD;
    }

    public void setAnswerD(String AnswerD) {
        this.AnswerD = AnswerD;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public String getExplaination() {
        return Explaination;
    }

    public void setExplaination(String Explaination) {
        this.Explaination = Explaination;
    }

    public List<StudentExcercise> getStudentExcercises() {
        return studentExcercises;
    }

    
}
