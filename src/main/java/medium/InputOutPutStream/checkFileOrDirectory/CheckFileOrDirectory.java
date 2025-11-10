package medium.InputOutPutStream.checkFileOrDirectory;

import java.io.File;

public class CheckFileOrDirectory {
    public static void main(String[] args) {

        File my_file_dir = new File("D:\\my_file\\document.txt");

        if (my_file_dir.isDirectory()) {
            System.out.println(my_file_dir.getAbsolutePath() + " is a directory");
        } else {
            System.out.println(my_file_dir.getAbsolutePath() + " is not a directory");
        }

        if (my_file_dir.isFile()) {
            System.out.println(my_file_dir.getAbsolutePath() + " is a file.\n" );
        } else {
            System.out.println(my_file_dir.getAbsolutePath() + " is not a file.\n" );
        }
    }
}
