package Model;

import Model.Course;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-06-21T11:20:04")
@StaticMetamodel(FAQ.class)
public class FAQ_ { 

    public static volatile SingularAttribute<FAQ, String> Answer;
    public static volatile SingularAttribute<FAQ, Integer> FAQOrder;
    public static volatile SingularAttribute<FAQ, Integer> FAQId;
    public static volatile SingularAttribute<FAQ, Course> course;
    public static volatile SingularAttribute<FAQ, String> Question;

}