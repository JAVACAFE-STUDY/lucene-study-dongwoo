package us.starstory.javacafe.lucene;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by nobaksan on 2017. 1. 20..
 */
public class IndexerTest {

    String indexDir = "/upload/test/lucene/index";
    String dataDir = "/upload/test/lucene/data";
    Indexer indexer;


    @Test
    public void createIndex() throws IOException {
        indexer = new Indexer(indexDir);
        int numIndexed;
        long startTime = System.currentTimeMillis();
        numIndexed = indexer.createIndex(dataDir,new TextFileFilter());
        long endTime = System.currentTimeMillis();
        indexer.close();
        System.out.println(numIndexed+" File indexed, time taken: " +(endTime-startTime)+" ms");
    }
}
