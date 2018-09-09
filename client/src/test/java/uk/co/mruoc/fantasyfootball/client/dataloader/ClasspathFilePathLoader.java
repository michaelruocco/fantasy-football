package uk.co.mruoc.fantasyfootball.client.dataloader;

import java.io.File;
import java.net.URISyntaxException;

public class ClasspathFilePathLoader {

    public static String loadAbsolutePath(String path) {
        try {
            File file = new File(ClasspathFilePathLoader.class.getResource(path).toURI());
            return file.getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new PathLoadException(e);
        }
    }

}
