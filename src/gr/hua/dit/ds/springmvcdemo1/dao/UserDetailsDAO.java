package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import gr.hua.dit.ds.springmvcdemo1.entity.UserDetails;

public interface UserDetailsDAO {
	
	public List<UserDetails> getnewUserDetails();
	
	public void InsertSalary(String username , int sal);
}
