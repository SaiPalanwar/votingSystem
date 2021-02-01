package dao;

import static utils.DBUtils.fetchDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojos.Voter;
public class VoterDaoImp implements IVoterDao {

	private Connection cn;
	private PreparedStatement pst,pst1,pst2,pst3;
	
	public VoterDaoImp() throws ClassNotFoundException, SQLException {
		cn=fetchDBConnection();
		String sql="select * from voters where email=? and password=?";
		String sqlUpdate="update voters set status=1 where id=? and status=0";
		String sqlvoters="select * from voters where role != 'admin'";
		String sqlRegister="insert into voters (name,email,password,status,role) values (?,?,?,0,?)";
		pst=cn.prepareStatement(sql);
		pst1=cn.prepareStatement(sqlUpdate);
		pst2=cn.prepareStatement(sqlvoters);
		pst3=cn.prepareStatement(sqlRegister);
	}

	@Override
	public Voter validateUser(String email, String pwd) throws SQLException {
		Voter voter=null;
		pst.setString(1, email);
		pst.setString(2,pwd);
		try(ResultSet rst=pst.executeQuery()){
			if(rst.next())
			voter= new Voter(rst.getInt(1), rst.getInt(5), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(6));		
		}
		return voter;
	}

	@Override
	public String updateVotingStatus(int voterId) throws SQLException {
		String msg=null;
		pst1.setInt(1, voterId);
		int i=pst1.executeUpdate();
		if(i != 0)
			msg= "Voting Succcessfully Submited";
		else
			msg=  "Already voted";
	return msg;
	}
	
	@Override
	public List<Voter> listVoter() throws SQLException {
		List<Voter> voter=new ArrayList<>();
		try(ResultSet rst=pst2.executeQuery()){
		while(rst.next())
			voter.add(new Voter(rst.getInt(1), rst.getInt(5), rst.getString(2), rst.getString(3), rst.getString(4),rst.getString(6)));
		}
		return voter;
	}

	@Override
	public boolean registerVoter(String name, String email, String password, String role) throws SQLException {
		pst3.setString(1, name);
		pst3.setString(2, email);
		pst3.setString(3, password);
		pst3.setString(4, role);
		int i=pst3.executeUpdate();
		if(i!=0)
			return true;
		return false;
	}
	public void cleanUp() throws SQLException {
		if (pst != null)
			pst.close();
		if (pst1 != null)
			pst1.close();
		if (cn != null)
			cn.close();

	}


	

	

}
