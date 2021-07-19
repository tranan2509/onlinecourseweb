package Model;

import Model.Account;
import Model.Course;
import Model.Role;
import Model.StudentExcercise;
import java.sql.Date;
import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Date> DateOfBirth;
    public static volatile ListAttribute<User, Course> courses;
    public static volatile SingularAttribute<User, String> Email;
    public static volatile SingularAttribute<User, Role> role;
    public static volatile SingularAttribute<User, Integer> UserId;
    public static volatile SingularAttribute<User, String> Phone;
    public static volatile ListAttribute<User, StudentExcercise> studentExcercises;
    public static volatile SingularAttribute<User, Timestamp> CreatedDate;
    public static volatile SingularAttribute<User, Boolean> Gender;
    public static volatile SingularAttribute<User, Account> account;
    public static volatile SingularAttribute<User, String> Name;

}