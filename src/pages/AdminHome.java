package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDaoImp;
import dao.VoterDaoImp;
import pojos.Candidate;
import pojos.Voter;


@WebServlet("/admin")
public class AdminHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CandidateDaoImp candidateDao;
	VoterDaoImp dao;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			candidateDao=new CandidateDaoImp();
			dao=new VoterDaoImp();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		try {
			candidateDao.cleanUp();
			dao.cleanUp();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
		List<Voter> voter=new ArrayList<>();
		String header="<head>\r\n" + 
				"<title> Admin Dashboard</title>\r\n" + 
				"<meta charset=\"utf-8\">\r\n" + 
				"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css\">\r\n" + 
				"        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.6.3/css/all.css\" integrity=\"sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/\" crossorigin=\"anonymous\">\r\n" + 
				"       \r\n" + 
				"        <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js\"></script>\r\n" + 
				"        <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.js\"></script>\r\n" + 
				"        <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.js\"></script>\r\n" + 
				"    \r\n" + 
				"</head>";
		pw.print(header);
		pw.print("<form align='center' action='voteraction' method='post'>");
	pw.print("<h3 align='center'> Voter List </h3><br>");	
		//pw.print("<input type='hidden' name='VoterName' value="+v.getName()+" hidden />");
	//	pw.print("<input type='hidden' name='voterid' value="+v+" />");
		pw.print("<table class='table table-striped table-bordered table-condensed table-primary' border='1' align='center'>"
				+ "<tr>"
				+ "<th>SR NO</th>"
				+ "<th>Voter Name</th>"
				+ "<th>Voter Email</th>"
				+ "<th>Action</th>"
				+ "<tr>");
		voter=dao.listVoter();
		for(Voter v:voter) {
			pw.print("<tr>"
					+ "<td>"+v.getId()+"</td>"
					+ "<td>"+v.getName()+"</td>"
					+ "<td>"+v.getEmail()+"</td>"
					+ "<td><a href='editvoter'><i class='fa fa-edit'/></a>  <a href='deletecandidate'><i class='fa fa-trash teal-color '/></a></td>");
					
		}
		pw.print("</tr></table>");
		
		
		List<Candidate> candidate=new ArrayList<>();
		
		pw.print("<form align='center' action='candidateaction' method='post'>");
		
		pw.print("<h3 align='center'> Candidate List </h3><br>");	
		//pw.print("<input type='hidden' name='VoterName' value="+v.getName()+" hidden />");
	//	pw.print("<input type='hidden' name='voterid' value="+v+" />");
		pw.print("<table class='table table-striped table-bordered table-condensed table-primary' border='1' align='center'>"
				+ "<tr>"
				+ "<th>SR NO</th>"
				+ "<th>CANDIADTAE NAME</th>"
				+ "<th>PARTY</th>"
				+ "<th>VOTES</th>"
				+ "<th>Action</th>"
				+ "<tr>");
		candidate=candidateDao.listCandidates();
		for(Candidate c:candidate) {
			pw.print("<tr>"
					+ "<td>"+c.getId()+"</td>"
					+ "<td>"+c.getName()+"</td>"
					+ "<td>"+c.getParty()+"</td>"
					+ "<td>"+c.getVotes()+"</td>"
					+ "<td><a href='editcandidate'><i class='fa fa-edit teal-color'/></a>  <a href='deletecandidate'><i class='fa fa-trash teal-color '/></a></td>");
					
		}
		pw.print("</tr></table>");
		pw.print("<center><a href='newvoteradd'>Add new Voter</a></center>");
	}catch (Exception e) {
	e.printStackTrace();
	}
	}

}
