package medium.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Example3 {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("cat1");
             ObjectOutputStream out = new ObjectOutputStream(fos)) {

            var owner = new Owner("Tom", new Dog());

            out.writeObject(owner);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
