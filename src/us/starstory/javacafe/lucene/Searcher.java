package us.starstory.javacafe.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.queryparser.classic.QueryParser;

import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by nobaksan on 2017. 1. 20..
 */
public class Searcher {
    private IndexSearcher indexSearcher;
    private Query query;
    private QueryParser queryParser;


    public Searcher(String indexDirectoryPath) throws IOException {
        File indexDir = new File(indexDirectoryPath);
        if (indexDir.exists()) {
            indexDir.delete();
        }
        indexDir.mkdirs();

        IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(indexDirectoryPath)));
        indexSearcher = new IndexSearcher(reader);

    }

    public TopDocs search(String field, String searchQuery) throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();
        int hitsPerPage = 10;
        queryParser = new QueryParser(field, analyzer);
        query = queryParser.parse(searchQuery);
        return indexSearcher.search(query, 5*hitsPerPage);
    }

    public Document getDocument(ScoreDoc scoreDoc)  throws IOException{
        return indexSearcher.doc(scoreDoc.doc);
    }
}
