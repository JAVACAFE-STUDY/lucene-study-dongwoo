package us.starstory.javacafe.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.junit.Test;

import java.io.IOException;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 * Created by nobaksan on 2017. 1. 20..
 */
public class SearcherTest {
    Searcher searcher;
    String indexDir = "/upload/test/lucene/index";

    @Test
    public void search() throws IOException,ParseException {

        String searchQuery = new String("네이버");

        searcher = new Searcher(indexDir);
        long startTime = System.currentTimeMillis();
        TopDocs hits = searcher.search("content", searchQuery);
        long endTime = System.currentTimeMillis();

        System.out.println(hits.totalHits +  " documents found. Time :" + (endTime - startTime));
        for(ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.getDocument(scoreDoc);
            System.out.println("title: " + doc.get("title"));
            System.out.println("content: " + doc.get("content"));
            System.out.println("modified: " + doc.get("modified"));
        }
    }

}
