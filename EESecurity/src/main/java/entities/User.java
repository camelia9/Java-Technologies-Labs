/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 *
 * @author milut
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "findUserById", query = "SELECT u FROM User u WHERE u.userid = :userid")
})
@Table(name="susers")
public class User implements Serializable {
	private static final long serialVersionUID = -5892169641074303723L;
	
	@Id
	@Column(name="userid", nullable=false, length=255)
	private String userid;
	
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	
	public User(){}
	public User(String email, String password, String name) {
		this.userid = email;
		this.password = password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}