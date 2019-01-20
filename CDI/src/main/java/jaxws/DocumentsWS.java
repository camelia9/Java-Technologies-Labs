/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import entities.Documents;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import repositories.DocumentsRepo;

/**
 *
 * @author milut
 */
@WebService(serviceName = "DocumentService")
public class DocumentsWS {

    @EJB
    private DocumentsRepo ejbRef;

    @WebMethod(operationName = "insertDocument")
    @Oneway
    public void insertDocument(@WebParam(name = "document") Documents document) {
        ejbRef.insertDocument(document);
    }

    @WebMethod(operationName = "getAllDocuments")
    public List<Documents> getAllDocuments() {
        return ejbRef.getAllDocuments();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "viewDocuments")
    public List<Documents> viewDocuments(@WebParam(name = "username") String username) {
        List<Documents> result = ejbRef.findDocumentsByUsername(username);
        if(result == null || result.isEmpty()){
            return ejbRef.getAllDocuments();
        }
        else{
            return result;
        }
    }
    
}
