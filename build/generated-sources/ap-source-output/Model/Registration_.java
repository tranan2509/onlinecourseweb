package Model;

import Model.Course;
import Model.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-04-19T12:44:40")
@StaticMetamodel(Registration.class)
public class Registration_ { 

    public static volatile SingularAttribute<Registration, Integer> registrationId;
    public static volatile SingularAttribute<Registration, Course> course;
    public static volatile SingularAttribute<Registration, User> user;

}