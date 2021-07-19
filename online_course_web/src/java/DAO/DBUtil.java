/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.*;


public class DBUtil {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("new_online_course_webPU");
    public static EntityManagerFactory getEmFactory()
    {
        return emf;
    }
}
