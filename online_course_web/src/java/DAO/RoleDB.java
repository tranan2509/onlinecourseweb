/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.*;

import java.util.*;
import javax.persistence.*;
/**
 *
 * @author TRAN VAN AN
 */
public class RoleDB {
    
    public static Role GetRoleByRoleId(int roleId)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            Role role = em.find(Role.class, roleId);
            return role;
        }finally{
            em.close();
        }
    }
    
    public static List<Role> GetRoles()
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        String qString = "SELECT r FROM Role r";
        TypedQuery<Role> q = em.createQuery(qString, Role.class);
        List<Role> roles = null;
        try{
            roles = q.getResultList();
            if (roles == null || roles.isEmpty())
                roles = null;
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }finally{
            em.close();
        }
        return roles;
    }
    
    public static List<Role> GetRolesByRole(String roleName){
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        if (roleName.contains("all"))
            return GetRoles();
        String qString = "SELECT r FROM Role r WHERE r.RoleName = :roleName";
        TypedQuery<Role> q = em.createQuery(qString, Role.class);
        q.setParameter("roleName", roleName);
        List<Role> roles = null;
        try{
            roles = q.getResultList();
            if (roles == null || roles.isEmpty())
                roles = null;
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
        }finally{
            em.close();
        }
        return roles;
    }
    
    public static Role GetRoleInListByRoleId(List<Role> roles, int roleId)
    {
        for (int i = 0; i < roles.size(); i++)
            if (roles.get(i).getRoleId() == roleId)
                return roles.get(i);
        return null;
    }
    
    public static Role GetRoleInListByRoleName(List<Role> roles, String roleName)
    {
        for (int i = 0; i < roles.size(); i++)
            if (roles.get(i).getRoleName().equals(roleName))
                return roles.get(i);
        return null;
    }
    
    public static boolean InsertRole(Role role)
    {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        boolean isInserted = false;
        try{
            trans.begin();
            em.persist(role);
            trans.commit();
            isInserted = true;
        }catch(Exception ex)
        {
            System.out.println("Error: " + ex.getMessage());
            trans.rollback();
        }finally{
            em.close();
        }
        return isInserted;
    }
}
