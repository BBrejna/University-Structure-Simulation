package tools;

import java.io.*;

public class Serializer<T> {
    public void saveToFile(MyHashSet<T> content, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(content);
            System.out.println("Content written to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public MyHashSet<T> readFromFile(String filename) {
        MyHashSet<T> content = new MyHashSet<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            content = (MyHashSet<T>) ois.readObject();
            System.out.println("Content read from file: " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}
