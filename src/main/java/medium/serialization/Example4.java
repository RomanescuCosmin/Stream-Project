package medium.serialization;

import java.io.*;

public class Example4 {

    public static void main(String[] args) {
        C c = new C(); // A B C

        try (FileOutputStream fos = new FileOutputStream("c");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            out.writeObject(c);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
