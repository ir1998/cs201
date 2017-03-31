package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DataStorage;
import data.StringConstants;


@WebServlet("/LoginServletAjax")
public class LoginServletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServletAjax() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("REACHED SERVLETS");
		HttpSession session = request.getSession(true);	 
		DataStorage ds = (DataStorage) session.getAttribute(StringConstants.DATA);
		//get username and password
		String username = "dkempe";
				//(String)request.getParameter("username");
		String password = "dkempe";
				//(String)request.getParameter("password");
		//if it is a valid username
		if(ds == null)
			System.out.println("ds is NULL");
		if (ds.validUsername(username)){
			//correct password
			if (ds.correctPassword(username, password)){
				ds.setLoggedInUser(username);
				//response.sendRedirect(StringConstants.JSP_EXT+StringConstants.FEED_JSP);
			}
			//incorrect password
			else{
				request.setAttribute(StringConstants.ERROR, "Incorrect password");
				//request.getRequestDispatcher(StringConstants.JSP_EXT+StringConstants.LOGIN_JSP).forward(request, response);
				PrintWriter pw = response.getWriter();
				pw.print("Incorrect password");
				pw.flush();
			}
		}
		//invalid username
		else{
			request.setAttribute(StringConstants.ERROR, "Invalid username");
			//request.getRequestDispatcher(StringConstants.JSP_EXT+StringConstants.LOGIN_JSP).forward(request, response);
			PrintWriter pw = response.getWriter();
			pw.print("Incorrect username");
			pw.flush();
		}	
	}
}
