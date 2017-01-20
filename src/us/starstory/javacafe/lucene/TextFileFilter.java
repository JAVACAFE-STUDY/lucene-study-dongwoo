package us.starstory.javacafe.lucene;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nobaksan on 2017. 1. 19..
 */
public class TextFileFilter implements FileFilter{

    public boolean acceptFile(File file){
        return file.getName().toLowerCase().endsWith(".txt");
    }

    public List<String> getFileData(File file, boolean isReadLine){
        BufferedReader bufferedReader= null;
        String cStr = null;
        String str =null;
        List<String> strReturnData = new ArrayList<String>();
        try{
            bufferedReader = new BufferedReader(new FileReader(file));
            while(isReadLine == true){
                str = bufferedReader.readLine();
                strReturnData.add(str);
                if(str == null){
                    continue;
                }
            }

            if(isReadLine == false){
                while(true){
                    str = bufferedReader.readLine();
                    if(str == null) break;
                    cStr = str;
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strReturnData;
    }
}
