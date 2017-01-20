package us.starstory.javacafe.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by nobaksan on 2017. 1. 19..
 */
public class Indexer {
    private IndexWriter writer;

    public Indexer(String indexDirectoryPath) throws IOException{
        File indexDir = new File(indexDirectoryPath);
        if (indexDir.exists()) {
            indexDir.delete();
        }
        indexDir.mkdirs();

        Directory indexDirectory = FSDirectory.open(Paths.get(indexDirectoryPath));

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(indexDirectory,indexWriterConfig);
    }

    public void close()throws CorruptIndexException, IOException{
        writer.close();
    }

    private Document getDocument(File file) throws IOException{
        FileFilter fileFilter = new TextFileFilter();
        Document document = new Document();
        System.out.println(file.getName());
        document.add(new StringField("title",file.getName(), Field.Store.YES));
        document.add(new StringField("content", String.valueOf(fileFilter.getFileData(file,true)), Field.Store.YES));
        Long currentTime = System.currentTimeMillis();
        document.add(new StringField("modified",String.valueOf(currentTime), Field.Store.YES));
        return document;

    }

    private void indexFile(File file) throws IOException{
        System.out.println("Indexing: " + file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);
    }

    public int createIndex(String dataDirPath, FileFilter fileFilter) throws IOException{
        File[] files = new File(dataDirPath).listFiles();
        for(File file : files){
            if(!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead() && fileFilter.acceptFile(file)){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }
}
