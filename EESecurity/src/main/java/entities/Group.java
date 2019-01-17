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
import javax.persistence.Table;
/**
 *
 * @author milut
 */
@Entity
@Table(name="susers_groups")
public class Group implements Serializable {
	private static final long serialVersionUID = 1528447384986169065L;
	
	public static final String USERS_GROUP = "users";
	
	@Id
	@Column(name="userid", nullable=false, length=255)
	private String userid;
	
	@Column(name="groupid", nullable=false, length=32)
	private String groupname;
	
	public Group() {}
	public Group(String email, String groupname) {
		this.userid = email;
		this.groupname = groupname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
}