package medium.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Example5 {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("c");
             ObjectInputStream ois = new ObjectInputStream(in)) {

                C c = (C) ois.readObject(); // A

            System.out.println(c);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
