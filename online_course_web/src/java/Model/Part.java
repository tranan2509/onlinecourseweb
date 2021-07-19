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
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
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
public class Part implements Serializable{

    @ManyToOne
    private Chap chap;
    
    @ManyToOne
    private Course course;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PartId;
    private int PartOrder;
    private String Name;
    private String Video;
    private String Document;
    
    @OneToMany (mappedBy = "Part", cascade = CascadeType.REMOVE)
    private List<Excercise> excercises;
    
    @OneToMany (mappedBy = "Part", cascade = CascadeType.REMOVE)
    private List<StudentExcercise> studentExcercises;
    
    public Part()
    {
        
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getPartOrder() {
        return PartOrder;
    }

    public void setPartOrder(int PartOrder) {
        this.PartOrder = PartOrder;
    }

  

    public Part(Chap chap, Course course, int PartOrder, String Name, String Video, String Document) {
        this.chap = chap;
        this.course = course;
        this.PartOrder = PartOrder;
        this.Name = Name;
        this.Video = Video;
        this.Document = Document;
    }
    
    

    public Chap getChap() {
        return chap;
    }

    public void setChap(Chap chap) {
        this.chap = chap;
    }

    public int getPartId() {
        return PartId;
    }

    public void setPartId(int PartId) {
        this.PartId = PartId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getVideo() {
        return Video;
    }

    public void setVideo(String Video) {
        this.Video = Video;
    }

    public String getDocument() {
        return Document;
    }

    public void setDocument(String Document) {
        this.Document = Document;
    }

    public List<Excercise> getExcercises() {
        return excercises;
    }

    
}
