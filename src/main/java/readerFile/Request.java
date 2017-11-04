/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readerFile;

import java.util.List;
import treatments.Word;


/**
 * @author Alexis Barthelemy
 */

public class Request {
    public String code;
    public List<Word> words;
    
    public Request(){}

    
    public Request(String code, List<Word> words) {
        this.code = code;
        this.words = words;
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

}
