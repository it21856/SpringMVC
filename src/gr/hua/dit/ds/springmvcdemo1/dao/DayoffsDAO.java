package gr.hua.dit.ds.springmvcdemo1.dao;

import java.util.List;

import gr.hua.dit.ds.springmvcdemo1.entity.Dayoffs;
import gr.hua.dit.ds.springmvcdemo1.entity.User;

public interface DayoffsDAO {
	
	public List<Dayoffs> getDayoffs();
	
	public List<Dayoffs> getAllDayoffsByUsername(String username);
	
	public void InsertDayoff(Dayoffs Dayoffs);
	
	public List<Dayoffs> getDayoffsByState(String State);
	
	public void UpdateDayoff(int id,String state);
	
	public void DeleteByDayoff();
	
}