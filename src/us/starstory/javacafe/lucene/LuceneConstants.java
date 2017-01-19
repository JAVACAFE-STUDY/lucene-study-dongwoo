package us.starstory.javacafe.lucene;

/**
 * Created by nobaksan on 2017. 1. 19..
 */
public class LuceneConstants {
    private final String CONTENTS = "contents";
    private final String FILE_NAME="filename";
    private final String FILE_PATH="filename";
    private final Integer MAX_SEARCH = 10;

    public String getCONTENTS() {
        return CONTENTS;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    public Integer getMAX_SEARCH() {
        return MAX_SEARCH;
    }
}
