package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springmvcdemo1.entity.UserDetails;

@Repository
public class UserDetailsDAOImpl implements UserDetailsDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<UserDetails> getnewUserDetails() {
		
		Session currentSession = sessionFactory.openSession(); //Get current hibernate session
				
		Query<UserDetails> query1 = currentSession.createQuery("select u from UserDetails u where salary=0", UserDetails.class); //Create a query

		List<UserDetails> req1 = query1.getResultList(); //Execute the query and get the results list

		return req1;
	}
	
	@Override
	@Transactional
	public void InsertSalary(String username ,int sal) {
		
		Session currentSession = sessionFactory.openSession();//Get current hibernate session
		
		Query<UserDetails> query = currentSession.createQuery("select u from  UserDetails u where username='" + username +"'", UserDetails.class); //Create a query
		
		List<UserDetails> list = query.getResultList(); //Execute the query and get the results list
		
		list.get(0).setSalary(sal);
		
		currentSession.beginTransaction();
		
		currentSession.saveOrUpdate(list.get(0)); //Update salary value in the db
		
		currentSession.getTransaction().commit(); //Commit changes
		
		currentSession.close();
	}
	
}
