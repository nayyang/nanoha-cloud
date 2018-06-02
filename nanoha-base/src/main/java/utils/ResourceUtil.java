package utils;


import java.io.File;
import java.net.URL;

public class ResourceUtil {
    public static File getResourcePath(String path) throws Exception {

        URL url = ResourceUtil.class.getClassLoader().getResource(path);
        if (url != null) {
            return new File(url.toURI());
        }
        return null;
    }
}
