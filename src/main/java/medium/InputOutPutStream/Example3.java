package medium.InputOutPutStream;

import java.io.*;

public class Example3 {
    public static void main(String[] args) {
        // Reader --> InputStreamReader or FileReader

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("test1.txt")))
        ) {


            String line = null;
            while ( (line = in.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
