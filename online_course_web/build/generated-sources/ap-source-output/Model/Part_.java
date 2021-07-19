package Model;

import Model.Chap;
import Model.Course;
import Model.Excercise;
import Model.StudentExcercise;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(Part.class)
public class Part_ { 

    public static volatile SingularAttribute<Part, Integer> PartOrder;
    public static volatile SingularAttribute<Part, String> Video;
    public static volatile ListAttribute<Part, StudentExcercise> studentExcercises;
    public static volatile SingularAttribute<Part, Course> course;
    public static volatile SingularAttribute<Part, Chap> chap;
    public static volatile ListAttribute<Part, Excercise> excercises;
    public static volatile SingularAttribute<Part, String> Document;
    public static volatile SingularAttribute<Part, Integer> PartId;
    public static volatile SingularAttribute<Part, String> Name;

}