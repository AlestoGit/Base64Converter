package alesto.converter.util;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * File manager used to serve base64 converter's needs.
 */
public class FileManager {


    /**
     * Returns file converted to byte array.
     *
     * @param uri
     * @return
     */
    public static byte[] getFileAsBytes(String uri) {
        try(FileInputStream fileInputStream = new FileInputStream(uri)){
            byte[] array = new byte[fileInputStream.available()];

            for (int i = 0; i < fileInputStream.available(); i++) {
                fileInputStream.read(array);
            }
            return array;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
