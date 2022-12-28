package gr.hua.dit.ds.springmvcdemo1.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private int enabled;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH }, orphanRemoval = true)
	private Set<Authority> authorities = new HashSet<>();

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH }, orphanRemoval = true)
	private UserDetails userDetails;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH }, orphanRemoval = true)
	private Set<Dayoffs> dayoffs;

	// Constructors
	public User() {
	}

	public User(String username, String password, int enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	// Setters+Getters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return this.enabled == 1;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public void setAuthority(Authority authority) {
		authorities.add(authority);
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public Set<Dayoffs> getDayoffs() {
		return dayoffs;
	}

	public void setDayoffs(Set<Dayoffs> dayoffs) {
		this.dayoffs = dayoffs;
	}

	//boolean method to get a specified authority
	public boolean containsAuthority(String authority) {
		return this.authorities.stream().map(Authority::getAuthority).filter(authority::equals).findFirst().isPresent();
	}

	//Overriden methods
	@Override
	public String toString() {
		return "[User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", authorities= "
				+ authorities + " ]";
	}

}
