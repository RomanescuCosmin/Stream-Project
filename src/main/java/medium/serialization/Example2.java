package medium.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Example2 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("cat1");
             ObjectInputStream ois = new ObjectInputStream(in)) {

            Cat c = (Cat) ois.readObject();
            System.out.println(c);
        } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
        }
    }
}
