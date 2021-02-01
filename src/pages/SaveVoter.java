package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VoterDaoImp;


@WebServlet("/savevoter")
public class SaveVoter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private VoterDaoImp dao;
	public void init(ServletConfig config) throws ServletException {
		try {
			dao=new VoterDaoImp();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			dao.cleanUp();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try{
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			String role=request.getParameter("role");
			if(name==""&&email==""&&password==""&&role=="") {
				pw.println("<script type=\"text/javascript\">");
				pw.println("alert('Please Fill THe Form');");
				pw.println("location='newvoteradd';");
				pw.println("</script>");	
			}
			else {
				if(dao.registerVoter(name, email, password, role)) {
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Register Successful');");
					pw.println("location='admin';");
					pw.println("</script>");	
				}
				else
				{
					pw.println("<script type=\"text/javascript\">");
					pw.println("alert('Register Un-successfull');");
					pw.println("location='newvoteradd';");
					pw.println("</script>");
				
				}
				}	
		}catch (Exception e) {
		e.printStackTrace();
		pw.println("some error occoured ");
		}
		
	}
}
