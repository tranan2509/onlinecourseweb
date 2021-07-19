package Model;



import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

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
public class Course implements Serializable{
    @Id 
    private int CourseId;
    private String Name;
    private String Objective;
    @ManyToOne
    private User user;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date ModifiedDate;
    private boolean Approved;
    private String document;
    
//    @ManyToMany(mappedBy = "course_user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
//    private Set<User> user_course = new HashSet<>();
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<Chap> chaps;
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<Part> parts;
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<Excercise> excercises;
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<FAQ> faqs;
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<Instructor> instructors;
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<StudentExcercise> studentExcercises;
    
    @OneToMany (mappedBy = "Course", cascade = CascadeType.REMOVE)
    private List<Registration> registrations;
    
    public Course()
    {
        
    }
    
    public Course(int CourseId, String Name, String Objective, User user, Date ModifiedDate, boolean Approved, String document) {
        this.CourseId = CourseId;
        this.Name = Name;
        this.Objective = Objective;
        this.user = user;
        this.ModifiedDate = ModifiedDate;
        this.Approved = Approved;
        this.document = document;
    }

    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int CourseId) {
        this.CourseId = CourseId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getObjective() {
        return Objective;
    }

    public void setObjective(String Objective) {
        this.Objective = Objective;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(Date ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    public boolean isApproved() {
        return Approved;
    }

    public void setApproved(boolean Approved) {
        this.Approved = Approved;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public List<Chap> getChaps() {
        return chaps;
    }
   
    
}
