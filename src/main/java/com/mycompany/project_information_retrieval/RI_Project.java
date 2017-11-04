/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.project_information_retrieval;

import java.util.List;
import readerFile.Reader;
import readerFile.Request;


/**
 * @author Alexis Barthelemy
 */

public class RI_Project {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws Exception {
        List<Request> listRequest;
        Reader reader= new Reader("C:\\Users\\Alex\\Downloads\\Text_Only_Ascii_Coll_MWI_NoSem\\Text_Only_Ascii_Coll_MWI_NoSem.txt", "C:\\Users\\Alex\\Downloads\\request.txt");

        listRequest=reader.getRequestFromFileRequest();
        Reader.checkConstructorRequest(listRequest);
        reader.readFileToCheck(listRequest);

    }
    
}
