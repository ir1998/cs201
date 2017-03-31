package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);	 
		if(request.getParameter("firstname").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your first name");
			pw.flush();
		}else if(request.getParameter("lastname").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your last name");
			pw.flush();
		}else if(request.getParameter("username").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter a username");
			pw.flush();
		}else if(request.getParameter("password").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter a password");
			pw.flush();
		}else if(request.getParameter("email").equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Please enter your email");
			pw.flush();
		}
		
		//if successful, update database
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}