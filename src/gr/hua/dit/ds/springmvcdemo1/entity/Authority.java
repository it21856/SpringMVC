package gr.hua.dit.ds.springmvcdemo1.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority{
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;


	@Column(name = "authority")
	private String role;
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "username", nullable = false)
	private User user;

	//Constructors
	public Authority() {
		super();
	}


	public Authority(String role) {
		super();
		this.role = role;
	}
	
	public Authority(String role,User user) {
		super();
		this.role=role;
		this.user=user;
	}
	
	
	//Setters+Getters
	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setAuthority(String role) {
		this.role = role;
	}

	public String getAuthority() {
		return role;
	}

	//Overriden methods
	@Override
	public String toString() {
		return "Authority [role=" + role + "]";
	}

}
