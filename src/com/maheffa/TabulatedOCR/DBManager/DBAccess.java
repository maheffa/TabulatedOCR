package com.maheffa.TabulatedOCR.DBManager;// File:    ConnectionManager.java
// Created: 14/05/2015

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * @author mahefa
 */
public class DBAccess {

    private static DBAccess dbAccess = null;
    private static Ocrconfig currentConfiguration = null;

    private SessionFactory sessionFactory;

    private DBAccess() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.WARNING);
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();
        cfg.configure("com/maheffa/TabulatedOCR/DBManager/hibernate.cfg.xml");
//        cfg.configure();
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

    public static Ocrconfig getCurrentConfiguration() {
        if (currentConfiguration == null) {
            currentConfiguration = dbAccess.getConfigurationByName("default");
        }
        return currentConfiguration;
    }

    public static void setCurrentConfiguration(Ocrconfig configuration) {
        currentConfiguration = configuration;
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
        Project p = l.size() == 0 ? null : (Project) l.get(0);
        if (p == null) {
            System.out.println("No project by name " + name + " found");
        } else {
            System.out.println("Fetching project " + ToStringBuilder.reflectionToString(p));
        }
        return p;
    }

    public Format getFormatByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Format f where f.name=:name");
        query.setParameter("name", name);
        List l = query.list();
        session.close();
        Format f = l.size() == 0 ? null : (Format) l.get(0);
        if (f == null) {
            System.out.println("No format by name " + name + " found");
        } else {
            System.out.println("Fetching format " + f);
//            Hibernate.initialize(f.getTableFormats());
//            Hibernate.initialize(f.getTextFormats());
            if (f.getType().equalsIgnoreCase("table")) {
                System.out.println("\tTableFormat fetched: ");
                for (TableFormat tf : (Set<TableFormat>)f.getTableFormats()) {
                    System.out.println("\t" + tf);
                    System.out.println("\t\tColumns:");
//                    Hibernate.initialize(tf.getColumnCharacteristics());
                    for (ColumnCharacteristic col : (Set<ColumnCharacteristic>) tf.getColumnCharacteristics()) {
                        System.out.println("\t\t" + col);
                    }
                }
            }
        }
        return f;
    }

    public Ocrconfig getConfigurationByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Ocrconfig c where c.name=:name");
        query.setParameter("name", name);
        List l = query.list();
        session.close();
        Ocrconfig c = l.size() == 0 ? null : (Ocrconfig) l.get(0);
        if (c == null) {
            System.out.println("No configuration by name " + name + " found");
        } else {
            System.out.println("Fetching configuration " + ToStringBuilder.reflectionToString(c));
        }
        return c;
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

    public Object[] getClassAndId(Object o) {
        int id = 0;
        Class c = null;
        if (o instanceof Project) {
            id = ((Project) o).getIdProject();
            c = Project.class;
        } else if (o instanceof Format) {
            id = ((Format) o).getIdFormat();
            c = Format.class;
        } else if (o instanceof Ocrconfig) {
            id = ((Ocrconfig) o).getIdOcrconfig();
            c = Ocrconfig.class;
        } else if (o instanceof TableFormat) {
            id = ((TableFormat) o).getIdTableFormat();
            c = TableFormat.class;
        } else if (o instanceof ColumnCharacteristic) {
            id = ((ColumnCharacteristic) o).getIdColumnCharacteristic();
            c = ColumnCharacteristic.class;
        } else if (o instanceof TextFormat) {
            id = ((TextFormat) o).getIdTextFormat();
            c = TextFormat.class;
        } else {
            System.err.println("Error while determining class instance");
        }
        return new Object[]{c, new Integer(id)};
    }

    public void addEntry(Object o) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(o);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
        session.close();
        System.out.println("Successfully added " + ToStringBuilder.reflectionToString(o));
    }

    public void updateEntry(Object o) {
        Object[] info = getClassAndId(o);
        Class c = (Class) info[0];
        Integer id = (Integer) info[1];
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Object o0 = session.get(c, id);
        if (o0 != null) {
            session.merge(o);
            tx.commit();
        }
        session.close();
        System.out.println("Successfully updated " + ToStringBuilder.reflectionToString(o));
    }

    public void deleteEntry(Object o) {
        Object[] info = getClassAndId(o);
        Class c = (Class) info[0];
        Integer id = (Integer) info[1];
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Object o0 = session.get(c, id);
        session.delete(o0);
        tx.commit();
        session.close();
        System.out.println("Successfully deleted " + ToStringBuilder.reflectionToString(o));
    }

}
