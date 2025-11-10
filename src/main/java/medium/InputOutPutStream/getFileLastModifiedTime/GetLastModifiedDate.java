package medium.InputOutPutStream.getFileLastModifiedTime;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Date;

public class GetLastModifiedDate {
    public static void main(String[] args) {
        File file = new File("test1.txt");
        Date date = new Date(file.lastModified());
        System.out.println("\nThe file was last modified on: "+date+"\n");

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Your name : ");
            String name = bf.readLine();
            System.out.println("Yout name is : " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
