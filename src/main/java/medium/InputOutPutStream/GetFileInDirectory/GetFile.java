package medium.InputOutPutStream.GetFileInDirectory;

import java.io.File;

public class GetFile {
    public static void main(String[] args) {

        File file = new File("D:\\my_file\\document.txt");
        String [] files = file.list();
        for (String name : files) {
            System.out.println(name);
        }
    }
}
