package dao;

import java.sql.SQLException;
import java.util.List;

import pojos.Voter;

public interface IVoterDao {
	Voter validateUser(String email,String pwd) throws SQLException;
	String updateVotingStatus(int voterId) throws SQLException;
	List<Voter> listVoter() throws SQLException;
	boolean registerVoter(String name,String email,String password,String role) throws SQLException;
	
}
