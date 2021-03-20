package com.venkat.service;

import com.venkat.entity.Actor;
import com.venkat.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

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

    public Actor getActorById(Integer id){
        Session session = getSession();
        return session.find(Actor.class, id);
    }

    public List<Actor> getAllActors(){
        Session session = getSession();
        Query<Actor> actorQuery = session.createQuery("from Actor", Actor.class);
        List<Actor> actors = actorQuery.getResultList();
        return actors;
    }

    public void deleteActorById(Integer id){
        Session session = getSession();
        Actor actor = session.find(Actor.class, id);
        session.beginTransaction();
        session.delete(actor);
        session.getTransaction().commit();
    }
}
