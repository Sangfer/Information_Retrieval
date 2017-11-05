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
    public List<String> words;
    
    public Request(){}

    
    public Request(String code, List<String> words) {
        this.code = code;
        this.words = words;
    }
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

}
