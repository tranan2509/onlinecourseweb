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

/**
 *
 * @author A556U
 */
@Entity
public class Instructor implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    
    private String name;
    private String position;  
    private String pathOfImage;
    @ManyToOne
    private Course course;

    public Instructor()
    {
        
    }
    public Instructor(int id, String name, String position, String pathOfImage, Course course) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.pathOfImage = pathOfImage;
        this.course = course;
    }
    
     public Instructor(String name, String position, String pathOfImage, Course course) {
        this.name = name;
        this.position = position;
        this.pathOfImage = pathOfImage;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPathOfImage() {
        return pathOfImage;
    }

    public void setPathOfImage(String pathOfImage) {
        this.pathOfImage = pathOfImage;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
   
}
