package us.starstory.javacafe.lucene;

import java.io.File;
import java.util.List;

/**
 * Created by nobaksan on 2017. 1. 19..
 */
public interface FileFilter {
    boolean acceptFile(File file);
    StringBuilder getFileData(File file, boolean isReadLine);
}
