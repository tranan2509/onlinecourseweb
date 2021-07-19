package Model;

import Model.Chap;
import Model.Excercise;
import Model.FAQ;
import Model.Instructor;
import Model.Part;
import Model.Registration;
import Model.StudentExcercise;
import Model.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(Course.class)
public class Course_ { 

    public static volatile ListAttribute<Course, Registration> registrations;
    public static volatile SingularAttribute<Course, String> document;
    public static volatile SingularAttribute<Course, Boolean> Approved;
    public static volatile SingularAttribute<Course, Date> ModifiedDate;
    public static volatile SingularAttribute<Course, String> Name;
    public static volatile ListAttribute<Course, Instructor> instructors;
    public static volatile SingularAttribute<Course, String> Objective;
    public static volatile ListAttribute<Course, FAQ> faqs;
    public static volatile SingularAttribute<Course, Integer> CourseId;
    public static volatile ListAttribute<Course, StudentExcercise> studentExcercises;
    public static volatile ListAttribute<Course, Part> parts;
    public static volatile ListAttribute<Course, Excercise> excercises;
    public static volatile ListAttribute<Course, Chap> chaps;
    public static volatile SingularAttribute<Course, User> user;

}