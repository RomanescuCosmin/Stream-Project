package medium.InputOutPutStream.GetFileInDirectory;

import java.io.File;
import java.io.FilenameFilter;

public class GetFilesByExtensions {
    public static void main(String[] args) {

        File file = new File("D:\\my_file\\document.txt");
        String[] list = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (name.toLowerCase().endsWith(".ftl")) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        for (String f : list) {
            System.out.println(f);
        }

        File myFileDir = new File("D:\\my_file\\document.txt");
        if(myFileDir.exists()) {
            System.out.println("The directory or file exists. ");
        } else {
            System.out.println("The directory or file does not exist.");
        }
    }
}
