// File:    Parameters.java
// Created: 28/04/2015

import java.io.File;
import java.io.Serializable;

/**
 * @author mahefa
 */
public class Parameters implements Serializable {
    private static Parameters instance = null;

    private String projectPath = "";
    private File projectDir = null;

    public Parameters() {
        setProjectPath();
    }

    public static Parameters getInstance() {
        if (instance == null) {
            return new Parameters();
        } else {
            String v = instance.projectPath;
            int index = v.lastIndexOf(File.separator);
            if (index >= v.length() - 1) {
                v = v.substring(0, index - 1);
            }
            if (!(new File(v)).exists()) {
                instance.setProjectPath();
            }
            return instance;
        }
    }

    private void setProjectPath() {
        String basePath = System.getProperty("user.home");
        File f = new File(basePath + File.separator + "TabulatedOCR");
        if (!f.exists()) {
            f.mkdir();
        }
        projectPath = basePath + File.separator + "TabulatedOCR" + File.separator;
        projectDir = new File(projectPath);
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public String getProjectPath() {
        return this.projectPath;
    }

    public File getProjectDir() {
        if (projectDir == null) {
            File f = new File(projectPath);
            if (!f.exists()) {
                setProjectPath();
            } else {
                return f;
            }
        }
        return projectDir;
    }

}
