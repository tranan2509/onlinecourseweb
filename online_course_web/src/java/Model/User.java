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
 * @author TRAN VAN AN
 */
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserId;
    private String Name;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date DateOfBirth;
    private String Email;
    private boolean Gender;
    private String Phone;
//    
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
////    List<Course> Registedcourses;
//     
//    @JoinTable(name = "userid_courseid", 
//    joinColumns = { @JoinColumn(name = "userid") }, 
//    inverseJoinColumns = {@JoinColumn(name = "courseid") })
//    private Set<Course> course_user = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name="RoleId")
    private Role role;
    
    @OneToOne
    @JoinColumn(name="AccountId")
    private Account account;

    @OneToMany (mappedBy = "User", cascade = CascadeType.REMOVE)
    private List<Course> courses;
    
    @OneToMany (mappedBy = "User", cascade = CascadeType.REMOVE)
    private List<StudentExcercise> studentExcercises;
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp CreatedDate;

    public User() {
    }

    public User(String Name, Date DateOfBirth, String Email, boolean Gender, String Phone, Role role, Timestamp CreatedDate) {
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Email = Email;
        this.Gender = Gender;
        this.Phone = Phone;
        this.role = role;
        this.CreatedDate = CreatedDate;
    }

    public User(int UserId, String Name, Date DateOfBirth, String Email, boolean Gender, String Phone, Role role, Account account, Timestamp CreatedDate) {
        this.UserId = UserId;
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Email = Email;
        this.Gender = Gender;
        this.Phone = Phone;
        this.role = role;
        this.account = account;
        this.CreatedDate = CreatedDate;
    }

    public User(String Name, Date DateOfBirth, String Email, boolean Gender, String Phone, Role role, Account account, Timestamp CreatedDate) {
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Email = Email;
        this.Gender = Gender;
        this.Phone = Phone;
        this.role = role;
        this.account = account;
        this.CreatedDate = CreatedDate;
    }
    
    

    
    public User(int UserId, String Name, Date DateOfBirth, String Email, boolean Gender, String Phone, Role role, Timestamp CreatedDate) {
        this.UserId = UserId;
        this.Name = Name;
        this.DateOfBirth = DateOfBirth;
        this.Email = Email;
        this.Gender = Gender;
        this.Phone = Phone;
        this.role = role;
        this.CreatedDate = CreatedDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Timestamp CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

//    public List<Course> getRegistedcourses() {
//        return Registedcourses;
//    }
//
//    public void setRegistedcourses(List<Course> Registedcourses) {
//        this.Registedcourses = Registedcourses;
//    }

    public List<Course> getCourses() {
        return courses;
    }

    
}
