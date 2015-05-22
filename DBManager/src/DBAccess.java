// File:    ConnectionManager.java
// Created: 14/05/2015

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import javax.imageio.spi.ServiceRegistry;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

/**
 * @author mahefa
 */
public class DBAccess {

    private static DBAccess dbAccess = null;

    private SessionFactory sessionFactory;

    private DBAccess() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
//        cfg.configure("hibernate.cfg.xml");
        cfg.configure();
        try {
            sessionFactory = cfg.buildSessionFactory(
                    new StandardServiceRegistryBuilder().applySettings(
                            cfg.getProperties()).build());
        } catch (PropertyNotFoundException e) {
            e.printStackTrace();
            sessionFactory.close();
        }
    }

    public static DBAccess getDbAccess() {
        if (dbAccess == null) {
            dbAccess = new DBAccess();
        }
        return dbAccess;
    }

    public static void DBClose() {
        if (dbAccess != null) {
            dbAccess.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }

    public Project getProjectByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Project p where p.name=:name");
        query.setParameter("name", name);
        List l = query.list();
        session.close();
        return l.size() == 0 ? null : (Project) l.get(0);
    }

    public Format getFormatByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Format f where f.name=:name");
        query.setParameter("name", name);
        List l = query.list();
        session.close();
        return l.size() == 0 ? null : (Format) l.get(0);
    }

    public Ocrconfig getConfigurationByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from OCRConfig c where c.name=:name");
        query.setParameter("name", name);
        List l = query.list();
        session.close();
        return l.size() == 0 ? null : (Ocrconfig) l.get(0);
    }

    public List<Project> listProject() {
        return sessionFactory.openSession().createCriteria(Project.class).list();
    }

    public List<Format> listFormat() {
        return sessionFactory.openSession().createCriteria(Format.class).list();
    }

    public List<Ocrconfig> listConfiguration() {
        return sessionFactory.openSession().createCriteria(Ocrconfig.class).list();
    }

    public void addProject(Project p) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(p);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void addFormat(Format f) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(f);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void addConfiguration(Ocrconfig c) {
        System.out.println("Adding Configuration: " + ToStringBuilder.reflectionToString(c));
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(c);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public void updateProject(Project p) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Project p0 = (Project) session.get(Project.class, p.getIdProject());
        if (p0 != null) {
            session.merge(p);
            tx.commit();
        }
        session.close();
    }

    public void updateFormat(Format f) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Format f0 = (Format) session.get(Format.class, f.getIdFormat());
        if (f0 != null) {
            session.merge(f);
            tx.commit();
        }
        session.close();
    }

    public void updateConfiguration(Ocrconfig c) {
        System.out.println("Updating Configuration: " + ToStringBuilder.reflectionToString(c));
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Ocrconfig c0 = (Ocrconfig) session.get(Ocrconfig.class, c.getIdOcrconfig());
        if (c0 != null) {
            session.merge(c);
            tx.commit();
        }
        session.close();
    }

    public void deleteProject(Project p) {
        Session session = sessionFactory.openSession();
        Project p0 = (Project) session.get(Project.class, p.getIdProject());
        session.delete(p0);
        session.close();
    }

    public void deleteFormat(Format f) {
        Session session = sessionFactory.openSession();
        Format f0 = (Format) session.get(Format.class, f.getIdFormat());
        session.delete(f0);
        session.close();
    }

    public void deleteConfiguration(Ocrconfig c) {
        Session session = sessionFactory.openSession();
        Ocrconfig c0 = (Ocrconfig) session.get(Project.class, c.getIdOcrconfig());
        session.delete(c0);
        session.close();
    }
}
