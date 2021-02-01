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

@WebServlet("/login3")
public class VoterLogin extends HttpServlet {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		try (PrintWriter pw = response.getWriter()) {
			HttpSession hs=request.getSession();
			String email=request.getParameter("em");
			String pwd=request.getParameter("pass");
			pw.print("in do post "+email+" "+pwd);
			Voter v=dao.validateUser(email, pwd);
			if(v.getRole().equals("admin")) {
			//	response.sendRedirect("voting?voter="+v.getId());
				hs.setAttribute("admin_login_deails", v);
				response.sendRedirect("admin");
			}
			else if(v.getRole().equals("voter")) {
				hs.setAttribute("voter_login_deails", v);
				response.sendRedirect("voting");
			}else
				pw.print("<h4> Invalid Login !!!!!! Please <a href='login.html'>Retry</a></h4>");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
