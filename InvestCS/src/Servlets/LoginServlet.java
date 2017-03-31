package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String)request.getParameter("username");
		String password = (String)request.getParameter("password");
		
		//check if username and password are valid in database
		if(username.equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Incorrect username");
			pw.flush();
		}else if(password.equals("")){
			PrintWriter pw = response.getWriter();
			pw.print("Incorrect password");
			pw.flush();
		}
			
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
