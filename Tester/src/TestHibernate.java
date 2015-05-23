// File:    TestHibernate.java
// Created: 20/05/2015

/**
 * @author mahefa
 */
public class TestHibernate {

    public TestHibernate() {
    }

    public static void main(String[] args) {
        DBAccess dbAccess = DBAccess.getDbAccess();
//        Project p = new Project("Blow", "path0");
//        Project p2 = new Project("Ino", "tada");
//        dbAccess.addProject(p);
//        dbAccess.addProject(p2);
        Ocrconfig c = dbAccess.getConfigurationByName("test0");
//        System.out.println("Got config : " + ToStringBuilder.reflectionToString(c));
//        c.setMargin(15);
//        Project p = dbAccess.getProjectByName("test0");
        dbAccess.deleteEntry(c);
        dbAccess.close();
    }
}
