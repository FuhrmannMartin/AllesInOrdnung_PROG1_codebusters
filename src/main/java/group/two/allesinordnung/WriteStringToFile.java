package group.two.allesinordnung;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Java Example Program to Write String to File
 * https://www.tutorialkart.com/java/java-write-string-to-file/
 */

public class WriteStringToFile {

    //public static void main(String[] args) {
    public static void main(File file, String data) {

        try(FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            //convert string to byte array
            byte[] bytes = data.getBytes();
            //write byte array to file
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.print("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}