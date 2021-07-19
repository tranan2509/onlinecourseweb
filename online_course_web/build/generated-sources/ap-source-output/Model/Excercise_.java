package Model;

import Model.Chap;
import Model.Course;
import Model.Part;
import Model.StudentExcercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(Excercise.class)
public class Excercise_ { 

    public static volatile SingularAttribute<Excercise, Integer> ExcerciseId;
    public static volatile SingularAttribute<Excercise, Integer> ExcerciseOrder;
    public static volatile SingularAttribute<Excercise, String> Explaination;
    public static volatile SingularAttribute<Excercise, Part> part;
    public static volatile SingularAttribute<Excercise, String> AnswerD;
    public static volatile SingularAttribute<Excercise, String> AnswerB;
    public static volatile SingularAttribute<Excercise, String> AnswerC;
    public static volatile SingularAttribute<Excercise, String> AnswerA;
    public static volatile ListAttribute<Excercise, StudentExcercise> studentExcercises;
    public static volatile SingularAttribute<Excercise, Course> course;
    public static volatile SingularAttribute<Excercise, Chap> chap;
    public static volatile SingularAttribute<Excercise, String> Question;
    public static volatile SingularAttribute<Excercise, String> CorrectAnswer;

}