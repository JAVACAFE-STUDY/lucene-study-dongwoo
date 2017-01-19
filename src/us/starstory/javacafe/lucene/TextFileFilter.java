package us.starstory.javacafe.lucene;

import java.io.File;

/**
 * Created by nobaksan on 2017. 1. 19..
 */
public class TextFileFilter implements FileFilter{

    public boolean acceptFile(File file){
        return file.getName().toLowerCase().endsWith(".txt");
    }

}
