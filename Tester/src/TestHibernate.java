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
        Project p3 = dbAccess.getProjectByName("Bro");
        p3.setInputFilePath("Bazinga");
        dbAccess.updateProject(p3);
        dbAccess.close();
    }
}
