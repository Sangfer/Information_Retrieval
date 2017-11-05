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
import java.util.Arrays;
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
    public List<Request> listRequest;
    

    public Reader(String fileToRead, String fileRequest){
        this.fileToRead=fileToRead;
        this.fileRequest=fileRequest;
        this.mapWord= new HashMap<>();
        this.listRequest= new ArrayList<>();
    }
    
    
  
    
    
    /****************************PART CONCERNING THE REQUEST FILE ****************************
    
    /**
     * From the file given, get the list of request
     * @return a List of request identified by a code and a List of words
     */
    public void getRequestFromFileRequest(){
        
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
           String code = sentence.split(":")[0];
           List<String> words = Arrays.asList(sentence.split(":")[1].split(" "));
           
           Request request= new Request(code,words);
           listRequest.add(request);
        }
        fin.close();
        this.listRequest=listRequest;
    }
    
    
    
     /**
     * From the file given, get the list of request
     * @param mylist the list of Request
     * @return a List of request identified by a code and a List of words
     */   
     public void checkConstructorRequest(){
            for(Request req: listRequest){
                System.out.print("Requête CODE: ");
                System.out.println(req.getCode());
                List<String> words= req.getWords();
                for(String word: words){
                    System.out.println(word);
                }
                System.out.println("................. \n\n");
            }
        }
    
     
     
     
    /****************************PART CONCERNING THE COLLECTION TO READ ****************************
     
    
   /**
    * Read word by word the file to check
    * @throws Exception concerning opening of the file
    */ 
    public void getMapWordsFromCollection() throws Exception
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
            doc=doc.replaceAll("\\r\\n\\t|\\r|\\n|\\t", " ");
            //Simplest delimiter: multiple space. Need to be improved for removing words from punctuation. Eg: here we get word like "animal." which will not match "animal"
            words = doc.split("[ ]+");
            
            for(String word: words){
                if(word.matches("(<docno>)[0-9]*(<\\/docno>)")){
                    docId = StringUtils.substringBetween(word, "<docno>", "</docno>");
                }
                else if(word.compareTo("</doc>") != 0){
                    word= word.toLowerCase();
                   //Word does not exist in the collection
                   if(this.mapWord.get(word)==null){
                       HashMap<String,Integer> mapDocReference = new HashMap<>();
                       mapDocReference.put(docId,1);
                       this.mapWord.put(word, mapDocReference);
                   }

                   else if(this.mapWord.get(word).get(docId)==null){
                       this.mapWord.get(word).put(docId,1);
                   } 
                   else {
                       Map<String, Integer> mapDocReference=this.mapWord.get(word);
                       Integer previousValue = mapDocReference.get(docId);
                       mapDocReference.put(docId, previousValue == null ? 1 : previousValue + 1);
                    }
                }
            }
        }
    }
    
    
    
    
    
     /**
     * PrintOut all the element from the map
     */   
     public void checkMapWord(){
        this.mapWord.forEach((k, v) -> System.out.println((k + ":" + v)));
    }
     
     
     /**
      * Write all result from previous function into a file called result.txt
      * @throws FileNotFoundException 
      */
     public void writeResultMapIntoAText() throws FileNotFoundException{
        File file = new File("result.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        checkMapWord();
     }
     
     
     
     
     
    
}
