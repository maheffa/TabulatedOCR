// File:    ProjectConfiguration.java
// Created: 05/05/2015

import java.io.Serializable;

/**
 * @author mahefa
 */
public class ProjectConfiguration implements Serializable {

    private String name = "";
    private String imagePath = "";
    private String formatName = "";

    public ProjectConfiguration(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[project: ").append(name).append("]\n");
        str.append("Image path: ").append(imagePath).append('\n');
        str.append("Format: ").append(formatName).append('\n');
        return str.toString();
    }
}
