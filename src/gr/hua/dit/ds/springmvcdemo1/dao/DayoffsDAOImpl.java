package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

import gr.hua.dit.ds.springmvcdemo1.entity.Dayoffs;

@Repository
public class DayoffsDAOImpl implements DayoffsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Dayoffs> getDayoffs() {

		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		Query<Dayoffs> query = currentSession.createQuery("select u from Dayoffs u", Dayoffs.class); // Create a query

		List<Dayoffs> req = query.getResultList(); // Execute the query and get the results list of dayoffs

		return req;
	}

	@Override
	public void InsertDayoff(Dayoffs Dayoffs) {
		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		try {

			currentSession.beginTransaction();
			currentSession.save(Dayoffs); // Save Dayoffs data in db

			currentSession.getTransaction().commit(); // Commit changes

		} catch (Exception e) {

			System.out.println("Error: " + e.getMessage());
			currentSession.getTransaction().rollback();

		} finally {

			currentSession.close();

		}

	}

	@Override
	public List<Dayoffs> getAllDayoffsByUsername(String username) {
		List<Dayoffs> dayoffs = new ArrayList<>();

		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		try {

			Query query = currentSession.createQuery("from Dayoffs where username=:username", Dayoffs.class); // Create a query

			query.setParameter("username", username); // Set parameter for the query above
			dayoffs = query.getResultList(); // Execute the query and get the results list of dayoffs

		} catch (Exception e) {

			System.out.println("exception: " + e.getMessage());

		} finally {

			currentSession.close(); // Close session
		}

		return dayoffs;

	}

	@Override
	public List<Dayoffs> getDayoffsByState(String State) {
		List<Dayoffs> dayoffs = new ArrayList<>();
		
		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session
		
		try {
		
			Query query = currentSession.createQuery("from Dayoffs where state=:state", Dayoffs.class); // Create a query
			
			query.setParameter("state", State); // Set parameter for the query above
			dayoffs = query.getResultList(); // Execute the query and get the results list of dayoffs
		
		} catch (Exception e) {
			System.out.println("exception: " + e.getMessage());
		} finally {
			currentSession.close(); // Close session
		}
		return dayoffs;
	}

	@Override
	public void UpdateDayoff(int id, String state) {
		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session
		
		try {
		
			currentSession.beginTransaction();
			
			Dayoffs dayoffs = currentSession.get(Dayoffs.class, id); // Gets Dayoffs with id as identifier
			
			dayoffs.setState(state);
			currentSession.update(dayoffs); // Update dayoffs data in db

			currentSession.getTransaction().commit();
			
			System.out.println("ok");
		
		} catch (Exception e) {
		
			System.out.println("Error: " + e.getMessage());
			
			currentSession.getTransaction().rollback();
		
		} finally {
		
			currentSession.close(); // Close session
		
		}

	}

	@Override
	public void DeleteByDayoff() {
		int counter = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Initialize date format
		Date todayDate = new Date();
		formatter.format(todayDate);

		Session currentSession = this.sessionFactory.openSession(); // Get current hibernate session

		try {
			currentSession.beginTransaction();
			Query<Dayoffs> query = currentSession.createQuery("Select d from Dayoffs d", Dayoffs.class); // Create a
																											// query
			List<Dayoffs> dayoffs = query.getResultList(); //// Execute the query and get the results list

			for (Dayoffs d : dayoffs) {
				Date dayoffdate = d.getEnddate();
				formatter.format(dayoffdate);

				if (todayDate.after(dayoffdate)) { // If dayoffs end date is today or previous days
					currentSession.delete(d); // Delete data in db
					counter++; // Get the number of dayoffs deleted from db
				}
			}
			currentSession.getTransaction().commit(); // Commit changes
		} catch (Exception e) {
			System.out.println("error deleting dayoffs: " + e.getMessage());
			currentSession.getTransaction().rollback();
		} finally {
			currentSession.close(); // Close session
		}

		System.out.println(counter + " dayoffs deleted");
	}

}
