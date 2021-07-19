package Model;

import Model.Course;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(Instructor.class)
public class Instructor_ { 

    public static volatile SingularAttribute<Instructor, String> name;
    public static volatile SingularAttribute<Instructor, Course> course;
    public static volatile SingularAttribute<Instructor, Integer> id;
    public static volatile SingularAttribute<Instructor, String> position;
    public static volatile SingularAttribute<Instructor, String> pathOfImage;

}