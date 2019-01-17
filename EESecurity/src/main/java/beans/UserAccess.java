/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.security.Principal;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author milut
 */
@Stateless
@DeclareRoles({"admin" , "user" })
public class UserAccess {
    
    @Resource SessionContext ctx;
    @PersistenceContext(unitName="mypu")
    EntityManager em;
    
    public String changeSomething(){
        if(ctx.isCallerInRole("admin")){
            return "admin";
        }
        else return "user";
    }
    
    
}
