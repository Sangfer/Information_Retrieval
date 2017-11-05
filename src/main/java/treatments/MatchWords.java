/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treatments;

import java.util.List;
import readerFile.Request;
import readerFile.Reader;


/**
 * @author Alexis Barthelemy
 */
public class MatchWords {
    
    public List<Request> listRequest;

    public MatchWords() {}
    
    public MatchWords(List<Request> listRequest) {
        this.listRequest = listRequest;
    }

    public List<Request> getListRequest() {
        return listRequest;
    }

    public void setListRequest(List<Request> listRequest) {
        this.listRequest = listRequest;
    }
    
    
    public static void MatchRequest(Reader reader){
       for(Request request: reader.listRequest){
           for(String word: request.words){
               if(reader.mapWord.get(word)!=null){
                   System.out.println("Word : " + word +" has been found ");
                   System.out.println(reader.mapWord.get(word).toString());
                   System.out.println("END OF WORD");
               }
            System.out.println("END OF REQUEST");
   
           }
       }
    }
    
    
}
