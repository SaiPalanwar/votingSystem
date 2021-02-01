package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImp;
import dao.VoterDaoImp;

import pojos.Voter;


@WebServlet("/votesave")
public class vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoterDaoImp dao;
	private CandidateDaoImp candidateDao;
    public vote() {
        super();
        // TODO Auto-generated constructor stub
    }

	
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
			dao.cleanUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			HttpSession hs=request.getSession();
			
			Voter voter=(Voter) hs.getAttribute("voter_login_deails");
			//Candidate candidate=(Candidate) hs.getAttribute("Candidate_Details");
			String candidateid=request.getParameter("candidateid");
			String status=dao.updateVotingStatus(voter.getId());
			if(status.equals("Already voted")) {
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('already voted');");
				pw.println("location='voting';");
				pw.println("</script>");

			}
			else {
			candidateDao.incrementVotes(Integer.parseInt(candidateid));
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Vote Saved !!');");
			pw.println("location='voting';");
			pw.println("</script>");
		
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
