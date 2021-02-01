package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CandidateDaoImp;
import dao.VoterDaoImp;


@WebServlet("/newvoteradd")
public class AddNew extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CandidateDaoImp candidateDao;
	VoterDaoImp dao;
	public void init(ServletConfig config) throws ServletException {
		try {
			candidateDao=new CandidateDaoImp();
			dao=new VoterDaoImp();
		}catch (Exception e) {
			e.printStackTrace();
		}}

	
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
			String header="<head>\r\n" + 
					"<title> Registration Page</title>\r\n" + 
					"<meta charset=\"utf-8\">\r\n"
					+ "<link href=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\" rel=\"stylesheet\" id=\"bootstrap-css\">\r\n" + 
					"<script src=\"//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\r\n" + 
					"<script src=\"//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>" + 
					"<style> \r\n" + 
					"\r\n" + 
					"body {\r\n" + 
					"  background: #C5E1A5;\r\n" + 
					"}\r\n" + 
					"form {\r\n" + 
					"  width: 60%;\r\n" + 
					"  margin: 60px auto;\r\n" + 
					"  background: #efefef;\r\n" + 
					"  padding: 60px 120px 80px 120px;\r\n" + 
					"  text-align: center;\r\n" + 
					"  -webkit-box-shadow: 2px 2px 3px rgba(0,0,0,0.1);\r\n" + 
					"  box-shadow: 2px 2px 3px rgba(0,0,0,0.1);\r\n" + 
					"}\r\n" + 
					"label {\r\n" + 
					"  display: block;\r\n" + 
					"  position: relative;\r\n" + 
					"  margin: 40px 0px;\r\n" + 
					"}\r\n" + 
					".label-txt {\r\n" + 
					"  position: absolute;\r\n" + 
					"  top: -1.6em;\r\n" + 
					"  padding: 10px;\r\n" + 
					"  font-family: sans-serif;\r\n" + 
					"  font-size: .8em;\r\n" + 
					"  letter-spacing: 1px;\r\n" + 
					"  color: rgb(120,120,120);\r\n" + 
					"  transition: ease .3s;\r\n" + 
					"}\r\n" + 
					".input {\r\n" + 
					"  width: 100%;\r\n" + 
					"  padding: 10px;\r\n" + 
					"  background: transparent;\r\n" + 
					"  border: none;\r\n" + 
					"  outline: none;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".line-box {\r\n" + 
					"  position: relative;\r\n" + 
					"  width: 100%;\r\n" + 
					"  height: 2px;\r\n" + 
					"  background: #BCBCBC;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".line {\r\n" + 
					"  position: absolute;\r\n" + 
					"  width: 0%;\r\n" + 
					"  height: 2px;\r\n" + 
					"  top: 0px;\r\n" + 
					"  left: 50%;\r\n" + 
					"  transform: translateX(-50%);\r\n" + 
					"  background: #8BC34A;\r\n" + 
					"  transition: ease .6s;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".input:focus + .line-box .line {\r\n" + 
					"  width: 100%;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".label-active {\r\n" + 
					"  top: -3em;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"button {\r\n" + 
					"  display: inline-block;\r\n" + 
					"  padding: 12px 24px;\r\n" + 
					"  background: rgb(220,220,220);\r\n" + 
					"  font-weight: bold;\r\n" + 
					"  color: rgb(120,120,120);\r\n" + 
					"  border: none;\r\n" + 
					"  outline: none;\r\n" + 
					"  border-radius: 3px;\r\n" + 
					"  cursor: pointer;\r\n" + 
					"  transition: ease .3s;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"button:hover {\r\n" + 
					"  background: #8BC34A;\r\n" + 
					"  color: #ffffff;\r\n" + 
					"}\r\n" + 
					"</style>"+
					"</head>";
			pw.print(header);
			String name="<label> "
					+ "<p class=\"label-txt\">ENTER YOUR NAME</p>"
					+ "<input type=\"text\" class=\"input\" name='name' value=''>"
					+ "<div class=\"line-box\">"
					+ "<div class=\"line\"></div>"
					+ "</div>"
					+ "</label>";
			String email="<label> "
					+ "<p class=\"label-txt\">ENTER YOUR EMAIL</p>"
					+ "<input type=\"email\" class=\"input\" name='email' value=''>"
					+ "<div class=\"line-box\">"
					+ "<div class=\"line\"></div>"
					+ "</div>"
					+ "</label>";
			String role="<label> "
					+ "<p class=\"label-txt\">SELECT YOUR ROLE</p>"
					+ "<select class=\"input\" name='role'>"
					+ "<option value=''>Choose Your Role</option>"
					+ "<option value='admin'>Admin</option>"
					+ "<option value='voter'>Voter</option>"
					+ "</select>"
					
					+ "<div class=\"line-box\">"
					+ "<div class=\"line\"></div>"
					+ "</div>"
					+ "</label>";
			String password="<label> "
					+ "<p class=\"label-txt\">ENTER YOUR PASSWORD</p>"
					+ "<input type=\"password\" class=\"input\" name='password' value=''>"
					+ "<div class=\"line-box\">"
					+ "<div class=\"line\"></div>"
					+ "</div>"
					+ "</label>";
			String submit=
					"<label> "
							
							+ "<input type=\"submit\" class=\"input\" name='submit'  value='register'>"
							+ "<div class=\"line-box\">"
							+ "<div class=\"line\"></div>"
							+ "</div>"
							+ "</label>";
			pw.print("<form align='center' action='savevoter' method='post'>"
					+ name
					+ email
					+ role
					+ password
					+ submit
					+ "</form>");
			
		
			}catch (Exception e) {
				e.printStackTrace();
			}
	}

}
