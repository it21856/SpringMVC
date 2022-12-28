package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import gr.hua.dit.ds.springmvcdemo1.entity.User;

public interface AdminDAO {
    public List<User> getUsers();
    
    public User getUserByUsername(String username);
    
    public void InsertUser(User user,String[] roles);
    
    public void DeleteUser(String username);
    
    public void UpdateUser(String username,String password,String roles[],int enabled);
    
    //Unused
	public void Test();

	

}
