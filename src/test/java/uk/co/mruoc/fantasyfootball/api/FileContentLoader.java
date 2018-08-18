package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.properties.ClasspathFileContentLoader;

public class FileContentLoader {

    public static String load(String path) {
        final uk.co.mruoc.properties.FileContentLoader contentLoader = new ClasspathFileContentLoader();
        return contentLoader.loadContent(path);
    }

}
