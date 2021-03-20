package com.venkat.service;

import com.venkat.entity.Actor;
import com.venkat.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;

public class ActorService {

    private Session getSession(){
        return HibernateUtil.getSessionFactory().openSession();
    }

    public Integer save(Actor actor){
        Session session = getSession();
        session.beginTransaction();
        Integer id = (Integer) session.save(actor);
        session.getTransaction().commit();
        return id;
    }

    public Actor update(Actor actor){
        Session session = getSession();
        session.beginTransaction();
        session.save(actor);
        session.getTransaction().commit();
        Actor a = session.find(Actor.class, actor.getId());
        return session.find(Actor.class, actor.getId());
    }
}
