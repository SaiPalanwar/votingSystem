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
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImp;
import dao.VoterDaoImp;
import pojos.Candidate;
import pojos.Voter;



@WebServlet("/voting")
public class VotingHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoterDaoImp dao;
	private CandidateDaoImp candidateDao;
	
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		List<Candidate> candidate=new ArrayList<>();
		
		try (PrintWriter pw = response.getWriter()) {
		HttpSession hs=request.getSession();
		Voter v=(Voter)hs.getAttribute("voter_login_deails");
		
		
		if(v==null){
			pw.print("Login Plz");
			response.sendRedirect("login.html");
		}
		else if(v.getRole().equals("voter")) {
		//int v=Integer.parseInt(request.getParameter("voter"));
		String header="<head>\r\n" + 
				"<title> Voting Home</title>\r\n" + 
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
			pw.print("<form align='center' action='votesave' method='post'>");
			pw.print("<h1 align='center'> WELCOME TO ONLINE VOTING SYSTEM </h1><br>");
			pw.print("<h3 align='center'> YOUR VOTE IS VALUABLE SO PLESE TAKE A RIGHT DECISION </h3><br>");	
		
			pw.print("<input  type='hidden' name='voterid' value="+v+" />");
			pw.print("<table class='table table-striped table-bordered table-condensed table-primary' border='1' align='center'>"
					+ "<tr>"
					+ "<th>SR NO</th>"
					+ "<th>CANDIADTAE NAME</th>"
					+ "<th>PARTY</th>"
					+ "<th>Action</th>"
					+ "<tr>");
			candidate=candidateDao.listCandidates();
			
			for(Candidate c:candidate) {
				pw.print("<tr>"
						+ "<td>"+c.getId()+"</td>"
						+ "<td>"+c.getName()+"</td>"
						+ "<td>"+c.getParty()+"</td>"
						+ "<td><input type='radio' name='candidateid' value="+c.getId()+" > &nbsp&nbsp <i class='fa fa-hand-lizard-o'></i></td></tr>");
				hs.setAttribute("Candidate_Details", c);	
			}
			pw.print("</table>");
			pw.print("<br><input style='align:center;' type='submit' name='submit' value='submit'/>");
			pw.print("<p><a href='logout'>Logout </a></p>");
			
			pw.print("</form>");
			
		}
		 
		}catch (Exception e) {
			e.printStackTrace();
		}
		}

}
