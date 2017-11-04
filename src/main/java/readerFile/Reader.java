/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Alexis Barthelemy
 */

package readerFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import treatments.Word;



public class Reader {
    public String fileToRead;
    public String fileRequest;
    public Map<String, Map<String, Integer>> mapWord;
    

    public Reader(String fileToRead, String fileRequest){
        this.fileToRead=fileToRead;
        this.fileRequest=fileRequest;
        this.mapWord= new HashMap<>();
    }
    
    
    
    
    /**
     * From the file given, get the list of request
     * @return a List of request identified by a code and a List of words
     */
    public List<Request> getRequestFromFileRequest(){
        
        List<Request> listRequest= new ArrayList<>();

        Scanner fin = null;
        try
        {
            fin = new Scanner(new FileReader(this.fileRequest));
            fin.useDelimiter("[\r]");
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("Cannot open "+this.fileRequest);
            System.exit(1);
        }

        while(fin.hasNext())
        {
            
           String sentence=fin.next();
           String[] splittedRequest=sentence.split(":");
           String[] arrayWords=splittedRequest[1].split(" ");
           
           List<Word> words= new ArrayList<>();
           for(String word: arrayWords){
              words.add(new Word(word,0));
           }
           
           Request request= new Request(splittedRequest[0],words);
           listRequest.add(request);
        }
        fin.close();
        return listRequest;
    }
    
     /**
     * From the file given, get the list of request
     * @param mylist the list of Request
     * @return a List of request identified by a code and a List of words
     */   
     public static void checkConstructorRequest(List<Request> mylist){
            for(Request req: mylist){
                System.out.print("Requête CODE: ");
                System.out.println(req.getCode());
                List<Word> words= req.getWords();
                for(Word word: words){
                    System.out.println(word);
                }
                System.out.println("................. \n\n");
            }
        }
    
    
   /**
    * Read word by word the file to check
     * @param requests
    * @throws Exception 
    */ 
    
    
    public void readFileToCheck(List<Request> requests) throws Exception
    {  
             
        
        Scanner textSplittedByDoc = null;
        try
        {
            textSplittedByDoc = new Scanner(new FileReader(this.fileToRead));
            //We split with the Tag <doc> in order to get Documents one by one
            textSplittedByDoc.useDelimiter("<doc>");
        }
        catch (FileNotFoundException fnfe)
        {
            System.err.println("Cannot open "+this.fileToRead);
            System.exit(1);
        }
        
        
        while(textSplittedByDoc.hasNext())
        {
            String docId="TEST";
            String doc=textSplittedByDoc.next();
            String []words;
            // Take off all escape Characters and replace them by space
            doc=doc.replaceAll("\\r\\n|\\r|\\n", " ");
            //Simplest delimiter: multiple space. Need to be improved for removing words from punctuation. Eg: here we get word like "animal." which will not match "animal"
            words = doc.split("[ ]+");
            
            for(String word: words){
                if(word.matches("(<docno>)[0-9]*(<\\/docno>)")){
                     docId = StringUtils.substringBetween(word, "<docno>", "</docno>");
                }
                 System.out.println(word);
                //Word does not exist in the collection
                if(mapWord.get(word)==null){
                    HashMap<String,Integer> mapDocReference = new HashMap<String,Integer>();
                    mapDocReference.put(docId,1);
                    mapWord.put(word, mapDocReference);
                }
                
                if(mapWord.get(word).get(docId)==null){
                    mapWord.get(word).put(docId,1);
                } 
                else {
                    Map<String, Integer> mapDocReference=mapWord.get(word);
                    Integer previousValue = mapDocReference.get(docId);
                    mapDocReference.put(docId, previousValue == null ? 1 : previousValue + 1);
                }
            }
        }
            
        File file = new File("result.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        mapWord.forEach((k, v) -> System.out.println((k + ":" + v)));        
    
    }
    
}
