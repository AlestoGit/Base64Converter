package alesto.converter.util;

import java.util.Arrays;
import java.util.Base64;

/**
 * This is very simple but useful converter created to simplify
 * and automate encoding files to base64-code.
 *
 * @see java.util.Base64
 *
 * @author Alesto
 * @version 0.1
 */

public class Base64Converter {

    /**
     * Encodes images to Base64 format exclusively.
     * @param uriToImage
     * @return
     */
    public static String encodeImage(String uriToImage) {
        int dotIndex = uriToImage.lastIndexOf('.');
        String extension = uriToImage.substring(dotIndex + 1);

        String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};

        //checking if our file is image
        if(Arrays.asList(allowedExtensions).contains(extension)) {
            return encode(uriToImage);
        } else {
            return null;
        }
    }

    /**
     * Takes data from given uri and encodes it to Base64 format.
     *
     * @param uri
     * @return
     */
    private static String encode(String uri) {
        byte[] array = FileManager.getFileAsBytes(uri);

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(array);
    }

}

