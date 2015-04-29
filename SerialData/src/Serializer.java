// File:    Serializer.java
// Created: 28/04/2015

import java.io.*;

/**
 * @author mahefa
 */
public class Serializer<T> {
    public static final String formatExtension = ".fmt";
    public static final String dbConnectionExtension = ".dbc";
    public static final String parametersExtension = ".cfg";

    private String extension = "";

    public Serializer(String extension) {
        this.extension = extension;
    }

    public void writeObject(T object, String name) {
        try {
            FileOutputStream fileOut = new FileOutputStream(name + "." + extension);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T readObject(String name) {
        T object = null;
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object = (T) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void saveFormat(CellPanel object, String name) {
        Serializer<CellPanel> cellPanelSerializer = new Serializer<CellPanel>(Serializer.formatExtension);
        cellPanelSerializer.writeObject(object, Parameters.getInstance().getProjectPath() + name);
    }

    public static CellPanel loadFormat(String name) {
        Serializer<CellPanel> cellPanelSerializer = new Serializer<CellPanel>(Serializer.formatExtension);
        return cellPanelSerializer.readObject(Parameters.getInstance().getProjectPath() + name);
    }

    public static void saveParameters(Parameters parameters) {
        Serializer<Parameters> parametersSerializer = new Serializer<Parameters>(Serializer.parametersExtension);
        parametersSerializer.writeObject(parameters, Parameters.getInstance().getProjectPath() + "configuration");
    }

    public static Parameters readParameters() {
        Serializer<Parameters> parametersSerializer = new Serializer<Parameters>(Serializer.parametersExtension);
        return parametersSerializer.readObject(Parameters.getInstance().getProjectPath() + "configuration");
    }

}
