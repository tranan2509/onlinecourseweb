package Model;


import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
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

public class Chap implements Serializable{
    @ManyToOne
    private Course course;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ChapId ;
    private int ChapOrder;
    private String Name;
    @OneToMany(mappedBy = "Chap",cascade = CascadeType.REMOVE )
    private List<Part> parts; 
    
     @OneToMany(mappedBy = "Chap",cascade = CascadeType.REMOVE )
    private List<Excercise> excercise; 

     @OneToMany (mappedBy = "Chap", cascade = CascadeType.REMOVE)
    private List<StudentExcercise> studentExcercises;
     
     public Chap() {
    }

    public Chap(Course course, int ChapId, int ChapOrder, String Name) {
        this.course = course;
        this.ChapId = ChapId;
        this.ChapOrder = ChapOrder;
        this.Name = Name;
    }

    public Chap(Course course, int ChapOrder, String Name) {
        this.course = course;
        this.ChapOrder = ChapOrder;
        this.Name = Name;
    }
    
    

    public int getChapOrder() {
        return ChapOrder;
    }

    public void setChapOrder(int ChapOrder) {
        this.ChapOrder = ChapOrder;
    }

    

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public int getChapId() {
        return ChapId;
    }

    public void setChapId(int ChapId) {
        this.ChapId = ChapId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public List<Part> getParts() {
        return parts;
    }

    public List<Excercise> getExcercise() {
        return excercise;
    }

    public List<StudentExcercise> getStudentExcercises() {
        return studentExcercises;
    }
    
    
}
