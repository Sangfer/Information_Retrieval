/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package treatments;

import java.util.List;
import readerFile.Request;


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
    
    
    public static void tokenMatchRequest(Request request, String token){
        List<Word> words=request.getWords();
        for(Word word: words){
            if(word.getToken().compareTo(token)==0){
                word.incrementCounter();
            }
        }
    }
    
    
}
