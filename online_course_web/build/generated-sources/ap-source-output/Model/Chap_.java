package Model;

import Model.Course;
import Model.Excercise;
import Model.Part;
import Model.StudentExcercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(Chap.class)
public class Chap_ { 

    public static volatile ListAttribute<Chap, Excercise> excercise;
    public static volatile SingularAttribute<Chap, Integer> ChapId;
    public static volatile ListAttribute<Chap, StudentExcercise> studentExcercises;
    public static volatile SingularAttribute<Chap, Integer> ChapOrder;
    public static volatile ListAttribute<Chap, Part> parts;
    public static volatile SingularAttribute<Chap, Course> course;
    public static volatile SingularAttribute<Chap, String> Name;

}