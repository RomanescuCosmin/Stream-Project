package medium.InputOutPutStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Example1 {
    public static void main(String[] args) {


        // InputStreamReader
        // FileReader
        // IOException and FileNotFoundException

        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String line = in.readLine();
            System.out.println("Line: " +  line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
