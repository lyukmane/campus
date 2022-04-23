package files;

import java.io.FileOutputStream;
import java.io.IOException;

public class fileWrite {

    public static void main(String[] args) {

        String text = "Hello world!";
        try(FileOutputStream fos = new FileOutputStream("/Users/dima/Documents/long"))
        {
            // перевод строки в байты
            byte[] buffer = text.getBytes();

            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}