package dao;

import static utils.DBUtils.fetchDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Candidate;

public class CandidateDaoImp implements ICandidateDao {

	private Connection cn;
	private PreparedStatement pst,pst1,pst2;
	
	public CandidateDaoImp() throws ClassNotFoundException, SQLException {
		cn=fetchDBConnection();
		String sql="select * from candidates";
		String sqlselectcandidate="select * from candidates where id=?";
		String sqlinc="update candidates set votes=votes+1 where id=?";
		pst=cn.prepareStatement(sql);
		pst1=cn.prepareStatement(sqlselectcandidate);
		pst2=cn.prepareStatement(sqlinc);
	}
	@Override
	public List<Candidate> listCandidates() throws SQLException {
		List<Candidate> candidate=new ArrayList<>();
		try(ResultSet rst=pst.executeQuery()){
		while(rst.next())
			candidate.add(new Candidate(rst.getInt(1),rst.getInt(4), rst.getString(2), rst.getString(3)));
		}
		return candidate;
	}
	@Override
	public String incrementVotes(int candidateId) throws SQLException {
		String msg=null;
		pst2.setInt(1, candidateId);
		int i=pst2.executeUpdate();
		if(i!=0)
			msg= "vote saved";
		else
			msg= "error";
		return msg;
	}
	public void cleanUp() throws SQLException {
		if (pst != null)
			pst.close();
		if (pst1 != null)
			pst1.close();
		if (pst2 != null)
			pst2.close();
		if (cn != null)
			cn.close();

	}
	
}
