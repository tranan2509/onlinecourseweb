/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.apache.commons.text.StringEscapeUtils;

/**
 *
 * @author TRAN VAN AN
 */
public class CourseDB {
    public static Course GetCourseByCourseId(int courseId)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
         Course course=null;
        try{
            course = em.find(Course.class, courseId);
            
        }finally{
            em.close();
        }
        return course;
    }
    
    public static List<Course> GetCourseByUser(User user)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.user = :user";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("user", user);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }
    
    public static List<Course> GetCoursesByUser_Registration(User user)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT r.course from Registration r where r.user = :user";
        TypedQuery<Course> q = em.createQuery(queryS, Course.class);
        q.setParameter("user", user);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }
    
    public static List<Course> EscapeCourses(List<Course> courses){
        List<Course> coursesEsc = new ArrayList<Course>();
        for (int i = 0; i < courses.size(); i++){
            Course temp = courses.get(i);
            Course course = new Course(temp.getCourseId(), StringEscapeUtils.escapeHtml4(temp.getName()), StringEscapeUtils.escapeHtml4(temp.getObjective())
                    , temp.getUser(), temp.getModifiedDate(), true, StringEscapeUtils.escapeHtml4(temp.getDocument()));
            coursesEsc.add(course);
        }
        return  coursesEsc;
    }
    
    public static List<Course> GetCourseByUserId(int userId)
    { 
        User user = UserDB.GetUserByUserId(userId);
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.user = :user";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("user", user);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }
    
    public static List<Course> GetCourses()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Course u where u.Approved = 1 ";
            
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
       
        List<Course> courses = null;
        try{
            courses = q.getResultList();
            if (courses == null || courses.isEmpty())
                courses = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
           em.close();
        }
        return courses;
    }
    
    public static boolean insertCourse(Course course) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.persist(course);
            tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }

     public static boolean updateCourse(Course course) {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.merge(course);
            tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
     
    public static boolean deleteCourse(Course course)
    {
        boolean result = true;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction tran = entityManager.getTransaction();
        tran.begin();

        try {
            entityManager.remove(entityManager.merge(course));
            tran.commit();
        } catch (Exception e) {
            System.out.println(e);
            tran.rollback();
            result = false;
        } finally {
            entityManager.close();
        }
        return result;
    }
    
    public static int getMaxCourseID() {
       int max=0;
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT max(CourseId) from Course";

        try {
            if ( entityManager.createNativeQuery(queryS).getSingleResult() == null)
                max = 0;
            else
                max = Integer.parseInt(entityManager.createNativeQuery(queryS).getSingleResult().toString());
        } catch (NumberFormatException e) {
            max=0;
        } finally {
            entityManager.close();
        }
        return max;
    }

    public static Course getCourseById(int courseid) {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "SELECT c from Course c where c.CourseId = :courseid";
        TypedQuery<Course> query = entityManager.createQuery(queryS, Course.class);
        query.setParameter("courseid", courseid);

         Course course =null;
        try {
            course= query.getSingleResult();
        } catch (Exception ex) {
            
        }
        finally
        {
            entityManager.close();
        }
        return course;
    }
    
    public static boolean courseExists(int courseid)
    {
        Course u = getCourseById(courseid);
        return u!=null;
    }
    
     public static List<Excercise> getAllPartOfChap( int courseid, int chapid, int partid)
    {
        EntityManager entityManager = DBUtil.getEmFactory().createEntityManager();
        String queryS = "Select e from Exercise e where e.CourseId = :courseid and e.ChapId = :chapid and e.PartId = :partid ";
        
        TypedQuery<Excercise> q = entityManager.createQuery(queryS, Excercise.class);
        q.setParameter("courseid", courseid);
        q.setParameter("chapid", chapid);
        q.setParameter("partid", partid);
        
        List<Excercise> exerciseList ;
        
        try
        {
            exerciseList = q.getResultList();
            if( exerciseList==null || exerciseList.isEmpty())
                exerciseList=null;
        }
        finally
        {
            entityManager.close();
        }
        return exerciseList;
    }
     
     //Kiểm tra khóa học của 1 user
     public static Course getCourseOfTeacher(int courseid, User user)
     {
         EntityManager em = DBUtil.getEmFactory().createEntityManager();
          String queryS = "Select c from Course c where c.CourseId = :courseid and c.user= :user ";
        
        TypedQuery<Course> q = em.createQuery(queryS, Course.class);
        q.setParameter("courseid", courseid);
        q.setParameter("user", user);
        
        
        Course course ;
        
        try
        {
            course = q.getSingleResult();
            
        }
        catch(Exception ex)
                {
                    course=null;
                }
        finally
        {
            em.close();
        }
        
        return course;
     }
     
     public static boolean courseOfTeacherExists(int courseid, User user)
     {
         Course c = getCourseOfTeacher(courseid, user);
         return c!=null;
     }

   
     //lấy các khóa học đã được duyệt
    public static List<Course> GetAllCoursesApprovedByUser(User user)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.user = :user and c.Approved = :approved";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("user", user);
         q.setParameter("approved", true);
     
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            courses = null;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }
    
    public static List<Course> GetAllCoursesNotApprovedByUser(User user)
    { 
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.user = :user and c.Approved = :approved";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("user", user);
         q.setParameter("approved", false);
     
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            courses = null;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }
     public static List<Course> GetCoursesByUserId(int userId) {
        User user = UserDB.GetUserByUserId(userId);
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.user = :user";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("user", user);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            courses = null;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }

    public static List<Course> GetCoursesOderByAsc() {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c ORDER BY c.ModifiedDate ASC";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            courses = null;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }

    public static List<Course> GetCourseByApproved(boolean approved) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course AS c WHERE c.Approved = :approved ORDER BY c.ModifiedDate ASC";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        q.setParameter("approved", approved);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
        }catch(NoResultException ex)
        {
            courses = null;
            System.out.println("Kết nối thất bại!");
        }
        finally{
            em.close();
        }
        return courses;
    }

    public static List<Course> GetCoursesByFilter(String search) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT c FROM Course c WHERE CONCAT(c.CourseId, c.Name) LIKE '%" + search + "%' ORDER BY c.ModifiedDate ASC";
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
        List<Course> courses = null;
        try{
            courses = q.getResultList();
            if (courses == null || courses.isEmpty())
                courses = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
            em.close();
        }
        return courses;
    }
    
    public static JsonArray ConvertListToJsonArray(List<Course> courses) {
        JsonArray json = new JsonArray();
        JsonArray jsonArray = new JsonArray();
        courses.forEach(course -> {
            JsonObject formDetailsJson = new JsonObject();
            formDetailsJson.addProperty("CourseId", course.getCourseId());
            formDetailsJson.addProperty("Name", StringEscapeUtils.escapeHtml4(course.getName()));
            formDetailsJson.addProperty("Teacher", StringEscapeUtils.escapeHtml4(course.getUser().getName()));
            formDetailsJson.addProperty("Approved", course.isApproved());
            jsonArray.add(formDetailsJson);
        });
        json.addAll(jsonArray);
        return json;
    }
    public static List<Course> GetCoursesNotRegistration()
    {
        
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Course u  where u.Approved = 1";
            
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
       
        List<Course> courses = null;
        try{
            courses = q.getResultList();
            
            if (courses == null || courses.isEmpty())
                courses = null;
        }catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        finally{
           em.close();
        }
        return courses;
        
    }
    public static List<Course> CourseNotRegistration(User user) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT u FROM Course u Left Join Registration r on u = r.course where u.Approved = 1 and r.user != :user";
            
        TypedQuery<Course> q = em.createQuery(qString, Course.class);
       q.setParameter("user", user);
        List<Course> courses = null;
//        try{
            courses = q.getResultList();
            
            if (courses == null || courses.isEmpty())
                courses = null;
//        }catch (Exception ex){
//            System.out.println("Error: " + ex.getMessage());
//        }
//        finally{
//           em.close();
//        }
        return courses;
        
    }

    public static List<Course> ConvertListCourseNotRegistration(User user, List<Course> courses) {
        List<Course> returnCourses = null;
        
        for(int  i = 0; i<courses.size()-1; i++){
            if(courses.get(i)==null)            
            {
                break;
            } 
            else  
            {
                 if(RegistrationDB.registrationExists(user, courses.get(i)) == false)
                {
                    returnCourses.add(courses.get(i));
                }
            }
            
        }
       return returnCourses;
    }
    public static List<Course> NotRegistration(User user) {
        List<Course> studying = GetCoursesByUser_Registration(user);
        List<Course> ListCoursesApproved = GetCoursesNotRegistration();
        
        if(studying!=null && ListCoursesApproved!=null)
        {
            int i=0;
            while (i<ListCoursesApproved.size())
            {
                boolean flag=false;
                for(int j=0; j<studying.size(); j++)
                {
                    if(studying.get(j).getCourseId()==ListCoursesApproved.get(i).getCourseId())
                    {
                        ListCoursesApproved.remove(i);
                        flag=true;
                        break;
                    }
                }
                if(flag==false)
                    i++;
            }
        }
        return ListCoursesApproved;
        
    }

   
}
