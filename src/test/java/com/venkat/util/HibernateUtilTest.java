package com.venkat.util;

import com.venkat.entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateUtilTest {
    private static SessionFactory sessionFactory;
    private Session session = null;

    @BeforeAll
    public static void setup(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterAll
    public static void tearDown(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }

    @BeforeEach
    public void openSession(){
        session = sessionFactory.openSession();
    }

    @AfterEach
    public void closeSession(){
        if(session != null){
            session.close();
        }
    }

    @Test
    public void createActorTest(){
        session.beginTransaction();
        Actor actor = new Actor();
        actor.setFirstName("Venkatram");
        actor.setLastName("Veerareddy");
        Integer id = (Integer) session.save(actor);
        session.getTransaction().commit();
        Assertions.assertTrue( id > 0);
    }

    @Test
    public void updateActorTest(){
        session.beginTransaction();
        Integer id = 202;
        Actor actor = new Actor();
        actor.setId(id);
        actor.setFirstName("Venkat");
        actor.setLastName("Veerareddy");
        session.save(actor);
        session.getTransaction().commit();
        Actor a = session.find(Actor.class, id);
        Assertions.assertTrue( a != null);
    }

    @Test
    public void getActorByIdTest(){
        Integer id = 202;
        Actor a = session.find(Actor.class, id);
        Assertions.assertTrue( a != null);
    }


    @Test
    public void getAllActorsTest(){
        Query<Actor> actorQuery = session.createQuery("from Actor", Actor.class);
        List<Actor> actors = actorQuery.getResultList();
        Assertions.assertFalse( actors.isEmpty());
    }

    @Test
    public void deleteActoryById(){
        Integer id = 202;
        Actor actor = session.find(Actor.class, id);
        session.beginTransaction();
        session.delete(actor);
        session.getTransaction().commit();
        Actor a = session.find(Actor.class, id);
        Assertions.assertNull(a);
    }

}