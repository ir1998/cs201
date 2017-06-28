package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import data.MySQLDriver;
import data.StringConstants;
import data.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MySQLDriver sql = new MySQLDriver();
		sql.connect();
		

		String username = (String)request.getParameter(StringConstants.USERNAME);
		String password = (String)request.getParameter(StringConstants.PASSWORD);
		//if it is a valid username
		if (!sql.isUsernameAvailable(username)){
			//correct password
			if (sql.isValidLogin(username, password)){
				sql.setLoggedInUser(username);
			}
			//incorrect password
			else{
				response.getWriter().write("Incorrect password");
			}
		}
		//invalid username
		else{
			response.getWriter().write("Invalid username");
		}	
		request.getSession().setAttribute("sql", sql);
		sql.stop();
	}
}
