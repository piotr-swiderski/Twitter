package hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static HibernateUtil instance;
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static HibernateUtil getInstance(){
        if(null == instance){
            instance = new HibernateUtil();
        }
        return instance;
    }

    public void saveByHibernateSession(Object o){
        Session s = entityManager.unwrap(Session.class);
        Transaction transaction = s.beginTransaction();
        s.save(o);
        transaction.commit();
        s.close();
    }

    public void save(Object o){
        entityManager.getTransaction().begin();
        if(!entityManager.contains(o)) {
            entityManager.persist(o);
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
    }

    public void delete(Class clazz, Long objectId) {
        entityManager.getTransaction().begin();
        Object toRemove = entityManager.find(clazz, objectId);
        entityManager.remove(toRemove);
        entityManager.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
