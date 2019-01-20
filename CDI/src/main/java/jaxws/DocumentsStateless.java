/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxws;

import entities.Documents;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import repositories.DocumentsRepo;

/**
 *
 * @author milut
 */
@WebService(serviceName = "DocumentsStateless")
@Stateless()
public class DocumentsStateless {

    @EJB
    private DocumentsRepo ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "insertDocument")
    @Oneway
    public void insertDocument(@WebParam(name = "document") Documents document) {
        ejbRef.insertDocument(document);
    }

    @WebMethod(operationName = "getAllDocuments")
    public List<Documents> getAllDocuments() {
        return ejbRef.getAllDocuments();
    }

    @WebMethod(operationName = "findDocumentsByUsername")
    public List<Documents> findDocumentsByUsername(@WebParam(name = "username") String username) {
        return ejbRef.findDocumentsByUsername(username);
    }
    
}
