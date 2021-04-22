package Model;

import Model.Chap;
import Model.Course;
import Model.Excercise;
import Model.Part;
import Model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-19T12:44:40")
@StaticMetamodel(StudentExcercise.class)
public class StudentExcercise_ { 

    public static volatile SingularAttribute<StudentExcercise, Excercise> excercise;
    public static volatile SingularAttribute<StudentExcercise, Boolean> correct;
    public static volatile SingularAttribute<StudentExcercise, String> selectedAnswer;
    public static volatile SingularAttribute<StudentExcercise, Part> part;
    public static volatile SingularAttribute<StudentExcercise, Course> course;
    public static volatile SingularAttribute<StudentExcercise, Chap> chap;
    public static volatile SingularAttribute<StudentExcercise, Integer> id;
    public static volatile SingularAttribute<StudentExcercise, Integer> time;
    public static volatile SingularAttribute<StudentExcercise, User> user;

}