package tools;

import java.io.*;
import java.util.ArrayList;

public class Serializer<T> {
    public void saveToFile(ArrayList<T> content, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(content);
            System.out.println("Content written to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<T> readFromFile(String filename) {
        ArrayList<T> content = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            content = (ArrayList<T>) ois.readObject();
            System.out.println("Content read from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}
