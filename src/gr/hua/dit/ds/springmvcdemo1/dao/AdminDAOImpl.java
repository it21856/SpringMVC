package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.ds.springmvcdemo1.config.AppSecurityConfig;
import gr.hua.dit.ds.springmvcdemo1.entity.Authority;

import gr.hua.dit.ds.springmvcdemo1.entity.User;
import gr.hua.dit.ds.springmvcdemo1.entity.UserDetails;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private AppSecurityConfig AppSecurityConfig;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<User> getUsers() {

		Session currentSession = sessionFactory.openSession(); // Get current hibernate session

		Query<User> query = currentSession.createQuery("select u from User u", User.class); // Create a query

		List<User> users = query.getResultList(); // Execute the query and get the results list

		return users;
	}

	@Override
	@Transactional
	public void InsertUser(User user, String[] roles) {
		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		user.setPassword(AppSecurityConfig.passwordEncoder().encode(user.getPassword()));

		try {
			currentSession.beginTransaction();

			UserDetails details = new UserDetails(user.getUsername(), 0); // Insert new user with salary 0

			user.setUserDetails(details);

			currentSession.save(user); // Save data in db

			for (String role : roles) {
				Authority auth = new Authority(role, user); // Adds each one of user's authorities

				user.setAuthority(auth); // Set each one of user's authorities

				currentSession.save(auth); // Save data in db
			}

			currentSession.save(details); // Save data in db
			currentSession.getTransaction().commit(); // Commit changes

		} catch (HibernateException ex) {
			if (currentSession.getTransaction() != null) {

				currentSession.getTransaction().rollback();
			}

			System.out.println("Exception: " + ex.getMessage());

		} finally {

			currentSession.close(); // Close session

		}

	}

	@Override
	public User getUserByUsername(String username) {
		List<User> users = this.getUsers();

		for (User u : users) {

			if (u.getUsername().equals(username)) {

				System.out.println("returns user");
				return u;
			}
		}

		System.out.println("not found");

		return null;
	}

	@Override
	@Transactional
	public void DeleteUser(String username) {

		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		User user = currentSession.get(User.class, username); // Gets user with username as identifier

		if (user == null) {

			return;

		} else {

			try {

				currentSession.beginTransaction();
				currentSession.delete(user); // Delete user from db

				currentSession.flush();
				currentSession.getTransaction().commit(); // Commit changes

				System.out.println("user deleted");

			} catch (Exception e) {

				System.out.println(e);

				currentSession.getTransaction().rollback();

			} finally {

				currentSession.close(); // Close session
			}
		}
	}

	@Override
	@Transactional
	public void UpdateUser(String username, String password, String[] roles, int enabled) {
		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session
		User user = currentSession.get(User.class, username); // Gets user with username as identifier
		if (user == null) {
			return;
		} else {
			if (!user.getPassword().equals(password)) {
				user.setPassword(AppSecurityConfig.passwordEncoder().encode(password));
			}
			user.setEnabled(enabled);
			try {
				currentSession.beginTransaction();
				Query query = currentSession.createQuery("delete from Authority where username=:username"); // Create a
																											// query
				query.setParameter("username", username); // Set parameter for the query above
				query.executeUpdate();
				currentSession.getTransaction().commit(); // Commit changes
				currentSession.beginTransaction();
				user.getAuthorities().clear(); // Clear authorities
				for (String role : roles) {
					Authority authority = new Authority(role, user); // Adds each one of user's authorities
					user.setAuthority(authority); // Set each one of user's authorities
					currentSession.saveOrUpdate(authority);
				}
				currentSession.update(user);
				currentSession.getTransaction().commit(); // Commit changes
				System.out.println("Updated");
			} catch (Exception e) {
				System.out.println("ex: " + e.getMessage());
				currentSession.getTransaction().rollback();
			} finally {
				currentSession.close(); // Close session
			}
		}

	}

	// Unused

	@Override
	public void Test() {

	}

}
