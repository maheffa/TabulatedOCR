// File:    Serializer.java
// Created: 28/04/2015

import java.io.*;
import java.util.ArrayList;

/**
 * @author mahefa
 */
public class Serializer<T> {
    public static final String FORMAT_EXTENSION = "fmt";
    public static final String DATABASE_CONNECTION_EXTENSION = "dbc";
    public static final String PARAMETER_EXTENSION = "cfg";

    public static final String[] extensions = new String[] {
            FORMAT_EXTENSION,
            DATABASE_CONNECTION_EXTENSION,
            PARAMETER_EXTENSION
    };

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
            FileInputStream fileIn = new FileInputStream(name + "." + extension);
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
        Serializer<CellPanel> cellPanelSerializer = new Serializer<CellPanel>(Serializer.FORMAT_EXTENSION);
        cellPanelSerializer.writeObject(object, Parameters.getInstance().getProjectPath() + name);
    }

    public static CellPanel loadFormat(String name) {
        Serializer<CellPanel> cellPanelSerializer = new Serializer<CellPanel>(Serializer.FORMAT_EXTENSION);
        return cellPanelSerializer.readObject(Parameters.getInstance().getProjectPath() + name);
    }

    public static void deleteFormat(String name) {
        File f = new File(Parameters.getInstance().getProjectPath() + name + "." + Serializer.FORMAT_EXTENSION);
        if (f.exists()) {
            f.delete();
        } else {
            System.out.println("Format not found");
        }
    }

    public static String[] listFormat() {
        File projectDir = Parameters.getInstance().getProjectDir();
        ArrayList<String> list = new ArrayList<String>();
        File[] listFile = projectDir.listFiles();
        if (listFile != null) {
            for (File f : projectDir.listFiles()) {
                String[] bpe = SerializerUtil.getPathBaseExtension(f.getPath());
                if (bpe[2].equals(Serializer.FORMAT_EXTENSION)) {
                    list.add(bpe[1]);
                }
            }
        }
        String[] s = new String[list.size()];
        for (int i = 0; i < s.length; i++) {
            s[i] = list.get(i);
        }
        return s;
    }

    public static void saveParameters(Parameters parameters) {
        Serializer<Parameters> parametersSerializer = new Serializer<Parameters>(Serializer.PARAMETER_EXTENSION);
        parametersSerializer.writeObject(parameters, Parameters.getInstance().getProjectPath() + "configuration");
    }

    public static Parameters readParameters() {
        Serializer<Parameters> parametersSerializer = new Serializer<Parameters>(Serializer.PARAMETER_EXTENSION);
        return parametersSerializer.readObject(Parameters.getInstance().getProjectPath() + "configuration");
    }

    public static boolean isProjectFile(String fileName) {
        String extension = SerializerUtil.getPathBaseExtension(fileName)[2];
        for (String s : extensions) {
            if (extension.equals(s)) {
                return true;
            }
        }
        return false;
    }

}
