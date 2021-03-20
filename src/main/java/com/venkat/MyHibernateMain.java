package com.venkat;

import com.venkat.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class MyHibernateMain {

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String sql = "select version()";
            String result = (String) session.createNativeQuery(sql).getSingleResult();
            System.out.println("MySql Database Version is ");
            System.out.println(result);
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
    }
}
