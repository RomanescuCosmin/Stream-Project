package medium.InputOutPutStream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Example2 {
    public static void main(String[] args) {
        /**
         *  FileOutputStream
         *  FileInputStream
         *
         *  BufferedWriter -->   Write
         *  BufferedReader -->   Reader
         *
         *
         *  PrintStream --> System.out / System.err
         *  PrintWriter
         *
         */


        try (BufferedWriter out = new BufferedWriter(new FileWriter("test1.txt"))){
            out.write("hello word");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
