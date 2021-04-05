package Utils;

import java.io.InputStream;
import java.net.URL;

public final class ObjectUtils {

    private static boolean isNullOrEmpty(String text) {
        return (text == null || text.isEmpty());
    }

    private static boolean isNullOrEmpty(URL url) {
        return (url == null || url.equals(""));
    }

    private static boolean isNullOrEmpty(InputStream inputStream) {
        return (inputStream == null || inputStream.equals(""));
    }
    public static boolean notValidObject(String text) {
        return isNullOrEmpty(text);
    }

    public static boolean notValidObject(URL url) {
        return isNullOrEmpty(url);
    }

    public static boolean notValidObject(InputStream inputStream) {
        return isNullOrEmpty(inputStream);
    }
}
