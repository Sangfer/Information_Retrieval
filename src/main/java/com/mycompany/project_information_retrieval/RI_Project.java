/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project_information_retrieval;

import readerFile.Reader;
import treatments.MatchWords;


/**
 * @author Alexis Barthelemy
 */

public class RI_Project {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws Exception {
        Reader reader= new Reader("C:\\Users\\Alex\\Downloads\\Text_Only_Ascii_Coll_MWI_NoSem\\Text_Only_Ascii_Coll_MWI_NoSem", "C:\\Users\\Alex\\Downloads\\request.txt");
        reader.getRequestFromFileRequest();
        reader.checkConstructorRequest();
        reader.getMapWordsFromCollection();
        MatchWords.MatchRequest(reader);
    }
    
}
